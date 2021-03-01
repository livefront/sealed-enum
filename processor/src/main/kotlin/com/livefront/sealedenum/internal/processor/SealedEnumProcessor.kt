package com.livefront.sealedenum.internal.processor

import com.google.auto.service.AutoService
import com.livefront.sealedenum.GenSealedEnum
import com.livefront.sealedenum.GenSealedEnums
import com.livefront.sealedenum.internal.common.SealedClassNode
import com.livefront.sealedenum.internal.common.Visibility
import com.livefront.sealedenum.internal.common.areUniqueBy
import com.livefront.sealedenum.internal.common.spec.SealedEnumFileSpec
import com.livefront.sealedenum.internal.common.spec.SealedEnumFileSpec.SealedEnumOption.SealedEnumOnly
import com.livefront.sealedenum.internal.common.spec.SealedEnumFileSpec.SealedEnumOption.SealedEnumWithEnum
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.classinspector.elements.ElementsClassInspector
import com.squareup.kotlinpoet.metadata.ImmutableKmClass
import com.squareup.kotlinpoet.metadata.isCompanionObject
import com.squareup.kotlinpoet.metadata.isInternal
import com.squareup.kotlinpoet.metadata.isObject
import com.squareup.kotlinpoet.metadata.isPublic
import com.squareup.kotlinpoet.metadata.isSealed
import com.squareup.kotlinpoet.metadata.specs.internal.ClassInspectorUtil
import com.squareup.kotlinpoet.metadata.specs.toTypeSpec
import com.squareup.kotlinpoet.metadata.toImmutableKmClass
import net.ltgt.gradle.incap.IncrementalAnnotationProcessor
import net.ltgt.gradle.incap.IncrementalAnnotationProcessorType
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

internal const val ERROR_ELEMENT_IS_ANNOTATED_WITH_REPEATED_TRAVERSAL_ORDER =
    "Element is annotated with the same traversal order multiple times"
internal const val ERROR_ELEMENT_IS_NOT_KOTLIN_CLASS = "Annotated element is not a Kotlin class"
internal const val ERROR_ELEMENT_IS_NOT_COMPANION_OBJECT = "Annotated element is not a companion object"
internal const val ERROR_COMPANION_OBJECT_HAS_INVALID_VISIBILITY = "Annotated companion object isn't internal or public"
internal const val ERROR_ENCLOSING_ELEMENT_IS_NOT_KOTLIN_CLASS = "Enclosing element is not a Kotlin class"
internal const val ERROR_CLASS_IS_NOT_SEALED = "Annotated companion object is not for a sealed class"
internal const val ERROR_NON_OBJECT_SEALED_SUBCLASSES = "Annotated sealed class has a non-object subclass"
internal const val ERROR_SUBCLASS_HAS_INVALID_VISIBILITY = "Subclass of sealed class isn't internal or public"
internal const val ERROR_SEALED_CLASS_HAS_INVALID_VISIBILITY = "Annotated sealed class isn't internal or public"

@IncrementalAnnotationProcessor(IncrementalAnnotationProcessorType.ISOLATING)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor::class)
public class SealedEnumProcessor : AbstractProcessor() {

    override fun getSupportedOptions(): MutableSet<String> = mutableSetOf()

    private val elementsClassInspector by lazy {
        ElementsClassInspector.create(
            processingEnv.elementUtils,
            processingEnv.typeUtils
        )
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> =
        mutableSetOf(GenSealedEnum::class.java.name, GenSealedEnums::class.java.name)

    override fun process(annotations: MutableSet<out TypeElement>, roundEnvironment: RoundEnvironment): Boolean {
        annotations
            .flatMap { annotation -> roundEnvironment.getElementsAnnotatedWith(annotation) }
            .distinct()
            .filterIsInstance<TypeElement>()
            .mapNotNull(::createSealedEnumFileSpec)
            .forEach {
                it.build().writeTo(processingEnv.filer)
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
    private fun createSealedEnumFileSpec(sealedClassCompanionObjectElement: TypeElement): SealedEnumFileSpec? {

        /**
         * A helper function to print the given [message] as an error.
         */
        fun printError(message: String, element: Element) {
            processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, message, element)
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
         * The [ImmutableKmClass] for the sealed class's companion object.
         */
        val sealedClassCompanionObjectKmClass =
            @Suppress("TooGenericExceptionCaught")
            try {
                sealedClassCompanionObjectElement.toImmutableKmClass()
            } catch (exception: Exception) {
                printError(ERROR_ELEMENT_IS_NOT_KOTLIN_CLASS, sealedClassCompanionObjectElement)
                return null
            }.apply {
                if (!isCompanionObject) {
                    printError(ERROR_ELEMENT_IS_NOT_COMPANION_OBJECT, sealedClassCompanionObjectElement)
                    return null
                }
            }

        val sealedClassCompanionObjectVisibility = when {
            sealedClassCompanionObjectKmClass.isPublic -> Visibility.PUBLIC
            sealedClassCompanionObjectKmClass.isInternal -> Visibility.INTERNAL
            else -> {
                printError(ERROR_COMPANION_OBJECT_HAS_INVALID_VISIBILITY, sealedClassCompanionObjectElement)
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
                printError(ERROR_ENCLOSING_ELEMENT_IS_NOT_KOTLIN_CLASS, sealedClassElement)
                return null
            }.apply {
                if (!isSealed) {
                    printError(ERROR_CLASS_IS_NOT_SEALED, sealedClassElement)
                    return null
                }
            }

        val sealedClassVisibility = when {
            sealedClassKmClass.isPublic -> Visibility.PUBLIC
            sealedClassKmClass.isInternal -> Visibility.INTERNAL
            else -> {
                printError(ERROR_SEALED_CLASS_HAS_INVALID_VISIBILITY, sealedClassElement)
                return null
            }
        }

        /**
         * The root of the tree representing the sealed class hierarchy.
         */
        val sealedClassNode = try {
            createSealedClassNode(sealedClassKmClass)
        } catch (nonSealedClassException: NonObjectSealedSubclassException) {
            printError(ERROR_NON_OBJECT_SEALED_SUBCLASSES, nonSealedClassException.element)
            return null
        } catch (invalidSubclassVisibilityException: InvalidSubclassVisibilityException) {
            printError(ERROR_SUBCLASS_HAS_INVALID_VISIBILITY, invalidSubclassVisibilityException.element)
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
        val sealedClassInterfaces: List<TypeName>? = if (sealedEnumAnnotations.any { it.generateEnum }) {
            SuperInterfaces(
                typeElement = sealedClassElement,
                typeSpec = sealedClassTypeSpec,
                types = processingEnv.typeUtils,
                elements = processingEnv.elementUtils,
                classInspector = elementsClassInspector
            ).getAllSuperInterfaces()
        } else {
            null
        }

        return SealedEnumFileSpec(
            sealedClass = ClassInspectorUtil.createClassName(sealedClassKmClass.name),
            sealedClassVisibility = sealedClassVisibility,
            sealedClassCompanionObject = ClassInspectorUtil.createClassName(sealedClassCompanionObjectKmClass.name),
            sealedClassCompanionObjectVisibility = sealedClassCompanionObjectVisibility,
            typeParameters = sealedClassTypeSpec.wildcardedTypeVariables,
            sealedClassCompanionObjectElement = sealedClassCompanionObjectElement,
            sealedClassNode = sealedClassNode,
            sealedEnumOptions = sealedEnumAnnotations.associate {
                it.traversalOrder to if (it.generateEnum) {
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
    private fun createSealedClassNode(
        sealedClassKmClass: ImmutableKmClass
    ): SealedClassNode.SealedClass = SealedClassNode.SealedClass(
        sealedClassKmClass.sealedSubclasses
            .map(ClassInspectorUtil::createClassName)
            .map(::convertSealedSubclassToNode)
    )

    /**
     * A recursive function used in concert with [createSealedClassNode] to try to create a [SealedClassNode].
     *
     * If [sealedSubclass] is not public or internal, than an [InvalidSubclassVisibilityException] will be thrown.
     *
     * If [sealedSubclass] is an object, then this function will return a [SealedClassNode.Object].
     * If [sealedSubclass] is another sealed class, then this function will return a [SealedClassNode.SealedClass].
     * If [sealedSubclass] is neither, then this function will throw a [NonObjectSealedSubclassException]
     */
    private fun convertSealedSubclassToNode(sealedSubclass: ClassName): SealedClassNode {
        val element = processingEnv.elementUtils.getTypeElement(sealedSubclass.canonicalName)
        val kmClass = element.toImmutableKmClass()

        return when {
            kmClass.isPublic || kmClass.isInternal -> when {
                kmClass.isObject -> SealedClassNode.Object(sealedSubclass)
                kmClass.isSealed -> createSealedClassNode(kmClass)
                else -> throw NonObjectSealedSubclassException(element)
            }
            else -> throw InvalidSubclassVisibilityException(element)
        }
    }
}
