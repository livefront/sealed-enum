package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.GenSealedEnum
import org.intellij.lang.annotations.Language

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

@Language("kotlin")
val firstHierarchyAGenerated = """
package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [FirstHierarchy.A]
 */
enum class FirstHierarchy_AEnum {
    FirstHierarchy_A_B_C
}

/**
 * The isomorphic [FirstHierarchy_AEnum] for [this].
 */
val FirstHierarchy.A.enum: FirstHierarchy_AEnum
    get() = FirstHierarchy_ASealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [FirstHierarchy.A] for [this].
 */
val FirstHierarchy_AEnum.sealedObject: FirstHierarchy.A
    get() = FirstHierarchy_ASealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [FirstHierarchy.A]
 */
object FirstHierarchy_ASealedEnum : SealedEnum<FirstHierarchy.A>,
        SealedEnumWithEnumProvider<FirstHierarchy.A, FirstHierarchy_AEnum>,
        EnumForSealedEnumProvider<FirstHierarchy.A, FirstHierarchy_AEnum> {
    override val values: List<FirstHierarchy.A> = listOf(
        FirstHierarchy.A.B.C
    )


    override val enumClass: Class<FirstHierarchy_AEnum>
        get() = FirstHierarchy_AEnum::class.java

    override fun ordinalOf(obj: FirstHierarchy.A): Int = when (obj) {
        FirstHierarchy.A.B.C -> 0
    }

    override fun nameOf(obj: FirstHierarchy.A): String = when (obj) {
        FirstHierarchy.A.B.C -> "FirstHierarchy_A_B_C"
    }

    override fun valueOf(name: String): FirstHierarchy.A = when (name) {
        "FirstHierarchy_A_B_C" -> FirstHierarchy.A.B.C
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    override fun sealedObjectToEnum(obj: FirstHierarchy.A): FirstHierarchy_AEnum = when (obj) {
        FirstHierarchy.A.B.C -> FirstHierarchy_AEnum.FirstHierarchy_A_B_C
    }

    override fun enumToSealedObject(enum: FirstHierarchy_AEnum): FirstHierarchy.A = when (enum) {
        FirstHierarchy_AEnum.FirstHierarchy_A_B_C -> FirstHierarchy.A.B.C
    }
}

/**
 * The index of [this] in the values list.
 */
val FirstHierarchy.A.ordinal: Int
    get() = FirstHierarchy_ASealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val FirstHierarchy.A.name: String
    get() = FirstHierarchy_ASealedEnum.nameOf(this)

/**
 * A list of all [FirstHierarchy.A] objects.
 */
val FirstHierarchy.A.Companion.values: List<FirstHierarchy.A>
    get() = FirstHierarchy_ASealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [FirstHierarchy.A]
 */
val FirstHierarchy.A.Companion.sealedEnum: FirstHierarchy_ASealedEnum
    get() = FirstHierarchy_ASealedEnum

/**
 * Returns the [FirstHierarchy.A] object for the given [name].
 *
 * If the given name doesn't correspond to any [FirstHierarchy.A], an [IllegalArgumentException]
 * will be thrown.
 */
fun FirstHierarchy.A.Companion.valueOf(name: String): FirstHierarchy.A =
        FirstHierarchy_ASealedEnum.valueOf(name)

""".trimIndent()

@Language("kotlin")
val firstHierarchyBGenerated = """
package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [FirstHierarchy.A.B]
 */
enum class FirstHierarchy_A_BEnum {
    FirstHierarchy_A_B_C
}

/**
 * The isomorphic [FirstHierarchy_A_BEnum] for [this].
 */
val FirstHierarchy.A.B.enum: FirstHierarchy_A_BEnum
    get() = FirstHierarchy_A_BSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [FirstHierarchy.A.B] for [this].
 */
val FirstHierarchy_A_BEnum.sealedObject: FirstHierarchy.A.B
    get() = FirstHierarchy_A_BSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [FirstHierarchy.A.B]
 */
object FirstHierarchy_A_BSealedEnum : SealedEnum<FirstHierarchy.A.B>,
        SealedEnumWithEnumProvider<FirstHierarchy.A.B, FirstHierarchy_A_BEnum>,
        EnumForSealedEnumProvider<FirstHierarchy.A.B, FirstHierarchy_A_BEnum> {
    override val values: List<FirstHierarchy.A.B> = listOf(
        FirstHierarchy.A.B.C
    )


    override val enumClass: Class<FirstHierarchy_A_BEnum>
        get() = FirstHierarchy_A_BEnum::class.java

    override fun ordinalOf(obj: FirstHierarchy.A.B): Int = when (obj) {
        FirstHierarchy.A.B.C -> 0
    }

    override fun nameOf(obj: FirstHierarchy.A.B): String = when (obj) {
        FirstHierarchy.A.B.C -> "FirstHierarchy_A_B_C"
    }

    override fun valueOf(name: String): FirstHierarchy.A.B = when (name) {
        "FirstHierarchy_A_B_C" -> FirstHierarchy.A.B.C
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    override fun sealedObjectToEnum(obj: FirstHierarchy.A.B): FirstHierarchy_A_BEnum = when (obj) {
        FirstHierarchy.A.B.C -> FirstHierarchy_A_BEnum.FirstHierarchy_A_B_C
    }

    override fun enumToSealedObject(enum: FirstHierarchy_A_BEnum): FirstHierarchy.A.B = when
            (enum) {
        FirstHierarchy_A_BEnum.FirstHierarchy_A_B_C -> FirstHierarchy.A.B.C
    }
}

/**
 * The index of [this] in the values list.
 */
val FirstHierarchy.A.B.ordinal: Int
    get() = FirstHierarchy_A_BSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val FirstHierarchy.A.B.name: String
    get() = FirstHierarchy_A_BSealedEnum.nameOf(this)

/**
 * A list of all [FirstHierarchy.A.B] objects.
 */
val FirstHierarchy.A.B.Companion.values: List<FirstHierarchy.A.B>
    get() = FirstHierarchy_A_BSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [FirstHierarchy.A.B]
 */
val FirstHierarchy.A.B.Companion.sealedEnum: FirstHierarchy_A_BSealedEnum
    get() = FirstHierarchy_A_BSealedEnum

/**
 * Returns the [FirstHierarchy.A.B] object for the given [name].
 *
 * If the given name doesn't correspond to any [FirstHierarchy.A.B], an [IllegalArgumentException]
 * will be thrown.
 */
fun FirstHierarchy.A.B.Companion.valueOf(name: String): FirstHierarchy.A.B =
        FirstHierarchy_A_BSealedEnum.valueOf(name)

""".trimIndent()

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

@Language("kotlin")
val secondHierarchyAGenerated = """
package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.SealedEnum
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An implementation of [SealedEnum] for the sealed class [SecondHierarchy.A]
 */
object SecondHierarchy_ASealedEnum : SealedEnum<SecondHierarchy.A> {
    override val values: List<SecondHierarchy.A> = listOf(
        SecondHierarchy.A.B,
        SecondHierarchy.A.C.D,
        SecondHierarchy.A.C.E,
        SecondHierarchy.A.C.F.G,
        SecondHierarchy.A.C.H.I,
        SecondHierarchy.A.J.K,
        SecondHierarchy.A.L
    )


    override fun ordinalOf(obj: SecondHierarchy.A): Int = when (obj) {
        SecondHierarchy.A.B -> 0
        SecondHierarchy.A.C.D -> 1
        SecondHierarchy.A.C.E -> 2
        SecondHierarchy.A.C.F.G -> 3
        SecondHierarchy.A.C.H.I -> 4
        SecondHierarchy.A.J.K -> 5
        SecondHierarchy.A.L -> 6
    }

    override fun nameOf(obj: SecondHierarchy.A): String = when (obj) {
        SecondHierarchy.A.B -> "SecondHierarchy_A_B"
        SecondHierarchy.A.C.D -> "SecondHierarchy_A_C_D"
        SecondHierarchy.A.C.E -> "SecondHierarchy_A_C_E"
        SecondHierarchy.A.C.F.G -> "SecondHierarchy_A_C_F_G"
        SecondHierarchy.A.C.H.I -> "SecondHierarchy_A_C_H_I"
        SecondHierarchy.A.J.K -> "SecondHierarchy_A_J_K"
        SecondHierarchy.A.L -> "SecondHierarchy_A_L"
    }

    override fun valueOf(name: String): SecondHierarchy.A = when (name) {
        "SecondHierarchy_A_B" -> SecondHierarchy.A.B
        "SecondHierarchy_A_C_D" -> SecondHierarchy.A.C.D
        "SecondHierarchy_A_C_E" -> SecondHierarchy.A.C.E
        "SecondHierarchy_A_C_F_G" -> SecondHierarchy.A.C.F.G
        "SecondHierarchy_A_C_H_I" -> SecondHierarchy.A.C.H.I
        "SecondHierarchy_A_J_K" -> SecondHierarchy.A.J.K
        "SecondHierarchy_A_L" -> SecondHierarchy.A.L
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }
}

/**
 * The index of [this] in the values list.
 */
val SecondHierarchy.A.ordinal: Int
    get() = SecondHierarchy_ASealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val SecondHierarchy.A.name: String
    get() = SecondHierarchy_ASealedEnum.nameOf(this)

/**
 * A list of all [SecondHierarchy.A] objects.
 */
val SecondHierarchy.A.Companion.values: List<SecondHierarchy.A>
    get() = SecondHierarchy_ASealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [SecondHierarchy.A]
 */
val SecondHierarchy.A.Companion.sealedEnum: SecondHierarchy_ASealedEnum
    get() = SecondHierarchy_ASealedEnum

/**
 * Returns the [SecondHierarchy.A] object for the given [name].
 *
 * If the given name doesn't correspond to any [SecondHierarchy.A], an [IllegalArgumentException]
 * will be thrown.
 */
fun SecondHierarchy.A.Companion.valueOf(name: String): SecondHierarchy.A =
        SecondHierarchy_ASealedEnum.valueOf(name)

""".trimIndent()

@Language("kotlin")
val secondHierarchyACGenerated = """
package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.SealedEnum
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An implementation of [SealedEnum] for the sealed class [SecondHierarchy.A.C]
 */
object SecondHierarchy_A_CSealedEnum : SealedEnum<SecondHierarchy.A.C> {
    override val values: List<SecondHierarchy.A.C> = listOf(
        SecondHierarchy.A.C.D,
        SecondHierarchy.A.C.E,
        SecondHierarchy.A.C.F.G,
        SecondHierarchy.A.C.H.I
    )


    override fun ordinalOf(obj: SecondHierarchy.A.C): Int = when (obj) {
        SecondHierarchy.A.C.D -> 0
        SecondHierarchy.A.C.E -> 1
        SecondHierarchy.A.C.F.G -> 2
        SecondHierarchy.A.C.H.I -> 3
    }

    override fun nameOf(obj: SecondHierarchy.A.C): String = when (obj) {
        SecondHierarchy.A.C.D -> "SecondHierarchy_A_C_D"
        SecondHierarchy.A.C.E -> "SecondHierarchy_A_C_E"
        SecondHierarchy.A.C.F.G -> "SecondHierarchy_A_C_F_G"
        SecondHierarchy.A.C.H.I -> "SecondHierarchy_A_C_H_I"
    }

    override fun valueOf(name: String): SecondHierarchy.A.C = when (name) {
        "SecondHierarchy_A_C_D" -> SecondHierarchy.A.C.D
        "SecondHierarchy_A_C_E" -> SecondHierarchy.A.C.E
        "SecondHierarchy_A_C_F_G" -> SecondHierarchy.A.C.F.G
        "SecondHierarchy_A_C_H_I" -> SecondHierarchy.A.C.H.I
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }
}

/**
 * The index of [this] in the values list.
 */
val SecondHierarchy.A.C.ordinal: Int
    get() = SecondHierarchy_A_CSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val SecondHierarchy.A.C.name: String
    get() = SecondHierarchy_A_CSealedEnum.nameOf(this)

/**
 * A list of all [SecondHierarchy.A.C] objects.
 */
val SecondHierarchy.A.C.Companion.values: List<SecondHierarchy.A.C>
    get() = SecondHierarchy_A_CSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [SecondHierarchy.A.C]
 */
val SecondHierarchy.A.C.Companion.sealedEnum: SecondHierarchy_A_CSealedEnum
    get() = SecondHierarchy_A_CSealedEnum

/**
 * Returns the [SecondHierarchy.A.C] object for the given [name].
 *
 * If the given name doesn't correspond to any [SecondHierarchy.A.C], an [IllegalArgumentException]
 * will be thrown.
 */
fun SecondHierarchy.A.C.Companion.valueOf(name: String): SecondHierarchy.A.C =
        SecondHierarchy_A_CSealedEnum.valueOf(name)

""".trimIndent()

@Language("kotlin")
val secondHierarchyACFGenerated = """
package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.SealedEnum
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An implementation of [SealedEnum] for the sealed class [SecondHierarchy.A.C.F]
 */
object SecondHierarchy_A_C_FSealedEnum : SealedEnum<SecondHierarchy.A.C.F> {
    override val values: List<SecondHierarchy.A.C.F> = listOf(
        SecondHierarchy.A.C.F.G
    )


    override fun ordinalOf(obj: SecondHierarchy.A.C.F): Int = when (obj) {
        SecondHierarchy.A.C.F.G -> 0
    }

    override fun nameOf(obj: SecondHierarchy.A.C.F): String = when (obj) {
        SecondHierarchy.A.C.F.G -> "SecondHierarchy_A_C_F_G"
    }

    override fun valueOf(name: String): SecondHierarchy.A.C.F = when (name) {
        "SecondHierarchy_A_C_F_G" -> SecondHierarchy.A.C.F.G
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }
}

/**
 * The index of [this] in the values list.
 */
val SecondHierarchy.A.C.F.ordinal: Int
    get() = SecondHierarchy_A_C_FSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val SecondHierarchy.A.C.F.name: String
    get() = SecondHierarchy_A_C_FSealedEnum.nameOf(this)

/**
 * A list of all [SecondHierarchy.A.C.F] objects.
 */
val SecondHierarchy.A.C.F.Companion.values: List<SecondHierarchy.A.C.F>
    get() = SecondHierarchy_A_C_FSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [SecondHierarchy.A.C.F]
 */
val SecondHierarchy.A.C.F.Companion.sealedEnum: SecondHierarchy_A_C_FSealedEnum
    get() = SecondHierarchy_A_C_FSealedEnum

/**
 * Returns the [SecondHierarchy.A.C.F] object for the given [name].
 *
 * If the given name doesn't correspond to any [SecondHierarchy.A.C.F], an
 * [IllegalArgumentException] will be thrown.
 */
fun SecondHierarchy.A.C.F.Companion.valueOf(name: String): SecondHierarchy.A.C.F =
        SecondHierarchy_A_C_FSealedEnum.valueOf(name)

""".trimIndent()

@Language("kotlin")
val secondHierarchyACHGenerated = """
package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.SealedEnum
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An implementation of [SealedEnum] for the sealed class [SecondHierarchy.A.C.H]
 */
object SecondHierarchy_A_C_HSealedEnum : SealedEnum<SecondHierarchy.A.C.H> {
    override val values: List<SecondHierarchy.A.C.H> = listOf(
        SecondHierarchy.A.C.H.I
    )


    override fun ordinalOf(obj: SecondHierarchy.A.C.H): Int = when (obj) {
        SecondHierarchy.A.C.H.I -> 0
    }

    override fun nameOf(obj: SecondHierarchy.A.C.H): String = when (obj) {
        SecondHierarchy.A.C.H.I -> "SecondHierarchy_A_C_H_I"
    }

    override fun valueOf(name: String): SecondHierarchy.A.C.H = when (name) {
        "SecondHierarchy_A_C_H_I" -> SecondHierarchy.A.C.H.I
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }
}

/**
 * The index of [this] in the values list.
 */
val SecondHierarchy.A.C.H.ordinal: Int
    get() = SecondHierarchy_A_C_HSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val SecondHierarchy.A.C.H.name: String
    get() = SecondHierarchy_A_C_HSealedEnum.nameOf(this)

/**
 * A list of all [SecondHierarchy.A.C.H] objects.
 */
val SecondHierarchy.A.C.H.Companion.values: List<SecondHierarchy.A.C.H>
    get() = SecondHierarchy_A_C_HSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [SecondHierarchy.A.C.H]
 */
val SecondHierarchy.A.C.H.Companion.sealedEnum: SecondHierarchy_A_C_HSealedEnum
    get() = SecondHierarchy_A_C_HSealedEnum

/**
 * Returns the [SecondHierarchy.A.C.H] object for the given [name].
 *
 * If the given name doesn't correspond to any [SecondHierarchy.A.C.H], an
 * [IllegalArgumentException] will be thrown.
 */
fun SecondHierarchy.A.C.H.Companion.valueOf(name: String): SecondHierarchy.A.C.H =
        SecondHierarchy_A_C_HSealedEnum.valueOf(name)

""".trimIndent()

@Language("kotlin")
val secondHierarchyAJGenerated = """
package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.SealedEnum
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An implementation of [SealedEnum] for the sealed class [SecondHierarchy.A.J]
 */
object SecondHierarchy_A_JSealedEnum : SealedEnum<SecondHierarchy.A.J> {
    override val values: List<SecondHierarchy.A.J> = listOf(
        SecondHierarchy.A.J.K
    )


    override fun ordinalOf(obj: SecondHierarchy.A.J): Int = when (obj) {
        SecondHierarchy.A.J.K -> 0
    }

    override fun nameOf(obj: SecondHierarchy.A.J): String = when (obj) {
        SecondHierarchy.A.J.K -> "SecondHierarchy_A_J_K"
    }

    override fun valueOf(name: String): SecondHierarchy.A.J = when (name) {
        "SecondHierarchy_A_J_K" -> SecondHierarchy.A.J.K
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }
}

/**
 * The index of [this] in the values list.
 */
val SecondHierarchy.A.J.ordinal: Int
    get() = SecondHierarchy_A_JSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val SecondHierarchy.A.J.name: String
    get() = SecondHierarchy_A_JSealedEnum.nameOf(this)

/**
 * A list of all [SecondHierarchy.A.J] objects.
 */
val SecondHierarchy.A.J.Companion.values: List<SecondHierarchy.A.J>
    get() = SecondHierarchy_A_JSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [SecondHierarchy.A.J]
 */
val SecondHierarchy.A.J.Companion.sealedEnum: SecondHierarchy_A_JSealedEnum
    get() = SecondHierarchy_A_JSealedEnum

/**
 * Returns the [SecondHierarchy.A.J] object for the given [name].
 *
 * If the given name doesn't correspond to any [SecondHierarchy.A.J], an [IllegalArgumentException]
 * will be thrown.
 */
fun SecondHierarchy.A.J.Companion.valueOf(name: String): SecondHierarchy.A.J =
        SecondHierarchy_A_JSealedEnum.valueOf(name)

""".trimIndent()
