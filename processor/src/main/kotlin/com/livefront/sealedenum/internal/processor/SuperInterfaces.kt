package com.livefront.sealedenum.internal.processor

import com.squareup.kotlinpoet.ANY
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.Dynamic
import com.squareup.kotlinpoet.LambdaTypeName
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.STAR
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.TypeVariableName
import com.squareup.kotlinpoet.WildcardTypeName
import com.squareup.kotlinpoet.metadata.isInternal
import com.squareup.kotlinpoet.metadata.isPublic
import com.squareup.kotlinpoet.metadata.isSealed
import com.squareup.kotlinpoet.metadata.specs.ClassInspector
import com.squareup.kotlinpoet.metadata.specs.internal.ClassInspectorUtil
import com.squareup.kotlinpoet.metadata.specs.toTypeSpec
import com.squareup.kotlinpoet.metadata.toImmutableKmClass
import javax.lang.model.element.Element
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement
import javax.lang.model.element.TypeParameterElement
import javax.lang.model.type.DeclaredType
import javax.lang.model.type.TypeMirror
import javax.lang.model.util.Elements
import javax.lang.model.util.Types

/**
 * An internal context with which super interfaces can be determined.
 */
internal class SuperInterfaces(
    private val typeElement: TypeElement,
    private val typeSpec: TypeSpec,
    private val types: Types,
    private val elements: Elements,
    private val classInspector: ClassInspector,
) {

    /**
     * Given a [typeElement] and its [typeSpec] and the type names for its type parameters, returns a list of all valid
     * super interfaces that it or any of its superclasses implement.
     *
     * A super interface is "valid" if it does not require a wildcard generic type parameter and if it is of an
     * appropriate visibility.
     */
    internal fun getAllSuperInterfaces(): List<TypeName> {
        val superInterfaces = mutableListOf<TypeName>()

        getAllSuperInterfacesKotlinImpl(
            typeElement = typeElement,
            typeSpec = typeSpec,
            parentTypeArguments = typeSpec.wildcardedTypeVariables,
            superInterfaces = superInterfaces
        )

        return superInterfaces
    }

    /**
     * The general recursive implementation to get all super interfaces. This method will call either
     * [getAllSuperInterfacesKotlinImpl] or [getAllSuperInterfacesJavaImpl] depending on if [typeElement] is for a Java
     * class or a Kotlin class.
     */
    private fun getAllSuperInterfacesImpl(
        typeElement: TypeElement,
        parentTypeArguments: List<TypeName>,
        superInterfaces: MutableList<TypeName>,
    ) {
        check(typeElement.typeParameters.size == parentTypeArguments.size)

        @Suppress("TooGenericExceptionCaught")
        try {
            val typeSpec = typeElement.toImmutableKmClass().toTypeSpec(classInspector)
            getAllSuperInterfacesKotlinImpl(typeElement, typeSpec, parentTypeArguments, superInterfaces)
        } catch (exception: Exception) {
            getAllSuperInterfacesJavaImpl(typeElement, parentTypeArguments, superInterfaces)
        }
    }

    /**
     * The recursive implementation for [getAllSuperInterfaces].
     *
     * This method adds all valid super interfaces to [superInterfaces].
     */
    private fun getAllSuperInterfacesKotlinImpl(
        typeElement: TypeElement,
        typeSpec: TypeSpec,
        parentTypeArguments: List<TypeName>,
        superInterfaces: MutableList<TypeName>,
    ) {
        check(typeSpec.typeVariables.size == parentTypeArguments.size)

        // Associate the type variable names with their type arguments from the subclass.
        val typeVariableNamesToTypeArguments = typeSpec.typeVariables.zip(parentTypeArguments).toMap()

        // Add all implement interfaces to our list, taking care to substitute the type names and throwing away invalid
        // interfaces
        superInterfaces += typeElement.interfaces
            .map(types::asElement)
            .map { it as TypeElement } // Interfaces should all be TypeElements
            .zip(typeSpec.superinterfaces.keys)
            .filter { (superInterfaceType, _) ->
                superInterfaceType.isVisibleInterface() && !superInterfaceType.isSealedInterface()
            }
            .map(Pair<Element, TypeName>::second)
            .map { it.substituteTypeNames(typeVariableNamesToTypeArguments) }
            .filter { it.isValidInterface() }

        // Setup the next call
        when (val nextTypeName = typeSpec.superclass) {
            is ClassName -> if (nextTypeName != ANY) {
                getAllSuperInterfacesImpl(
                    typeElement = (typeElement.superclass as DeclaredType).asElement() as TypeElement,
                    parentTypeArguments = emptyList(),
                    superInterfaces = superInterfaces
                )
            }
            is ParameterizedTypeName -> {
                getAllSuperInterfacesImpl(
                    typeElement = (typeElement.superclass as DeclaredType).asElement() as TypeElement,
                    parentTypeArguments = nextTypeName.typeArguments.map {
                        it.substituteTypeNames(typeVariableNamesToTypeArguments)
                    },
                    superInterfaces = superInterfaces
                )
            }
            else -> Unit
        }
    }

    private fun getAllSuperInterfacesJavaImpl(
        typeElement: TypeElement,
        parentTypeArguments: List<TypeName>,
        superInterfaces: MutableList<TypeName>,
    ) {
        // Associate the type parameter elements with their type arguments from the subclass.
        val typeParameterElementsToTypeArguments = typeElement.typeParameters.zip(parentTypeArguments).toMap()

        superInterfaces += typeElement.interfaces
            .map(types::asElement)
            .map { it as TypeElement } // Interfaces should all be TypeElements
            .zip(typeElement.interfaces)
            .filter { (superInterfaceType, _) ->
                superInterfaceType.isVisibleInterface() && !superInterfaceType.isSealedInterface()
            }
            .map(Pair<Element, TypeMirror>::second)
            .map { typeMirror -> substituteTypeNames(typeMirror, typeParameterElementsToTypeArguments) }
            .filter { it.isValidInterface() }

        val superClassTypeMirror = typeElement.superclass as? DeclaredType ?: return
        val superClassElement = superClassTypeMirror.asElement() as? TypeElement ?: return

        val newParentTypeArguments = superClassTypeMirror.typeArguments.map {
            substituteTypeNames(it, typeParameterElementsToTypeArguments)
        }

        getAllSuperInterfacesImpl(
            superClassElement,
            newParentTypeArguments,
            superInterfaces
        )
    }

    /**
     * Recursively substitutes all [TypeVariableName] within [this] and all of its nested generic types with their
     * mappings found in [map].
     *
     * Any remaining type variable names that have no mapping are replaced with [STAR].
     */
    private fun TypeName.substituteTypeNames(map: Map<TypeVariableName, TypeName>): TypeName = when (this) {
        is ClassName -> this
        Dynamic -> this
        is LambdaTypeName -> this
        is ParameterizedTypeName -> rawType.parameterizedBy(typeArguments.map { it.substituteTypeNames(map) })
        is TypeVariableName -> map.getOrDefault(this, STAR)
        is WildcardTypeName -> this
    }

    /**
     * Recursively substitutes all [TypeParameterElement] within [this] and all of its nested generic types with their
     * mappings found in [map].
     *
     * Any remaining type variable names that have no mapping are replaced with [STAR].
     */
    private fun substituteTypeNames(
        typeMirror: TypeMirror,
        map: Map<TypeParameterElement, TypeName>,
    ): TypeName {
        val element = types.asElement(typeMirror)

        return map[element] ?: run {
            if (typeMirror !is DeclaredType || element !is TypeElement) {
                return STAR
            }

            @Suppress("TooGenericExceptionCaught")
            val className = try {
                ClassInspectorUtil.createClassName(element.toImmutableKmClass().name)
            } catch (exception: Exception) {
                ClassName.bestGuess(element.qualifiedName.toString())
            }

            val typeArgumentClassNames = typeMirror.typeArguments.map { substituteTypeNames(it, map) }

            if (typeArgumentClassNames.isEmpty()) {
                className
            } else {
                className.parameterizedBy(typeArgumentClassNames)
            }
        }
    }

    /**
     * Returns true if the [TypeElement] interface has the correct visibility to be implemented by a generated enum.
     */
    @Suppress("TooGenericExceptionCaught")
    private fun TypeElement.isVisibleInterface(): Boolean =
        try {
            // If interface is a Kotlin interface
            val kmClass = toImmutableKmClass()
            kmClass.isPublic || kmClass.isInternal
        } catch (exception: Exception) {
            // If interface is a Java interface
            Modifier.PRIVATE !in modifiers &&
                (Modifier.PROTECTED !in modifiers || elements.getPackageOf(this) == elements.getPackageOf(typeElement))
        }

    /**
     * Returns true if the [TypeElement] interface is a sealed interface.
     */
    @Suppress("TooGenericExceptionCaught")
    private fun TypeElement.isSealedInterface(): Boolean =
        try {
            // If interface is a Kotlin interface
            val kmClass = toImmutableKmClass()
            kmClass.isSealed
        } catch (exception: Exception) {
            // If interface is a Java interface
            false // TODO: Java sealed interfaces?
        }

    /**
     * Returns true if the [TypeName] interface has the correct parameterization (if any) to be implemented by a
     * generated enum.
     */
    private fun TypeName.isValidInterface(): Boolean = when (this) {
        is ClassName -> true
        is ParameterizedTypeName -> typeArguments.none { it is WildcardTypeName }
        else -> false
    }
}
