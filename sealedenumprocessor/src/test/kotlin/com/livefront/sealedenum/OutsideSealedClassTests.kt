package com.livefront.sealedenum

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@GenSealedEnum
sealed class AlphaOutsideSealedClass

object AlphaFirstObject : AlphaOutsideSealedClass()

@GenSealedEnum
sealed class BetaOutsideSealedClass

object BetaFirstObject : BetaOutsideSealedClass()

object BetaSecondObject : BetaOutsideSealedClass()

object GammaFirstObject : GammaOutsideSealedClass()

@GenSealedEnum
sealed class GammaOutsideSealedClass {

    object GammaSecondObject : GammaOutsideSealedClass()
}

object GammaThirdObject : GammaOutsideSealedClass()

class OutsideSealedClassTests {

    @Test
    fun `one object outside sealed class`() {
        assertEquals(listOf(AlphaFirstObject), AlphaOutsideSealedClassSealedEnum.values)
    }

    @Test
    fun `two objects outside sealed class`() {
        assertEquals(listOf(BetaFirstObject, BetaSecondObject), BetaOutsideSealedClassSealedEnum.values)
    }

    @Test
    fun `outside object ordering`() {
        assertEquals(
            listOf(GammaFirstObject, GammaThirdObject, GammaOutsideSealedClass.GammaSecondObject),
            GammaOutsideSealedClassSealedEnum.values
        )
    }
}
