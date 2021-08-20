package com.livefront.sealedenum.testing

import com.livefront.sealedenum.internal.ksp.SealedEnumProcessorProvider
import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile
import com.tschuchort.compiletesting.symbolProcessorProviders

/**
 * Compiles the given [sourceFiles] with the application of [SealedEnumProcessorProvider].
 */
internal fun compile(vararg sourceFiles: SourceFile): KotlinCompilation.Result =
    KotlinCompilation().apply {
        sources = sourceFiles.toList()
        symbolProcessorProviders = listOf(SealedEnumProcessorProvider())
        inheritClassPath = false
    }.compile()
