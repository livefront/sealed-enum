package com.livefront.sealedenum.testing

import com.livefront.sealedenum.internal.processor.SealedEnumProcessor
import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile

/**
 * Compiles the given [sourceFiles] with the application of [SealedEnumProcessor].
 */
internal fun compile(vararg sourceFiles: SourceFile): KotlinCompilation.Result =
    KotlinCompilation().apply {
        sources = sourceFiles.toList()
        annotationProcessors = listOf(SealedEnumProcessor())
        inheritClassPath = true
        kotlincArguments = listOf("-language-version", "1.5")
    }.compile()
