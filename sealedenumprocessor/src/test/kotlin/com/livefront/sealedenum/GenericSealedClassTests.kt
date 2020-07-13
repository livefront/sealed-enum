package com.livefront.sealedenum

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

sealed class OneTypeParameterSealedClass<out T> {
    object FirstObject : OneTypeParameterSealedClass<Int>()

    object SecondObject : OneTypeParameterSealedClass<String>()

    object ThirdObject : OneTypeParameterSealedClass<Nothing>()
}

sealed class TwoTypeParameterSealedClass<out A, out B> {
    object FirstObject : TwoTypeParameterSealedClass<Any?, Nothing>()

    object SecondObject : TwoTypeParameterSealedClass<Double, Double>()
}

sealed class LimitedTypeParameterSealedClass<in Number, out String> {
    object FirstObject : LimitedTypeParameterSealedClass<Int, String>()

    object SecondObject : LimitedTypeParameterSealedClass<Int, Any>()
}

class GenericSealedClassTests {

    @Test
    fun `one type parameter sealed class`() {
        assertEquals(
            listOf(
                OneTypeParameterSealedClass.FirstObject,
                OneTypeParameterSealedClass.SecondObject,
                OneTypeParameterSealedClass.ThirdObject
            ),
            OneTypeParameterSealedClassSealedEnum.values
        )
    }

    @Test
    fun `two type parameter sealed class`() {
        assertEquals(
            listOf(
                TwoTypeParameterSealedClass.FirstObject,
                TwoTypeParameterSealedClass.SecondObject
            ),
            TwoTypeParameterSealedClassSealedEnum.values
        )
    }

    @Test
    fun `limited type parameter sealed class`() {
        assertEquals(
            listOf(
                LimitedTypeParameterSealedClass.FirstObject,
                LimitedTypeParameterSealedClass.SecondObject
            ),
            LimitedTypeParameterSealedClassSealedEnum.values
        )
    }
}
