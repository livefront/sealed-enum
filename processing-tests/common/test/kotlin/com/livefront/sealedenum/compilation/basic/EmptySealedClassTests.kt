package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getCommonSourceFile
import org.junit.jupiter.api.Test

class EmptySealedClassTests {
    @Test
    fun `empty sealed class`() {
        // assertEquals(emptyList<EmptySealedClass>(), EmptySealedClass.values)
    }

    @Test
    fun `compilation generates correct code`() {
        val result = compile(getCommonSourceFile("compilation", "basic", "EmptySealedClass.kt"))

        assertCompiles(result)
    }
}
