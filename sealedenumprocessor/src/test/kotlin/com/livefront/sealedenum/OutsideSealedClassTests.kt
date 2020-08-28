package com.livefront.sealedenum

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

sealed class AlphaOutsideSealedClass {
    @GenSealedEnum(generateEnum = true)
    companion object
}

object AlphaFirstObject : AlphaOutsideSealedClass()

sealed class BetaOutsideSealedClass {
    @GenSealedEnum(generateEnum = true)
    companion object
}

object BetaFirstObject : BetaOutsideSealedClass()

object BetaSecondObject : BetaOutsideSealedClass()

object GammaFirstObject : GammaOutsideSealedClass()

sealed class GammaOutsideSealedClass {

    object GammaSecondObject : GammaOutsideSealedClass()

    @GenSealedEnum(generateEnum = true)
    companion object
}

object GammaThirdObject : GammaOutsideSealedClass()

sealed class DeltaOutsideSealedClass {
    object DeltaObject : DeltaOutsideSealedClass()

    @GenSealedEnum(generateEnum = true)
    companion object
}

object DeltaObject : DeltaOutsideSealedClass()

class OutsideSealedClassTests {

    @Test
    fun `one object outside sealed class`() {
        assertEquals(listOf(AlphaFirstObject), AlphaOutsideSealedClass.values)
    }

    @Test
    fun `one enum outside sealed class`() {
        assertEquals(
            listOf(AlphaOutsideSealedClassEnum.AlphaFirstObject),
            enumValues<AlphaOutsideSealedClassEnum>().toList()
        )
    }

    @Test
    fun `one enum outside sealed class with mapping`() {
        assertEquals(
            AlphaOutsideSealedClass.values.map(AlphaOutsideSealedClass::enum),
            enumValues<AlphaOutsideSealedClassEnum>().toList()
        )
    }

    @Test
    fun `two objects outside sealed class`() {
        assertEquals(listOf(BetaFirstObject, BetaSecondObject), BetaOutsideSealedClass.values)
    }

    @Test
    fun `two enums outside sealed class`() {
        assertEquals(
            listOf(
                BetaOutsideSealedClassEnum.BetaFirstObject,
                BetaOutsideSealedClassEnum.BetaSecondObject
            ),
            enumValues<BetaOutsideSealedClassEnum>().toList()
        )
    }

    @Test
    fun `two enums outside sealed class with mapping`() {
        assertEquals(
            BetaOutsideSealedClass.values.map(BetaOutsideSealedClass::enum),
            enumValues<BetaOutsideSealedClassEnum>().toList()
        )
    }

    @Test
    fun `outside object ordering`() {
        assertEquals(
            listOf(GammaFirstObject, GammaThirdObject, GammaOutsideSealedClass.GammaSecondObject),
            GammaOutsideSealedClass.values
        )
    }

    @Test
    fun `outside enum ordering`() {
        assertEquals(
            listOf(
                GammaOutsideSealedClassEnum.GammaFirstObject,
                GammaOutsideSealedClassEnum.GammaThirdObject,
                GammaOutsideSealedClassEnum.GammaOutsideSealedClass_GammaSecondObject
            ),
            enumValues<GammaOutsideSealedClassEnum>().toList()
        )
    }

    @Test
    fun `outside enum ordering with mapping`() {
        assertEquals(
            GammaOutsideSealedClass.values.map(GammaOutsideSealedClass::enum),
            enumValues<GammaOutsideSealedClassEnum>().toList()
        )
    }

    @Test
    fun `duplicate name objects`() {
        assertEquals(
            listOf(DeltaObject, DeltaOutsideSealedClass.DeltaObject),
            DeltaOutsideSealedClass.values
        )
    }

    @Test
    fun `duplicate name enums`() {
        assertEquals(
            listOf(
                DeltaOutsideSealedClassEnum.DeltaObject,
                DeltaOutsideSealedClassEnum.DeltaOutsideSealedClass_DeltaObject
            ),
            enumValues<DeltaOutsideSealedClassEnum>().toList()
        )
    }

    @Test
    fun `duplicate name enums with mapping`() {
        assertEquals(
            DeltaOutsideSealedClass.values.map(DeltaOutsideSealedClass::enum),
            enumValues<DeltaOutsideSealedClassEnum>().toList()
        )
    }
}
