package com.livefront.sealedenum

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FirstHierarchy {

    sealed class A {

        sealed class B : A() {
            object C : B()

            @GenSealedEnum(generateEnum = true)
            companion object
        }

        @GenSealedEnum(generateEnum = true)
        companion object
    }
}

class SecondHierarchy {

    sealed class A {

        object B : A()

        sealed class C : A() {

            object D : C()

            object E : C()

            sealed class F : C() {
                object G : F()

                @GenSealedEnum
                companion object
            }

            sealed class H : C() {
                object I : H()

                @GenSealedEnum
                companion object
            }

            @GenSealedEnum
            companion object
        }

        sealed class J : A() {

            object K : J()

            @GenSealedEnum
            companion object
        }

        object L : A()

        @GenSealedEnum
        companion object
    }
}

class SealedClassHierarchyTests {

    @Test
    fun `first hierarchy class A values`() {
        assertEquals(listOf(FirstHierarchy.A.B.C), FirstHierarchy.A.values)
    }

    @Test
    fun `first hierarchy class A ordinal of A_B_C`() {
        assertEquals(0, (FirstHierarchy.A.B.C as FirstHierarchy.A).ordinal)
    }

    @Test
    fun `first hierarchy class B values`() {
        assertEquals(listOf(FirstHierarchy.A.B.C), FirstHierarchy.A.B.values)
    }

    @Test
    fun `first hierarchy class B ordinal`() {
        assertEquals(0, (FirstHierarchy.A.B.C as FirstHierarchy.A.B).ordinal)
    }

    @Test
    fun `second hierarchy class A values`() {
        assertEquals(
            listOf(
                SecondHierarchy.A.B,
                SecondHierarchy.A.C.D,
                SecondHierarchy.A.C.E,
                SecondHierarchy.A.C.F.G,
                SecondHierarchy.A.C.H.I,
                SecondHierarchy.A.J.K,
                SecondHierarchy.A.L
            ),
            SecondHierarchy.A.values
        )
    }

    @Test
    fun `second hierarchy class A ordinal of A_B`() {
        assertEquals(0, (SecondHierarchy.A.B as SecondHierarchy.A).ordinal)
    }

    @Test
    fun `second hierarchy class A ordinal of A_C_D`() {
        assertEquals(1, (SecondHierarchy.A.C.D as SecondHierarchy.A).ordinal)
    }

    @Test
    fun `second hierarchy class A ordinal of A_C_E`() {
        assertEquals(2, (SecondHierarchy.A.C.E as SecondHierarchy.A).ordinal)
    }

    @Test
    fun `second hierarchy class A ordinal of A_C_F_G`() {
        assertEquals(3, (SecondHierarchy.A.C.F.G as SecondHierarchy.A).ordinal)
    }

    @Test
    fun `second hierarchy class A ordinal of A_C_H_I`() {
        assertEquals(4, (SecondHierarchy.A.C.H.I as SecondHierarchy.A).ordinal)
    }

    @Test
    fun `second hierarchy class A ordinal of A_J_K`() {
        assertEquals(5, (SecondHierarchy.A.J.K as SecondHierarchy.A).ordinal)
    }

    @Test
    fun `second hierarchy class A ordinal of A_L`() {
        assertEquals(6, (SecondHierarchy.A.L as SecondHierarchy.A).ordinal)
    }

    @Test
    fun `second hierarchy class C values`() {
        assertEquals(
            listOf(
                SecondHierarchy.A.C.D,
                SecondHierarchy.A.C.E,
                SecondHierarchy.A.C.F.G,
                SecondHierarchy.A.C.H.I
            ),
            SecondHierarchy.A.C.values
        )
    }

    @Test
    fun `second hierarchy class C ordinal of A_C_D`() {
        assertEquals(0, (SecondHierarchy.A.C.D as SecondHierarchy.A.C).ordinal)
    }

    @Test
    fun `second hierarchy class C ordinal of A_C_E`() {
        assertEquals(1, (SecondHierarchy.A.C.E as SecondHierarchy.A.C).ordinal)
    }

    @Test
    fun `second hierarchy class C ordinal of A_C_F_G`() {
        assertEquals(2, (SecondHierarchy.A.C.F.G as SecondHierarchy.A.C).ordinal)
    }

    @Test
    fun `second hierarchy class C ordinal of A_C_H_I`() {
        assertEquals(3, (SecondHierarchy.A.C.H.I as SecondHierarchy.A.C).ordinal)
    }

    @Test
    fun `second hierarchy class F values`() {
        assertEquals(
            listOf(SecondHierarchy.A.C.F.G),
            SecondHierarchy.A.C.F.values
        )
    }

    @Test
    fun `second hierarchy class F ordinal of A_L`() {
        assertEquals(0, (SecondHierarchy.A.C.F.G as SecondHierarchy.A.C.F).ordinal)
    }

    @Test
    fun `second hierarchy class H values`() {
        assertEquals(
            listOf(SecondHierarchy.A.C.H.I),
            SecondHierarchy.A.C.H.values
        )
    }

    @Test
    fun `second hierarchy class H ordinal of A_C_H_I`() {
        assertEquals(0, (SecondHierarchy.A.C.H.I as SecondHierarchy.A.C.H).ordinal)
    }

    @Test
    fun `second hierarchy class J values`() {
        assertEquals(
            listOf(SecondHierarchy.A.J.K),
            SecondHierarchy.A.J.values
        )
    }

    @Test
    fun `second hierarchy class J ordinal of A_J_K`() {
        assertEquals(0, (SecondHierarchy.A.J.K as SecondHierarchy.A.J).ordinal)
    }
}
