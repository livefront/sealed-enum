package com.livefront.sealedenum.compilation.hierarchy

import kotlin.test.Test
import kotlin.test.assertEquals

class SealedInterfaceHierarchyTests {
    @Test
    fun first_hierarchy_class_A_values() {
        assertEquals(listOf(FirstInterfaceHierarchy.A.B.C), FirstInterfaceHierarchy.A.values)
    }

    @Test
    fun first_hierarchy_class_A_ordinal_of_A_B_C() {
        assertEquals(0, (FirstInterfaceHierarchy.A.B.C as FirstInterfaceHierarchy.A).ordinal)
    }

    @Test
    fun first_hierarchy_class_B_values() {
        assertEquals(listOf(FirstInterfaceHierarchy.A.B.C), FirstInterfaceHierarchy.A.B.values)
    }

    @Test
    fun first_hierarchy_class_B_ordinal() {
        assertEquals(0, (FirstInterfaceHierarchy.A.B.C as FirstInterfaceHierarchy.A.B).ordinal)
    }

    @Test
    fun second_hierarchy_class_A_values() {
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
    fun second_hierarchy_class_A_ordinal_of_A_B() {
        assertEquals(0, (SecondInterfaceHierarchy.A.B as SecondInterfaceHierarchy.A).ordinal)
    }

    @Test
    fun second_hierarchy_class_A_ordinal_of_A_C_D() {
        assertEquals(1, (SecondInterfaceHierarchy.A.C.D as SecondInterfaceHierarchy.A).ordinal)
    }

    @Test
    fun second_hierarchy_class_A_ordinal_of_A_C_E() {
        assertEquals(2, (SecondInterfaceHierarchy.A.C.E as SecondInterfaceHierarchy.A).ordinal)
    }

    @Test
    fun second_hierarchy_class_A_ordinal_of_A_C_F_G() {
        assertEquals(3, (SecondInterfaceHierarchy.A.C.F.G as SecondInterfaceHierarchy.A).ordinal)
    }

    @Test
    fun second_hierarchy_class_A_ordinal_of_A_C_H_I() {
        assertEquals(4, (SecondInterfaceHierarchy.A.C.H.I as SecondInterfaceHierarchy.A).ordinal)
    }

    @Test
    fun second_hierarchy_class_A_ordinal_of_A_J_K() {
        assertEquals(5, (SecondInterfaceHierarchy.A.J.K as SecondInterfaceHierarchy.A).ordinal)
    }

    @Test
    fun second_hierarchy_class_A_ordinal_of_A_L() {
        assertEquals(6, (SecondInterfaceHierarchy.A.L as SecondInterfaceHierarchy.A).ordinal)
    }

    @Test
    fun second_hierarchy_class_C_values() {
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
    fun second_hierarchy_class_C_ordinal_of_A_C_D() {
        assertEquals(0, (SecondInterfaceHierarchy.A.C.D as SecondInterfaceHierarchy.A.C).ordinal)
    }

    @Test
    fun second_hierarchy_class_C_ordinal_of_A_C_E() {
        assertEquals(1, (SecondInterfaceHierarchy.A.C.E as SecondInterfaceHierarchy.A.C).ordinal)
    }

    @Test
    fun second_hierarchy_class_C_ordinal_of_A_C_F_G() {
        assertEquals(2, (SecondInterfaceHierarchy.A.C.F.G as SecondInterfaceHierarchy.A.C).ordinal)
    }

    @Test
    fun second_hierarchy_class_C_ordinal_of_A_C_H_I() {
        assertEquals(3, (SecondInterfaceHierarchy.A.C.H.I as SecondInterfaceHierarchy.A.C).ordinal)
    }

    @Test
    fun second_hierarchy_class_F_values() {
        assertEquals(
            listOf(SecondInterfaceHierarchy.A.C.F.G),
            SecondInterfaceHierarchy.A.C.F.values
        )
    }

    @Test
    fun second_hierarchy_class_F_ordinal_of_A_L() {
        assertEquals(0, (SecondInterfaceHierarchy.A.C.F.G as SecondInterfaceHierarchy.A.C.F).ordinal)
    }

    @Test
    fun second_hierarchy_class_H_values() {
        assertEquals(
            listOf(SecondInterfaceHierarchy.A.C.H.I),
            SecondInterfaceHierarchy.A.C.H.values
        )
    }

    @Test
    fun second_hierarchy_class_H_ordinal_of_A_C_H_I() {
        assertEquals(0, (SecondInterfaceHierarchy.A.C.H.I as SecondInterfaceHierarchy.A.C.H).ordinal)
    }

    @Test
    fun second_hierarchy_class_J_values() {
        assertEquals(
            listOf(SecondInterfaceHierarchy.A.J.K),
            SecondInterfaceHierarchy.A.J.values
        )
    }

    @Test
    fun second_hierarchy_class_J_ordinal_of_A_J_K() {
        assertEquals(0, (SecondInterfaceHierarchy.A.J.K as SecondInterfaceHierarchy.A.J).ordinal)
    }
}
