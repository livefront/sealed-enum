package com.livefront.sealedenum.compilation.usecases

import kotlin.test.Test
import kotlin.test.assertEquals

class FlagTests {
    @Test
    fun two_objects_sealed_class() {
        assertEquals(
            listOf(Flag.FirstFlag, Flag.SecondFlag),
            FlagSealedEnum.values
        )
    }

    @Test
    fun two_enums_for_sealed_class() {
        assertEquals(
            listOf(
                FlagEnum.Flag_FirstFlag,
                FlagEnum.Flag_SecondFlag
            ),
            enumValues<FlagEnum>().toList()
        )
    }

    @Test
    fun two_enums_for_sealed_class_with_mapping() {
        assertEquals(
            Flag.values.map(Flag::enum),
            enumValues<FlagEnum>().toList()
        )
    }

    @Test
    fun correct_enum_class() {
        assertEquals(FlagEnum::class, FlagSealedEnum.enumClass)
    }
}
