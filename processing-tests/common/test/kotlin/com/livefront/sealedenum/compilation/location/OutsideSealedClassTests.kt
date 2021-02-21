package com.livefront.sealedenum.compilation.location

import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

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
    fun `compilation for alpha outside sealed class generates correct code`() {
        val result = compile(getSourceFile("compilation", "location", "OutsideSealedClass.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches("AlphaOutsideSealedClass_SealedEnum.kt", alphaOutsideSealedClassGenerated, result)
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
    fun `compilation for beta outside sealed class generates correct code`() {
        val result = compile(getSourceFile("compilation", "location", "OutsideSealedClass.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches("BetaOutsideSealedClass_SealedEnum.kt", betaOutsideSealedClassGenerated, result)
    }

    @Test
    fun `outside object ordering`() {
        assertEquals(
            listOf(GammaFirstObject, GammaOutsideSealedClass.GammaSecondObject, GammaThirdObject),
            GammaOutsideSealedClass.values
        )
    }

    @Test
    fun `outside enum ordering`() {
        assertEquals(
            listOf(
                GammaOutsideSealedClassEnum.GammaFirstObject,
                GammaOutsideSealedClassEnum.GammaOutsideSealedClass_GammaSecondObject,
                GammaOutsideSealedClassEnum.GammaThirdObject,
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
    fun `compilation for gamma outside sealed class generates correct code`() {
        val result = compile(getSourceFile("compilation", "location", "OutsideSealedClass.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches("GammaOutsideSealedClass_SealedEnum.kt", gammaOutsideSealedClassGenerated, result)
    }

    @Test
    fun `duplicate name objects`() {
        assertEquals(
            listOf(DeltaOutsideSealedClass.DeltaObject, DeltaObject),
            DeltaOutsideSealedClass.values
        )
    }

    @Test
    fun `duplicate name enums`() {
        assertEquals(
            listOf(
                DeltaOutsideSealedClassEnum.DeltaOutsideSealedClass_DeltaObject,
                DeltaOutsideSealedClassEnum.DeltaObject
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

    @Test
    fun `compilation for delta outside sealed class generates correct code`() {
        val result = compile(getSourceFile("compilation", "location", "OutsideSealedClass.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches("DeltaOutsideSealedClass_SealedEnum.kt", deltaOutsideSealedClassGenerated, result)
    }
}
