package com.livefront.sealedenum.compilation.basic

import kotlin.test.Test
import kotlin.test.assertEquals

class TwoObjectSealedInterfaceTests {
    @Test
    fun two_objects_sealed_interface() {
        assertEquals(
            listOf(TwoObjectSealedInterface.FirstObject, TwoObjectSealedInterface.SecondObject),
            TwoObjectSealedInterface.values
        )
    }

    @Test
    fun two_enums_for_sealed_interface() {
        assertEquals(
            listOf(
                TwoObjectSealedInterfaceEnum.TwoObjectSealedInterface_FirstObject,
                TwoObjectSealedInterfaceEnum.TwoObjectSealedInterface_SecondObject
            ),
            enumValues<TwoObjectSealedInterfaceEnum>().toList()
        )
    }

    @Test
    fun two_enums_for_sealed_interface_with_mapping() {
        assertEquals(
            TwoObjectSealedInterface.values.map(TwoObjectSealedInterface::enum),
            enumValues<TwoObjectSealedInterfaceEnum>().toList()
        )
    }

    @Test
    fun correct_enum_class() {
        assertEquals(TwoObjectSealedInterfaceEnum::class, TwoObjectSealedInterface.sealedEnum.enumClass)
    }
}
