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
public enum class FirstClassHierarchy_AEnum {
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
public enum class FirstClassHierarchy_A_BEnum {
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

@Language("kotlin")
val secondClassHierarchyAGenerated = """
package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.SealedEnum
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An implementation of [SealedEnum] for the sealed class [SecondClassHierarchy.A]
 */
public object SecondClassHierarchy_ASealedEnum : SealedEnum<SecondClassHierarchy.A> {
    public override val values: List<SecondClassHierarchy.A> = listOf(
        SecondClassHierarchy.A.B,
        SecondClassHierarchy.A.C.D,
        SecondClassHierarchy.A.C.E,
        SecondClassHierarchy.A.C.F.G,
        SecondClassHierarchy.A.C.H.I,
        SecondClassHierarchy.A.J.K,
        SecondClassHierarchy.A.L
    )


    public override fun ordinalOf(obj: SecondClassHierarchy.A): Int = when (obj) {
        SecondClassHierarchy.A.B -> 0
        SecondClassHierarchy.A.C.D -> 1
        SecondClassHierarchy.A.C.E -> 2
        SecondClassHierarchy.A.C.F.G -> 3
        SecondClassHierarchy.A.C.H.I -> 4
        SecondClassHierarchy.A.J.K -> 5
        SecondClassHierarchy.A.L -> 6
    }

    public override fun nameOf(obj: SecondClassHierarchy.A): String = when (obj) {
        SecondClassHierarchy.A.B -> "SecondClassHierarchy_A_B"
        SecondClassHierarchy.A.C.D -> "SecondClassHierarchy_A_C_D"
        SecondClassHierarchy.A.C.E -> "SecondClassHierarchy_A_C_E"
        SecondClassHierarchy.A.C.F.G -> "SecondClassHierarchy_A_C_F_G"
        SecondClassHierarchy.A.C.H.I -> "SecondClassHierarchy_A_C_H_I"
        SecondClassHierarchy.A.J.K -> "SecondClassHierarchy_A_J_K"
        SecondClassHierarchy.A.L -> "SecondClassHierarchy_A_L"
    }

    public override fun valueOf(name: String): SecondClassHierarchy.A = when (name) {
        "SecondClassHierarchy_A_B" -> SecondClassHierarchy.A.B
        "SecondClassHierarchy_A_C_D" -> SecondClassHierarchy.A.C.D
        "SecondClassHierarchy_A_C_E" -> SecondClassHierarchy.A.C.E
        "SecondClassHierarchy_A_C_F_G" -> SecondClassHierarchy.A.C.F.G
        "SecondClassHierarchy_A_C_H_I" -> SecondClassHierarchy.A.C.H.I
        "SecondClassHierarchy_A_J_K" -> SecondClassHierarchy.A.J.K
        "SecondClassHierarchy_A_L" -> SecondClassHierarchy.A.L
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }
}

/**
 * The index of [this] in the values list.
 */
public val SecondClassHierarchy.A.ordinal: Int
    get() = SecondClassHierarchy_ASealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val SecondClassHierarchy.A.name: String
    get() = SecondClassHierarchy_ASealedEnum.nameOf(this)

/**
 * A list of all [SecondClassHierarchy.A] objects.
 */
public val SecondClassHierarchy.A.Companion.values: List<SecondClassHierarchy.A>
    get() = SecondClassHierarchy_ASealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [SecondClassHierarchy.A]
 */
public val SecondClassHierarchy.A.Companion.sealedEnum: SecondClassHierarchy_ASealedEnum
    get() = SecondClassHierarchy_ASealedEnum

/**
 * Returns the [SecondClassHierarchy.A] object for the given [name].
 *
 * If the given name doesn't correspond to any [SecondClassHierarchy.A], an
 * [IllegalArgumentException] will be thrown.
 */
public fun SecondClassHierarchy.A.Companion.valueOf(name: String): SecondClassHierarchy.A =
        SecondClassHierarchy_ASealedEnum.valueOf(name)

""".trimIndent()

@Language("kotlin")
val secondClassHierarchyACGenerated = """
package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.SealedEnum
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An implementation of [SealedEnum] for the sealed class [SecondClassHierarchy.A.C]
 */
public object SecondClassHierarchy_A_CSealedEnum : SealedEnum<SecondClassHierarchy.A.C> {
    public override val values: List<SecondClassHierarchy.A.C> = listOf(
        SecondClassHierarchy.A.C.D,
        SecondClassHierarchy.A.C.E,
        SecondClassHierarchy.A.C.F.G,
        SecondClassHierarchy.A.C.H.I
    )


    public override fun ordinalOf(obj: SecondClassHierarchy.A.C): Int = when (obj) {
        SecondClassHierarchy.A.C.D -> 0
        SecondClassHierarchy.A.C.E -> 1
        SecondClassHierarchy.A.C.F.G -> 2
        SecondClassHierarchy.A.C.H.I -> 3
    }

    public override fun nameOf(obj: SecondClassHierarchy.A.C): String = when (obj) {
        SecondClassHierarchy.A.C.D -> "SecondClassHierarchy_A_C_D"
        SecondClassHierarchy.A.C.E -> "SecondClassHierarchy_A_C_E"
        SecondClassHierarchy.A.C.F.G -> "SecondClassHierarchy_A_C_F_G"
        SecondClassHierarchy.A.C.H.I -> "SecondClassHierarchy_A_C_H_I"
    }

    public override fun valueOf(name: String): SecondClassHierarchy.A.C = when (name) {
        "SecondClassHierarchy_A_C_D" -> SecondClassHierarchy.A.C.D
        "SecondClassHierarchy_A_C_E" -> SecondClassHierarchy.A.C.E
        "SecondClassHierarchy_A_C_F_G" -> SecondClassHierarchy.A.C.F.G
        "SecondClassHierarchy_A_C_H_I" -> SecondClassHierarchy.A.C.H.I
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }
}

/**
 * The index of [this] in the values list.
 */
public val SecondClassHierarchy.A.C.ordinal: Int
    get() = SecondClassHierarchy_A_CSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val SecondClassHierarchy.A.C.name: String
    get() = SecondClassHierarchy_A_CSealedEnum.nameOf(this)

/**
 * A list of all [SecondClassHierarchy.A.C] objects.
 */
public val SecondClassHierarchy.A.C.Companion.values: List<SecondClassHierarchy.A.C>
    get() = SecondClassHierarchy_A_CSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [SecondClassHierarchy.A.C]
 */
public val SecondClassHierarchy.A.C.Companion.sealedEnum: SecondClassHierarchy_A_CSealedEnum
    get() = SecondClassHierarchy_A_CSealedEnum

/**
 * Returns the [SecondClassHierarchy.A.C] object for the given [name].
 *
 * If the given name doesn't correspond to any [SecondClassHierarchy.A.C], an
 * [IllegalArgumentException] will be thrown.
 */
public fun SecondClassHierarchy.A.C.Companion.valueOf(name: String): SecondClassHierarchy.A.C =
        SecondClassHierarchy_A_CSealedEnum.valueOf(name)

""".trimIndent()

@Language("kotlin")
val secondClassHierarchyACFGenerated = """
package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.SealedEnum
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An implementation of [SealedEnum] for the sealed class [SecondClassHierarchy.A.C.F]
 */
public object SecondClassHierarchy_A_C_FSealedEnum : SealedEnum<SecondClassHierarchy.A.C.F> {
    public override val values: List<SecondClassHierarchy.A.C.F> = listOf(
        SecondClassHierarchy.A.C.F.G
    )


    public override fun ordinalOf(obj: SecondClassHierarchy.A.C.F): Int = when (obj) {
        SecondClassHierarchy.A.C.F.G -> 0
    }

    public override fun nameOf(obj: SecondClassHierarchy.A.C.F): String = when (obj) {
        SecondClassHierarchy.A.C.F.G -> "SecondClassHierarchy_A_C_F_G"
    }

    public override fun valueOf(name: String): SecondClassHierarchy.A.C.F = when (name) {
        "SecondClassHierarchy_A_C_F_G" -> SecondClassHierarchy.A.C.F.G
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }
}

/**
 * The index of [this] in the values list.
 */
public val SecondClassHierarchy.A.C.F.ordinal: Int
    get() = SecondClassHierarchy_A_C_FSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val SecondClassHierarchy.A.C.F.name: String
    get() = SecondClassHierarchy_A_C_FSealedEnum.nameOf(this)

/**
 * A list of all [SecondClassHierarchy.A.C.F] objects.
 */
public val SecondClassHierarchy.A.C.F.Companion.values: List<SecondClassHierarchy.A.C.F>
    get() = SecondClassHierarchy_A_C_FSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [SecondClassHierarchy.A.C.F]
 */
public val SecondClassHierarchy.A.C.F.Companion.sealedEnum: SecondClassHierarchy_A_C_FSealedEnum
    get() = SecondClassHierarchy_A_C_FSealedEnum

/**
 * Returns the [SecondClassHierarchy.A.C.F] object for the given [name].
 *
 * If the given name doesn't correspond to any [SecondClassHierarchy.A.C.F], an
 * [IllegalArgumentException] will be thrown.
 */
public fun SecondClassHierarchy.A.C.F.Companion.valueOf(name: String): SecondClassHierarchy.A.C.F =
        SecondClassHierarchy_A_C_FSealedEnum.valueOf(name)

""".trimIndent()

@Language("kotlin")
val secondClassHierarchyACHGenerated = """
package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.SealedEnum
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An implementation of [SealedEnum] for the sealed class [SecondClassHierarchy.A.C.H]
 */
public object SecondClassHierarchy_A_C_HSealedEnum : SealedEnum<SecondClassHierarchy.A.C.H> {
    public override val values: List<SecondClassHierarchy.A.C.H> = listOf(
        SecondClassHierarchy.A.C.H.I
    )


    public override fun ordinalOf(obj: SecondClassHierarchy.A.C.H): Int = when (obj) {
        SecondClassHierarchy.A.C.H.I -> 0
    }

    public override fun nameOf(obj: SecondClassHierarchy.A.C.H): String = when (obj) {
        SecondClassHierarchy.A.C.H.I -> "SecondClassHierarchy_A_C_H_I"
    }

    public override fun valueOf(name: String): SecondClassHierarchy.A.C.H = when (name) {
        "SecondClassHierarchy_A_C_H_I" -> SecondClassHierarchy.A.C.H.I
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }
}

/**
 * The index of [this] in the values list.
 */
public val SecondClassHierarchy.A.C.H.ordinal: Int
    get() = SecondClassHierarchy_A_C_HSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val SecondClassHierarchy.A.C.H.name: String
    get() = SecondClassHierarchy_A_C_HSealedEnum.nameOf(this)

/**
 * A list of all [SecondClassHierarchy.A.C.H] objects.
 */
public val SecondClassHierarchy.A.C.H.Companion.values: List<SecondClassHierarchy.A.C.H>
    get() = SecondClassHierarchy_A_C_HSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [SecondClassHierarchy.A.C.H]
 */
public val SecondClassHierarchy.A.C.H.Companion.sealedEnum: SecondClassHierarchy_A_C_HSealedEnum
    get() = SecondClassHierarchy_A_C_HSealedEnum

/**
 * Returns the [SecondClassHierarchy.A.C.H] object for the given [name].
 *
 * If the given name doesn't correspond to any [SecondClassHierarchy.A.C.H], an
 * [IllegalArgumentException] will be thrown.
 */
public fun SecondClassHierarchy.A.C.H.Companion.valueOf(name: String): SecondClassHierarchy.A.C.H =
        SecondClassHierarchy_A_C_HSealedEnum.valueOf(name)

""".trimIndent()

@Language("kotlin")
val secondClassHierarchyAJGenerated = """
package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.SealedEnum
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An implementation of [SealedEnum] for the sealed class [SecondClassHierarchy.A.J]
 */
public object SecondClassHierarchy_A_JSealedEnum : SealedEnum<SecondClassHierarchy.A.J> {
    public override val values: List<SecondClassHierarchy.A.J> = listOf(
        SecondClassHierarchy.A.J.K
    )


    public override fun ordinalOf(obj: SecondClassHierarchy.A.J): Int = when (obj) {
        SecondClassHierarchy.A.J.K -> 0
    }

    public override fun nameOf(obj: SecondClassHierarchy.A.J): String = when (obj) {
        SecondClassHierarchy.A.J.K -> "SecondClassHierarchy_A_J_K"
    }

    public override fun valueOf(name: String): SecondClassHierarchy.A.J = when (name) {
        "SecondClassHierarchy_A_J_K" -> SecondClassHierarchy.A.J.K
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }
}

/**
 * The index of [this] in the values list.
 */
public val SecondClassHierarchy.A.J.ordinal: Int
    get() = SecondClassHierarchy_A_JSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val SecondClassHierarchy.A.J.name: String
    get() = SecondClassHierarchy_A_JSealedEnum.nameOf(this)

/**
 * A list of all [SecondClassHierarchy.A.J] objects.
 */
public val SecondClassHierarchy.A.J.Companion.values: List<SecondClassHierarchy.A.J>
    get() = SecondClassHierarchy_A_JSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [SecondClassHierarchy.A.J]
 */
public val SecondClassHierarchy.A.J.Companion.sealedEnum: SecondClassHierarchy_A_JSealedEnum
    get() = SecondClassHierarchy_A_JSealedEnum

/**
 * Returns the [SecondClassHierarchy.A.J] object for the given [name].
 *
 * If the given name doesn't correspond to any [SecondClassHierarchy.A.J], an
 * [IllegalArgumentException] will be thrown.
 */
public fun SecondClassHierarchy.A.J.Companion.valueOf(name: String): SecondClassHierarchy.A.J =
        SecondClassHierarchy_A_JSealedEnum.valueOf(name)

""".trimIndent()
