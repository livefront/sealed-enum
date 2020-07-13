package com.livefront.sealedenum

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@GenSealedEnum(generateEnum = true)
sealed class TwoObjectSealedClass {
    object FirstObject : TwoObjectSealedClass()

    object SecondObject : TwoObjectSealedClass()
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
    fun `correct enum class`() {
        assertEquals(TwoObjectSealedClassEnum::class.java, TwoObjectSealedClassSealedEnum.enumClass)
    }
}