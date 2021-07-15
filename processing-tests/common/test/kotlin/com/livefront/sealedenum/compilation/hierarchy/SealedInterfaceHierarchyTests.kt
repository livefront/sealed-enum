package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SealedInterfaceHierarchyTests {

    @Test
    fun `first hierarchy class A values`() {
        assertEquals(listOf(FirstInterfaceHierarchy.A.B.C), FirstInterfaceHierarchy.A.values)
    }

    @Test
    fun `first hierarchy class A ordinal of A_B_C`() {
        assertEquals(0, (FirstInterfaceHierarchy.A.B.C as FirstInterfaceHierarchy.A).ordinal)
    }

    @Test
    fun `compilation for first hierarchy A generates correct code`() {
        val result = compile(getSourceFile("compilation", "hierarchy", "SealedInterfaceHierarchy.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches(
            "FirstInterfaceHierarchy.A_SealedEnum.kt",
            firstInterfaceHierarchyAGenerated,
            result
        )
    }

    @Test
    fun `first hierarchy class B values`() {
        assertEquals(listOf(FirstInterfaceHierarchy.A.B.C), FirstInterfaceHierarchy.A.B.values)
    }

    @Test
    fun `first hierarchy class B ordinal`() {
        assertEquals(0, (FirstInterfaceHierarchy.A.B.C as FirstInterfaceHierarchy.A.B).ordinal)
    }

    @Test
    fun `compilation for first hierarchy B generates correct code`() {
        val result = compile(getSourceFile("compilation", "hierarchy", "SealedInterfaceHierarchy.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches(
            "FirstInterfaceHierarchy.A.B_SealedEnum.kt",
            firstInterfaceHierarchyBGenerated,
            result
        )
    }

    @Test
    fun `second hierarchy class A values`() {
        assertEquals(
            listOf(
                SecondInterfaceHierarchy.A.B,
                SecondInterfaceHierarchy.A.C.D,
                SecondInterfaceHierarchy.A.C.E,
                SecondInterfaceHierarchy.A.C.F.G,
                SecondInterfaceHierarchy.A.C.H.I,
                SecondInterfaceHierarchy.A.J.K,
                SecondInterfaceHierarchy.A.L
            ),
            SecondInterfaceHierarchy.A.values
        )
    }

    @Test
    fun `second hierarchy class A ordinal of A_B`() {
        assertEquals(0, (SecondInterfaceHierarchy.A.B as SecondInterfaceHierarchy.A).ordinal)
    }

    @Test
    fun `second hierarchy class A ordinal of A_C_D`() {
        assertEquals(1, (SecondInterfaceHierarchy.A.C.D as SecondInterfaceHierarchy.A).ordinal)
    }

    @Test
    fun `second hierarchy class A ordinal of A_C_E`() {
        assertEquals(2, (SecondInterfaceHierarchy.A.C.E as SecondInterfaceHierarchy.A).ordinal)
    }

    @Test
    fun `second hierarchy class A ordinal of A_C_F_G`() {
        assertEquals(3, (SecondInterfaceHierarchy.A.C.F.G as SecondInterfaceHierarchy.A).ordinal)
    }

    @Test
    fun `second hierarchy class A ordinal of A_C_H_I`() {
        assertEquals(4, (SecondInterfaceHierarchy.A.C.H.I as SecondInterfaceHierarchy.A).ordinal)
    }

    @Test
    fun `second hierarchy class A ordinal of A_J_K`() {
        assertEquals(5, (SecondInterfaceHierarchy.A.J.K as SecondInterfaceHierarchy.A).ordinal)
    }

    @Test
    fun `second hierarchy class A ordinal of A_L`() {
        assertEquals(6, (SecondInterfaceHierarchy.A.L as SecondInterfaceHierarchy.A).ordinal)
    }

    @Test
    fun `compilation for second hierarchy A generates correct code`() {
        val result = compile(getSourceFile("compilation", "hierarchy", "SealedInterfaceHierarchy.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches(
            "SecondInterfaceHierarchy.A_SealedEnum.kt",
            secondInterfaceHierarchyAGenerated,
            result
        )
    }

    @Test
    fun `second hierarchy class C values`() {
        assertEquals(
            listOf(
                SecondInterfaceHierarchy.A.C.D,
                SecondInterfaceHierarchy.A.C.E,
                SecondInterfaceHierarchy.A.C.F.G,
                SecondInterfaceHierarchy.A.C.H.I
            ),
            SecondInterfaceHierarchy.A.C.values
        )
    }

    @Test
    fun `second hierarchy class C ordinal of A_C_D`() {
        assertEquals(0, (SecondInterfaceHierarchy.A.C.D as SecondInterfaceHierarchy.A.C).ordinal)
    }

    @Test
    fun `second hierarchy class C ordinal of A_C_E`() {
        assertEquals(1, (SecondInterfaceHierarchy.A.C.E as SecondInterfaceHierarchy.A.C).ordinal)
    }

    @Test
    fun `second hierarchy class C ordinal of A_C_F_G`() {
        assertEquals(2, (SecondInterfaceHierarchy.A.C.F.G as SecondInterfaceHierarchy.A.C).ordinal)
    }

    @Test
    fun `second hierarchy class C ordinal of A_C_H_I`() {
        assertEquals(3, (SecondInterfaceHierarchy.A.C.H.I as SecondInterfaceHierarchy.A.C).ordinal)
    }

    @Test
    fun `compilation for second hierarchy C generates correct code`() {
        val result = compile(getSourceFile("compilation", "hierarchy", "SealedInterfaceHierarchy.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches(
            "SecondInterfaceHierarchy.A.C_SealedEnum.kt",
            secondInterfaceHierarchyACGenerated,
            result
        )
    }

    @Test
    fun `second hierarchy class F values`() {
        assertEquals(
            listOf(SecondInterfaceHierarchy.A.C.F.G),
            SecondInterfaceHierarchy.A.C.F.values
        )
    }

    @Test
    fun `second hierarchy class F ordinal of A_L`() {
        assertEquals(0, (SecondInterfaceHierarchy.A.C.F.G as SecondInterfaceHierarchy.A.C.F).ordinal)
    }

    @Test
    fun `compilation for second hierarchy F generates correct code`() {
        val result = compile(getSourceFile("compilation", "hierarchy", "SealedInterfaceHierarchy.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches(
            "SecondInterfaceHierarchy.A.C.F_SealedEnum.kt",
            secondInterfaceHierarchyACFGenerated,
            result
        )
    }

    @Test
    fun `second hierarchy class H values`() {
        assertEquals(
            listOf(SecondInterfaceHierarchy.A.C.H.I),
            SecondInterfaceHierarchy.A.C.H.values
        )
    }

    @Test
    fun `second hierarchy class H ordinal of A_C_H_I`() {
        assertEquals(0, (SecondInterfaceHierarchy.A.C.H.I as SecondInterfaceHierarchy.A.C.H).ordinal)
    }

    @Test
    fun `compilation for second hierarchy H generates correct code`() {
        val result = compile(getSourceFile("compilation", "hierarchy", "SealedInterfaceHierarchy.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches(
            "SecondInterfaceHierarchy.A.C.H_SealedEnum.kt",
            secondInterfaceHierarchyACHGenerated,
            result
        )
    }

    @Test
    fun `second hierarchy class J values`() {
        assertEquals(
            listOf(SecondInterfaceHierarchy.A.J.K),
            SecondInterfaceHierarchy.A.J.values
        )
    }

    @Test
    fun `second hierarchy class J ordinal of A_J_K`() {
        assertEquals(0, (SecondInterfaceHierarchy.A.J.K as SecondInterfaceHierarchy.A.J).ordinal)
    }

    @Test
    fun `compilation for second hierarchy J generates correct code`() {
        val result = compile(getSourceFile("compilation", "hierarchy", "SealedInterfaceHierarchy.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches(
            "SecondInterfaceHierarchy.A.J_SealedEnum.kt",
            secondInterfaceHierarchyAJGenerated,
            result
        )
    }
}
