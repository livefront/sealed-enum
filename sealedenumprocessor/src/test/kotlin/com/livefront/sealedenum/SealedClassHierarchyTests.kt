package com.livefront.sealedenum

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FirstHierarchy {

    @GenSealedEnum
    sealed class A {

        @GenSealedEnum
        sealed class B : A() {
            object C : B()
        }
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
            }

            sealed class H : C() {
                object I : H()
            }
        }

        sealed class J : A() {

            object K : J()
        }

        object L : A()
    }
}

class SealedClassHierarchyTests {

    @Test
    fun `first hierarchy class A values`() {
        assertEquals(listOf(FirstHierarchy.A.B.C), FirstHierarchy_ASealedEnum.values)
    }

    @Test
    fun `first hierarchy class A ordinal of A_B_C`() {
        assertEquals(0, FirstHierarchy_ASealedEnum.ordinalOf(FirstHierarchy.A.B.C))
    }

    @Test
    fun `first hierarchy class B values`() {
        assertEquals(listOf(FirstHierarchy.A.B.C), FirstHierarchy_A_BSealedEnum.values)
    }

    @Test
    fun `first hierarchy class B ordinal`() {
        assertEquals(0, FirstHierarchy_A_BSealedEnum.ordinalOf(FirstHierarchy.A.B.C))
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
            SecondHierarchy_ASealedEnum.values
        )
    }

    @Test
    fun `second hierarchy class A ordinal of A_B`() {
        assertEquals(0, SecondHierarchy_ASealedEnum.ordinalOf(SecondHierarchy.A.B))
    }

    @Test
    fun `second hierarchy class A ordinal of A_C_D`() {
        assertEquals(1, SecondHierarchy_ASealedEnum.ordinalOf(SecondHierarchy.A.C.D))
    }

    @Test
    fun `second hierarchy class A ordinal of A_C_E`() {
        assertEquals(2, SecondHierarchy_ASealedEnum.ordinalOf(SecondHierarchy.A.C.E))
    }

    @Test
    fun `second hierarchy class A ordinal of A_C_F_G`() {
        assertEquals(3, SecondHierarchy_ASealedEnum.ordinalOf(SecondHierarchy.A.C.F.G))
    }

    @Test
    fun `second hierarchy class A ordinal of A_C_H_I`() {
        assertEquals(4, SecondHierarchy_ASealedEnum.ordinalOf(SecondHierarchy.A.C.H.I))
    }

    @Test
    fun `second hierarchy class A ordinal of A_J_K`() {
        assertEquals(5, SecondHierarchy_ASealedEnum.ordinalOf(SecondHierarchy.A.J.K))
    }

    @Test
    fun `second hierarchy class A ordinal of A_L`() {
        assertEquals(6, SecondHierarchy_ASealedEnum.ordinalOf(SecondHierarchy.A.L))
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
            SecondHierarchy_A_CSealedEnum.values
        )
    }

    @Test
    fun `second hierarchy class C ordinal of A_C_D`() {
        assertEquals(0, SecondHierarchy_A_CSealedEnum.ordinalOf(SecondHierarchy.A.C.D))
    }

    @Test
    fun `second hierarchy class C ordinal of A_C_E`() {
        assertEquals(1, SecondHierarchy_A_CSealedEnum.ordinalOf(SecondHierarchy.A.C.E))
    }

    @Test
    fun `second hierarchy class C ordinal of A_C_F_G`() {
        assertEquals(2, SecondHierarchy_A_CSealedEnum.ordinalOf(SecondHierarchy.A.C.F.G))
    }

    @Test
    fun `second hierarchy class C ordinal of A_C_H_I`() {
        assertEquals(3, SecondHierarchy_A_CSealedEnum.ordinalOf(SecondHierarchy.A.C.H.I))
    }

    @Test
    fun `second hierarchy class F values`() {
        assertEquals(
            listOf(SecondHierarchy.A.C.F.G),
            SecondHierarchy_A_C_FSealedEnum.values
        )
    }

    @Test
    fun `second hierarchy class F ordinal of A_L`() {
        assertEquals(0, SecondHierarchy_A_C_FSealedEnum.ordinalOf(SecondHierarchy.A.C.F.G))
    }

    @Test
    fun `second hierarchy class H values`() {
        assertEquals(
            listOf(SecondHierarchy.A.C.H.I),
            SecondHierarchy_A_C_HSealedEnum.values
        )
    }

    @Test
    fun `second hierarchy class H ordinal of A_C_H_I`() {
        assertEquals(0, SecondHierarchy_A_C_HSealedEnum.ordinalOf(SecondHierarchy.A.C.H.I))
    }

    @Test
    fun `second hierarchy class J values`() {
        assertEquals(
            listOf(SecondHierarchy.A.J.K),
            SecondHierarchy_A_JSealedEnum.values
        )
    }

    @Test
    fun `second hierarchy class J ordinal of A_J_K`() {
        assertEquals(0, SecondHierarchy_A_JSealedEnum.ordinalOf(SecondHierarchy.A.J.K))
    }
}
