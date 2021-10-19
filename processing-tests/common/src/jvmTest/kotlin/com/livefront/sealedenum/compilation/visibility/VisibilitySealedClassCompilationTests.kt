package com.livefront.sealedenum.compilation.visibility

import com.livefront.sealedenum.testing.PlatformSourceType
import com.livefront.sealedenum.testing.SharableProcessingSourceType
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Test

class VisibilitySealedClassCompilationTests {
    @Test
    fun `internal objects compilation generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "visibility",
                "VisibilitySealedClass.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "InternalObjectsSealedClass_SealedEnum.kt",
            internalObjectsSealedClassGenerated,
            result
        )
    }

    @Test
    fun `internal sealed class generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "visibility",
                "VisibilitySealedClass.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches("InternalSealedClass_SealedEnum.kt", internalSealedClassGenerated, result)
    }

    @Test
    fun `internal companion compilation generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "visibility",
                "VisibilitySealedClass.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "InternalCompanionSealedClass_SealedEnum.kt",
            internalCompanionSealedClassGenerated,
            result
        )
    }

    @Test
    fun `internal sealed class and companion compilation generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "visibility",
                "VisibilitySealedClass.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "InternalSealedAndCompanionSealedClass_SealedEnum.kt",
            internalSealedAndCompanionSealedClassGenerated,
            result
        )
    }
}
