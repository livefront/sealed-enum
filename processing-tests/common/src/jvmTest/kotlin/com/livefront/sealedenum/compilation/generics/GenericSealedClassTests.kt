package com.livefront.sealedenum.compilation.generics

import com.livefront.sealedenum.testing.PlatformSourceType
import com.livefront.sealedenum.testing.SharableProcessingSourceType
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GenericSealedClassTests {

    @Test
    fun `one type parameter sealed class`() {
        assertEquals(
            listOf(
                OneTypeParameterSealedClass.FirstObject,
                OneTypeParameterSealedClass.SecondObject,
                OneTypeParameterSealedClass.ThirdObject
            ),
            OneTypeParameterSealedClass.values
        )
    }

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
    fun `two type parameter sealed class`() {
        assertEquals(
            listOf(
                TwoTypeParameterSealedClass.FirstObject,
                TwoTypeParameterSealedClass.SecondObject
            ),
            TwoTypeParameterSealedClass.values
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
    fun `limited type parameter sealed class`() {
        assertEquals(
            listOf(
                LimitedTypeParameterSealedClass.FirstObject,
                LimitedTypeParameterSealedClass.SecondObject
            ),
            LimitedTypeParameterSealedClass.values
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
    fun `multiple bounds sealed class`() {
        assertEquals(
            listOf(
                MultipleBoundsSealedClass.FirstObject
            ),
            MultipleBoundsSealedClass.values
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
