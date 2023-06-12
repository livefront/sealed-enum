package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.testing.PlatformSourceType
import com.livefront.sealedenum.testing.SharableProcessingSourceType
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Test

/**
 * @see SealedInterfaceHierarchyTests
 */
class SealedInterfaceHierarchyCompilationTests {
    @Test
    fun `compilation for first hierarchy A generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "hierarchy",
                "SealedInterfaceHierarchy.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "FirstInterfaceHierarchy.A_SealedEnum.kt",
            firstInterfaceHierarchyAGenerated,
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
                "SealedInterfaceHierarchy.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "FirstInterfaceHierarchy.A.B_SealedEnum.kt",
            firstInterfaceHierarchyBGenerated,
            result
        )
    }

    @Test
    fun `compilation for second hierarchy A generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "hierarchy",
                "SealedInterfaceHierarchy.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "SecondInterfaceHierarchy.A_SealedEnum.kt",
            secondInterfaceHierarchyAGenerated,
            result
        )
    }

    @Test
    fun `compilation for second hierarchy C generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "hierarchy",
                "SealedInterfaceHierarchy.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "SecondInterfaceHierarchy.A.C_SealedEnum.kt",
            secondInterfaceHierarchyACGenerated,
            result
        )
    }

    @Test
    fun `compilation for second hierarchy F generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "hierarchy",
                "SealedInterfaceHierarchy.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "SecondInterfaceHierarchy.A.C.F_SealedEnum.kt",
            secondInterfaceHierarchyACFGenerated,
            result
        )
    }

    @Test
    fun `compilation for second hierarchy H generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "hierarchy",
                "SealedInterfaceHierarchy.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "SecondInterfaceHierarchy.A.C.H_SealedEnum.kt",
            secondInterfaceHierarchyACHGenerated,
            result
        )
    }

    @Test
    fun `compilation for second hierarchy J generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "hierarchy",
                "SealedInterfaceHierarchy.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "SecondInterfaceHierarchy.A.J_SealedEnum.kt",
            secondInterfaceHierarchyAJGenerated,
            result
        )
    }
}
