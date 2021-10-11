package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.testing.PlatformSourceType
import com.livefront.sealedenum.testing.SharableProcessingSourceType
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SealedClassHierarchyTests {

    @Test
    fun `first hierarchy class A values`() {
        assertEquals(listOf(FirstClassHierarchy.A.B.C), FirstClassHierarchy.A.values)
    }

    @Test
    fun `first hierarchy class A ordinal of A_B_C`() {
        assertEquals(0, (FirstClassHierarchy.A.B.C as FirstClassHierarchy.A).ordinal)
    }

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
    fun `first hierarchy class B values`() {
        assertEquals(listOf(FirstClassHierarchy.A.B.C), FirstClassHierarchy.A.B.values)
    }

    @Test
    fun `first hierarchy class B ordinal`() {
        assertEquals(0, (FirstClassHierarchy.A.B.C as FirstClassHierarchy.A.B).ordinal)
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
    fun `second hierarchy class Z values`() {
        assertEquals(
            listOf(
                SecondClassHierarchy.Z.Y,
                SecondClassHierarchy.Z.X.W,
                SecondClassHierarchy.Z.X.V,
                SecondClassHierarchy.Z.X.U.T,
                SecondClassHierarchy.Z.X.S.R,
                SecondClassHierarchy.Z.Q.P,
                SecondClassHierarchy.Z.O
            ),
            SecondClassHierarchy.Z.values
        )
    }

    @Test
    fun `second hierarchy class Z ordinal of Z_Y`() {
        assertEquals(0, (SecondClassHierarchy.Z.Y as SecondClassHierarchy.Z).ordinal)
    }

    @Test
    fun `second hierarchy class Z ordinal of Z_X_W`() {
        assertEquals(1, (SecondClassHierarchy.Z.X.W as SecondClassHierarchy.Z).ordinal)
    }

    @Test
    fun `second hierarchy class Z ordinal of Z_X_V`() {
        assertEquals(2, (SecondClassHierarchy.Z.X.V as SecondClassHierarchy.Z).ordinal)
    }

    @Test
    fun `second hierarchy class Z ordinal of Z_X_U_T`() {
        assertEquals(3, (SecondClassHierarchy.Z.X.U.T as SecondClassHierarchy.Z).ordinal)
    }

    @Test
    fun `second hierarchy class Z ordinal of Z_X_S_R`() {
        assertEquals(4, (SecondClassHierarchy.Z.X.S.R as SecondClassHierarchy.Z).ordinal)
    }

    @Test
    fun `second hierarchy class Z ordinal of Z_Q_P`() {
        assertEquals(5, (SecondClassHierarchy.Z.Q.P as SecondClassHierarchy.Z).ordinal)
    }

    @Test
    fun `second hierarchy class Z ordinal of Z_O`() {
        assertEquals(6, (SecondClassHierarchy.Z.O as SecondClassHierarchy.Z).ordinal)
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
    fun `second hierarchy class X values`() {
        assertEquals(
            listOf(
                SecondClassHierarchy.Z.X.W,
                SecondClassHierarchy.Z.X.V,
                SecondClassHierarchy.Z.X.U.T,
                SecondClassHierarchy.Z.X.S.R
            ),
            SecondClassHierarchy.Z.X.values
        )
    }

    @Test
    fun `second hierarchy class X ordinal of Z_X_W`() {
        assertEquals(0, (SecondClassHierarchy.Z.X.W as SecondClassHierarchy.Z.X).ordinal)
    }

    @Test
    fun `second hierarchy class X ordinal of Z_X_V`() {
        assertEquals(1, (SecondClassHierarchy.Z.X.V as SecondClassHierarchy.Z.X).ordinal)
    }

    @Test
    fun `second hierarchy class X ordinal of Z_X_U_T`() {
        assertEquals(2, (SecondClassHierarchy.Z.X.U.T as SecondClassHierarchy.Z.X).ordinal)
    }

    @Test
    fun `second hierarchy class X ordinal of Z_X_S_R`() {
        assertEquals(3, (SecondClassHierarchy.Z.X.S.R as SecondClassHierarchy.Z.X).ordinal)
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
    fun `second hierarchy class U values`() {
        assertEquals(
            listOf(SecondClassHierarchy.Z.X.U.T),
            SecondClassHierarchy.Z.X.U.values
        )
    }

    @Test
    fun `second hierarchy class U ordinal of Z_X_U_T`() {
        assertEquals(0, (SecondClassHierarchy.Z.X.U.T as SecondClassHierarchy.Z.X.U).ordinal)
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
    fun `second hierarchy class S values`() {
        assertEquals(
            listOf(SecondClassHierarchy.Z.X.S.R),
            SecondClassHierarchy.Z.X.S.values
        )
    }

    @Test
    fun `second hierarchy class S ordinal of Z_X_S_R`() {
        assertEquals(0, (SecondClassHierarchy.Z.X.S.R as SecondClassHierarchy.Z.X.S).ordinal)
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
    fun `second hierarchy class Q values`() {
        assertEquals(
            listOf(SecondClassHierarchy.Z.Q.P),
            SecondClassHierarchy.Z.Q.values
        )
    }

    @Test
    fun `second hierarchy class Q ordinal of Z_Q_P`() {
        assertEquals(0, (SecondClassHierarchy.Z.Q.P as SecondClassHierarchy.Z.Q).ordinal)
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
