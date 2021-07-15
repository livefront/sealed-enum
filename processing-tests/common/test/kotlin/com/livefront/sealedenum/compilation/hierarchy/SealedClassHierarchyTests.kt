package com.livefront.sealedenum.compilation.hierarchy

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
        val result = compile(getSourceFile("compilation", "hierarchy", "SealedClassHierarchy.kt"))

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
        val result = compile(getSourceFile("compilation", "hierarchy", "SealedClassHierarchy.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches(
            "FirstClassHierarchy.A.B_SealedEnum.kt",
            firstClassHierarchyBGenerated,
            result
        )
    }

    @Test
    fun `second hierarchy class A values`() {
        assertEquals(
            listOf(
                SecondClassHierarchy.A.B,
                SecondClassHierarchy.A.C.D,
                SecondClassHierarchy.A.C.E,
                SecondClassHierarchy.A.C.F.G,
                SecondClassHierarchy.A.C.H.I,
                SecondClassHierarchy.A.J.K,
                SecondClassHierarchy.A.L
            ),
            SecondClassHierarchy.A.values
        )
    }

    @Test
    fun `second hierarchy class A ordinal of A_B`() {
        assertEquals(0, (SecondClassHierarchy.A.B as SecondClassHierarchy.A).ordinal)
    }

    @Test
    fun `second hierarchy class A ordinal of A_C_D`() {
        assertEquals(1, (SecondClassHierarchy.A.C.D as SecondClassHierarchy.A).ordinal)
    }

    @Test
    fun `second hierarchy class A ordinal of A_C_E`() {
        assertEquals(2, (SecondClassHierarchy.A.C.E as SecondClassHierarchy.A).ordinal)
    }

    @Test
    fun `second hierarchy class A ordinal of A_C_F_G`() {
        assertEquals(3, (SecondClassHierarchy.A.C.F.G as SecondClassHierarchy.A).ordinal)
    }

    @Test
    fun `second hierarchy class A ordinal of A_C_H_I`() {
        assertEquals(4, (SecondClassHierarchy.A.C.H.I as SecondClassHierarchy.A).ordinal)
    }

    @Test
    fun `second hierarchy class A ordinal of A_J_K`() {
        assertEquals(5, (SecondClassHierarchy.A.J.K as SecondClassHierarchy.A).ordinal)
    }

    @Test
    fun `second hierarchy class A ordinal of A_L`() {
        assertEquals(6, (SecondClassHierarchy.A.L as SecondClassHierarchy.A).ordinal)
    }

    @Test
    fun `compilation for second hierarchy A generates correct code`() {
        val result = compile(getSourceFile("compilation", "hierarchy", "SealedClassHierarchy.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches(
            "SecondClassHierarchy.A_SealedEnum.kt",
            secondClassHierarchyAGenerated,
            result
        )
    }

    @Test
    fun `second hierarchy class C values`() {
        assertEquals(
            listOf(
                SecondClassHierarchy.A.C.D,
                SecondClassHierarchy.A.C.E,
                SecondClassHierarchy.A.C.F.G,
                SecondClassHierarchy.A.C.H.I
            ),
            SecondClassHierarchy.A.C.values
        )
    }

    @Test
    fun `second hierarchy class C ordinal of A_C_D`() {
        assertEquals(0, (SecondClassHierarchy.A.C.D as SecondClassHierarchy.A.C).ordinal)
    }

    @Test
    fun `second hierarchy class C ordinal of A_C_E`() {
        assertEquals(1, (SecondClassHierarchy.A.C.E as SecondClassHierarchy.A.C).ordinal)
    }

    @Test
    fun `second hierarchy class C ordinal of A_C_F_G`() {
        assertEquals(2, (SecondClassHierarchy.A.C.F.G as SecondClassHierarchy.A.C).ordinal)
    }

    @Test
    fun `second hierarchy class C ordinal of A_C_H_I`() {
        assertEquals(3, (SecondClassHierarchy.A.C.H.I as SecondClassHierarchy.A.C).ordinal)
    }

    @Test
    fun `compilation for second hierarchy C generates correct code`() {
        val result = compile(getSourceFile("compilation", "hierarchy", "SealedClassHierarchy.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches(
            "SecondClassHierarchy.A.C_SealedEnum.kt",
            secondClassHierarchyACGenerated,
            result
        )
    }

    @Test
    fun `second hierarchy class F values`() {
        assertEquals(
            listOf(SecondClassHierarchy.A.C.F.G),
            SecondClassHierarchy.A.C.F.values
        )
    }

    @Test
    fun `second hierarchy class F ordinal of A_L`() {
        assertEquals(0, (SecondClassHierarchy.A.C.F.G as SecondClassHierarchy.A.C.F).ordinal)
    }

    @Test
    fun `compilation for second hierarchy F generates correct code`() {
        val result = compile(getSourceFile("compilation", "hierarchy", "SealedClassHierarchy.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches(
            "SecondClassHierarchy.A.C.F_SealedEnum.kt",
            secondClassHierarchyACFGenerated,
            result
        )
    }

    @Test
    fun `second hierarchy class H values`() {
        assertEquals(
            listOf(SecondClassHierarchy.A.C.H.I),
            SecondClassHierarchy.A.C.H.values
        )
    }

    @Test
    fun `second hierarchy class H ordinal of A_C_H_I`() {
        assertEquals(0, (SecondClassHierarchy.A.C.H.I as SecondClassHierarchy.A.C.H).ordinal)
    }

    @Test
    fun `compilation for second hierarchy H generates correct code`() {
        val result = compile(getSourceFile("compilation", "hierarchy", "SealedClassHierarchy.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches(
            "SecondClassHierarchy.A.C.H_SealedEnum.kt",
            secondClassHierarchyACHGenerated,
            result
        )
    }

    @Test
    fun `second hierarchy class J values`() {
        assertEquals(
            listOf(SecondClassHierarchy.A.J.K),
            SecondClassHierarchy.A.J.values
        )
    }

    @Test
    fun `second hierarchy class J ordinal of A_J_K`() {
        assertEquals(0, (SecondClassHierarchy.A.J.K as SecondClassHierarchy.A.J).ordinal)
    }

    @Test
    fun `compilation for second hierarchy J generates correct code`() {
        val result = compile(getSourceFile("compilation", "hierarchy", "SealedClassHierarchy.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches(
            "SecondClassHierarchy.A.J_SealedEnum.kt",
            secondClassHierarchyAJGenerated,
            result
        )
    }
}
