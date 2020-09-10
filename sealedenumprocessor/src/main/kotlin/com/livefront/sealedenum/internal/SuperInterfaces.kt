package com.livefront.sealedenum.internal

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
import com.squareup.kotlinpoet.metadata.specs.ClassInspector
import com.squareup.kotlinpoet.metadata.specs.classFor
import com.squareup.kotlinpoet.metadata.specs.toTypeSpec

/**
 * Given a [typeSpec] and the type names for its type parameters, returns a list of all valid super interfaces that it
 * or any of its superclasses implement.
 *
 * A super interface is "valid" if it does not require a wildcard generic type parameter.
 */
internal fun ClassInspector.getAllSuperInterfaces(typeSpec: TypeSpec): List<TypeName> {
    val superInterfaces = mutableListOf<TypeName>()
    getAllSuperInterfacesImpl(
        typeSpec,
        typeSpec.wildcardedTypeVariables,
        superInterfaces
    )
    return superInterfaces
}

/**
 * The recursive implementation for [getAllSuperInterfaces].
 *
 * This method adds all valid super interfaces to [superInterfaces].
 */
private tailrec fun ClassInspector.getAllSuperInterfacesImpl(
    typeSpec: TypeSpec,
    parentTypeArguments: List<TypeName>,
    superInterfaces: MutableList<TypeName>
) {
    check(typeSpec.typeVariables.size == parentTypeArguments.size)

    // Associate the type variable names with their type arguments from the subclass.
    val typeVariableNamesToTypeArguments = typeSpec.typeVariables.zip(parentTypeArguments).toMap()

    // Add all implement interfaces to our list, taking care to substitute the type names and throwing away invalid
    // interfaces
    superInterfaces += typeSpec.superinterfaces.keys.mapNotNull { superinterface ->
        when (val adjustedTypeName = superinterface.substituteTypeNames(typeVariableNamesToTypeArguments)) {
            is ClassName -> adjustedTypeName
            is ParameterizedTypeName -> {
                // Check if this interface is valid or not
                if (adjustedTypeName.typeArguments.any { it is WildcardTypeName }) {
                    null
                } else {
                    adjustedTypeName
                }
            }
            else -> null
        }
    }

    // Setup the tail recursion
    when (val nextTypeName = typeSpec.superclass) {
        is ClassName -> if (nextTypeName != ANY) {
            val nextTypeSpec = getTypeSpec(nextTypeName) ?: return

            getAllSuperInterfacesImpl(
                typeSpec = nextTypeSpec,
                parentTypeArguments = emptyList(),
                superInterfaces = superInterfaces
            )
        }
        is ParameterizedTypeName -> {
            val nextTypeSpec = getTypeSpec(nextTypeName.rawType) ?: return

            getAllSuperInterfacesImpl(
                typeSpec = nextTypeSpec,
                parentTypeArguments = nextTypeName.typeArguments.map {
                    if (it is TypeVariableName) {
                        typeVariableNamesToTypeArguments[it] ?: it
                    } else {
                        it
                    }
                },
                superInterfaces = superInterfaces
            )
        }
        else -> Unit
    }
}

@Suppress("TooGenericExceptionCaught")
internal fun ClassInspector.getTypeSpec(className: ClassName): TypeSpec? = try {
    classFor(className).toTypeSpec(this, className)
} catch (e: Exception) {
    null
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
