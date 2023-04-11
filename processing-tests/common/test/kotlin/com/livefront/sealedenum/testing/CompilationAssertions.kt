package com.livefront.sealedenum.testing

import com.oneeyedmen.okeydoke.Approver
import com.tschuchort.compiletesting.KotlinCompilation
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
 * Asserts that the contents of the generated source file with the name of [fileName] for the given [result] of
 * compilation is approved
 */
internal fun Approver.assertApprovedGeneratedFile(fileName: String, result: KotlinCompilation.Result) {
    assertApproved(result.outputDirectory.parentFile.walk().first { it.name == fileName }.readText())
}
