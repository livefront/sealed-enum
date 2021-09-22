package com.livefront.sealedenum.internal.processor

import com.squareup.kotlinpoet.ClassName
import kotlinx.metadata.isLocal

/**
 * Converts a [kotlinx.metadata.ClassName] to a [com.squareup.kotlinpoet.ClassName].
 */
internal fun createClassName(kotlinMetadataName: kotlinx.metadata.ClassName): ClassName {
    require(!kotlinMetadataName.isLocal) {
        "Local/anonymous classes are not supported!"
    }
    val simpleNames = kotlinMetadataName.substringAfterLast(
        delimiter = '/'
    )
        .split(".")
    val packageName = kotlinMetadataName.substringBeforeLast(
        delimiter = '/',
        missingDelimiterValue = ""
    )

    return ClassName(
        packageName = packageName.replace("/", "."),
        simpleNames = simpleNames
    )
}
