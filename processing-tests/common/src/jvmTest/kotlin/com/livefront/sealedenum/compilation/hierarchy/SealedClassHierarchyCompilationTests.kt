package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.testing.PlatformSourceType
import com.livefront.sealedenum.testing.SharableProcessingSourceType
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Test

/**
 * @see SealedClassHierarchyTests
 */
class SealedClassHierarchyCompilationTests {
    @Test
    fun `compilation for first hierarchy A generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "hierarchy",
                "SealedClassHierarchy.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "FirstClassHierarchy.A_SealedEnum.kt",
            firstClassHierarchyAGenerated,
            result
        )
    }

    @Test
    fun `compilation for first hierarchy B generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "hierarchy",
                "SealedClassHierarchy.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "FirstClassHierarchy.A.B_SealedEnum.kt",
            firstClassHierarchyBGenerated,
            result
        )
    }

    @Test
    fun `compilation for second hierarchy Z generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "hierarchy",
                "SealedClassHierarchy.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "SecondClassHierarchy.Z_SealedEnum.kt",
            secondClassHierarchyZGenerated,
            result
        )
    }

    @Test
    fun `compilation for second hierarchy X generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "hierarchy",
                "SealedClassHierarchy.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "SecondClassHierarchy.Z.X_SealedEnum.kt",
            secondClassHierarchyZXGenerated,
            result
        )
    }

    @Test
    fun `compilation for second hierarchy U generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "hierarchy",
                "SealedClassHierarchy.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "SecondClassHierarchy.Z.X.U_SealedEnum.kt",
            secondClassHierarchyZXUGenerated,
            result
        )
    }

    @Test
    fun `compilation for second hierarchy S generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "hierarchy",
                "SealedClassHierarchy.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "SecondClassHierarchy.Z.X.S_SealedEnum.kt",
            secondClassHierarchyZXSGenerated,
            result
        )
    }

    @Test
    fun `compilation for second hierarchy Q generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "hierarchy",
                "SealedClassHierarchy.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "SecondClassHierarchy.Z.Q_SealedEnum.kt",
            secondClassHierarchyZQGenerated,
            result
        )
    }
}
