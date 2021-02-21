package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.GenSealedEnum
import org.intellij.lang.annotations.Language

sealed interface OneObjectSealedInterface {
    object FirstObject : OneObjectSealedInterface

    @GenSealedEnum(generateEnum = true)
    companion object
}

@Language("kotlin")
val oneObjectSealedInterfaceGenerated = """
package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [OneObjectSealedInterface]
 */
public enum class OneObjectSealedInterfaceEnum {
    OneObjectSealedInterface_FirstObject,
}

/**
 * The isomorphic [OneObjectSealedInterfaceEnum] for [this].
 */
public val OneObjectSealedInterface.`enum`: OneObjectSealedInterfaceEnum
    get() = OneObjectSealedInterfaceSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [OneObjectSealedInterface] for [this].
 */
public val OneObjectSealedInterfaceEnum.sealedObject: OneObjectSealedInterface
    get() = OneObjectSealedInterfaceSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [OneObjectSealedInterface]
 */
public object OneObjectSealedInterfaceSealedEnum : SealedEnum<OneObjectSealedInterface>,
        SealedEnumWithEnumProvider<OneObjectSealedInterface, OneObjectSealedInterfaceEnum>,
        EnumForSealedEnumProvider<OneObjectSealedInterface, OneObjectSealedInterfaceEnum> {
    public override val values: List<OneObjectSealedInterface> = listOf(
        OneObjectSealedInterface.FirstObject
    )


    public override val enumClass: Class<OneObjectSealedInterfaceEnum>
        get() = OneObjectSealedInterfaceEnum::class.java

    public override fun ordinalOf(obj: OneObjectSealedInterface): Int = when (obj) {
        OneObjectSealedInterface.FirstObject -> 0
    }

    public override fun nameOf(obj: OneObjectSealedInterface): String = when (obj) {
        OneObjectSealedInterface.FirstObject -> "OneObjectSealedInterface_FirstObject"
    }

    public override fun valueOf(name: String): OneObjectSealedInterface = when (name) {
        "OneObjectSealedInterface_FirstObject" -> OneObjectSealedInterface.FirstObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: OneObjectSealedInterface):
            OneObjectSealedInterfaceEnum = when (obj) {
        OneObjectSealedInterface.FirstObject ->
                OneObjectSealedInterfaceEnum.OneObjectSealedInterface_FirstObject
    }

    public override fun enumToSealedObject(`enum`: OneObjectSealedInterfaceEnum):
            OneObjectSealedInterface = when (enum) {
        OneObjectSealedInterfaceEnum.OneObjectSealedInterface_FirstObject ->
                OneObjectSealedInterface.FirstObject
    }
}

/**
 * The index of [this] in the values list.
 */
public val OneObjectSealedInterface.ordinal: Int
    get() = OneObjectSealedInterfaceSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val OneObjectSealedInterface.name: String
    get() = OneObjectSealedInterfaceSealedEnum.nameOf(this)

/**
 * A list of all [OneObjectSealedInterface] objects.
 */
public val OneObjectSealedInterface.Companion.values: List<OneObjectSealedInterface>
    get() = OneObjectSealedInterfaceSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [OneObjectSealedInterface]
 */
public val OneObjectSealedInterface.Companion.sealedEnum: OneObjectSealedInterfaceSealedEnum
    get() = OneObjectSealedInterfaceSealedEnum

/**
 * Returns the [OneObjectSealedInterface] object for the given [name].
 *
 * If the given name doesn't correspond to any [OneObjectSealedInterface], an
 * [IllegalArgumentException] will be thrown.
 */
public fun OneObjectSealedInterface.Companion.valueOf(name: String): OneObjectSealedInterface =
        OneObjectSealedInterfaceSealedEnum.valueOf(name)

""".trimIndent()
