package com.livefront.sealedenum.compilation.location

import kotlin.test.Test
import kotlin.test.assertEquals

class OutsideSealedClassTests {
    @Test
    fun one_object_outside_sealed_class() {
        assertEquals(listOf(AlphaFirstObject), AlphaOutsideSealedClass.values)
    }

    @Test
    fun one_enum_outside_sealed_class() {
        assertEquals(
            listOf(AlphaOutsideSealedClassEnum.AlphaFirstObject),
            enumValues<AlphaOutsideSealedClassEnum>().toList()
        )
    }

    @Test
    fun one_enum_outside_sealed_class_with_mapping() {
        assertEquals(
            AlphaOutsideSealedClass.values.map(AlphaOutsideSealedClass::enum),
            enumValues<AlphaOutsideSealedClassEnum>().toList()
        )
    }

    @Test
    fun two_objects_outside_sealed_class() {
        assertEquals(
            listOf(
                BetaFirstObject,
                BetaFourthObject,
                BetaSecondObject,
                BetaThirdObject,
            ),
            BetaOutsideSealedClass.values
        )
    }

    @Test
    fun two_enums_outside_sealed_class() {
        assertEquals(
            listOf(
                BetaOutsideSealedClassEnum.BetaFirstObject,
                BetaOutsideSealedClassEnum.BetaFourthObject,
                BetaOutsideSealedClassEnum.BetaSecondObject,
                BetaOutsideSealedClassEnum.BetaThirdObject,
            ),
            enumValues<BetaOutsideSealedClassEnum>().toList()
        )
    }

    @Test
    fun two_enums_outside_sealed_class_with_mapping() {
        assertEquals(
            BetaOutsideSealedClass.values.map(BetaOutsideSealedClass::enum),
            enumValues<BetaOutsideSealedClassEnum>().toList()
        )
    }

    @Test
    fun outside_object_ordering() {
        assertEquals(
            listOf(
                GammaOutsideSealedClass.GammaSecondObject,
                GammaFirstObject,
                GammaFourthObject,
                GammaThirdObject,
            ),
            GammaOutsideSealedClass.values
        )
    }

    @Test
    fun outside_enum_ordering() {
        assertEquals(
            listOf(
                GammaOutsideSealedClassEnum.GammaOutsideSealedClass_GammaSecondObject,
                GammaOutsideSealedClassEnum.GammaFirstObject,
                GammaOutsideSealedClassEnum.GammaFourthObject,
                GammaOutsideSealedClassEnum.GammaThirdObject,
            ),
            enumValues<GammaOutsideSealedClassEnum>().toList()
        )
    }

    @Test
    fun outside_enum_ordering_with_mapping() {
        assertEquals(
            GammaOutsideSealedClass.values.map(GammaOutsideSealedClass::enum),
            enumValues<GammaOutsideSealedClassEnum>().toList()
        )
    }

    @Test
    fun duplicate_name_objects() {
        assertEquals(
            listOf(DeltaOutsideSealedClass.DeltaObject, DeltaObject),
            DeltaOutsideSealedClass.values
        )
    }

    @Test
    fun duplicate_name_enums() {
        assertEquals(
            listOf(
                DeltaOutsideSealedClassEnum.DeltaOutsideSealedClass_DeltaObject,
                DeltaOutsideSealedClassEnum.DeltaObject,
            ),
            enumValues<DeltaOutsideSealedClassEnum>().toList()
        )
    }

    @Test
    fun duplicate_name_enums_with_mapping() {
        assertEquals(
            DeltaOutsideSealedClass.values.map(DeltaOutsideSealedClass::enum),
            enumValues<DeltaOutsideSealedClassEnum>().toList()
        )
    }
}
