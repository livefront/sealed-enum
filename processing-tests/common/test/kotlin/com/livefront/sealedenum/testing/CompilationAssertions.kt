package com.livefront.sealedenum.testing

import com.tschuchort.compiletesting.KotlinCompilation
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Assertions.assertEquals

/**
 * Asserts that the given [result] compiled successfully.
 */
internal fun assertCompiles(result: CompilationResult) {
    assertEquals(KotlinCompilation.ExitCode.OK, result.kotlinCompilationResult.exitCode)
}

/**
 * Asserts that the given [result] failed to compile.
 */
internal fun assertFails(result: CompilationResult) {
    assertEquals(KotlinCompilation.ExitCode.COMPILATION_ERROR, result.kotlinCompilationResult.exitCode)
}

/**
 * Asserts that the contents of the generated source file with the name of [fileName] is equal to [expected] for the
 * given [result] of compilation.
 */
internal fun assertGeneratedFileMatches(
    fileName: String,
    @Language("kotlin") expected: String,
    result: CompilationResult
) {
    assertEquals(
        expected,
        result.kotlinCompilationResult.outputDirectory.parentFile.walk().first { it.name == fileName }.readText()
    )
}
