package com.livefront.sealedenum.testing

import com.tschuchort.compiletesting.SourceFile
import java.nio.file.Paths

/**
 * Returns the [SourceFile] for the given [paths] calculated relative to [com.livefront.sealedenum] in the test
 * folder.
 */
internal fun getSourceFile(vararg paths: String): SourceFile = SourceFile.fromPath(
    Paths.get(
        "..",
        "common",
        "test",
        if (paths.last().endsWith(".java")) "java" else "kotlin",
        "com",
        "livefront",
        "sealedenum",
        *paths
    ).toFile()
)
