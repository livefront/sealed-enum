package com.livefront.sealedenum

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@GenSealedEnum(generateEnum = true)
sealed class EmptySealedClass

class EmptySealedClassTests {
    @Test
    fun `empty sealed class`() {
        assertEquals(emptyList<EmptySealedClass>(), EmptySealedClassSealedEnum.values)
    }

    @Test
    fun `empty enum for sealed class`() {
        assertEquals(emptyList<EmptySealedClassEnum>(), enumValues<EmptySealedClassEnum>().toList())
    }

    @Test
    fun `correct enum class`() {
        assertEquals(EmptySealedClassEnum::class.java, EmptySealedClassSealedEnum.enumClass)
    }
}
