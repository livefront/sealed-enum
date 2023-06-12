package com.livefront.sealedenum.testing

import com.tschuchort.compiletesting.SourceFile
import java.nio.file.Paths

/**
 * Returns the [SourceFile] for the given [paths] calculated relative to [com.livefront.sealedenum] in the test
 * folder, subject to the given [SharableProcessingSourceType] and [PlatformSourceType]
 */
internal fun getSourceFile(
    sharableProcessingSourceType: SharableProcessingSourceType,
    platformSourceType: PlatformSourceType,
    vararg paths: String
): SourceFile {
    val relativePaths = when (sharableProcessingSourceType) {
        SharableProcessingSourceType.COMMMON -> arrayOf("..", "common")
        SharableProcessingSourceType.KSP_COMMON -> arrayOf("..", "ksp-common-tests")
        SharableProcessingSourceType.UNIQUE -> emptyArray()
    } + arrayOf(
        "src",
        when (platformSourceType) {
            PlatformSourceType.COMMON -> "commonMain"
            PlatformSourceType.JVM -> "jvmMain"
        },
        if (paths.last().endsWith(".java")) "java" else "kotlin",
        "com",
        "livefront",
        "sealedenum",
    ) + paths

    return SourceFile.fromPath(
        Paths.get(
            relativePaths.first(),
            *relativePaths.drop(1).toTypedArray()
        ).toFile()
    )
}

/**
 * The location of test code, since some is shared between different processing types.
 */
enum class SharableProcessingSourceType {
    /**
     * Source files common to all types of processing
     */
    COMMMON,

    /**
     * Source files common to ksp processing
     */
    KSP_COMMON,

    /**
     * Source files unique to the current tests
     */
    UNIQUE
}

/**
 * The platform for the test code.
 */
enum class PlatformSourceType {
    /**
     * Test common source files
     */
    COMMON,

    /**
     * Test jvm source files
     */
    JVM
}
