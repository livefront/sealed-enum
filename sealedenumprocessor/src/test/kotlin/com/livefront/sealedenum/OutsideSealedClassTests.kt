package com.livefront.sealedenum

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@GenSealedEnum(generateEnum = true)
sealed class AlphaOutsideSealedClass

object AlphaFirstObject : AlphaOutsideSealedClass()

@GenSealedEnum(generateEnum = true)
sealed class BetaOutsideSealedClass

object BetaFirstObject : BetaOutsideSealedClass()

object BetaSecondObject : BetaOutsideSealedClass()

object GammaFirstObject : GammaOutsideSealedClass()

@GenSealedEnum(generateEnum = true)
sealed class GammaOutsideSealedClass {

    object GammaSecondObject : GammaOutsideSealedClass()
}

object GammaThirdObject : GammaOutsideSealedClass()

@GenSealedEnum(generateEnum = true)
sealed class DeltaOutsideSealedClass {
    object DeltaObject : DeltaOutsideSealedClass()
}

object DeltaObject : DeltaOutsideSealedClass()

class OutsideSealedClassTests {

    @Test
    fun `one object outside sealed class`() {
        assertEquals(listOf(AlphaFirstObject), AlphaOutsideSealedClassSealedEnum.values)
    }

    @Test
    fun `one enum outside sealed class`() {
        assertEquals(
            listOf(AlphaOutsideSealedClassEnum.AlphaFirstObject),
            enumValues<AlphaOutsideSealedClassEnum>().toList()
        )
    }

    @Test
    fun `two objects outside sealed class`() {
        assertEquals(listOf(BetaFirstObject, BetaSecondObject), BetaOutsideSealedClassSealedEnum.values)
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
    fun `outside object ordering`() {
        assertEquals(
            listOf(GammaFirstObject, GammaThirdObject, GammaOutsideSealedClass.GammaSecondObject),
            GammaOutsideSealedClassSealedEnum.values
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
    fun `duplicate name objects`() {
        assertEquals(
            listOf(DeltaObject, DeltaOutsideSealedClass.DeltaObject),
            DeltaOutsideSealedClassSealedEnum.values
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
}
