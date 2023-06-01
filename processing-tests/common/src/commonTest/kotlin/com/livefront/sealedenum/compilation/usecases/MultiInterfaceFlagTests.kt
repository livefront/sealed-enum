package com.livefront.sealedenum.compilation.usecases

import kotlin.test.Test
import kotlin.test.assertEquals

class MultiInterfaceFlagTests {
    @Test
    fun three_objects_sealed_interface() {
        assertEquals(
            listOf(MultiInterfaceFlag.BothFlags, MultiInterfaceFlag.FirstFlag, MultiInterfaceFlag.SecondFlag),
            MultiInterfaceFlag.values
        )
    }

    @Test
    fun three_enums_for_sealed_class() {
        assertEquals(
            listOf(
                MultiInterfaceFlagEnum.MultiInterfaceFlag_BothFlags,
                MultiInterfaceFlagEnum.MultiInterfaceFlag_FirstFlag,
                MultiInterfaceFlagEnum.MultiInterfaceFlag_SecondFlag
            ),
            enumValues<MultiInterfaceFlagEnum>().toList()
        )
    }

    @Test
    fun three_enums_for_sealed_interface_with_mapping() {
        assertEquals(
            MultiInterfaceFlag.values.map(MultiInterfaceFlag::enum),
            enumValues<MultiInterfaceFlagEnum>().toList()
        )
    }

    @Test
    fun correct_enum_class() {
        assertEquals(MultiInterfaceFlagEnum::class, MultiInterfaceFlagSealedEnum.enumClass)
    }
}
