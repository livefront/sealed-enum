package com.livefront.sealedenum.compilation.traversal

import com.livefront.sealedenum.testing.SealedEnumApprovalsExtension
import com.livefront.sealedenum.testing.assertApprovedGeneratedFile
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getCommonSourceFile
import com.oneeyedmen.okeydoke.Approver
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(SealedEnumApprovalsExtension::class)
class TraversalOrderTests {

    @Test
    fun `pre order objects`() {
        assertEquals(
            listOf(
                Tree.A,
                Tree.K,
                Tree.T,
                Tree.B.C.D,
                Tree.B.C.E,
                Tree.B.C.J,
                Tree.B.C.F.G,
                Tree.B.C.F.H,
                Tree.B.C.F.I,
                Tree.L.S,
                Tree.L.M.N,
                Tree.L.M.O,
                Tree.L.P.Q,
                Tree.L.P.R
            ),
            Tree.preOrderValues
        )
    }

    @Test
    fun `pre order objects in sealed enum`() {
        assertEquals(
            Tree.preOrderValues,
            TreePreOrderSealedEnum.values
        )
    }

    @Test
    fun `pre order enum`() {
        assertEquals(
            listOf(
                TreePreOrderEnum.Tree_A,
                TreePreOrderEnum.Tree_K,
                TreePreOrderEnum.Tree_T,
                TreePreOrderEnum.Tree_B_C_D,
                TreePreOrderEnum.Tree_B_C_E,
                TreePreOrderEnum.Tree_B_C_J,
                TreePreOrderEnum.Tree_B_C_F_G,
                TreePreOrderEnum.Tree_B_C_F_H,
                TreePreOrderEnum.Tree_B_C_F_I,
                TreePreOrderEnum.Tree_L_S,
                TreePreOrderEnum.Tree_L_M_N,
                TreePreOrderEnum.Tree_L_M_O,
                TreePreOrderEnum.Tree_L_P_Q,
                TreePreOrderEnum.Tree_L_P_R
            ),
            enumValues<TreePreOrderEnum>().toList()
        )
    }

    @Test
    fun `pre order enum mapping`() {
        assertEquals(
            Tree.preOrderValues.map(Tree::preOrderEnum),
            enumValues<TreePreOrderEnum>().toList()
        )
    }

    @Test
    fun `in order objects`() {
        assertEquals(
            listOf(
                Tree.A,
                Tree.B.C.D,
                Tree.B.C.E,
                Tree.B.C.F.G,
                Tree.B.C.F.H,
                Tree.B.C.F.I,
                Tree.B.C.J,
                Tree.K,
                Tree.L.M.N,
                Tree.L.M.O,
                Tree.L.P.Q,
                Tree.L.P.R,
                Tree.L.S,
                Tree.T
            ),
            TreeInOrderSealedEnum.values
        )
    }

    @Test
    fun `in order objects in sealed enum`() {
        assertEquals(
            Tree.inOrderValues,
            TreeInOrderSealedEnum.values
        )
    }

    @Test
    fun `in order enum`() {
        assertEquals(
            listOf(
                TreeInOrderEnum.Tree_A,
                TreeInOrderEnum.Tree_B_C_D,
                TreeInOrderEnum.Tree_B_C_E,
                TreeInOrderEnum.Tree_B_C_F_G,
                TreeInOrderEnum.Tree_B_C_F_H,
                TreeInOrderEnum.Tree_B_C_F_I,
                TreeInOrderEnum.Tree_B_C_J,
                TreeInOrderEnum.Tree_K,
                TreeInOrderEnum.Tree_L_M_N,
                TreeInOrderEnum.Tree_L_M_O,
                TreeInOrderEnum.Tree_L_P_Q,
                TreeInOrderEnum.Tree_L_P_R,
                TreeInOrderEnum.Tree_L_S,
                TreeInOrderEnum.Tree_T
            ),
            enumValues<TreeInOrderEnum>().toList()
        )
    }

    @Test
    fun `in order enum mapping`() {
        assertEquals(
            Tree.inOrderValues.map(Tree::inOrderEnum),
            enumValues<TreeInOrderEnum>().toList()
        )
    }

    @Test
    fun `post order objects`() {
        assertEquals(
            listOf(
                Tree.B.C.F.G,
                Tree.B.C.F.H,
                Tree.B.C.F.I,
                Tree.B.C.D,
                Tree.B.C.E,
                Tree.B.C.J,
                Tree.L.M.N,
                Tree.L.M.O,
                Tree.L.P.Q,
                Tree.L.P.R,
                Tree.L.S,
                Tree.A,
                Tree.K,
                Tree.T
            ),
            Tree.postOrderValues
        )
    }

    @Test
    fun `post order objects in sealed enum`() {
        assertEquals(
            Tree.postOrderValues,
            TreePostOrderSealedEnum.values
        )
    }

    @Test
    fun `post order enum`() {
        assertEquals(
            listOf(
                TreePostOrderEnum.Tree_B_C_F_G,
                TreePostOrderEnum.Tree_B_C_F_H,
                TreePostOrderEnum.Tree_B_C_F_I,
                TreePostOrderEnum.Tree_B_C_D,
                TreePostOrderEnum.Tree_B_C_E,
                TreePostOrderEnum.Tree_B_C_J,
                TreePostOrderEnum.Tree_L_M_N,
                TreePostOrderEnum.Tree_L_M_O,
                TreePostOrderEnum.Tree_L_P_Q,
                TreePostOrderEnum.Tree_L_P_R,
                TreePostOrderEnum.Tree_L_S,
                TreePostOrderEnum.Tree_A,
                TreePostOrderEnum.Tree_K,
                TreePostOrderEnum.Tree_T
            ),
            enumValues<TreePostOrderEnum>().toList()
        )
    }

    @Test
    fun `post order enum mapping`() {
        assertEquals(
            Tree.postOrderValues.map(Tree::postOrderEnum),
            enumValues<TreePostOrderEnum>().toList()
        )
    }

    @Test
    fun `level order objects`() {
        assertEquals(
            listOf(
                Tree.A,
                Tree.K,
                Tree.T,
                Tree.L.S,
                Tree.B.C.D,
                Tree.B.C.E,
                Tree.B.C.J,
                Tree.L.M.N,
                Tree.L.M.O,
                Tree.L.P.Q,
                Tree.L.P.R,
                Tree.B.C.F.G,
                Tree.B.C.F.H,
                Tree.B.C.F.I
            ),
            Tree.levelOrderValues
        )
    }

    @Test
    fun `level order objects in sealed enum`() {
        assertEquals(
            Tree.levelOrderValues,
            TreeLevelOrderSealedEnum.values
        )
    }

    @Test
    fun `level order enum`() {
        assertEquals(
            listOf(
                TreeLevelOrderEnum.Tree_A,
                TreeLevelOrderEnum.Tree_K,
                TreeLevelOrderEnum.Tree_T,
                TreeLevelOrderEnum.Tree_L_S,
                TreeLevelOrderEnum.Tree_B_C_D,
                TreeLevelOrderEnum.Tree_B_C_E,
                TreeLevelOrderEnum.Tree_B_C_J,
                TreeLevelOrderEnum.Tree_L_M_N,
                TreeLevelOrderEnum.Tree_L_M_O,
                TreeLevelOrderEnum.Tree_L_P_Q,
                TreeLevelOrderEnum.Tree_L_P_R,
                TreeLevelOrderEnum.Tree_B_C_F_G,
                TreeLevelOrderEnum.Tree_B_C_F_H,
                TreeLevelOrderEnum.Tree_B_C_F_I
            ),
            enumValues<TreeLevelOrderEnum>().toList()
        )
    }

    @Test
    fun `level order enum with mapping`() {
        assertEquals(
            Tree.levelOrderValues.map(Tree::levelOrderEnum),
            enumValues<TreeLevelOrderEnum>().toList()
        )
    }

    @Test
    fun Approver.`compilation generates correct code`() {
        val result = compile(getCommonSourceFile("compilation", "traversal", "TraversalOrder.kt"))

        assertCompiles(result)
        assertApprovedGeneratedFile("Tree_SealedEnum.kt", result)
    }
}
