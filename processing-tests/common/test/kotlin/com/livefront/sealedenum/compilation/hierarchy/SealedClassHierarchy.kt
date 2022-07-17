package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.GenSealedEnum
import org.intellij.lang.annotations.Language

class FirstClassHierarchy {

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

@Language("kotlin")
val firstClassHierarchyAGenerated = """
package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [FirstClassHierarchy.A]
 */
public enum class FirstClassHierarchy_AEnum() {
    FirstClassHierarchy_A_B_C,
}

/**
 * The isomorphic [FirstClassHierarchy_AEnum] for [this].
 */
public val FirstClassHierarchy.A.`enum`: FirstClassHierarchy_AEnum
    get() = FirstClassHierarchy_ASealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [FirstClassHierarchy.A] for [this].
 */
public val FirstClassHierarchy_AEnum.sealedObject: FirstClassHierarchy.A
    get() = FirstClassHierarchy_ASealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [FirstClassHierarchy.A]
 */
public object FirstClassHierarchy_ASealedEnum : SealedEnum<FirstClassHierarchy.A>,
        SealedEnumWithEnumProvider<FirstClassHierarchy.A, FirstClassHierarchy_AEnum>,
        EnumForSealedEnumProvider<FirstClassHierarchy.A, FirstClassHierarchy_AEnum> {
    public override val values: List<FirstClassHierarchy.A> = listOf(
        FirstClassHierarchy.A.B.C
    )


    public override val enumClass: Class<FirstClassHierarchy_AEnum>
        get() = FirstClassHierarchy_AEnum::class.java

    public override fun ordinalOf(obj: FirstClassHierarchy.A): Int = when (obj) {
        FirstClassHierarchy.A.B.C -> 0
    }

    public override fun nameOf(obj: FirstClassHierarchy.A): String = when (obj) {
        FirstClassHierarchy.A.B.C -> "FirstClassHierarchy_A_B_C"
    }

    public override fun valueOf(name: String): FirstClassHierarchy.A = when (name) {
        "FirstClassHierarchy_A_B_C" -> FirstClassHierarchy.A.B.C
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: FirstClassHierarchy.A): FirstClassHierarchy_AEnum =
            when (obj) {
        FirstClassHierarchy.A.B.C -> FirstClassHierarchy_AEnum.FirstClassHierarchy_A_B_C
    }

    public override fun enumToSealedObject(`enum`: FirstClassHierarchy_AEnum): FirstClassHierarchy.A
            = when (enum) {
        FirstClassHierarchy_AEnum.FirstClassHierarchy_A_B_C -> FirstClassHierarchy.A.B.C
    }
}

/**
 * The index of [this] in the values list.
 */
public val FirstClassHierarchy.A.ordinal: Int
    get() = FirstClassHierarchy_ASealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val FirstClassHierarchy.A.name: String
    get() = FirstClassHierarchy_ASealedEnum.nameOf(this)

/**
 * A list of all [FirstClassHierarchy.A] objects.
 */
public val FirstClassHierarchy.A.Companion.values: List<FirstClassHierarchy.A>
    get() = FirstClassHierarchy_ASealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [FirstClassHierarchy.A]
 */
public val FirstClassHierarchy.A.Companion.sealedEnum: FirstClassHierarchy_ASealedEnum
    get() = FirstClassHierarchy_ASealedEnum

/**
 * Returns the [FirstClassHierarchy.A] object for the given [name].
 *
 * If the given name doesn't correspond to any [FirstClassHierarchy.A], an
 * [IllegalArgumentException] will be thrown.
 */
public fun FirstClassHierarchy.A.Companion.valueOf(name: String): FirstClassHierarchy.A =
        FirstClassHierarchy_ASealedEnum.valueOf(name)

""".trimIndent()

@Language("kotlin")
val firstClassHierarchyBGenerated = """
package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [FirstClassHierarchy.A.B]
 */
public enum class FirstClassHierarchy_A_BEnum() {
    FirstClassHierarchy_A_B_C,
}

/**
 * The isomorphic [FirstClassHierarchy_A_BEnum] for [this].
 */
public val FirstClassHierarchy.A.B.`enum`: FirstClassHierarchy_A_BEnum
    get() = FirstClassHierarchy_A_BSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [FirstClassHierarchy.A.B] for [this].
 */
public val FirstClassHierarchy_A_BEnum.sealedObject: FirstClassHierarchy.A.B
    get() = FirstClassHierarchy_A_BSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [FirstClassHierarchy.A.B]
 */
public object FirstClassHierarchy_A_BSealedEnum : SealedEnum<FirstClassHierarchy.A.B>,
        SealedEnumWithEnumProvider<FirstClassHierarchy.A.B, FirstClassHierarchy_A_BEnum>,
        EnumForSealedEnumProvider<FirstClassHierarchy.A.B, FirstClassHierarchy_A_BEnum> {
    public override val values: List<FirstClassHierarchy.A.B> = listOf(
        FirstClassHierarchy.A.B.C
    )


    public override val enumClass: Class<FirstClassHierarchy_A_BEnum>
        get() = FirstClassHierarchy_A_BEnum::class.java

    public override fun ordinalOf(obj: FirstClassHierarchy.A.B): Int = when (obj) {
        FirstClassHierarchy.A.B.C -> 0
    }

    public override fun nameOf(obj: FirstClassHierarchy.A.B): String = when (obj) {
        FirstClassHierarchy.A.B.C -> "FirstClassHierarchy_A_B_C"
    }

    public override fun valueOf(name: String): FirstClassHierarchy.A.B = when (name) {
        "FirstClassHierarchy_A_B_C" -> FirstClassHierarchy.A.B.C
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: FirstClassHierarchy.A.B):
            FirstClassHierarchy_A_BEnum = when (obj) {
        FirstClassHierarchy.A.B.C -> FirstClassHierarchy_A_BEnum.FirstClassHierarchy_A_B_C
    }

    public override fun enumToSealedObject(`enum`: FirstClassHierarchy_A_BEnum):
            FirstClassHierarchy.A.B = when (enum) {
        FirstClassHierarchy_A_BEnum.FirstClassHierarchy_A_B_C -> FirstClassHierarchy.A.B.C
    }
}

/**
 * The index of [this] in the values list.
 */
public val FirstClassHierarchy.A.B.ordinal: Int
    get() = FirstClassHierarchy_A_BSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val FirstClassHierarchy.A.B.name: String
    get() = FirstClassHierarchy_A_BSealedEnum.nameOf(this)

/**
 * A list of all [FirstClassHierarchy.A.B] objects.
 */
public val FirstClassHierarchy.A.B.Companion.values: List<FirstClassHierarchy.A.B>
    get() = FirstClassHierarchy_A_BSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [FirstClassHierarchy.A.B]
 */
public val FirstClassHierarchy.A.B.Companion.sealedEnum: FirstClassHierarchy_A_BSealedEnum
    get() = FirstClassHierarchy_A_BSealedEnum

/**
 * Returns the [FirstClassHierarchy.A.B] object for the given [name].
 *
 * If the given name doesn't correspond to any [FirstClassHierarchy.A.B], an
 * [IllegalArgumentException] will be thrown.
 */
public fun FirstClassHierarchy.A.B.Companion.valueOf(name: String): FirstClassHierarchy.A.B =
        FirstClassHierarchy_A_BSealedEnum.valueOf(name)

""".trimIndent()

class SecondClassHierarchy {

    sealed class Z {

        object Y : Z()

        sealed class X : Z() {

            object W : X()

            object V : X()

            sealed class U : X() {
                object T : U()

                @GenSealedEnum
                companion object
            }

            sealed class S : X() {
                object R : S()

                @GenSealedEnum
                companion object
            }

            @GenSealedEnum
            companion object
        }

        sealed class Q : Z() {

            object P : Q()

            @GenSealedEnum
            companion object
        }

        object O : Z()

        @GenSealedEnum
        companion object
    }
}

@Language("kotlin")
val secondClassHierarchyZGenerated = """
package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.SealedEnum
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An implementation of [SealedEnum] for the sealed class [SecondClassHierarchy.Z]
 */
public object SecondClassHierarchy_ZSealedEnum : SealedEnum<SecondClassHierarchy.Z> {
    public override val values: List<SecondClassHierarchy.Z> = listOf(
        SecondClassHierarchy.Z.Y,
        SecondClassHierarchy.Z.X.W,
        SecondClassHierarchy.Z.X.V,
        SecondClassHierarchy.Z.X.U.T,
        SecondClassHierarchy.Z.X.S.R,
        SecondClassHierarchy.Z.Q.P,
        SecondClassHierarchy.Z.O
    )


    public override fun ordinalOf(obj: SecondClassHierarchy.Z): Int = when (obj) {
        SecondClassHierarchy.Z.Y -> 0
        SecondClassHierarchy.Z.X.W -> 1
        SecondClassHierarchy.Z.X.V -> 2
        SecondClassHierarchy.Z.X.U.T -> 3
        SecondClassHierarchy.Z.X.S.R -> 4
        SecondClassHierarchy.Z.Q.P -> 5
        SecondClassHierarchy.Z.O -> 6
    }

    public override fun nameOf(obj: SecondClassHierarchy.Z): String = when (obj) {
        SecondClassHierarchy.Z.Y -> "SecondClassHierarchy_Z_Y"
        SecondClassHierarchy.Z.X.W -> "SecondClassHierarchy_Z_X_W"
        SecondClassHierarchy.Z.X.V -> "SecondClassHierarchy_Z_X_V"
        SecondClassHierarchy.Z.X.U.T -> "SecondClassHierarchy_Z_X_U_T"
        SecondClassHierarchy.Z.X.S.R -> "SecondClassHierarchy_Z_X_S_R"
        SecondClassHierarchy.Z.Q.P -> "SecondClassHierarchy_Z_Q_P"
        SecondClassHierarchy.Z.O -> "SecondClassHierarchy_Z_O"
    }

    public override fun valueOf(name: String): SecondClassHierarchy.Z = when (name) {
        "SecondClassHierarchy_Z_Y" -> SecondClassHierarchy.Z.Y
        "SecondClassHierarchy_Z_X_W" -> SecondClassHierarchy.Z.X.W
        "SecondClassHierarchy_Z_X_V" -> SecondClassHierarchy.Z.X.V
        "SecondClassHierarchy_Z_X_U_T" -> SecondClassHierarchy.Z.X.U.T
        "SecondClassHierarchy_Z_X_S_R" -> SecondClassHierarchy.Z.X.S.R
        "SecondClassHierarchy_Z_Q_P" -> SecondClassHierarchy.Z.Q.P
        "SecondClassHierarchy_Z_O" -> SecondClassHierarchy.Z.O
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }
}

/**
 * The index of [this] in the values list.
 */
public val SecondClassHierarchy.Z.ordinal: Int
    get() = SecondClassHierarchy_ZSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val SecondClassHierarchy.Z.name: String
    get() = SecondClassHierarchy_ZSealedEnum.nameOf(this)

/**
 * A list of all [SecondClassHierarchy.Z] objects.
 */
public val SecondClassHierarchy.Z.Companion.values: List<SecondClassHierarchy.Z>
    get() = SecondClassHierarchy_ZSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [SecondClassHierarchy.Z]
 */
public val SecondClassHierarchy.Z.Companion.sealedEnum: SecondClassHierarchy_ZSealedEnum
    get() = SecondClassHierarchy_ZSealedEnum

/**
 * Returns the [SecondClassHierarchy.Z] object for the given [name].
 *
 * If the given name doesn't correspond to any [SecondClassHierarchy.Z], an
 * [IllegalArgumentException] will be thrown.
 */
public fun SecondClassHierarchy.Z.Companion.valueOf(name: String): SecondClassHierarchy.Z =
        SecondClassHierarchy_ZSealedEnum.valueOf(name)

""".trimIndent()

@Language("kotlin")
val secondClassHierarchyZXGenerated = """
package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.SealedEnum
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An implementation of [SealedEnum] for the sealed class [SecondClassHierarchy.Z.X]
 */
public object SecondClassHierarchy_Z_XSealedEnum : SealedEnum<SecondClassHierarchy.Z.X> {
    public override val values: List<SecondClassHierarchy.Z.X> = listOf(
        SecondClassHierarchy.Z.X.W,
        SecondClassHierarchy.Z.X.V,
        SecondClassHierarchy.Z.X.U.T,
        SecondClassHierarchy.Z.X.S.R
    )


    public override fun ordinalOf(obj: SecondClassHierarchy.Z.X): Int = when (obj) {
        SecondClassHierarchy.Z.X.W -> 0
        SecondClassHierarchy.Z.X.V -> 1
        SecondClassHierarchy.Z.X.U.T -> 2
        SecondClassHierarchy.Z.X.S.R -> 3
    }

    public override fun nameOf(obj: SecondClassHierarchy.Z.X): String = when (obj) {
        SecondClassHierarchy.Z.X.W -> "SecondClassHierarchy_Z_X_W"
        SecondClassHierarchy.Z.X.V -> "SecondClassHierarchy_Z_X_V"
        SecondClassHierarchy.Z.X.U.T -> "SecondClassHierarchy_Z_X_U_T"
        SecondClassHierarchy.Z.X.S.R -> "SecondClassHierarchy_Z_X_S_R"
    }

    public override fun valueOf(name: String): SecondClassHierarchy.Z.X = when (name) {
        "SecondClassHierarchy_Z_X_W" -> SecondClassHierarchy.Z.X.W
        "SecondClassHierarchy_Z_X_V" -> SecondClassHierarchy.Z.X.V
        "SecondClassHierarchy_Z_X_U_T" -> SecondClassHierarchy.Z.X.U.T
        "SecondClassHierarchy_Z_X_S_R" -> SecondClassHierarchy.Z.X.S.R
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }
}

/**
 * The index of [this] in the values list.
 */
public val SecondClassHierarchy.Z.X.ordinal: Int
    get() = SecondClassHierarchy_Z_XSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val SecondClassHierarchy.Z.X.name: String
    get() = SecondClassHierarchy_Z_XSealedEnum.nameOf(this)

/**
 * A list of all [SecondClassHierarchy.Z.X] objects.
 */
public val SecondClassHierarchy.Z.X.Companion.values: List<SecondClassHierarchy.Z.X>
    get() = SecondClassHierarchy_Z_XSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [SecondClassHierarchy.Z.X]
 */
public val SecondClassHierarchy.Z.X.Companion.sealedEnum: SecondClassHierarchy_Z_XSealedEnum
    get() = SecondClassHierarchy_Z_XSealedEnum

/**
 * Returns the [SecondClassHierarchy.Z.X] object for the given [name].
 *
 * If the given name doesn't correspond to any [SecondClassHierarchy.Z.X], an
 * [IllegalArgumentException] will be thrown.
 */
public fun SecondClassHierarchy.Z.X.Companion.valueOf(name: String): SecondClassHierarchy.Z.X =
        SecondClassHierarchy_Z_XSealedEnum.valueOf(name)

""".trimIndent()

@Language("kotlin")
val secondClassHierarchyZXUGenerated = """
package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.SealedEnum
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An implementation of [SealedEnum] for the sealed class [SecondClassHierarchy.Z.X.U]
 */
public object SecondClassHierarchy_Z_X_USealedEnum : SealedEnum<SecondClassHierarchy.Z.X.U> {
    public override val values: List<SecondClassHierarchy.Z.X.U> = listOf(
        SecondClassHierarchy.Z.X.U.T
    )


    public override fun ordinalOf(obj: SecondClassHierarchy.Z.X.U): Int = when (obj) {
        SecondClassHierarchy.Z.X.U.T -> 0
    }

    public override fun nameOf(obj: SecondClassHierarchy.Z.X.U): String = when (obj) {
        SecondClassHierarchy.Z.X.U.T -> "SecondClassHierarchy_Z_X_U_T"
    }

    public override fun valueOf(name: String): SecondClassHierarchy.Z.X.U = when (name) {
        "SecondClassHierarchy_Z_X_U_T" -> SecondClassHierarchy.Z.X.U.T
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }
}

/**
 * The index of [this] in the values list.
 */
public val SecondClassHierarchy.Z.X.U.ordinal: Int
    get() = SecondClassHierarchy_Z_X_USealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val SecondClassHierarchy.Z.X.U.name: String
    get() = SecondClassHierarchy_Z_X_USealedEnum.nameOf(this)

/**
 * A list of all [SecondClassHierarchy.Z.X.U] objects.
 */
public val SecondClassHierarchy.Z.X.U.Companion.values: List<SecondClassHierarchy.Z.X.U>
    get() = SecondClassHierarchy_Z_X_USealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [SecondClassHierarchy.Z.X.U]
 */
public val SecondClassHierarchy.Z.X.U.Companion.sealedEnum: SecondClassHierarchy_Z_X_USealedEnum
    get() = SecondClassHierarchy_Z_X_USealedEnum

/**
 * Returns the [SecondClassHierarchy.Z.X.U] object for the given [name].
 *
 * If the given name doesn't correspond to any [SecondClassHierarchy.Z.X.U], an
 * [IllegalArgumentException] will be thrown.
 */
public fun SecondClassHierarchy.Z.X.U.Companion.valueOf(name: String): SecondClassHierarchy.Z.X.U =
        SecondClassHierarchy_Z_X_USealedEnum.valueOf(name)

""".trimIndent()

@Language("kotlin")
val secondClassHierarchyZXSGenerated = """
package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.SealedEnum
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An implementation of [SealedEnum] for the sealed class [SecondClassHierarchy.Z.X.S]
 */
public object SecondClassHierarchy_Z_X_SSealedEnum : SealedEnum<SecondClassHierarchy.Z.X.S> {
    public override val values: List<SecondClassHierarchy.Z.X.S> = listOf(
        SecondClassHierarchy.Z.X.S.R
    )


    public override fun ordinalOf(obj: SecondClassHierarchy.Z.X.S): Int = when (obj) {
        SecondClassHierarchy.Z.X.S.R -> 0
    }

    public override fun nameOf(obj: SecondClassHierarchy.Z.X.S): String = when (obj) {
        SecondClassHierarchy.Z.X.S.R -> "SecondClassHierarchy_Z_X_S_R"
    }

    public override fun valueOf(name: String): SecondClassHierarchy.Z.X.S = when (name) {
        "SecondClassHierarchy_Z_X_S_R" -> SecondClassHierarchy.Z.X.S.R
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }
}

/**
 * The index of [this] in the values list.
 */
public val SecondClassHierarchy.Z.X.S.ordinal: Int
    get() = SecondClassHierarchy_Z_X_SSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val SecondClassHierarchy.Z.X.S.name: String
    get() = SecondClassHierarchy_Z_X_SSealedEnum.nameOf(this)

/**
 * A list of all [SecondClassHierarchy.Z.X.S] objects.
 */
public val SecondClassHierarchy.Z.X.S.Companion.values: List<SecondClassHierarchy.Z.X.S>
    get() = SecondClassHierarchy_Z_X_SSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [SecondClassHierarchy.Z.X.S]
 */
public val SecondClassHierarchy.Z.X.S.Companion.sealedEnum: SecondClassHierarchy_Z_X_SSealedEnum
    get() = SecondClassHierarchy_Z_X_SSealedEnum

/**
 * Returns the [SecondClassHierarchy.Z.X.S] object for the given [name].
 *
 * If the given name doesn't correspond to any [SecondClassHierarchy.Z.X.S], an
 * [IllegalArgumentException] will be thrown.
 */
public fun SecondClassHierarchy.Z.X.S.Companion.valueOf(name: String): SecondClassHierarchy.Z.X.S =
        SecondClassHierarchy_Z_X_SSealedEnum.valueOf(name)

""".trimIndent()

@Language("kotlin")
val secondClassHierarchyZQGenerated = """
package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.SealedEnum
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An implementation of [SealedEnum] for the sealed class [SecondClassHierarchy.Z.Q]
 */
public object SecondClassHierarchy_Z_QSealedEnum : SealedEnum<SecondClassHierarchy.Z.Q> {
    public override val values: List<SecondClassHierarchy.Z.Q> = listOf(
        SecondClassHierarchy.Z.Q.P
    )


    public override fun ordinalOf(obj: SecondClassHierarchy.Z.Q): Int = when (obj) {
        SecondClassHierarchy.Z.Q.P -> 0
    }

    public override fun nameOf(obj: SecondClassHierarchy.Z.Q): String = when (obj) {
        SecondClassHierarchy.Z.Q.P -> "SecondClassHierarchy_Z_Q_P"
    }

    public override fun valueOf(name: String): SecondClassHierarchy.Z.Q = when (name) {
        "SecondClassHierarchy_Z_Q_P" -> SecondClassHierarchy.Z.Q.P
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }
}

/**
 * The index of [this] in the values list.
 */
public val SecondClassHierarchy.Z.Q.ordinal: Int
    get() = SecondClassHierarchy_Z_QSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val SecondClassHierarchy.Z.Q.name: String
    get() = SecondClassHierarchy_Z_QSealedEnum.nameOf(this)

/**
 * A list of all [SecondClassHierarchy.Z.Q] objects.
 */
public val SecondClassHierarchy.Z.Q.Companion.values: List<SecondClassHierarchy.Z.Q>
    get() = SecondClassHierarchy_Z_QSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [SecondClassHierarchy.Z.Q]
 */
public val SecondClassHierarchy.Z.Q.Companion.sealedEnum: SecondClassHierarchy_Z_QSealedEnum
    get() = SecondClassHierarchy_Z_QSealedEnum

/**
 * Returns the [SecondClassHierarchy.Z.Q] object for the given [name].
 *
 * If the given name doesn't correspond to any [SecondClassHierarchy.Z.Q], an
 * [IllegalArgumentException] will be thrown.
 */
public fun SecondClassHierarchy.Z.Q.Companion.valueOf(name: String): SecondClassHierarchy.Z.Q =
        SecondClassHierarchy_Z_QSealedEnum.valueOf(name)

""".trimIndent()
