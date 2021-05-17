package com.livefront.sealedenum.testing

import com.tschuchort.compiletesting.KotlinCompilation

/**
 * A wrapper around [KotlinCompilation.Result] that includes [messages] separately.
 */
class CompilationResult(
    val kotlinCompilationResult: KotlinCompilation.Result,
    val messages: String
)
