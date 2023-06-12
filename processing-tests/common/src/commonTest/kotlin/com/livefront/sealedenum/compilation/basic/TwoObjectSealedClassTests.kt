package com.livefront.sealedenum.compilation.basic

import kotlin.test.Test
import kotlin.test.assertEquals

class TwoObjectSealedClassTests {
    @Test
    fun two_objects_sealed_class() {
        assertEquals(
            listOf(TwoObjectSealedClass.FirstObject, TwoObjectSealedClass.SecondObject),
            TwoObjectSealedClassSealedEnum.values
        )
    }

    @Test
    fun two_enums_for_sealed_class() {
        assertEquals(
            listOf(
                TwoObjectSealedClassEnum.TwoObjectSealedClass_FirstObject,
                TwoObjectSealedClassEnum.TwoObjectSealedClass_SecondObject
            ),
            enumValues<TwoObjectSealedClassEnum>().toList()
        )
    }

    @Test
    fun two_enums_for_sealed_class_with_mapping() {
        assertEquals(
            TwoObjectSealedClass.values.map(TwoObjectSealedClass::enum),
            enumValues<TwoObjectSealedClassEnum>().toList()
        )
    }

    @Test
    fun correct_enum_class() {
        assertEquals(TwoObjectSealedClassEnum::class, TwoObjectSealedClassSealedEnum.enumClass)
    }
}
