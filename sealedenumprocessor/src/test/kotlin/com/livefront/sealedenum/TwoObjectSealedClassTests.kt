package com.livefront.sealedenum

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

sealed class TwoObjectSealedClass {
    object FirstObject : TwoObjectSealedClass()

    object SecondObject : TwoObjectSealedClass()

    @GenSealedEnum(generateEnum = true)
    companion object
}

class TwoObjectSealedClassTests {
    @Test
    fun `two objects sealed class`() {
        assertEquals(
            listOf(TwoObjectSealedClass.FirstObject, TwoObjectSealedClass.SecondObject),
            TwoObjectSealedClassSealedEnum.values
        )
    }

    @Test
    fun `two enums for sealed class`() {
        assertEquals(
            listOf(
                TwoObjectSealedClassEnum.TwoObjectSealedClass_FirstObject,
                TwoObjectSealedClassEnum.TwoObjectSealedClass_SecondObject
            ),
            enumValues<TwoObjectSealedClassEnum>().toList()
        )
    }

    @Test
    fun `two enums for sealed class with mapping`() {
        assertEquals(
            TwoObjectSealedClass.values.map(TwoObjectSealedClass::enum),
            enumValues<TwoObjectSealedClassEnum>().toList()
        )
    }

    @Test
    fun `correct enum class`() {
        assertEquals(TwoObjectSealedClassEnum::class.java, TwoObjectSealedClassSealedEnum.enumClass)
    }
}