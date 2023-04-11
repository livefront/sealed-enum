@file:Suppress("MatchingDeclarationName", "Filename")

package com.livefront.sealedenum.compilation.traversal

import com.livefront.sealedenum.GenSealedEnum
import com.livefront.sealedenum.TreeTraversalOrder

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

    @GenSealedEnum(traversalOrder = TreeTraversalOrder.PRE_ORDER, generateEnum = true)
    @GenSealedEnum(traversalOrder = TreeTraversalOrder.IN_ORDER, generateEnum = true)
    @GenSealedEnum(traversalOrder = TreeTraversalOrder.POST_ORDER, generateEnum = true)
    @GenSealedEnum(traversalOrder = TreeTraversalOrder.LEVEL_ORDER, generateEnum = true)
    companion object
}
