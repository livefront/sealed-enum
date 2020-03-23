package com.livefront.sealedenum.internal

import com.livefront.sealedenum.GenSealedEnum
import com.livefront.sealedenum.GenSealedEnums
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.TreeTraversalOrder
import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.metadata.ImmutableKmClass
import com.squareup.kotlinpoet.metadata.KotlinPoetMetadataPreview
import com.squareup.kotlinpoet.metadata.isObject
import com.squareup.kotlinpoet.metadata.isSealed
import com.squareup.kotlinpoet.metadata.specs.internal.ClassInspectorUtil
import com.squareup.kotlinpoet.metadata.toImmutableKmClass
import net.ltgt.gradle.incap.IncrementalAnnotationProcessor
import net.ltgt.gradle.incap.IncrementalAnnotationProcessorType
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

internal const val OPTION_AUTO_GENERATE_SEALED_ENUMS = "sealedenum.autoGenerateSealedEnums"

internal const val ERROR_ELEMENT_IS_NOT_KOTLIN_CLASS = "Annotated element is not a Kotlin class"
internal const val ERROR_CLASS_IS_NOT_SEALED = "Annotated class is not a sealed class."
internal const val ERROR_NON_OBJECT_SEALED_SUBCLASSES = "Annotated sealed class has a non-object subclass"

@IncrementalAnnotationProcessor(IncrementalAnnotationProcessorType.ISOLATING)
@KotlinPoetMetadataPreview
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor::class)
internal class SealedEnumProcessor(
    private val customProcessingEnv: ProcessingEnvironment? = null
) : AbstractProcessor() {

    private val injectedProcessEnv: ProcessingEnvironment
        get() = customProcessingEnv ?: processingEnv

    override fun getSupportedOptions(): MutableSet<String> =
        mutableSetOf(OPTION_AUTO_GENERATE_SEALED_ENUMS)

    override fun getSupportedAnnotationTypes(): MutableSet<String> =
        (if (injectedProcessEnv.options[OPTION_AUTO_GENERATE_SEALED_ENUMS].equals("true", ignoreCase = true)) {
            setOf(Metadata::class.java.name)
        } else {
            emptySet()
        } + setOf(GenSealedEnum::class.java.name, GenSealedEnums::class.java.name)).toMutableSet()

    override fun process(annotations: MutableSet<out TypeElement>, roundEnvironment: RoundEnvironment): Boolean {
        annotations
            .flatMap { annotation -> roundEnvironment.getElementsAnnotatedWith(annotation) }
            .distinct()
            .filterIsInstance<TypeElement>()
            .mapNotNull(::createSealedEnumFileSpec)
            .forEach {
                it.create().writeTo(injectedProcessEnv.filer)
            }

        // Only one round of annotation processing is needed
        return true
    }

    /**
     * Tries to create a [SealedEnumFileSpec] for the given [sealedClassElement] that was annotated.
     *
     * If there is an error processing the given [TypeElement], a relevant error message will be printed and a null
     * [SealedEnumFileSpec] will be returned.
     */
    @Suppress("ReturnCount")
    internal fun createSealedEnumFileSpec(sealedClassElement: TypeElement): SealedEnumFileSpec? {

        /**
         * A helper function to print the given [message] as an error.
         */
        fun printError(message: String) {
            injectedProcessEnv.messager.printMessage(Diagnostic.Kind.ERROR, message, sealedClassElement)
        }

        /**
         * The [GenSealedEnum] annotations used to annotate the [sealedClassElement]
         */
        val sealedEnumAnnotations = sealedClassElement
            .getAnnotationsByType(GenSealedEnum::class.java)
            .map { it.traversalOrder }
            .distinct()

        /**
         * If the list of [GenSealedEnum] annotations is empty, then we must be trying to generate [SealedEnum] from
         * all sealed classes.
         */
        val isAnnotatedByMetadataOnly = sealedEnumAnnotations.isEmpty()

        val traversalOrders = if (isAnnotatedByMetadataOnly) {
            listOf(TreeTraversalOrder.IN_ORDER)
        } else {
            sealedEnumAnnotations
        }

        /**
         * The [ImmutableKmClass] for the sealed class.
         */
        val sealedClassKmClass =
            @Suppress("TooGenericExceptionCaught")
            try {
                sealedClassElement.toImmutableKmClass()
            } catch (exception: Exception) {
                printError(ERROR_ELEMENT_IS_NOT_KOTLIN_CLASS)
                return null
            }.apply {
                if (!isSealed) {
                    // If this element wasn't explicitly annotated, quietly fail if the class wasn't sealed.
                    if (!isAnnotatedByMetadataOnly) {
                        printError(ERROR_CLASS_IS_NOT_SEALED)
                    }
                    return null
                }
            }

        /**
         * The root of the tree representing the sealed class hierarchy.
         */
        val sealedClassNode = try {
            createSealedClassNode(sealedClassKmClass)
        } catch (nonSealedClassException: NonObjectSealedSubclassException) {
            if (!isAnnotatedByMetadataOnly) {
                // If this element wasn't explicitly annotated, quietly fail if the class had a non-object subclass.
                printError(ERROR_NON_OBJECT_SEALED_SUBCLASSES)
            }
            return null
        }

        return SealedEnumFileSpec(
            sealedClass = ClassInspectorUtil.createClassName(sealedClassKmClass.name),
            numberOfTypeParameters = sealedClassKmClass.typeParameters.size,
            sealedClassElement = sealedClassElement,
            traversalOrders = traversalOrders,
            sealedClassNode = sealedClassNode
        )
    }

    /**
     * A recursive function used in concert with [convertSealedSubclassToNode] to create a
     * [SealedClassNode.SealedClass] given a [ImmutableKmClass] for the sealed class.
     */
    internal fun createSealedClassNode(
        sealedClassKmClass: ImmutableKmClass
    ): SealedClassNode.SealedClass = SealedClassNode.SealedClass(
        sealedClassKmClass.sealedSubclasses
            .map(ClassInspectorUtil::createClassName)
            .map(::convertSealedSubclassToNode)
    )

    /**
     * A recursive function used in concert with [createSealedClassNode] to try to create a [SealedClassNode].
     *
     * If [sealedSubclass] is an object, then this function will return a [SealedClassNode.Object].
     * If [sealedSubclass] is another sealed class, then this function will return a [SealedClassNode.SealedClass].
     * If [sealedSubclass] is neither, then this function will throw a [NonObjectSealedSubclassException].
     */
    private fun convertSealedSubclassToNode(sealedSubclass: ClassName): SealedClassNode {
        val kmClass = injectedProcessEnv.elementUtils.getTypeElement(sealedSubclass.canonicalName).toImmutableKmClass()

        return when {
            kmClass.isObject -> SealedClassNode.Object(sealedSubclass)
            kmClass.isSealed -> createSealedClassNode(kmClass)
            else -> throw NonObjectSealedSubclassException()
        }
    }
}
