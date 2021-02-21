package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.GenSealedEnum
import org.intellij.lang.annotations.Language

class FirstInterfaceHierarchy {

    sealed interface A {

        sealed interface B : A {
            object C : B

            @GenSealedEnum(generateEnum = true)
            companion object
        }

        @GenSealedEnum(generateEnum = true)
        companion object
    }
}

@Language("kotlin")
val firstInterfaceHierarchyAGenerated = """
package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [FirstInterfaceHierarchy.A]
 */
public enum class FirstInterfaceHierarchy_AEnum {
    FirstInterfaceHierarchy_A_B_C,
}

/**
 * The isomorphic [FirstInterfaceHierarchy_AEnum] for [this].
 */
public val FirstInterfaceHierarchy.A.`enum`: FirstInterfaceHierarchy_AEnum
    get() = FirstInterfaceHierarchy_ASealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [FirstInterfaceHierarchy.A] for [this].
 */
public val FirstInterfaceHierarchy_AEnum.sealedObject: FirstInterfaceHierarchy.A
    get() = FirstInterfaceHierarchy_ASealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [FirstInterfaceHierarchy.A]
 */
public object FirstInterfaceHierarchy_ASealedEnum : SealedEnum<FirstInterfaceHierarchy.A>,
        SealedEnumWithEnumProvider<FirstInterfaceHierarchy.A, FirstInterfaceHierarchy_AEnum>,
        EnumForSealedEnumProvider<FirstInterfaceHierarchy.A, FirstInterfaceHierarchy_AEnum> {
    public override val values: List<FirstInterfaceHierarchy.A> = listOf(
        FirstInterfaceHierarchy.A.B.C
    )


    public override val enumClass: Class<FirstInterfaceHierarchy_AEnum>
        get() = FirstInterfaceHierarchy_AEnum::class.java

    public override fun ordinalOf(obj: FirstInterfaceHierarchy.A): Int = when (obj) {
        FirstInterfaceHierarchy.A.B.C -> 0
    }

    public override fun nameOf(obj: FirstInterfaceHierarchy.A): String = when (obj) {
        FirstInterfaceHierarchy.A.B.C -> "FirstInterfaceHierarchy_A_B_C"
    }

    public override fun valueOf(name: String): FirstInterfaceHierarchy.A = when (name) {
        "FirstInterfaceHierarchy_A_B_C" -> FirstInterfaceHierarchy.A.B.C
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: FirstInterfaceHierarchy.A):
            FirstInterfaceHierarchy_AEnum = when (obj) {
        FirstInterfaceHierarchy.A.B.C -> FirstInterfaceHierarchy_AEnum.FirstInterfaceHierarchy_A_B_C
    }

    public override fun enumToSealedObject(`enum`: FirstInterfaceHierarchy_AEnum):
            FirstInterfaceHierarchy.A = when (enum) {
        FirstInterfaceHierarchy_AEnum.FirstInterfaceHierarchy_A_B_C -> FirstInterfaceHierarchy.A.B.C
    }
}

/**
 * The index of [this] in the values list.
 */
public val FirstInterfaceHierarchy.A.ordinal: Int
    get() = FirstInterfaceHierarchy_ASealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val FirstInterfaceHierarchy.A.name: String
    get() = FirstInterfaceHierarchy_ASealedEnum.nameOf(this)

/**
 * A list of all [FirstInterfaceHierarchy.A] objects.
 */
public val FirstInterfaceHierarchy.A.Companion.values: List<FirstInterfaceHierarchy.A>
    get() = FirstInterfaceHierarchy_ASealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [FirstInterfaceHierarchy.A]
 */
public val FirstInterfaceHierarchy.A.Companion.sealedEnum: FirstInterfaceHierarchy_ASealedEnum
    get() = FirstInterfaceHierarchy_ASealedEnum

/**
 * Returns the [FirstInterfaceHierarchy.A] object for the given [name].
 *
 * If the given name doesn't correspond to any [FirstInterfaceHierarchy.A], an
 * [IllegalArgumentException] will be thrown.
 */
public fun FirstInterfaceHierarchy.A.Companion.valueOf(name: String): FirstInterfaceHierarchy.A =
        FirstInterfaceHierarchy_ASealedEnum.valueOf(name)

""".trimIndent()

@Language("kotlin")
val firstInterfaceHierarchyBGenerated = """
package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [FirstInterfaceHierarchy.A.B]
 */
public enum class FirstInterfaceHierarchy_A_BEnum {
    FirstInterfaceHierarchy_A_B_C,
}

/**
 * The isomorphic [FirstInterfaceHierarchy_A_BEnum] for [this].
 */
public val FirstInterfaceHierarchy.A.B.`enum`: FirstInterfaceHierarchy_A_BEnum
    get() = FirstInterfaceHierarchy_A_BSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [FirstInterfaceHierarchy.A.B] for [this].
 */
public val FirstInterfaceHierarchy_A_BEnum.sealedObject: FirstInterfaceHierarchy.A.B
    get() = FirstInterfaceHierarchy_A_BSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [FirstInterfaceHierarchy.A.B]
 */
public object FirstInterfaceHierarchy_A_BSealedEnum : SealedEnum<FirstInterfaceHierarchy.A.B>,
        SealedEnumWithEnumProvider<FirstInterfaceHierarchy.A.B, FirstInterfaceHierarchy_A_BEnum>,
        EnumForSealedEnumProvider<FirstInterfaceHierarchy.A.B, FirstInterfaceHierarchy_A_BEnum> {
    public override val values: List<FirstInterfaceHierarchy.A.B> = listOf(
        FirstInterfaceHierarchy.A.B.C
    )


    public override val enumClass: Class<FirstInterfaceHierarchy_A_BEnum>
        get() = FirstInterfaceHierarchy_A_BEnum::class.java

    public override fun ordinalOf(obj: FirstInterfaceHierarchy.A.B): Int = when (obj) {
        FirstInterfaceHierarchy.A.B.C -> 0
    }

    public override fun nameOf(obj: FirstInterfaceHierarchy.A.B): String = when (obj) {
        FirstInterfaceHierarchy.A.B.C -> "FirstInterfaceHierarchy_A_B_C"
    }

    public override fun valueOf(name: String): FirstInterfaceHierarchy.A.B = when (name) {
        "FirstInterfaceHierarchy_A_B_C" -> FirstInterfaceHierarchy.A.B.C
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: FirstInterfaceHierarchy.A.B):
            FirstInterfaceHierarchy_A_BEnum = when (obj) {
        FirstInterfaceHierarchy.A.B.C ->
                FirstInterfaceHierarchy_A_BEnum.FirstInterfaceHierarchy_A_B_C
    }

    public override fun enumToSealedObject(`enum`: FirstInterfaceHierarchy_A_BEnum):
            FirstInterfaceHierarchy.A.B = when (enum) {
        FirstInterfaceHierarchy_A_BEnum.FirstInterfaceHierarchy_A_B_C ->
                FirstInterfaceHierarchy.A.B.C
    }
}

/**
 * The index of [this] in the values list.
 */
public val FirstInterfaceHierarchy.A.B.ordinal: Int
    get() = FirstInterfaceHierarchy_A_BSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val FirstInterfaceHierarchy.A.B.name: String
    get() = FirstInterfaceHierarchy_A_BSealedEnum.nameOf(this)

/**
 * A list of all [FirstInterfaceHierarchy.A.B] objects.
 */
public val FirstInterfaceHierarchy.A.B.Companion.values: List<FirstInterfaceHierarchy.A.B>
    get() = FirstInterfaceHierarchy_A_BSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [FirstInterfaceHierarchy.A.B]
 */
public val FirstInterfaceHierarchy.A.B.Companion.sealedEnum: FirstInterfaceHierarchy_A_BSealedEnum
    get() = FirstInterfaceHierarchy_A_BSealedEnum

/**
 * Returns the [FirstInterfaceHierarchy.A.B] object for the given [name].
 *
 * If the given name doesn't correspond to any [FirstInterfaceHierarchy.A.B], an
 * [IllegalArgumentException] will be thrown.
 */
public fun FirstInterfaceHierarchy.A.B.Companion.valueOf(name: String): FirstInterfaceHierarchy.A.B
        = FirstInterfaceHierarchy_A_BSealedEnum.valueOf(name)

""".trimIndent()

class SecondInterfaceHierarchy {

    sealed interface A {

        object B : A

        sealed interface C : A {

            object D : C

            object E : C

            sealed interface F : C {
                object G : F

                @GenSealedEnum
                companion object
            }

            sealed interface H : C {
                object I : H

                @GenSealedEnum
                companion object
            }

            @GenSealedEnum
            companion object
        }

        sealed interface J : A {

            object K : J

            @GenSealedEnum
            companion object
        }

        object L : A

        @GenSealedEnum
        companion object
    }
}

@Language("kotlin")
val secondInterfaceHierarchyAGenerated = """
package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.SealedEnum
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An implementation of [SealedEnum] for the sealed class [SecondInterfaceHierarchy.A]
 */
public object SecondInterfaceHierarchy_ASealedEnum : SealedEnum<SecondInterfaceHierarchy.A> {
    public override val values: List<SecondInterfaceHierarchy.A> = listOf(
        SecondInterfaceHierarchy.A.B,
        SecondInterfaceHierarchy.A.C.D,
        SecondInterfaceHierarchy.A.C.E,
        SecondInterfaceHierarchy.A.C.F.G,
        SecondInterfaceHierarchy.A.C.H.I,
        SecondInterfaceHierarchy.A.J.K,
        SecondInterfaceHierarchy.A.L
    )


    public override fun ordinalOf(obj: SecondInterfaceHierarchy.A): Int = when (obj) {
        SecondInterfaceHierarchy.A.B -> 0
        SecondInterfaceHierarchy.A.C.D -> 1
        SecondInterfaceHierarchy.A.C.E -> 2
        SecondInterfaceHierarchy.A.C.F.G -> 3
        SecondInterfaceHierarchy.A.C.H.I -> 4
        SecondInterfaceHierarchy.A.J.K -> 5
        SecondInterfaceHierarchy.A.L -> 6
    }

    public override fun nameOf(obj: SecondInterfaceHierarchy.A): String = when (obj) {
        SecondInterfaceHierarchy.A.B -> "SecondInterfaceHierarchy_A_B"
        SecondInterfaceHierarchy.A.C.D -> "SecondInterfaceHierarchy_A_C_D"
        SecondInterfaceHierarchy.A.C.E -> "SecondInterfaceHierarchy_A_C_E"
        SecondInterfaceHierarchy.A.C.F.G -> "SecondInterfaceHierarchy_A_C_F_G"
        SecondInterfaceHierarchy.A.C.H.I -> "SecondInterfaceHierarchy_A_C_H_I"
        SecondInterfaceHierarchy.A.J.K -> "SecondInterfaceHierarchy_A_J_K"
        SecondInterfaceHierarchy.A.L -> "SecondInterfaceHierarchy_A_L"
    }

    public override fun valueOf(name: String): SecondInterfaceHierarchy.A = when (name) {
        "SecondInterfaceHierarchy_A_B" -> SecondInterfaceHierarchy.A.B
        "SecondInterfaceHierarchy_A_C_D" -> SecondInterfaceHierarchy.A.C.D
        "SecondInterfaceHierarchy_A_C_E" -> SecondInterfaceHierarchy.A.C.E
        "SecondInterfaceHierarchy_A_C_F_G" -> SecondInterfaceHierarchy.A.C.F.G
        "SecondInterfaceHierarchy_A_C_H_I" -> SecondInterfaceHierarchy.A.C.H.I
        "SecondInterfaceHierarchy_A_J_K" -> SecondInterfaceHierarchy.A.J.K
        "SecondInterfaceHierarchy_A_L" -> SecondInterfaceHierarchy.A.L
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }
}

/**
 * The index of [this] in the values list.
 */
public val SecondInterfaceHierarchy.A.ordinal: Int
    get() = SecondInterfaceHierarchy_ASealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val SecondInterfaceHierarchy.A.name: String
    get() = SecondInterfaceHierarchy_ASealedEnum.nameOf(this)

/**
 * A list of all [SecondInterfaceHierarchy.A] objects.
 */
public val SecondInterfaceHierarchy.A.Companion.values: List<SecondInterfaceHierarchy.A>
    get() = SecondInterfaceHierarchy_ASealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [SecondInterfaceHierarchy.A]
 */
public val SecondInterfaceHierarchy.A.Companion.sealedEnum: SecondInterfaceHierarchy_ASealedEnum
    get() = SecondInterfaceHierarchy_ASealedEnum

/**
 * Returns the [SecondInterfaceHierarchy.A] object for the given [name].
 *
 * If the given name doesn't correspond to any [SecondInterfaceHierarchy.A], an
 * [IllegalArgumentException] will be thrown.
 */
public fun SecondInterfaceHierarchy.A.Companion.valueOf(name: String): SecondInterfaceHierarchy.A =
        SecondInterfaceHierarchy_ASealedEnum.valueOf(name)

""".trimIndent()

@Language("kotlin")
val secondInterfaceHierarchyACGenerated = """
package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.SealedEnum
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An implementation of [SealedEnum] for the sealed class [SecondInterfaceHierarchy.A.C]
 */
public object SecondInterfaceHierarchy_A_CSealedEnum : SealedEnum<SecondInterfaceHierarchy.A.C> {
    public override val values: List<SecondInterfaceHierarchy.A.C> = listOf(
        SecondInterfaceHierarchy.A.C.D,
        SecondInterfaceHierarchy.A.C.E,
        SecondInterfaceHierarchy.A.C.F.G,
        SecondInterfaceHierarchy.A.C.H.I
    )


    public override fun ordinalOf(obj: SecondInterfaceHierarchy.A.C): Int = when (obj) {
        SecondInterfaceHierarchy.A.C.D -> 0
        SecondInterfaceHierarchy.A.C.E -> 1
        SecondInterfaceHierarchy.A.C.F.G -> 2
        SecondInterfaceHierarchy.A.C.H.I -> 3
    }

    public override fun nameOf(obj: SecondInterfaceHierarchy.A.C): String = when (obj) {
        SecondInterfaceHierarchy.A.C.D -> "SecondInterfaceHierarchy_A_C_D"
        SecondInterfaceHierarchy.A.C.E -> "SecondInterfaceHierarchy_A_C_E"
        SecondInterfaceHierarchy.A.C.F.G -> "SecondInterfaceHierarchy_A_C_F_G"
        SecondInterfaceHierarchy.A.C.H.I -> "SecondInterfaceHierarchy_A_C_H_I"
    }

    public override fun valueOf(name: String): SecondInterfaceHierarchy.A.C = when (name) {
        "SecondInterfaceHierarchy_A_C_D" -> SecondInterfaceHierarchy.A.C.D
        "SecondInterfaceHierarchy_A_C_E" -> SecondInterfaceHierarchy.A.C.E
        "SecondInterfaceHierarchy_A_C_F_G" -> SecondInterfaceHierarchy.A.C.F.G
        "SecondInterfaceHierarchy_A_C_H_I" -> SecondInterfaceHierarchy.A.C.H.I
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }
}

/**
 * The index of [this] in the values list.
 */
public val SecondInterfaceHierarchy.A.C.ordinal: Int
    get() = SecondInterfaceHierarchy_A_CSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val SecondInterfaceHierarchy.A.C.name: String
    get() = SecondInterfaceHierarchy_A_CSealedEnum.nameOf(this)

/**
 * A list of all [SecondInterfaceHierarchy.A.C] objects.
 */
public val SecondInterfaceHierarchy.A.C.Companion.values: List<SecondInterfaceHierarchy.A.C>
    get() = SecondInterfaceHierarchy_A_CSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [SecondInterfaceHierarchy.A.C]
 */
public val SecondInterfaceHierarchy.A.C.Companion.sealedEnum: SecondInterfaceHierarchy_A_CSealedEnum
    get() = SecondInterfaceHierarchy_A_CSealedEnum

/**
 * Returns the [SecondInterfaceHierarchy.A.C] object for the given [name].
 *
 * If the given name doesn't correspond to any [SecondInterfaceHierarchy.A.C], an
 * [IllegalArgumentException] will be thrown.
 */
public fun SecondInterfaceHierarchy.A.C.Companion.valueOf(name: String):
        SecondInterfaceHierarchy.A.C = SecondInterfaceHierarchy_A_CSealedEnum.valueOf(name)

""".trimIndent()

@Language("kotlin")
val secondInterfaceHierarchyACFGenerated = """
package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.SealedEnum
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An implementation of [SealedEnum] for the sealed class [SecondInterfaceHierarchy.A.C.F]
 */
public object SecondInterfaceHierarchy_A_C_FSealedEnum : SealedEnum<SecondInterfaceHierarchy.A.C.F>
        {
    public override val values: List<SecondInterfaceHierarchy.A.C.F> = listOf(
        SecondInterfaceHierarchy.A.C.F.G
    )


    public override fun ordinalOf(obj: SecondInterfaceHierarchy.A.C.F): Int = when (obj) {
        SecondInterfaceHierarchy.A.C.F.G -> 0
    }

    public override fun nameOf(obj: SecondInterfaceHierarchy.A.C.F): String = when (obj) {
        SecondInterfaceHierarchy.A.C.F.G -> "SecondInterfaceHierarchy_A_C_F_G"
    }

    public override fun valueOf(name: String): SecondInterfaceHierarchy.A.C.F = when (name) {
        "SecondInterfaceHierarchy_A_C_F_G" -> SecondInterfaceHierarchy.A.C.F.G
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }
}

/**
 * The index of [this] in the values list.
 */
public val SecondInterfaceHierarchy.A.C.F.ordinal: Int
    get() = SecondInterfaceHierarchy_A_C_FSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val SecondInterfaceHierarchy.A.C.F.name: String
    get() = SecondInterfaceHierarchy_A_C_FSealedEnum.nameOf(this)

/**
 * A list of all [SecondInterfaceHierarchy.A.C.F] objects.
 */
public val SecondInterfaceHierarchy.A.C.F.Companion.values: List<SecondInterfaceHierarchy.A.C.F>
    get() = SecondInterfaceHierarchy_A_C_FSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [SecondInterfaceHierarchy.A.C.F]
 */
public val SecondInterfaceHierarchy.A.C.F.Companion.sealedEnum:
        SecondInterfaceHierarchy_A_C_FSealedEnum
    get() = SecondInterfaceHierarchy_A_C_FSealedEnum

/**
 * Returns the [SecondInterfaceHierarchy.A.C.F] object for the given [name].
 *
 * If the given name doesn't correspond to any [SecondInterfaceHierarchy.A.C.F], an
 * [IllegalArgumentException] will be thrown.
 */
public fun SecondInterfaceHierarchy.A.C.F.Companion.valueOf(name: String):
        SecondInterfaceHierarchy.A.C.F = SecondInterfaceHierarchy_A_C_FSealedEnum.valueOf(name)

""".trimIndent()

@Language("kotlin")
val secondInterfaceHierarchyACHGenerated = """
package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.SealedEnum
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An implementation of [SealedEnum] for the sealed class [SecondInterfaceHierarchy.A.C.H]
 */
public object SecondInterfaceHierarchy_A_C_HSealedEnum : SealedEnum<SecondInterfaceHierarchy.A.C.H>
        {
    public override val values: List<SecondInterfaceHierarchy.A.C.H> = listOf(
        SecondInterfaceHierarchy.A.C.H.I
    )


    public override fun ordinalOf(obj: SecondInterfaceHierarchy.A.C.H): Int = when (obj) {
        SecondInterfaceHierarchy.A.C.H.I -> 0
    }

    public override fun nameOf(obj: SecondInterfaceHierarchy.A.C.H): String = when (obj) {
        SecondInterfaceHierarchy.A.C.H.I -> "SecondInterfaceHierarchy_A_C_H_I"
    }

    public override fun valueOf(name: String): SecondInterfaceHierarchy.A.C.H = when (name) {
        "SecondInterfaceHierarchy_A_C_H_I" -> SecondInterfaceHierarchy.A.C.H.I
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }
}

/**
 * The index of [this] in the values list.
 */
public val SecondInterfaceHierarchy.A.C.H.ordinal: Int
    get() = SecondInterfaceHierarchy_A_C_HSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val SecondInterfaceHierarchy.A.C.H.name: String
    get() = SecondInterfaceHierarchy_A_C_HSealedEnum.nameOf(this)

/**
 * A list of all [SecondInterfaceHierarchy.A.C.H] objects.
 */
public val SecondInterfaceHierarchy.A.C.H.Companion.values: List<SecondInterfaceHierarchy.A.C.H>
    get() = SecondInterfaceHierarchy_A_C_HSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [SecondInterfaceHierarchy.A.C.H]
 */
public val SecondInterfaceHierarchy.A.C.H.Companion.sealedEnum:
        SecondInterfaceHierarchy_A_C_HSealedEnum
    get() = SecondInterfaceHierarchy_A_C_HSealedEnum

/**
 * Returns the [SecondInterfaceHierarchy.A.C.H] object for the given [name].
 *
 * If the given name doesn't correspond to any [SecondInterfaceHierarchy.A.C.H], an
 * [IllegalArgumentException] will be thrown.
 */
public fun SecondInterfaceHierarchy.A.C.H.Companion.valueOf(name: String):
        SecondInterfaceHierarchy.A.C.H = SecondInterfaceHierarchy_A_C_HSealedEnum.valueOf(name)

""".trimIndent()

@Language("kotlin")
val secondInterfaceHierarchyAJGenerated = """
package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.SealedEnum
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An implementation of [SealedEnum] for the sealed class [SecondInterfaceHierarchy.A.J]
 */
public object SecondInterfaceHierarchy_A_JSealedEnum : SealedEnum<SecondInterfaceHierarchy.A.J> {
    public override val values: List<SecondInterfaceHierarchy.A.J> = listOf(
        SecondInterfaceHierarchy.A.J.K
    )


    public override fun ordinalOf(obj: SecondInterfaceHierarchy.A.J): Int = when (obj) {
        SecondInterfaceHierarchy.A.J.K -> 0
    }

    public override fun nameOf(obj: SecondInterfaceHierarchy.A.J): String = when (obj) {
        SecondInterfaceHierarchy.A.J.K -> "SecondInterfaceHierarchy_A_J_K"
    }

    public override fun valueOf(name: String): SecondInterfaceHierarchy.A.J = when (name) {
        "SecondInterfaceHierarchy_A_J_K" -> SecondInterfaceHierarchy.A.J.K
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }
}

/**
 * The index of [this] in the values list.
 */
public val SecondInterfaceHierarchy.A.J.ordinal: Int
    get() = SecondInterfaceHierarchy_A_JSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val SecondInterfaceHierarchy.A.J.name: String
    get() = SecondInterfaceHierarchy_A_JSealedEnum.nameOf(this)

/**
 * A list of all [SecondInterfaceHierarchy.A.J] objects.
 */
public val SecondInterfaceHierarchy.A.J.Companion.values: List<SecondInterfaceHierarchy.A.J>
    get() = SecondInterfaceHierarchy_A_JSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [SecondInterfaceHierarchy.A.J]
 */
public val SecondInterfaceHierarchy.A.J.Companion.sealedEnum: SecondInterfaceHierarchy_A_JSealedEnum
    get() = SecondInterfaceHierarchy_A_JSealedEnum

/**
 * Returns the [SecondInterfaceHierarchy.A.J] object for the given [name].
 *
 * If the given name doesn't correspond to any [SecondInterfaceHierarchy.A.J], an
 * [IllegalArgumentException] will be thrown.
 */
public fun SecondInterfaceHierarchy.A.J.Companion.valueOf(name: String):
        SecondInterfaceHierarchy.A.J = SecondInterfaceHierarchy_A_JSealedEnum.valueOf(name)

""".trimIndent()
