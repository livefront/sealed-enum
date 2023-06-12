package com.livefront.sealedenum.compilation.generics

import com.livefront.sealedenum.testing.PlatformSourceType
import com.livefront.sealedenum.testing.SharableProcessingSourceType
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Test

/**
 * @see GenericSealedClassTests
 */
class GenericSealedClassCompilationTests {
    @Test
    fun `compilation for one type parameter generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "generics",
                "GenericSealedClass.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "OneTypeParameterSealedClass_SealedEnum.kt",
            oneTypeParameterSealedClassGenerated,
            result
        )
    }

    @Test
    fun `compilation for two type parameter generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "generics",
                "GenericSealedClass.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "TwoTypeParameterSealedClass_SealedEnum.kt",
            twoTypeParameterSealedClassGenerated,
            result
        )
    }

    @Test
    fun `compilation for limited type parameter generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "generics",
                "GenericSealedClass.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "LimitedTypeParameterSealedClass_SealedEnum.kt",
            limitedTypeParameterSealedClassGenerated,
            result
        )
    }

    @Test
    fun `compilation for multiple bounds sealed class generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "generics",
                "GenericSealedClass.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "MultipleBoundsSealedClass_SealedEnum.kt",
            multipleBoundsSealedClassGenerated,
            result
        )
    }
}
