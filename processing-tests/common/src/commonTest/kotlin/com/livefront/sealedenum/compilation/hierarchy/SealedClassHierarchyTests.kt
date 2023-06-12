package com.livefront.sealedenum.compilation.hierarchy

import kotlin.test.Test
import kotlin.test.assertEquals

class SealedClassHierarchyTests {
    @Test
    fun first_hierarchy_class_A_values() {
        assertEquals(listOf(FirstClassHierarchy.A.B.C), FirstClassHierarchy.A.values)
    }

    @Test
    fun first_hierarchy_class_A_ordinal_of_A_B_C() {
        assertEquals(0, (FirstClassHierarchy.A.B.C as FirstClassHierarchy.A).ordinal)
    }

    @Test
    fun first_hierarchy_class_B_values() {
        assertEquals(listOf(FirstClassHierarchy.A.B.C), FirstClassHierarchy.A.B.values)
    }

    @Test
    fun first_hierarchy_class_B_ordinal() {
        assertEquals(0, (FirstClassHierarchy.A.B.C as FirstClassHierarchy.A.B).ordinal)
    }

    @Test
    fun second_hierarchy_class_Z_values() {
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
    fun second_hierarchy_class_Z_ordinal_of_Z_Y() {
        assertEquals(0, (SecondClassHierarchy.Z.Y as SecondClassHierarchy.Z).ordinal)
    }

    @Test
    fun second_hierarchy_class_Z_ordinal_of_Z_X_W() {
        assertEquals(1, (SecondClassHierarchy.Z.X.W as SecondClassHierarchy.Z).ordinal)
    }

    @Test
    fun second_hierarchy_class_Z_ordinal_of_Z_X_V() {
        assertEquals(2, (SecondClassHierarchy.Z.X.V as SecondClassHierarchy.Z).ordinal)
    }

    @Test
    fun second_hierarchy_class_Z_ordinal_of_Z_X_U_T() {
        assertEquals(3, (SecondClassHierarchy.Z.X.U.T as SecondClassHierarchy.Z).ordinal)
    }

    @Test
    fun second_hierarchy_class_Z_ordinal_of_Z_X_S_R() {
        assertEquals(4, (SecondClassHierarchy.Z.X.S.R as SecondClassHierarchy.Z).ordinal)
    }

    @Test
    fun second_hierarchy_class_Z_ordinal_of_Z_Q_P() {
        assertEquals(5, (SecondClassHierarchy.Z.Q.P as SecondClassHierarchy.Z).ordinal)
    }

    @Test
    fun second_hierarchy_class_Z_ordinal_of_Z_O() {
        assertEquals(6, (SecondClassHierarchy.Z.O as SecondClassHierarchy.Z).ordinal)
    }

    @Test
    fun second_hierarchy_class_X_values() {
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
    fun second_hierarchy_class_X_ordinal_of_Z_X_W() {
        assertEquals(0, (SecondClassHierarchy.Z.X.W as SecondClassHierarchy.Z.X).ordinal)
    }

    @Test
    fun second_hierarchy_class_X_ordinal_of_Z_X_V() {
        assertEquals(1, (SecondClassHierarchy.Z.X.V as SecondClassHierarchy.Z.X).ordinal)
    }

    @Test
    fun second_hierarchy_class_X_ordinal_of_Z_X_U_T() {
        assertEquals(2, (SecondClassHierarchy.Z.X.U.T as SecondClassHierarchy.Z.X).ordinal)
    }

    @Test
    fun second_hierarchy_class_X_ordinal_of_Z_X_S_R() {
        assertEquals(3, (SecondClassHierarchy.Z.X.S.R as SecondClassHierarchy.Z.X).ordinal)
    }

    @Test
    fun second_hierarchy_class_U_values() {
        assertEquals(
            listOf(SecondClassHierarchy.Z.X.U.T),
            SecondClassHierarchy.Z.X.U.values
        )
    }

    @Test
    fun second_hierarchy_class_U_ordinal_of_Z_X_U_T() {
        assertEquals(0, (SecondClassHierarchy.Z.X.U.T as SecondClassHierarchy.Z.X.U).ordinal)
    }

    @Test
    fun second_hierarchy_class_S_values() {
        assertEquals(
            listOf(SecondClassHierarchy.Z.X.S.R),
            SecondClassHierarchy.Z.X.S.values
        )
    }

    @Test
    fun second_hierarchy_class_S_ordinal_of_Z_X_S_R() {
        assertEquals(0, (SecondClassHierarchy.Z.X.S.R as SecondClassHierarchy.Z.X.S).ordinal)
    }

    @Test
    fun second_hierarchy_class_Q_values() {
        assertEquals(
            listOf(SecondClassHierarchy.Z.Q.P),
            SecondClassHierarchy.Z.Q.values
        )
    }

    @Test
    fun second_hierarchy_class_Q_ordinal_of_Z_Q_P() {
        assertEquals(0, (SecondClassHierarchy.Z.Q.P as SecondClassHierarchy.Z.Q).ordinal)
    }
}
