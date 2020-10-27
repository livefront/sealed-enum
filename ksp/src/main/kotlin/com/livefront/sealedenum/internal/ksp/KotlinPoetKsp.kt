package com.livefront.sealedenum.internal.ksp

import com.google.devtools.ksp.isLocal
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSType
import com.google.devtools.ksp.symbol.KSTypeArgument
import com.google.devtools.ksp.symbol.KSTypeParameter
import com.google.devtools.ksp.symbol.Variance
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.STAR
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.WildcardTypeName
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets.UTF_8

internal fun KSClassDeclaration.toClassName(): ClassName {
    require(!isLocal()) { "Local/anonymous classes are not supported!" }
    val pkgName = packageName.asString()
    val typesString = qualifiedName!!.asString().removePrefix("$pkgName.")

    val simpleNames = typesString.split(".")
    return ClassName(pkgName, simpleNames)
}

internal fun KSClassDeclaration.toTypeName(argumentList: List<TypeName> = emptyList()): TypeName {
    val className = toClassName()
    return if (argumentList.isNotEmpty()) {
        className.parameterizedBy(argumentList)
    } else {
        className
    }
}

/**
 * A mapping of [KSClassDeclaration.typeParameters] to [TypeName]s.
 *
 * Rather than returning variable names, they are converted into a [TypeName] that can be directly specified in an
 * interface.
 *
 * In particular, single invariant types can be replaced with the type directly. In all other cases (multiple bounds,
 * other types of variance) the only thing we can do is wildcard the type.
 */
internal fun KSClassDeclaration.wildcardedTypeNames(): List<TypeName> =
    typeParameters.map {
        if (it.bounds.size == 1) {
            when (it.variance) {
                Variance.INVARIANT -> it.bounds[0].resolve().toTypeName()
                else -> STAR
            }
        } else {
            STAR
        }
    }

internal fun KSType.toTypeName(): TypeName {
    val type = when (val decl = declaration) {
        is KSClassDeclaration -> decl.toTypeName(arguments.map { it.toTypeName() })
        is KSTypeParameter -> STAR
        else -> error("Unsupported type: $declaration")
    }

    return type.copy(nullable = isMarkedNullable)
}

internal fun KSTypeArgument.toTypeName(): TypeName {
    val typeName = type?.resolve()?.toTypeName() ?: return STAR
    return when (variance) {
        Variance.COVARIANT -> WildcardTypeName.producerOf(typeName)
        Variance.CONTRAVARIANT -> WildcardTypeName.consumerOf(typeName)
        Variance.STAR -> STAR
        Variance.INVARIANT -> typeName
    }
}

internal fun FileSpec.writeTo(
    codeGenerator: CodeGenerator,
    originatingFiles: List<KSFile>
) {
    @Suppress("SpreadOperator")
    val file = codeGenerator.createNewFile(
        dependencies = Dependencies(false, *originatingFiles.toTypedArray()),
        packageName = packageName,
        fileName = name
    )
    // Don't use writeTo(file) because that tries to handle directories under the hood
    OutputStreamWriter(file, UTF_8).use(::writeTo)
}
