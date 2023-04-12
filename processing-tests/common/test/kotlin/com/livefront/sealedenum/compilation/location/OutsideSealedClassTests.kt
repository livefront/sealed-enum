package com.livefront.sealedenum.compilation.location

import com.livefront.sealedenum.testing.SealedEnumApprovalsExtension
import com.livefront.sealedenum.testing.assertApprovedGeneratedFile
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getCommonSourceFile
import com.oneeyedmen.okeydoke.Approver
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(SealedEnumApprovalsExtension::class)
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
    fun Approver.`compilation for alpha outside sealed class generates correct code`() {
        val result = compile(getCommonSourceFile("compilation", "location", "OutsideSealedClass.kt"))

        assertCompiles(result)
        assertApprovedGeneratedFile("AlphaOutsideSealedClass_SealedEnum.kt", result)
    }

    @Test
    fun `two objects outside sealed class`() {
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
    fun `two enums outside sealed class`() {
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
    fun `two enums outside sealed class with mapping`() {
        assertEquals(
            BetaOutsideSealedClass.values.map(BetaOutsideSealedClass::enum),
            enumValues<BetaOutsideSealedClassEnum>().toList()
        )
    }

    @Test
    fun Approver.`compilation for beta outside sealed class generates correct code`() {
        val result = compile(getCommonSourceFile("compilation", "location", "OutsideSealedClass.kt"))

        assertCompiles(result)
        assertApprovedGeneratedFile("BetaOutsideSealedClass_SealedEnum.kt", result)
    }

    @Test
    fun `outside object ordering`() {
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
    fun `outside enum ordering`() {
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
    fun `outside enum ordering with mapping`() {
        assertEquals(
            GammaOutsideSealedClass.values.map(GammaOutsideSealedClass::enum),
            enumValues<GammaOutsideSealedClassEnum>().toList()
        )
    }

    @Test
    fun Approver.`compilation for gamma outside sealed class generates correct code`() {
        val result = compile(getCommonSourceFile("compilation", "location", "OutsideSealedClass.kt"))

        assertCompiles(result)
        assertApprovedGeneratedFile("GammaOutsideSealedClass_SealedEnum.kt", result)
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
                DeltaOutsideSealedClassEnum.DeltaObject,
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
    fun Approver.`compilation for delta outside sealed class generates correct code`() {
        val result = compile(getCommonSourceFile("compilation", "location", "OutsideSealedClass.kt"))

        assertCompiles(result)
        assertApprovedGeneratedFile("DeltaOutsideSealedClass_SealedEnum.kt", result)
    }
}
