package com.livefront.sealedenum.testing

import com.livefront.sealedenum.internal.ksp.SealedEnumProcessor
import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile
import com.tschuchort.compiletesting.symbolProcessors

/**
 * Compiles the given [sourceFiles] with the application of [SealedEnumProcessor].
 */
internal fun compile(vararg sourceFiles: SourceFile): KotlinCompilation.Result =
    KotlinCompilation().apply {
        sources = sourceFiles.toList()
        symbolProcessors = listOf(SealedEnumProcessor())
        inheritClassPath = true
    }.compile()
