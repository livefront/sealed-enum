package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.GenSealedEnum

public sealed interface TwoObjectSealedInterface {
    public object FirstObject : TwoObjectSealedInterface

    public object SecondObject : TwoObjectSealedInterface

    @GenSealedEnum(generateEnum = true)
    public companion object
}

public val twoObjectSealedInterfaceGenerated: String = """
package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import kotlin.Int
import kotlin.LazyThreadSafetyMode
import kotlin.String
import kotlin.collections.List
import kotlin.reflect.KClass

/**
 * An isomorphic enum for the sealed class [TwoObjectSealedInterface]
 */
public enum class TwoObjectSealedInterfaceEnum() {
    TwoObjectSealedInterface_FirstObject,
    TwoObjectSealedInterface_SecondObject,
}

/**
 * The isomorphic [TwoObjectSealedInterfaceEnum] for [this].
 */
public val TwoObjectSealedInterface.`enum`: TwoObjectSealedInterfaceEnum
    get() = TwoObjectSealedInterfaceSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [TwoObjectSealedInterface] for [this].
 */
public val TwoObjectSealedInterfaceEnum.sealedObject: TwoObjectSealedInterface
    get() = TwoObjectSealedInterfaceSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [TwoObjectSealedInterface]
 */
public object TwoObjectSealedInterfaceSealedEnum : SealedEnum<TwoObjectSealedInterface>,
        SealedEnumWithEnumProvider<TwoObjectSealedInterface, TwoObjectSealedInterfaceEnum>,
        EnumForSealedEnumProvider<TwoObjectSealedInterface, TwoObjectSealedInterfaceEnum> {
    public override val values: List<TwoObjectSealedInterface> by lazy(mode =
            LazyThreadSafetyMode.PUBLICATION) {
        listOf(
            TwoObjectSealedInterface.FirstObject,
            TwoObjectSealedInterface.SecondObject
        )
    }


    public override val enumClass: KClass<TwoObjectSealedInterfaceEnum>
        get() = TwoObjectSealedInterfaceEnum::class

    public override fun ordinalOf(obj: TwoObjectSealedInterface): Int = when (obj) {
        is TwoObjectSealedInterface.FirstObject -> 0
        is TwoObjectSealedInterface.SecondObject -> 1
    }

    public override fun nameOf(obj: TwoObjectSealedInterface): String = when (obj) {
        is TwoObjectSealedInterface.FirstObject -> "TwoObjectSealedInterface_FirstObject"
        is TwoObjectSealedInterface.SecondObject -> "TwoObjectSealedInterface_SecondObject"
    }

    public override fun valueOf(name: String): TwoObjectSealedInterface = when (name) {
        "TwoObjectSealedInterface_FirstObject" -> TwoObjectSealedInterface.FirstObject
        "TwoObjectSealedInterface_SecondObject" -> TwoObjectSealedInterface.SecondObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: TwoObjectSealedInterface):
            TwoObjectSealedInterfaceEnum = when (obj) {
        is TwoObjectSealedInterface.FirstObject ->
                TwoObjectSealedInterfaceEnum.TwoObjectSealedInterface_FirstObject
        is TwoObjectSealedInterface.SecondObject ->
                TwoObjectSealedInterfaceEnum.TwoObjectSealedInterface_SecondObject
    }

    public override fun enumToSealedObject(`enum`: TwoObjectSealedInterfaceEnum):
            TwoObjectSealedInterface = when (enum) {
        TwoObjectSealedInterfaceEnum.TwoObjectSealedInterface_FirstObject ->
                TwoObjectSealedInterface.FirstObject
        TwoObjectSealedInterfaceEnum.TwoObjectSealedInterface_SecondObject ->
                TwoObjectSealedInterface.SecondObject
    }
}

/**
 * The index of [this] in the values list.
 */
public val TwoObjectSealedInterface.ordinal: Int
    get() = TwoObjectSealedInterfaceSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val TwoObjectSealedInterface.name: String
    get() = TwoObjectSealedInterfaceSealedEnum.nameOf(this)

/**
 * A list of all [TwoObjectSealedInterface] objects.
 */
public val TwoObjectSealedInterface.Companion.values: List<TwoObjectSealedInterface>
    get() = TwoObjectSealedInterfaceSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [TwoObjectSealedInterface]
 */
public val TwoObjectSealedInterface.Companion.sealedEnum: TwoObjectSealedInterfaceSealedEnum
    get() = TwoObjectSealedInterfaceSealedEnum

/**
 * Returns the [TwoObjectSealedInterface] object for the given [name].
 *
 * If the given name doesn't correspond to any [TwoObjectSealedInterface], an
 * [IllegalArgumentException] will be thrown.
 */
public fun TwoObjectSealedInterface.Companion.valueOf(name: String): TwoObjectSealedInterface =
        TwoObjectSealedInterfaceSealedEnum.valueOf(name)

""".trimIndent()
