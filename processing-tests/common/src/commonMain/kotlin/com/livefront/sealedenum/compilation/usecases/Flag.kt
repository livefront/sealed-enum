package com.livefront.sealedenum.compilation.usecases

import com.livefront.sealedenum.GenSealedEnum

public sealed class Flag {
    public val i: Int = 1 shl ordinal
    public object FirstFlag : Flag()

    public object SecondFlag : Flag()

    @GenSealedEnum(generateEnum = true)
    public companion object
}

public val flagGenerated: String = """
package com.livefront.sealedenum.compilation.usecases

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import kotlin.Int
import kotlin.LazyThreadSafetyMode
import kotlin.String
import kotlin.collections.List
import kotlin.reflect.KClass

/**
 * An isomorphic enum for the sealed class [Flag]
 */
public enum class FlagEnum() {
    Flag_FirstFlag,
    Flag_SecondFlag,
}

/**
 * The isomorphic [FlagEnum] for [this].
 */
public val Flag.`enum`: FlagEnum
    get() = FlagSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [Flag] for [this].
 */
public val FlagEnum.sealedObject: Flag
    get() = FlagSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [Flag]
 */
public object FlagSealedEnum : SealedEnum<Flag>, SealedEnumWithEnumProvider<Flag, FlagEnum>,
        EnumForSealedEnumProvider<Flag, FlagEnum> {
    public override val values: List<Flag> by lazy(mode = LazyThreadSafetyMode.PUBLICATION) {
        listOf(
            Flag.FirstFlag,
            Flag.SecondFlag
        )
    }


    public override val enumClass: KClass<FlagEnum>
        get() = FlagEnum::class

    public override fun ordinalOf(obj: Flag): Int = when (obj) {
        is Flag.FirstFlag -> 0
        is Flag.SecondFlag -> 1
    }

    public override fun nameOf(obj: Flag): String = when (obj) {
        is Flag.FirstFlag -> "Flag_FirstFlag"
        is Flag.SecondFlag -> "Flag_SecondFlag"
    }

    public override fun valueOf(name: String): Flag = when (name) {
        "Flag_FirstFlag" -> Flag.FirstFlag
        "Flag_SecondFlag" -> Flag.SecondFlag
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: Flag): FlagEnum = when (obj) {
        is Flag.FirstFlag -> FlagEnum.Flag_FirstFlag
        is Flag.SecondFlag -> FlagEnum.Flag_SecondFlag
    }

    public override fun enumToSealedObject(`enum`: FlagEnum): Flag = when (enum) {
        FlagEnum.Flag_FirstFlag -> Flag.FirstFlag
        FlagEnum.Flag_SecondFlag -> Flag.SecondFlag
    }
}

/**
 * The index of [this] in the values list.
 */
public val Flag.ordinal: Int
    get() = FlagSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val Flag.name: String
    get() = FlagSealedEnum.nameOf(this)

/**
 * A list of all [Flag] objects.
 */
public val Flag.Companion.values: List<Flag>
    get() = FlagSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [Flag]
 */
public val Flag.Companion.sealedEnum: FlagSealedEnum
    get() = FlagSealedEnum

/**
 * Returns the [Flag] object for the given [name].
 *
 * If the given name doesn't correspond to any [Flag], an [IllegalArgumentException] will be thrown.
 */
public fun Flag.Companion.valueOf(name: String): Flag = FlagSealedEnum.valueOf(name)

""".trimIndent()
