package com.livefront.sealedenum.testing

import com.livefront.sealedenum.internal.SealedEnumProcessor
import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile

/**
 * Compiles the given [sourceFile] with the application of [SealedEnumProcessor].
 */
internal fun compile(sourceFile: SourceFile): KotlinCompilation.Result =
    KotlinCompilation().apply {
        sources = listOf(sourceFile)
        annotationProcessors = listOf(SealedEnumProcessor())
        inheritClassPath = true
    }.compile()
