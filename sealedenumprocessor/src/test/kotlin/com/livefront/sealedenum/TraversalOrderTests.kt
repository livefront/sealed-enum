package com.livefront.sealedenum

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@GenSealedEnum(traversalOrder = TreeTraversalOrder.PRE_ORDER)
@GenSealedEnum(traversalOrder = TreeTraversalOrder.IN_ORDER)
@GenSealedEnum(traversalOrder = TreeTraversalOrder.POST_ORDER)
@GenSealedEnum(traversalOrder = TreeTraversalOrder.LEVEL_ORDER)
sealed class Tree {

    object A : Tree()

    sealed class B : Tree() {

        sealed class C : B() {

            object D : C()

            object E : C()

            sealed class F : C() {

                object G : F()

                object H : F()

                object I : F()
            }

            object J : C()
        }
    }

    object K : Tree()

    sealed class L : Tree() {

        sealed class M : L() {

            object N : M()

            object O : M()
        }

        sealed class P : L() {

            object Q : P()

            object R : P()
        }

        object S : L()
    }

    object T : Tree()
}

class TraversalOrderTests {

    @Test
    fun `pre order`() {
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
            TreePreOrderSealedEnum.values
        )
    }


    @Test
    fun `in order`() {
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
    fun `post order`() {
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
            TreePostOrderSealedEnum.values
        )
    }


    @Test
    fun `level order`() {
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
            TreeLevelOrderSealedEnum.values
        )
    }
}
