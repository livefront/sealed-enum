package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getCommonSourceFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EmptySealedClassTests {
    @Test
    fun `empty sealed class`() {
        assertEquals(emptyList<EmptySealedClass>(), EmptySealedClass.values)
    }

    @Test
    fun `empty enum for sealed class`() {
        assertEquals(
            EmptySealedClass.values.map(EmptySealedClass::enum),
            enumValues<EmptySealedClassEnum>().toList()
        )
    }

    @Test
    fun `correct enum class`() {
        assertEquals(EmptySealedClassEnum::class.java, EmptySealedClass.sealedEnum.enumClass)
    }

    @Test
    fun `compilation generates correct code`() {
        val result = compile(getCommonSourceFile("compilation", "basic", "EmptySealedClass.kt"))

        assertCompiles(result)
    }
}
