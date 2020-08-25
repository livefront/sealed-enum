package com.livefront.sealedenum.internal

import com.google.auto.service.AutoService
import com.livefront.sealedenum.GenSealedEnum
import com.livefront.sealedenum.GenSealedEnums
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.TreeTraversalOrder
import com.livefront.sealedenum.internal.SealedEnumFileSpec.SealedEnumOption.SealedEnumOnly
import com.livefront.sealedenum.internal.SealedEnumFileSpec.SealedEnumOption.SealedEnumWithEnum
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.classinspector.elements.ElementsClassInspector
import com.squareup.kotlinpoet.metadata.ImmutableKmClass
import com.squareup.kotlinpoet.metadata.KotlinPoetMetadataPreview
import com.squareup.kotlinpoet.metadata.isCompanionObject
import com.squareup.kotlinpoet.metadata.isObject
import com.squareup.kotlinpoet.metadata.isSealed
import com.squareup.kotlinpoet.metadata.specs.internal.ClassInspectorUtil
import com.squareup.kotlinpoet.metadata.specs.toTypeSpec
import com.squareup.kotlinpoet.metadata.toImmutableKmClass
import net.ltgt.gradle.incap.IncrementalAnnotationProcessor
import net.ltgt.gradle.incap.IncrementalAnnotationProcessorType
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

internal const val OPTION_AUTO_GENERATE_SEALED_ENUMS = "sealedenum.autoGenerateSealedEnums"

internal const val ERROR_ELEMENT_IS_ANNOTATED_WITH_REPEATED_TRAVERSAL_ORDER =
    "Element is annotated with the same traversal order multiple times"
internal const val ERROR_ELEMENT_IS_NOT_KOTLIN_CLASS = "Annotated element is not a Kotlin class"
internal const val ERROR_ELEMENT_IS_NOT_COMPANION_OBJECT = "Annotated element is not a companion object"
internal const val ERROR_ENCLOSING_ELEMENT_IS_NOT_KOTLIN_CLASS = "Enclosing element is not a Kotlin class"
internal const val ERROR_CLASS_IS_NOT_SEALED = "Annotated companion object is not for a sealed class."
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

    private val elementsClassInspector by lazy {
        ElementsClassInspector.create(
            injectedProcessEnv.elementUtils,
            injectedProcessEnv.typeUtils
        )
    }

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
                it.build().writeTo(injectedProcessEnv.filer)
            }

        // Only one round of annotation processing is needed
        return true
    }

    /**
     * Tries to create a [SealedEnumFileSpec] for the given [sealedClassCompanionObjectElement] that was annotated.
     *
     * If there is an error processing the given [TypeElement], a relevant error message will be printed and a null
     * [SealedEnumFileSpec] will be returned.
     */
    @Suppress("ReturnCount", "LongMethod", "ComplexMethod")
    internal fun createSealedEnumFileSpec(sealedClassCompanionObjectElement: TypeElement): SealedEnumFileSpec? {

        /**
         * A helper function to print the given [message] as an error.
         */
        fun printError(message: String, element: Element) {
            injectedProcessEnv.messager.printMessage(Diagnostic.Kind.ERROR, message, element)
        }

        /**
         * The [GenSealedEnum] annotations used to annotate the [sealedClassCompanionObjectElement]
         */
        val sealedEnumAnnotations = sealedClassCompanionObjectElement
            .getAnnotationsByType(GenSealedEnum::class.java)

        // Ensure that the annotation are unique by traversal order
        if (!sealedEnumAnnotations.areUniqueBy { it.traversalOrder }) {
            printError(ERROR_ELEMENT_IS_ANNOTATED_WITH_REPEATED_TRAVERSAL_ORDER, sealedClassCompanionObjectElement)
            return null
        }

        /**
         * If the list of [GenSealedEnum] annotations is empty, then we must be trying to generate [SealedEnum] from
         * all sealed classes.
         */
        val isAnnotatedByMetadataOnly = sealedEnumAnnotations.isEmpty()

        /**
         * The list of [SealedEnumSeed]s, from which sealed enum classes will be generated
         */
        val sealedEnumSeeds: List<SealedEnumSeed> = if (isAnnotatedByMetadataOnly) {
            listOf(
                SealedEnumSeed(
                    treeTraversalOrder = TreeTraversalOrder.IN_ORDER,
                    generateEnum = false
                )
            )
        } else {
            sealedEnumAnnotations.map {
                SealedEnumSeed(
                    treeTraversalOrder = it.traversalOrder,
                    generateEnum = it.generateEnum
                )
            }
        }

        /**
         * The [ImmutableKmClass] for the sealed class's companion object.
         */
        val sealedClassCompanionObjectKmClass =
            @Suppress("TooGenericExceptionCaught")
            try {
                sealedClassCompanionObjectElement.toImmutableKmClass()
            } catch (exception: Exception) {
                if (!isAnnotatedByMetadataOnly) {
                    printError(ERROR_ELEMENT_IS_NOT_KOTLIN_CLASS, sealedClassCompanionObjectElement)
                }
                return null
            }.apply {
                if (!isCompanionObject) {
                    // If this element wasn't explicitly annotated, quietly fail if the class isn't a companion object.
                    if (!isAnnotatedByMetadataOnly) {
                        printError(ERROR_ELEMENT_IS_NOT_COMPANION_OBJECT, sealedClassCompanionObjectElement)
                    }
                    return null
                }
            }

        val sealedClassElement = sealedClassCompanionObjectElement.enclosingElement

        /**
         * The [ImmutableKmClass] for the sealed class.
         */
        val sealedClassKmClass =
            @Suppress("TooGenericExceptionCaught")
            try {
                (sealedClassElement as TypeElement).toImmutableKmClass()
            } catch (exception: Exception) {
                if (!isAnnotatedByMetadataOnly) {
                    printError(ERROR_ENCLOSING_ELEMENT_IS_NOT_KOTLIN_CLASS, sealedClassElement)
                }
                return null
            }.apply {
                if (!isSealed) {
                    // If this element wasn't explicitly annotated, quietly fail if the class wasn't sealed.
                    if (!isAnnotatedByMetadataOnly) {
                        printError(ERROR_CLASS_IS_NOT_SEALED, sealedClassElement)
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
                printError(ERROR_NON_OBJECT_SEALED_SUBCLASSES, sealedClassElement)
            }
            return null
        }

        /**
         * The [TypeSpec] for the sealed class.
         */
        val sealedClassTypeSpec = sealedClassElement.toTypeSpec(elementsClassInspector)

        /**
         * A nullable list of interfaces that the sealed class (or any of its super classes) implement.
         * This list is only created if it will be used (that is, if `generateEnum` is true for any sealed enum seed).
         */
        val sealedClassInterfaces: List<TypeName>? = if (sealedEnumSeeds.any { it.generateEnum }) {
            elementsClassInspector.getAllSuperInterfaces(sealedClassTypeSpec)
        } else {
            null
        }

        return SealedEnumFileSpec(
            sealedClass = ClassInspectorUtil.createClassName(sealedClassKmClass.name),
            sealedClassCompanionObject = ClassInspectorUtil.createClassName(sealedClassCompanionObjectKmClass.name),
            typeParameters = sealedClassTypeSpec.wildcardedTypeVariables,
            sealedClassCompanionObjectElement = sealedClassCompanionObjectElement,
            sealedClassNode = sealedClassNode,
            sealedEnumOptions = sealedEnumSeeds.associate {
                it.treeTraversalOrder to if (it.generateEnum) {
                    SealedEnumWithEnum(sealedClassInterfaces!!)
                } else {
                    SealedEnumOnly
                }
            }
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
