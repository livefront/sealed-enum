package com.livefront.sealedenum.compilation.basic

import kotlin.test.Test
import kotlin.test.assertEquals

class OneObjectSealedInterfaceTests {
    @Test
    fun one_object_sealed_interface() {
        assertEquals(listOf(OneObjectSealedInterface.FirstObject), OneObjectSealedInterface.values)
    }

    @Test
    fun one_enum_for_sealed_interface() {
        assertEquals(
            listOf(OneObjectSealedInterfaceEnum.OneObjectSealedInterface_FirstObject),
            enumValues<OneObjectSealedInterfaceEnum>().toList()
        )
    }

    @Test
    fun one_enum_for_sealed_interface_with_mapping() {
        assertEquals(
            OneObjectSealedInterface.values.map(OneObjectSealedInterface::enum),
            enumValues<OneObjectSealedInterfaceEnum>().toList()
        )
    }

    @Test
    fun correct_enum_class() {
        assertEquals(OneObjectSealedInterfaceEnum::class, OneObjectSealedInterface.sealedEnum.enumClass)
    }
}
