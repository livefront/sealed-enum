package com.livefront.sealedenum.compilation.basic

import kotlin.test.Test
import kotlin.test.assertEquals

class OneObjectSealedClassTests {
    @Test
    fun one_object_sealed_class() {
        assertEquals(listOf(OneObjectSealedClass.FirstObject), OneObjectSealedClass.values)
    }

    @Test
    fun one_enum_for_sealed_class() {
        assertEquals(
            listOf(OneObjectSealedClassEnum.OneObjectSealedClass_FirstObject),
            enumValues<OneObjectSealedClassEnum>().toList()
        )
    }

    @Test
    fun one_enum_for_sealed_class_with_mapping() {
        assertEquals(
            OneObjectSealedClass.values.map(OneObjectSealedClass::enum),
            enumValues<OneObjectSealedClassEnum>().toList()
        )
    }

    @Test
    fun correct_enum_class() {
        assertEquals(OneObjectSealedClassEnum::class, OneObjectSealedClass.sealedEnum.enumClass)
    }
}
