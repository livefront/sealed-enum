package com.livefront.sealedenum.testing

import com.tschuchort.compiletesting.KotlinCompilation
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Assertions.assertEquals

/**
 * Asserts that the given [result] compiled successfully.
 */
internal fun assertCompiles(result: KotlinCompilation.Result) {
    assertEquals(KotlinCompilation.ExitCode.OK, result.exitCode)
}

/**
 * Asserts that the given [result] failed to compile.
 */
internal fun assertFails(result: KotlinCompilation.Result) {
    assertEquals(KotlinCompilation.ExitCode.COMPILATION_ERROR, result.exitCode)
}

/**
 * Asserts that the contents of the generated source file with the name of [fileName] is equal to [expected] for the
 * given [result] of compilation.
 */
internal fun assertGeneratedFileMatches(
    fileName: String,
    @Language("kotlin") expected: String,
    result: KotlinCompilation.Result
) {
    assertEquals(
        expected,
        result.outputDirectory.parentFile.walk().first { it.name == fileName }.readText()
    )
}
