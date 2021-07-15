package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.GenSealedEnum
import org.intellij.lang.annotations.Language

sealed interface EmptySealedInterface {
    @GenSealedEnum(generateEnum = true)
    companion object
}

@Language("kotlin")
val emptySealedInterfaceGenerated = """
package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [EmptySealedInterface]
 */
public enum class EmptySealedInterfaceEnum

/**
 * The isomorphic [EmptySealedInterfaceEnum] for [this].
 */
public val EmptySealedInterface.`enum`: EmptySealedInterfaceEnum
    get() = EmptySealedInterfaceSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [EmptySealedInterface] for [this].
 */
public val EmptySealedInterfaceEnum.sealedObject: EmptySealedInterface
    get() = EmptySealedInterfaceSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [EmptySealedInterface]
 */
public object EmptySealedInterfaceSealedEnum : SealedEnum<EmptySealedInterface>,
        SealedEnumWithEnumProvider<EmptySealedInterface, EmptySealedInterfaceEnum>,
        EnumForSealedEnumProvider<EmptySealedInterface, EmptySealedInterfaceEnum> {
    public override val values: List<EmptySealedInterface> = emptyList()


    public override val enumClass: Class<EmptySealedInterfaceEnum>
        get() = EmptySealedInterfaceEnum::class.java

    public override fun ordinalOf(obj: EmptySealedInterface): Int = throw
            AssertionError("Constructing a EmptySealedInterface is impossible, since it has no sealed subclasses")

    public override fun nameOf(obj: EmptySealedInterface): String = throw
            AssertionError("Constructing a EmptySealedInterface is impossible, since it has no sealed subclasses")

    public override fun valueOf(name: String): EmptySealedInterface = throw
            IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})

    public override fun sealedObjectToEnum(obj: EmptySealedInterface): EmptySealedInterfaceEnum =
            throw
            AssertionError("Constructing a EmptySealedInterface is impossible, since it has no sealed subclasses")

    public override fun enumToSealedObject(`enum`: EmptySealedInterfaceEnum): EmptySealedInterface =
            throw
            AssertionError("Constructing a EmptySealedInterface is impossible, since it has no sealed subclasses")
}

/**
 * The index of [this] in the values list.
 */
public val EmptySealedInterface.ordinal: Int
    get() = EmptySealedInterfaceSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val EmptySealedInterface.name: String
    get() = EmptySealedInterfaceSealedEnum.nameOf(this)

/**
 * A list of all [EmptySealedInterface] objects.
 */
public val EmptySealedInterface.Companion.values: List<EmptySealedInterface>
    get() = EmptySealedInterfaceSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [EmptySealedInterface]
 */
public val EmptySealedInterface.Companion.sealedEnum: EmptySealedInterfaceSealedEnum
    get() = EmptySealedInterfaceSealedEnum

/**
 * Returns the [EmptySealedInterface] object for the given [name].
 *
 * If the given name doesn't correspond to any [EmptySealedInterface], an [IllegalArgumentException]
 * will be thrown.
 */
public fun EmptySealedInterface.Companion.valueOf(name: String): EmptySealedInterface =
        EmptySealedInterfaceSealedEnum.valueOf(name)

""".trimIndent()
