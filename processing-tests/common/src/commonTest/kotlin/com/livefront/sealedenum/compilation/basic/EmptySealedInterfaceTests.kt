package com.livefront.sealedenum.compilation.basic

import kotlin.test.Test
import kotlin.test.assertEquals

class EmptySealedInterfaceTests {
    @Test
    fun empty_sealed_interface() {
        assertEquals(emptyList<EmptySealedInterface>(), EmptySealedInterface.values)
    }

    @Test
    fun empty_enum_for_sealed_interface() {
        assertEquals(
            EmptySealedInterface.values.map(EmptySealedInterface::enum),
            enumValues<EmptySealedInterfaceEnum>().toList()
        )
    }

    @Test
    fun correct_enum_class() {
        assertEquals(EmptySealedInterfaceEnum::class, EmptySealedInterface.sealedEnum.enumClass)
    }
}
