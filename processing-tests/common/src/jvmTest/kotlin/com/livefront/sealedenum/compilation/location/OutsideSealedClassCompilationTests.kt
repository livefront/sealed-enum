package com.livefront.sealedenum.compilation.location

import com.livefront.sealedenum.testing.PlatformSourceType
import com.livefront.sealedenum.testing.SharableProcessingSourceType
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Test

class OutsideSealedClassCompilationTests {
    @Test
    fun `compilation for alpha outside sealed class generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "location",
                "OutsideSealedClass.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches("AlphaOutsideSealedClass_SealedEnum.kt", alphaOutsideSealedClassGenerated, result)
    }

    @Test
    fun `compilation for beta outside sealed class generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "location",
                "OutsideSealedClass.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches("BetaOutsideSealedClass_SealedEnum.kt", betaOutsideSealedClassGenerated, result)
    }

    @Test
    fun `compilation for gamma outside sealed class generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "location",
                "OutsideSealedClass.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches("GammaOutsideSealedClass_SealedEnum.kt", gammaOutsideSealedClassGenerated, result)
    }

    @Test
    fun `compilation for delta outside sealed class generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "location",
                "OutsideSealedClass.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches("DeltaOutsideSealedClass_SealedEnum.kt", deltaOutsideSealedClassGenerated, result)
    }
}
