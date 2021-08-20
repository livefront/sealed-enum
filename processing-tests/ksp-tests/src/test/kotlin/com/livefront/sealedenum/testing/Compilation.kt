package com.livefront.sealedenum.testing

import com.livefront.sealedenum.internal.ksp.SealedEnumProcessorProvider
import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile
import com.tschuchort.compiletesting.symbolProcessorProviders
import java.io.File
import java.nio.file.Paths

/**
 * Compiles the given [sourceFiles] with the application of [SealedEnumProcessorProvider].
 */
internal fun compile(vararg sourceFiles: SourceFile): KotlinCompilation.Result =
    KotlinCompilation().apply {
        // Directly add all runtime sources to compilation, instead of inheriting the classpath
        val runtimeSources =
            Paths.get("..", "..", "runtime", "src", "main")
                .toFile()
                .walk()
                .filter(File::isFile)
                .map(SourceFile::fromPath)

        sources = sourceFiles.toList() + runtimeSources
        symbolProcessorProviders = listOf(SealedEnumProcessorProvider())
        inheritClassPath = false
    }.compile()
