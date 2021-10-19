package com.livefront.sealedenum.compilation.basic

import kotlin.test.Test
import kotlin.test.assertEquals

class EmptySealedClassTests {
    @Test
    fun empty_sealed_class() {
        assertEquals(emptyList<EmptySealedClass>(), EmptySealedClass.values)
    }

    @Test
    fun empty_enum_for_sealed_class() {
        assertEquals(
            EmptySealedClass.values.map(EmptySealedClass::enum),
            enumValues<EmptySealedClassEnum>().toList()
        )
    }

    @Test
    fun correct_enum_class() {
        assertEquals(EmptySealedClassEnum::class, EmptySealedClass.sealedEnum.enumClass)
    }
}
