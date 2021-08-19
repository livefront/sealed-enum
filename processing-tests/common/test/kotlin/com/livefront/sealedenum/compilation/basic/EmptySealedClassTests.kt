package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.compile
import com.tschuchort.compiletesting.SourceFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class EmptySealedClassTests {
    @Test
    fun `empty sealed class`() {
        assertEquals(emptyList<EmptySealedClass>(), EmptySealedClass.values)
    }

    @Test
    fun `compilation generates correct code`() {
        val paths = arrayOf("compilation", "basic", "EmptySealedClass.kt")
        val result = compile(
            SourceFile.fromPath(
                Paths.get(
                    "..",
                    "common",
                    "test",
                    "kotlin",
                    "com",
                    "livefront",
                    "sealedenum",
                    *paths
                ).toFile()
            )
        )

        assertCompiles(result)
    }
}
