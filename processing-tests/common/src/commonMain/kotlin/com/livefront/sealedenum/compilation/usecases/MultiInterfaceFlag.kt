package com.livefront.sealedenum.compilation.usecases

import com.livefront.sealedenum.GenSealedEnum

public sealed interface MultiInterfaceFlag {
    public sealed interface FirstInterface : MultiInterfaceFlag
    public sealed interface SecondInterface : MultiInterfaceFlag
    public object FirstFlag : FirstInterface
    public object SecondFlag : SecondInterface
    public object BothFlags : FirstInterface, SecondInterface

    @GenSealedEnum(generateEnum = true)
    public companion object
}

public val multiInterfaceFlagGenerated: String = """
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
 * An isomorphic enum for the sealed class [MultiInterfaceFlag]
 */
public enum class MultiInterfaceFlagEnum() {
    MultiInterfaceFlag_BothFlags,
    MultiInterfaceFlag_FirstFlag,
    MultiInterfaceFlag_SecondFlag,
}

/**
 * The isomorphic [MultiInterfaceFlagEnum] for [this].
 */
public val MultiInterfaceFlag.`enum`: MultiInterfaceFlagEnum
    get() = MultiInterfaceFlagSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [MultiInterfaceFlag] for [this].
 */
public val MultiInterfaceFlagEnum.sealedObject: MultiInterfaceFlag
    get() = MultiInterfaceFlagSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [MultiInterfaceFlag]
 */
public object MultiInterfaceFlagSealedEnum : SealedEnum<MultiInterfaceFlag>,
        SealedEnumWithEnumProvider<MultiInterfaceFlag, MultiInterfaceFlagEnum>,
        EnumForSealedEnumProvider<MultiInterfaceFlag, MultiInterfaceFlagEnum> {
    public override val values: List<MultiInterfaceFlag> by lazy(mode =
            LazyThreadSafetyMode.PUBLICATION) {
        listOf(
            MultiInterfaceFlag.BothFlags,
            MultiInterfaceFlag.FirstFlag,
            MultiInterfaceFlag.SecondFlag
        )
    }


    public override val enumClass: KClass<MultiInterfaceFlagEnum>
        get() = MultiInterfaceFlagEnum::class

    public override fun ordinalOf(obj: MultiInterfaceFlag): Int = when (obj) {
        is MultiInterfaceFlag.BothFlags -> 0
        is MultiInterfaceFlag.FirstFlag -> 1
        is MultiInterfaceFlag.SecondFlag -> 2
    }

    public override fun nameOf(obj: MultiInterfaceFlag): String = when (obj) {
        is MultiInterfaceFlag.BothFlags -> "MultiInterfaceFlag_BothFlags"
        is MultiInterfaceFlag.FirstFlag -> "MultiInterfaceFlag_FirstFlag"
        is MultiInterfaceFlag.SecondFlag -> "MultiInterfaceFlag_SecondFlag"
    }

    public override fun valueOf(name: String): MultiInterfaceFlag = when (name) {
        "MultiInterfaceFlag_BothFlags" -> MultiInterfaceFlag.BothFlags
        "MultiInterfaceFlag_FirstFlag" -> MultiInterfaceFlag.FirstFlag
        "MultiInterfaceFlag_SecondFlag" -> MultiInterfaceFlag.SecondFlag
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: MultiInterfaceFlag): MultiInterfaceFlagEnum = when
            (obj) {
        is MultiInterfaceFlag.BothFlags -> MultiInterfaceFlagEnum.MultiInterfaceFlag_BothFlags
        is MultiInterfaceFlag.FirstFlag -> MultiInterfaceFlagEnum.MultiInterfaceFlag_FirstFlag
        is MultiInterfaceFlag.SecondFlag -> MultiInterfaceFlagEnum.MultiInterfaceFlag_SecondFlag
    }

    public override fun enumToSealedObject(`enum`: MultiInterfaceFlagEnum): MultiInterfaceFlag =
            when (enum) {
        MultiInterfaceFlagEnum.MultiInterfaceFlag_BothFlags -> MultiInterfaceFlag.BothFlags
        MultiInterfaceFlagEnum.MultiInterfaceFlag_FirstFlag -> MultiInterfaceFlag.FirstFlag
        MultiInterfaceFlagEnum.MultiInterfaceFlag_SecondFlag -> MultiInterfaceFlag.SecondFlag
    }
}

/**
 * The index of [this] in the values list.
 */
public val MultiInterfaceFlag.ordinal: Int
    get() = MultiInterfaceFlagSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val MultiInterfaceFlag.name: String
    get() = MultiInterfaceFlagSealedEnum.nameOf(this)

/**
 * A list of all [MultiInterfaceFlag] objects.
 */
public val MultiInterfaceFlag.Companion.values: List<MultiInterfaceFlag>
    get() = MultiInterfaceFlagSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [MultiInterfaceFlag]
 */
public val MultiInterfaceFlag.Companion.sealedEnum: MultiInterfaceFlagSealedEnum
    get() = MultiInterfaceFlagSealedEnum

/**
 * Returns the [MultiInterfaceFlag] object for the given [name].
 *
 * If the given name doesn't correspond to any [MultiInterfaceFlag], an [IllegalArgumentException]
 * will be thrown.
 */
public fun MultiInterfaceFlag.Companion.valueOf(name: String): MultiInterfaceFlag =
        MultiInterfaceFlagSealedEnum.valueOf(name)

""".trimIndent()
