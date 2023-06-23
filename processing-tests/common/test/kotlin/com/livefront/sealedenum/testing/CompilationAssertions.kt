package com.livefront.sealedenum.testing

import com.oneeyedmen.okeydoke.Approver
import com.oneeyedmen.okeydoke.ApproverFactory
import com.oneeyedmen.okeydoke.Checker
import com.oneeyedmen.okeydoke.Formatters
import com.oneeyedmen.okeydoke.Serializers
import com.oneeyedmen.okeydoke.Sources
import com.oneeyedmen.okeydoke.junit5.ApprovalsExtension
import com.tschuchort.compiletesting.KotlinCompilation
import org.junit.jupiter.api.Assertions.assertEquals
import org.opentest4j.AssertionFailedError
import java.io.File

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
    val fileText = result.outputDirectory.parentFile.walk().first { it.name == fileName }.readText()
    assertApproved(fileText)
}

object SealedEnumApproverFactory : ApproverFactory<Approver> {
    override fun createApprover(testName: String, testClass: Class<*>): Approver = Approver(
        testName,
        Sources.`in`(File("src/test/kotlin"), testClass.getPackage()),
        Formatters.stringFormatter(),
        Serializers.stringSerializer(),
        SealedEnumStringChecker
    )
}

object SealedEnumStringChecker : Checker<String> {
    override fun assertEquals(expectedMayBeNull: String?, actualMayBeNull: String?) {
        if (expectedMayBeNull == null && actualMayBeNull == null) return
        // Ignore windows line separator
        if (expectedMayBeNull == null || actualMayBeNull == null || expectedMayBeNull.replace(
                "\r\n",
                "\n"
            ) != actualMayBeNull.replace("\r\n", "\n")
        ) {
            System.err.println("Expected: $expectedMayBeNull\nActual: $actualMayBeNull")
            throw AssertionFailedError("Actual was not the same as approved", expectedMayBeNull, actualMayBeNull)
        }
    }
}

class SealedEnumApprovalsExtension : ApprovalsExtension(SealedEnumApproverFactory)
