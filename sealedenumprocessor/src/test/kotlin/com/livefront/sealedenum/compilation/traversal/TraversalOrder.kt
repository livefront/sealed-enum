package com.livefront.sealedenum.compilation.traversal

import com.livefront.sealedenum.GenSealedEnum
import com.livefront.sealedenum.TreeTraversalOrder
import org.intellij.lang.annotations.Language

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

@Language("kotlin")
val treeGenerated = """
package com.livefront.sealedenum.compilation.traversal

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [Tree]
 */
enum class TreeLevelOrderEnum {
    Tree_A,

    Tree_K,

    Tree_T,

    Tree_L_S,

    Tree_B_C_D,

    Tree_B_C_E,

    Tree_B_C_J,

    Tree_L_M_N,

    Tree_L_M_O,

    Tree_L_P_Q,

    Tree_L_P_R,

    Tree_B_C_F_G,

    Tree_B_C_F_H,

    Tree_B_C_F_I
}

/**
 * The isomorphic [TreeLevelOrderEnum] for [this].
 */
val Tree.levelOrderEnum: TreeLevelOrderEnum
    get() = TreeLevelOrderSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [Tree] for [this].
 */
val TreeLevelOrderEnum.sealedObject: Tree
    get() = TreeLevelOrderSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [Tree]
 */
object TreeLevelOrderSealedEnum : SealedEnum<Tree>, SealedEnumWithEnumProvider<Tree,
        TreeLevelOrderEnum>, EnumForSealedEnumProvider<Tree, TreeLevelOrderEnum> {
    override val values: List<Tree> = listOf(
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
    )


    override val enumClass: Class<TreeLevelOrderEnum>
        get() = TreeLevelOrderEnum::class.java

    override fun ordinalOf(obj: Tree): Int = when (obj) {
        Tree.A -> 0
        Tree.K -> 1
        Tree.T -> 2
        Tree.L.S -> 3
        Tree.B.C.D -> 4
        Tree.B.C.E -> 5
        Tree.B.C.J -> 6
        Tree.L.M.N -> 7
        Tree.L.M.O -> 8
        Tree.L.P.Q -> 9
        Tree.L.P.R -> 10
        Tree.B.C.F.G -> 11
        Tree.B.C.F.H -> 12
        Tree.B.C.F.I -> 13
    }

    override fun nameOf(obj: Tree): String = when (obj) {
        Tree.A -> "Tree_A"
        Tree.K -> "Tree_K"
        Tree.T -> "Tree_T"
        Tree.L.S -> "Tree_L_S"
        Tree.B.C.D -> "Tree_B_C_D"
        Tree.B.C.E -> "Tree_B_C_E"
        Tree.B.C.J -> "Tree_B_C_J"
        Tree.L.M.N -> "Tree_L_M_N"
        Tree.L.M.O -> "Tree_L_M_O"
        Tree.L.P.Q -> "Tree_L_P_Q"
        Tree.L.P.R -> "Tree_L_P_R"
        Tree.B.C.F.G -> "Tree_B_C_F_G"
        Tree.B.C.F.H -> "Tree_B_C_F_H"
        Tree.B.C.F.I -> "Tree_B_C_F_I"
    }

    override fun valueOf(name: String): Tree = when (name) {
        "Tree_A" -> Tree.A
        "Tree_K" -> Tree.K
        "Tree_T" -> Tree.T
        "Tree_L_S" -> Tree.L.S
        "Tree_B_C_D" -> Tree.B.C.D
        "Tree_B_C_E" -> Tree.B.C.E
        "Tree_B_C_J" -> Tree.B.C.J
        "Tree_L_M_N" -> Tree.L.M.N
        "Tree_L_M_O" -> Tree.L.M.O
        "Tree_L_P_Q" -> Tree.L.P.Q
        "Tree_L_P_R" -> Tree.L.P.R
        "Tree_B_C_F_G" -> Tree.B.C.F.G
        "Tree_B_C_F_H" -> Tree.B.C.F.H
        "Tree_B_C_F_I" -> Tree.B.C.F.I
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    override fun sealedObjectToEnum(obj: Tree): TreeLevelOrderEnum = when (obj) {
        Tree.A -> TreeLevelOrderEnum.Tree_A
        Tree.K -> TreeLevelOrderEnum.Tree_K
        Tree.T -> TreeLevelOrderEnum.Tree_T
        Tree.L.S -> TreeLevelOrderEnum.Tree_L_S
        Tree.B.C.D -> TreeLevelOrderEnum.Tree_B_C_D
        Tree.B.C.E -> TreeLevelOrderEnum.Tree_B_C_E
        Tree.B.C.J -> TreeLevelOrderEnum.Tree_B_C_J
        Tree.L.M.N -> TreeLevelOrderEnum.Tree_L_M_N
        Tree.L.M.O -> TreeLevelOrderEnum.Tree_L_M_O
        Tree.L.P.Q -> TreeLevelOrderEnum.Tree_L_P_Q
        Tree.L.P.R -> TreeLevelOrderEnum.Tree_L_P_R
        Tree.B.C.F.G -> TreeLevelOrderEnum.Tree_B_C_F_G
        Tree.B.C.F.H -> TreeLevelOrderEnum.Tree_B_C_F_H
        Tree.B.C.F.I -> TreeLevelOrderEnum.Tree_B_C_F_I
    }

    override fun enumToSealedObject(enum: TreeLevelOrderEnum): Tree = when (enum) {
        TreeLevelOrderEnum.Tree_A -> Tree.A
        TreeLevelOrderEnum.Tree_K -> Tree.K
        TreeLevelOrderEnum.Tree_T -> Tree.T
        TreeLevelOrderEnum.Tree_L_S -> Tree.L.S
        TreeLevelOrderEnum.Tree_B_C_D -> Tree.B.C.D
        TreeLevelOrderEnum.Tree_B_C_E -> Tree.B.C.E
        TreeLevelOrderEnum.Tree_B_C_J -> Tree.B.C.J
        TreeLevelOrderEnum.Tree_L_M_N -> Tree.L.M.N
        TreeLevelOrderEnum.Tree_L_M_O -> Tree.L.M.O
        TreeLevelOrderEnum.Tree_L_P_Q -> Tree.L.P.Q
        TreeLevelOrderEnum.Tree_L_P_R -> Tree.L.P.R
        TreeLevelOrderEnum.Tree_B_C_F_G -> Tree.B.C.F.G
        TreeLevelOrderEnum.Tree_B_C_F_H -> Tree.B.C.F.H
        TreeLevelOrderEnum.Tree_B_C_F_I -> Tree.B.C.F.I
    }
}

/**
 * The index of [this] in the values list.
 */
val Tree.levelOrderOrdinal: Int
    get() = TreeLevelOrderSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val Tree.levelOrderName: String
    get() = TreeLevelOrderSealedEnum.nameOf(this)

/**
 * A list of all [Tree] objects.
 */
val Tree.Companion.levelOrderValues: List<Tree>
    get() = TreeLevelOrderSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [Tree]
 */
val Tree.Companion.levelOrderSealedEnum: TreeLevelOrderSealedEnum
    get() = TreeLevelOrderSealedEnum

/**
 * Returns the [Tree] object for the given [name].
 *
 * If the given name doesn't correspond to any [Tree], an [IllegalArgumentException] will be thrown.
 */
fun Tree.Companion.levelOrderValueOf(name: String): Tree = TreeLevelOrderSealedEnum.valueOf(name)

/**
 * An isomorphic enum for the sealed class [Tree]
 */
enum class TreePostOrderEnum {
    Tree_B_C_F_G,

    Tree_B_C_F_H,

    Tree_B_C_F_I,

    Tree_B_C_D,

    Tree_B_C_E,

    Tree_B_C_J,

    Tree_L_M_N,

    Tree_L_M_O,

    Tree_L_P_Q,

    Tree_L_P_R,

    Tree_L_S,

    Tree_A,

    Tree_K,

    Tree_T
}

/**
 * The isomorphic [TreePostOrderEnum] for [this].
 */
val Tree.postOrderEnum: TreePostOrderEnum
    get() = TreePostOrderSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [Tree] for [this].
 */
val TreePostOrderEnum.sealedObject: Tree
    get() = TreePostOrderSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [Tree]
 */
object TreePostOrderSealedEnum : SealedEnum<Tree>, SealedEnumWithEnumProvider<Tree,
        TreePostOrderEnum>, EnumForSealedEnumProvider<Tree, TreePostOrderEnum> {
    override val values: List<Tree> = listOf(
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
    )


    override val enumClass: Class<TreePostOrderEnum>
        get() = TreePostOrderEnum::class.java

    override fun ordinalOf(obj: Tree): Int = when (obj) {
        Tree.B.C.F.G -> 0
        Tree.B.C.F.H -> 1
        Tree.B.C.F.I -> 2
        Tree.B.C.D -> 3
        Tree.B.C.E -> 4
        Tree.B.C.J -> 5
        Tree.L.M.N -> 6
        Tree.L.M.O -> 7
        Tree.L.P.Q -> 8
        Tree.L.P.R -> 9
        Tree.L.S -> 10
        Tree.A -> 11
        Tree.K -> 12
        Tree.T -> 13
    }

    override fun nameOf(obj: Tree): String = when (obj) {
        Tree.B.C.F.G -> "Tree_B_C_F_G"
        Tree.B.C.F.H -> "Tree_B_C_F_H"
        Tree.B.C.F.I -> "Tree_B_C_F_I"
        Tree.B.C.D -> "Tree_B_C_D"
        Tree.B.C.E -> "Tree_B_C_E"
        Tree.B.C.J -> "Tree_B_C_J"
        Tree.L.M.N -> "Tree_L_M_N"
        Tree.L.M.O -> "Tree_L_M_O"
        Tree.L.P.Q -> "Tree_L_P_Q"
        Tree.L.P.R -> "Tree_L_P_R"
        Tree.L.S -> "Tree_L_S"
        Tree.A -> "Tree_A"
        Tree.K -> "Tree_K"
        Tree.T -> "Tree_T"
    }

    override fun valueOf(name: String): Tree = when (name) {
        "Tree_B_C_F_G" -> Tree.B.C.F.G
        "Tree_B_C_F_H" -> Tree.B.C.F.H
        "Tree_B_C_F_I" -> Tree.B.C.F.I
        "Tree_B_C_D" -> Tree.B.C.D
        "Tree_B_C_E" -> Tree.B.C.E
        "Tree_B_C_J" -> Tree.B.C.J
        "Tree_L_M_N" -> Tree.L.M.N
        "Tree_L_M_O" -> Tree.L.M.O
        "Tree_L_P_Q" -> Tree.L.P.Q
        "Tree_L_P_R" -> Tree.L.P.R
        "Tree_L_S" -> Tree.L.S
        "Tree_A" -> Tree.A
        "Tree_K" -> Tree.K
        "Tree_T" -> Tree.T
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    override fun sealedObjectToEnum(obj: Tree): TreePostOrderEnum = when (obj) {
        Tree.B.C.F.G -> TreePostOrderEnum.Tree_B_C_F_G
        Tree.B.C.F.H -> TreePostOrderEnum.Tree_B_C_F_H
        Tree.B.C.F.I -> TreePostOrderEnum.Tree_B_C_F_I
        Tree.B.C.D -> TreePostOrderEnum.Tree_B_C_D
        Tree.B.C.E -> TreePostOrderEnum.Tree_B_C_E
        Tree.B.C.J -> TreePostOrderEnum.Tree_B_C_J
        Tree.L.M.N -> TreePostOrderEnum.Tree_L_M_N
        Tree.L.M.O -> TreePostOrderEnum.Tree_L_M_O
        Tree.L.P.Q -> TreePostOrderEnum.Tree_L_P_Q
        Tree.L.P.R -> TreePostOrderEnum.Tree_L_P_R
        Tree.L.S -> TreePostOrderEnum.Tree_L_S
        Tree.A -> TreePostOrderEnum.Tree_A
        Tree.K -> TreePostOrderEnum.Tree_K
        Tree.T -> TreePostOrderEnum.Tree_T
    }

    override fun enumToSealedObject(enum: TreePostOrderEnum): Tree = when (enum) {
        TreePostOrderEnum.Tree_B_C_F_G -> Tree.B.C.F.G
        TreePostOrderEnum.Tree_B_C_F_H -> Tree.B.C.F.H
        TreePostOrderEnum.Tree_B_C_F_I -> Tree.B.C.F.I
        TreePostOrderEnum.Tree_B_C_D -> Tree.B.C.D
        TreePostOrderEnum.Tree_B_C_E -> Tree.B.C.E
        TreePostOrderEnum.Tree_B_C_J -> Tree.B.C.J
        TreePostOrderEnum.Tree_L_M_N -> Tree.L.M.N
        TreePostOrderEnum.Tree_L_M_O -> Tree.L.M.O
        TreePostOrderEnum.Tree_L_P_Q -> Tree.L.P.Q
        TreePostOrderEnum.Tree_L_P_R -> Tree.L.P.R
        TreePostOrderEnum.Tree_L_S -> Tree.L.S
        TreePostOrderEnum.Tree_A -> Tree.A
        TreePostOrderEnum.Tree_K -> Tree.K
        TreePostOrderEnum.Tree_T -> Tree.T
    }
}

/**
 * The index of [this] in the values list.
 */
val Tree.postOrderOrdinal: Int
    get() = TreePostOrderSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val Tree.postOrderName: String
    get() = TreePostOrderSealedEnum.nameOf(this)

/**
 * A list of all [Tree] objects.
 */
val Tree.Companion.postOrderValues: List<Tree>
    get() = TreePostOrderSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [Tree]
 */
val Tree.Companion.postOrderSealedEnum: TreePostOrderSealedEnum
    get() = TreePostOrderSealedEnum

/**
 * Returns the [Tree] object for the given [name].
 *
 * If the given name doesn't correspond to any [Tree], an [IllegalArgumentException] will be thrown.
 */
fun Tree.Companion.postOrderValueOf(name: String): Tree = TreePostOrderSealedEnum.valueOf(name)

/**
 * An isomorphic enum for the sealed class [Tree]
 */
enum class TreeInOrderEnum {
    Tree_A,

    Tree_B_C_D,

    Tree_B_C_E,

    Tree_B_C_F_G,

    Tree_B_C_F_H,

    Tree_B_C_F_I,

    Tree_B_C_J,

    Tree_K,

    Tree_L_M_N,

    Tree_L_M_O,

    Tree_L_P_Q,

    Tree_L_P_R,

    Tree_L_S,

    Tree_T
}

/**
 * The isomorphic [TreeInOrderEnum] for [this].
 */
val Tree.inOrderEnum: TreeInOrderEnum
    get() = TreeInOrderSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [Tree] for [this].
 */
val TreeInOrderEnum.sealedObject: Tree
    get() = TreeInOrderSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [Tree]
 */
object TreeInOrderSealedEnum : SealedEnum<Tree>, SealedEnumWithEnumProvider<Tree, TreeInOrderEnum>,
        EnumForSealedEnumProvider<Tree, TreeInOrderEnum> {
    override val values: List<Tree> = listOf(
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
    )


    override val enumClass: Class<TreeInOrderEnum>
        get() = TreeInOrderEnum::class.java

    override fun ordinalOf(obj: Tree): Int = when (obj) {
        Tree.A -> 0
        Tree.B.C.D -> 1
        Tree.B.C.E -> 2
        Tree.B.C.F.G -> 3
        Tree.B.C.F.H -> 4
        Tree.B.C.F.I -> 5
        Tree.B.C.J -> 6
        Tree.K -> 7
        Tree.L.M.N -> 8
        Tree.L.M.O -> 9
        Tree.L.P.Q -> 10
        Tree.L.P.R -> 11
        Tree.L.S -> 12
        Tree.T -> 13
    }

    override fun nameOf(obj: Tree): String = when (obj) {
        Tree.A -> "Tree_A"
        Tree.B.C.D -> "Tree_B_C_D"
        Tree.B.C.E -> "Tree_B_C_E"
        Tree.B.C.F.G -> "Tree_B_C_F_G"
        Tree.B.C.F.H -> "Tree_B_C_F_H"
        Tree.B.C.F.I -> "Tree_B_C_F_I"
        Tree.B.C.J -> "Tree_B_C_J"
        Tree.K -> "Tree_K"
        Tree.L.M.N -> "Tree_L_M_N"
        Tree.L.M.O -> "Tree_L_M_O"
        Tree.L.P.Q -> "Tree_L_P_Q"
        Tree.L.P.R -> "Tree_L_P_R"
        Tree.L.S -> "Tree_L_S"
        Tree.T -> "Tree_T"
    }

    override fun valueOf(name: String): Tree = when (name) {
        "Tree_A" -> Tree.A
        "Tree_B_C_D" -> Tree.B.C.D
        "Tree_B_C_E" -> Tree.B.C.E
        "Tree_B_C_F_G" -> Tree.B.C.F.G
        "Tree_B_C_F_H" -> Tree.B.C.F.H
        "Tree_B_C_F_I" -> Tree.B.C.F.I
        "Tree_B_C_J" -> Tree.B.C.J
        "Tree_K" -> Tree.K
        "Tree_L_M_N" -> Tree.L.M.N
        "Tree_L_M_O" -> Tree.L.M.O
        "Tree_L_P_Q" -> Tree.L.P.Q
        "Tree_L_P_R" -> Tree.L.P.R
        "Tree_L_S" -> Tree.L.S
        "Tree_T" -> Tree.T
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    override fun sealedObjectToEnum(obj: Tree): TreeInOrderEnum = when (obj) {
        Tree.A -> TreeInOrderEnum.Tree_A
        Tree.B.C.D -> TreeInOrderEnum.Tree_B_C_D
        Tree.B.C.E -> TreeInOrderEnum.Tree_B_C_E
        Tree.B.C.F.G -> TreeInOrderEnum.Tree_B_C_F_G
        Tree.B.C.F.H -> TreeInOrderEnum.Tree_B_C_F_H
        Tree.B.C.F.I -> TreeInOrderEnum.Tree_B_C_F_I
        Tree.B.C.J -> TreeInOrderEnum.Tree_B_C_J
        Tree.K -> TreeInOrderEnum.Tree_K
        Tree.L.M.N -> TreeInOrderEnum.Tree_L_M_N
        Tree.L.M.O -> TreeInOrderEnum.Tree_L_M_O
        Tree.L.P.Q -> TreeInOrderEnum.Tree_L_P_Q
        Tree.L.P.R -> TreeInOrderEnum.Tree_L_P_R
        Tree.L.S -> TreeInOrderEnum.Tree_L_S
        Tree.T -> TreeInOrderEnum.Tree_T
    }

    override fun enumToSealedObject(enum: TreeInOrderEnum): Tree = when (enum) {
        TreeInOrderEnum.Tree_A -> Tree.A
        TreeInOrderEnum.Tree_B_C_D -> Tree.B.C.D
        TreeInOrderEnum.Tree_B_C_E -> Tree.B.C.E
        TreeInOrderEnum.Tree_B_C_F_G -> Tree.B.C.F.G
        TreeInOrderEnum.Tree_B_C_F_H -> Tree.B.C.F.H
        TreeInOrderEnum.Tree_B_C_F_I -> Tree.B.C.F.I
        TreeInOrderEnum.Tree_B_C_J -> Tree.B.C.J
        TreeInOrderEnum.Tree_K -> Tree.K
        TreeInOrderEnum.Tree_L_M_N -> Tree.L.M.N
        TreeInOrderEnum.Tree_L_M_O -> Tree.L.M.O
        TreeInOrderEnum.Tree_L_P_Q -> Tree.L.P.Q
        TreeInOrderEnum.Tree_L_P_R -> Tree.L.P.R
        TreeInOrderEnum.Tree_L_S -> Tree.L.S
        TreeInOrderEnum.Tree_T -> Tree.T
    }
}

/**
 * The index of [this] in the values list.
 */
val Tree.inOrderOrdinal: Int
    get() = TreeInOrderSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val Tree.inOrderName: String
    get() = TreeInOrderSealedEnum.nameOf(this)

/**
 * A list of all [Tree] objects.
 */
val Tree.Companion.inOrderValues: List<Tree>
    get() = TreeInOrderSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [Tree]
 */
val Tree.Companion.inOrderSealedEnum: TreeInOrderSealedEnum
    get() = TreeInOrderSealedEnum

/**
 * Returns the [Tree] object for the given [name].
 *
 * If the given name doesn't correspond to any [Tree], an [IllegalArgumentException] will be thrown.
 */
fun Tree.Companion.inOrderValueOf(name: String): Tree = TreeInOrderSealedEnum.valueOf(name)

/**
 * An isomorphic enum for the sealed class [Tree]
 */
enum class TreePreOrderEnum {
    Tree_A,

    Tree_K,

    Tree_T,

    Tree_B_C_D,

    Tree_B_C_E,

    Tree_B_C_J,

    Tree_B_C_F_G,

    Tree_B_C_F_H,

    Tree_B_C_F_I,

    Tree_L_S,

    Tree_L_M_N,

    Tree_L_M_O,

    Tree_L_P_Q,

    Tree_L_P_R
}

/**
 * The isomorphic [TreePreOrderEnum] for [this].
 */
val Tree.preOrderEnum: TreePreOrderEnum
    get() = TreePreOrderSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [Tree] for [this].
 */
val TreePreOrderEnum.sealedObject: Tree
    get() = TreePreOrderSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [Tree]
 */
object TreePreOrderSealedEnum : SealedEnum<Tree>, SealedEnumWithEnumProvider<Tree,
        TreePreOrderEnum>, EnumForSealedEnumProvider<Tree, TreePreOrderEnum> {
    override val values: List<Tree> = listOf(
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
    )


    override val enumClass: Class<TreePreOrderEnum>
        get() = TreePreOrderEnum::class.java

    override fun ordinalOf(obj: Tree): Int = when (obj) {
        Tree.A -> 0
        Tree.K -> 1
        Tree.T -> 2
        Tree.B.C.D -> 3
        Tree.B.C.E -> 4
        Tree.B.C.J -> 5
        Tree.B.C.F.G -> 6
        Tree.B.C.F.H -> 7
        Tree.B.C.F.I -> 8
        Tree.L.S -> 9
        Tree.L.M.N -> 10
        Tree.L.M.O -> 11
        Tree.L.P.Q -> 12
        Tree.L.P.R -> 13
    }

    override fun nameOf(obj: Tree): String = when (obj) {
        Tree.A -> "Tree_A"
        Tree.K -> "Tree_K"
        Tree.T -> "Tree_T"
        Tree.B.C.D -> "Tree_B_C_D"
        Tree.B.C.E -> "Tree_B_C_E"
        Tree.B.C.J -> "Tree_B_C_J"
        Tree.B.C.F.G -> "Tree_B_C_F_G"
        Tree.B.C.F.H -> "Tree_B_C_F_H"
        Tree.B.C.F.I -> "Tree_B_C_F_I"
        Tree.L.S -> "Tree_L_S"
        Tree.L.M.N -> "Tree_L_M_N"
        Tree.L.M.O -> "Tree_L_M_O"
        Tree.L.P.Q -> "Tree_L_P_Q"
        Tree.L.P.R -> "Tree_L_P_R"
    }

    override fun valueOf(name: String): Tree = when (name) {
        "Tree_A" -> Tree.A
        "Tree_K" -> Tree.K
        "Tree_T" -> Tree.T
        "Tree_B_C_D" -> Tree.B.C.D
        "Tree_B_C_E" -> Tree.B.C.E
        "Tree_B_C_J" -> Tree.B.C.J
        "Tree_B_C_F_G" -> Tree.B.C.F.G
        "Tree_B_C_F_H" -> Tree.B.C.F.H
        "Tree_B_C_F_I" -> Tree.B.C.F.I
        "Tree_L_S" -> Tree.L.S
        "Tree_L_M_N" -> Tree.L.M.N
        "Tree_L_M_O" -> Tree.L.M.O
        "Tree_L_P_Q" -> Tree.L.P.Q
        "Tree_L_P_R" -> Tree.L.P.R
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    override fun sealedObjectToEnum(obj: Tree): TreePreOrderEnum = when (obj) {
        Tree.A -> TreePreOrderEnum.Tree_A
        Tree.K -> TreePreOrderEnum.Tree_K
        Tree.T -> TreePreOrderEnum.Tree_T
        Tree.B.C.D -> TreePreOrderEnum.Tree_B_C_D
        Tree.B.C.E -> TreePreOrderEnum.Tree_B_C_E
        Tree.B.C.J -> TreePreOrderEnum.Tree_B_C_J
        Tree.B.C.F.G -> TreePreOrderEnum.Tree_B_C_F_G
        Tree.B.C.F.H -> TreePreOrderEnum.Tree_B_C_F_H
        Tree.B.C.F.I -> TreePreOrderEnum.Tree_B_C_F_I
        Tree.L.S -> TreePreOrderEnum.Tree_L_S
        Tree.L.M.N -> TreePreOrderEnum.Tree_L_M_N
        Tree.L.M.O -> TreePreOrderEnum.Tree_L_M_O
        Tree.L.P.Q -> TreePreOrderEnum.Tree_L_P_Q
        Tree.L.P.R -> TreePreOrderEnum.Tree_L_P_R
    }

    override fun enumToSealedObject(enum: TreePreOrderEnum): Tree = when (enum) {
        TreePreOrderEnum.Tree_A -> Tree.A
        TreePreOrderEnum.Tree_K -> Tree.K
        TreePreOrderEnum.Tree_T -> Tree.T
        TreePreOrderEnum.Tree_B_C_D -> Tree.B.C.D
        TreePreOrderEnum.Tree_B_C_E -> Tree.B.C.E
        TreePreOrderEnum.Tree_B_C_J -> Tree.B.C.J
        TreePreOrderEnum.Tree_B_C_F_G -> Tree.B.C.F.G
        TreePreOrderEnum.Tree_B_C_F_H -> Tree.B.C.F.H
        TreePreOrderEnum.Tree_B_C_F_I -> Tree.B.C.F.I
        TreePreOrderEnum.Tree_L_S -> Tree.L.S
        TreePreOrderEnum.Tree_L_M_N -> Tree.L.M.N
        TreePreOrderEnum.Tree_L_M_O -> Tree.L.M.O
        TreePreOrderEnum.Tree_L_P_Q -> Tree.L.P.Q
        TreePreOrderEnum.Tree_L_P_R -> Tree.L.P.R
    }
}

/**
 * The index of [this] in the values list.
 */
val Tree.preOrderOrdinal: Int
    get() = TreePreOrderSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val Tree.preOrderName: String
    get() = TreePreOrderSealedEnum.nameOf(this)

/**
 * A list of all [Tree] objects.
 */
val Tree.Companion.preOrderValues: List<Tree>
    get() = TreePreOrderSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [Tree]
 */
val Tree.Companion.preOrderSealedEnum: TreePreOrderSealedEnum
    get() = TreePreOrderSealedEnum

/**
 * Returns the [Tree] object for the given [name].
 *
 * If the given name doesn't correspond to any [Tree], an [IllegalArgumentException] will be thrown.
 */
fun Tree.Companion.preOrderValueOf(name: String): Tree = TreePreOrderSealedEnum.valueOf(name)

""".trimIndent()
