package com.livefront.sealedenum.compilation.generics

import kotlin.test.Test
import kotlin.test.assertEquals

class GenericSealedClassTests {
    @Test
    fun one_type_parameter_sealed_class() {
        assertEquals(
            listOf(
                OneTypeParameterSealedClass.FirstObject,
                OneTypeParameterSealedClass.SecondObject,
                OneTypeParameterSealedClass.ThirdObject
            ),
            OneTypeParameterSealedClass.values
        )
    }

    @Test
    fun two_type_parameter_sealed_class() {
        assertEquals(
            listOf(
                TwoTypeParameterSealedClass.FirstObject,
                TwoTypeParameterSealedClass.SecondObject
            ),
            TwoTypeParameterSealedClass.values
        )
    }

    @Test
    fun limited_type_parameter_sealed_class() {
        assertEquals(
            listOf(
                LimitedTypeParameterSealedClass.FirstObject,
                LimitedTypeParameterSealedClass.SecondObject
            ),
            LimitedTypeParameterSealedClass.values
        )
    }

    @Test
    fun multiple_bounds_sealed_class() {
        assertEquals(
            listOf(
                MultipleBoundsSealedClass.FirstObject
            ),
            MultipleBoundsSealedClass.values
        )
    }
}
