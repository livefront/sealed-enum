package com.livefront.sealedenum

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

sealed class EmptySealedClass {
    @GenSealedEnum(generateEnum = true)
    companion object
}

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
}
