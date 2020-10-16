package com.livefront.sealedenum.compilation.generics

import com.livefront.sealedenum.GenSealedEnum
import org.intellij.lang.annotations.Language

interface TestInterface

interface TestGenericInterface<T>

interface TestGetterInterface {
    fun get(): String
}

sealed class EmptySealedClassWithInterface : TestInterface {
    @GenSealedEnum(generateEnum = true)
    companion object
}

@Language("kotlin")
val emptySealedClassWithInterfaceGenerated = """
package com.livefront.sealedenum.compilation.generics

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [EmptySealedClassWithInterface]
 */
enum class EmptySealedClassWithInterfaceEnum(
    sealedObject: EmptySealedClassWithInterface
) : TestInterface by sealedObject

/**
 * The isomorphic [EmptySealedClassWithInterfaceEnum] for [this].
 */
val EmptySealedClassWithInterface.enum: EmptySealedClassWithInterfaceEnum
    get() = EmptySealedClassWithInterfaceSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [EmptySealedClassWithInterface] for [this].
 */
val EmptySealedClassWithInterfaceEnum.sealedObject: EmptySealedClassWithInterface
    get() = EmptySealedClassWithInterfaceSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [EmptySealedClassWithInterface]
 */
object EmptySealedClassWithInterfaceSealedEnum : SealedEnum<EmptySealedClassWithInterface>,
        SealedEnumWithEnumProvider<EmptySealedClassWithInterface,
        EmptySealedClassWithInterfaceEnum>, EnumForSealedEnumProvider<EmptySealedClassWithInterface,
        EmptySealedClassWithInterfaceEnum> {
    override val values: List<EmptySealedClassWithInterface> = emptyList()


    override val enumClass: Class<EmptySealedClassWithInterfaceEnum>
        get() = EmptySealedClassWithInterfaceEnum::class.java

    override fun ordinalOf(obj: EmptySealedClassWithInterface): Int = throw
            AssertionError("Constructing a EmptySealedClassWithInterface is impossible, since it has no sealed subclasses")

    override fun nameOf(obj: EmptySealedClassWithInterface): String = throw
            AssertionError("Constructing a EmptySealedClassWithInterface is impossible, since it has no sealed subclasses")

    override fun valueOf(name: String): EmptySealedClassWithInterface = throw
            IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})

    override fun sealedObjectToEnum(obj: EmptySealedClassWithInterface):
            EmptySealedClassWithInterfaceEnum = throw
            AssertionError("Constructing a EmptySealedClassWithInterface is impossible, since it has no sealed subclasses")

    override fun enumToSealedObject(enum: EmptySealedClassWithInterfaceEnum):
            EmptySealedClassWithInterface = throw
            AssertionError("Constructing a EmptySealedClassWithInterface is impossible, since it has no sealed subclasses")
}

/**
 * The index of [this] in the values list.
 */
val EmptySealedClassWithInterface.ordinal: Int
    get() = EmptySealedClassWithInterfaceSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val EmptySealedClassWithInterface.name: String
    get() = EmptySealedClassWithInterfaceSealedEnum.nameOf(this)

/**
 * A list of all [EmptySealedClassWithInterface] objects.
 */
val EmptySealedClassWithInterface.Companion.values: List<EmptySealedClassWithInterface>
    get() = EmptySealedClassWithInterfaceSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [EmptySealedClassWithInterface]
 */
val EmptySealedClassWithInterface.Companion.sealedEnum: EmptySealedClassWithInterfaceSealedEnum
    get() = EmptySealedClassWithInterfaceSealedEnum

/**
 * Returns the [EmptySealedClassWithInterface] object for the given [name].
 *
 * If the given name doesn't correspond to any [EmptySealedClassWithInterface], an
 * [IllegalArgumentException] will be thrown.
 */
fun EmptySealedClassWithInterface.Companion.valueOf(name: String): EmptySealedClassWithInterface =
        EmptySealedClassWithInterfaceSealedEnum.valueOf(name)

""".trimIndent()

sealed class OneObjectSealedClassWithInterface : TestInterface {
    object FirstObject : OneObjectSealedClassWithInterface()

    @GenSealedEnum(generateEnum = true)
    companion object
}

@Language("kotlin")
val oneObjectSealedClassWithInterfaceGenerated = """
package com.livefront.sealedenum.compilation.generics

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [OneObjectSealedClassWithInterface]
 */
enum class OneObjectSealedClassWithInterfaceEnum(
    sealedObject: OneObjectSealedClassWithInterface
) : TestInterface by sealedObject {
    OneObjectSealedClassWithInterface_FirstObject(com.livefront.sealedenum.compilation.generics.OneObjectSealedClassWithInterface.FirstObject)
}

/**
 * The isomorphic [OneObjectSealedClassWithInterfaceEnum] for [this].
 */
val OneObjectSealedClassWithInterface.enum: OneObjectSealedClassWithInterfaceEnum
    get() = OneObjectSealedClassWithInterfaceSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [OneObjectSealedClassWithInterface] for [this].
 */
val OneObjectSealedClassWithInterfaceEnum.sealedObject: OneObjectSealedClassWithInterface
    get() = OneObjectSealedClassWithInterfaceSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [OneObjectSealedClassWithInterface]
 */
object OneObjectSealedClassWithInterfaceSealedEnum : SealedEnum<OneObjectSealedClassWithInterface>,
        SealedEnumWithEnumProvider<OneObjectSealedClassWithInterface,
        OneObjectSealedClassWithInterfaceEnum>,
        EnumForSealedEnumProvider<OneObjectSealedClassWithInterface,
        OneObjectSealedClassWithInterfaceEnum> {
    override val values: List<OneObjectSealedClassWithInterface> = listOf(
        OneObjectSealedClassWithInterface.FirstObject
    )


    override val enumClass: Class<OneObjectSealedClassWithInterfaceEnum>
        get() = OneObjectSealedClassWithInterfaceEnum::class.java

    override fun ordinalOf(obj: OneObjectSealedClassWithInterface): Int = when (obj) {
        OneObjectSealedClassWithInterface.FirstObject -> 0
    }

    override fun nameOf(obj: OneObjectSealedClassWithInterface): String = when (obj) {
        OneObjectSealedClassWithInterface.FirstObject ->
                "OneObjectSealedClassWithInterface_FirstObject"
    }

    override fun valueOf(name: String): OneObjectSealedClassWithInterface = when (name) {
        "OneObjectSealedClassWithInterface_FirstObject" ->
                OneObjectSealedClassWithInterface.FirstObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    override fun sealedObjectToEnum(obj: OneObjectSealedClassWithInterface):
            OneObjectSealedClassWithInterfaceEnum = when (obj) {
        OneObjectSealedClassWithInterface.FirstObject ->
                OneObjectSealedClassWithInterfaceEnum.OneObjectSealedClassWithInterface_FirstObject
    }

    override fun enumToSealedObject(enum: OneObjectSealedClassWithInterfaceEnum):
            OneObjectSealedClassWithInterface = when (enum) {
        OneObjectSealedClassWithInterfaceEnum.OneObjectSealedClassWithInterface_FirstObject ->
                OneObjectSealedClassWithInterface.FirstObject
    }
}

/**
 * The index of [this] in the values list.
 */
val OneObjectSealedClassWithInterface.ordinal: Int
    get() = OneObjectSealedClassWithInterfaceSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val OneObjectSealedClassWithInterface.name: String
    get() = OneObjectSealedClassWithInterfaceSealedEnum.nameOf(this)

/**
 * A list of all [OneObjectSealedClassWithInterface] objects.
 */
val OneObjectSealedClassWithInterface.Companion.values: List<OneObjectSealedClassWithInterface>
    get() = OneObjectSealedClassWithInterfaceSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class
 * [OneObjectSealedClassWithInterface]
 */
val OneObjectSealedClassWithInterface.Companion.sealedEnum:
        OneObjectSealedClassWithInterfaceSealedEnum
    get() = OneObjectSealedClassWithInterfaceSealedEnum

/**
 * Returns the [OneObjectSealedClassWithInterface] object for the given [name].
 *
 * If the given name doesn't correspond to any [OneObjectSealedClassWithInterface], an
 * [IllegalArgumentException] will be thrown.
 */
fun OneObjectSealedClassWithInterface.Companion.valueOf(name: String):
        OneObjectSealedClassWithInterface =
        OneObjectSealedClassWithInterfaceSealedEnum.valueOf(name)

""".trimIndent()

sealed class TwoObjectSealedClassWithGenericInterface<T : TestInterface> : TestGenericInterface<T> {
    object FirstObject : TwoObjectSealedClassWithGenericInterface<TestInterface>()
    object SecondObject : TwoObjectSealedClassWithGenericInterface<TestInterface>()

    @GenSealedEnum(generateEnum = true)
    companion object
}

@Language("kotlin")
val twoObjectSealedClassWithGenericInterfaceGenerated = """
package com.livefront.sealedenum.compilation.generics

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [TwoObjectSealedClassWithGenericInterface]
 */
enum class TwoObjectSealedClassWithGenericInterfaceEnum(
    sealedObject: TwoObjectSealedClassWithGenericInterface<TestInterface>
) : TestGenericInterface<TestInterface> by sealedObject {
    TwoObjectSealedClassWithGenericInterface_FirstObject(com.livefront.sealedenum.compilation.generics.TwoObjectSealedClassWithGenericInterface.FirstObject),

    TwoObjectSealedClassWithGenericInterface_SecondObject(com.livefront.sealedenum.compilation.generics.TwoObjectSealedClassWithGenericInterface.SecondObject)
}

/**
 * The isomorphic [TwoObjectSealedClassWithGenericInterfaceEnum] for [this].
 */
val TwoObjectSealedClassWithGenericInterface<TestInterface>.enum:
        TwoObjectSealedClassWithGenericInterfaceEnum
    get() = TwoObjectSealedClassWithGenericInterfaceSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [TwoObjectSealedClassWithGenericInterface] for [this].
 */
val TwoObjectSealedClassWithGenericInterfaceEnum.sealedObject:
        TwoObjectSealedClassWithGenericInterface<TestInterface>
    get() = TwoObjectSealedClassWithGenericInterfaceSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [TwoObjectSealedClassWithGenericInterface]
 */
object TwoObjectSealedClassWithGenericInterfaceSealedEnum :
        SealedEnum<TwoObjectSealedClassWithGenericInterface<TestInterface>>,
        SealedEnumWithEnumProvider<TwoObjectSealedClassWithGenericInterface<TestInterface>,
        TwoObjectSealedClassWithGenericInterfaceEnum>,
        EnumForSealedEnumProvider<TwoObjectSealedClassWithGenericInterface<TestInterface>,
        TwoObjectSealedClassWithGenericInterfaceEnum> {
    override val values: List<TwoObjectSealedClassWithGenericInterface<TestInterface>> = listOf(
        TwoObjectSealedClassWithGenericInterface.FirstObject,
        TwoObjectSealedClassWithGenericInterface.SecondObject
    )


    override val enumClass: Class<TwoObjectSealedClassWithGenericInterfaceEnum>
        get() = TwoObjectSealedClassWithGenericInterfaceEnum::class.java

    override fun ordinalOf(obj: TwoObjectSealedClassWithGenericInterface<TestInterface>): Int = when
            (obj) {
        TwoObjectSealedClassWithGenericInterface.FirstObject -> 0
        TwoObjectSealedClassWithGenericInterface.SecondObject -> 1
    }

    override fun nameOf(obj: TwoObjectSealedClassWithGenericInterface<TestInterface>): String = when
            (obj) {
        TwoObjectSealedClassWithGenericInterface.FirstObject ->
                "TwoObjectSealedClassWithGenericInterface_FirstObject"
        TwoObjectSealedClassWithGenericInterface.SecondObject ->
                "TwoObjectSealedClassWithGenericInterface_SecondObject"
    }

    override fun valueOf(name: String): TwoObjectSealedClassWithGenericInterface<TestInterface> =
            when (name) {
        "TwoObjectSealedClassWithGenericInterface_FirstObject" ->
                TwoObjectSealedClassWithGenericInterface.FirstObject
        "TwoObjectSealedClassWithGenericInterface_SecondObject" ->
                TwoObjectSealedClassWithGenericInterface.SecondObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    override fun sealedObjectToEnum(obj: TwoObjectSealedClassWithGenericInterface<TestInterface>):
            TwoObjectSealedClassWithGenericInterfaceEnum = when (obj) {
        TwoObjectSealedClassWithGenericInterface.FirstObject ->
                TwoObjectSealedClassWithGenericInterfaceEnum.TwoObjectSealedClassWithGenericInterface_FirstObject
        TwoObjectSealedClassWithGenericInterface.SecondObject ->
                TwoObjectSealedClassWithGenericInterfaceEnum.TwoObjectSealedClassWithGenericInterface_SecondObject
    }

    override fun enumToSealedObject(enum: TwoObjectSealedClassWithGenericInterfaceEnum):
            TwoObjectSealedClassWithGenericInterface<TestInterface> = when (enum) {
        TwoObjectSealedClassWithGenericInterfaceEnum.TwoObjectSealedClassWithGenericInterface_FirstObject ->
                TwoObjectSealedClassWithGenericInterface.FirstObject
        TwoObjectSealedClassWithGenericInterfaceEnum.TwoObjectSealedClassWithGenericInterface_SecondObject ->
                TwoObjectSealedClassWithGenericInterface.SecondObject
    }
}

/**
 * The index of [this] in the values list.
 */
val TwoObjectSealedClassWithGenericInterface<TestInterface>.ordinal: Int
    get() = TwoObjectSealedClassWithGenericInterfaceSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val TwoObjectSealedClassWithGenericInterface<TestInterface>.name: String
    get() = TwoObjectSealedClassWithGenericInterfaceSealedEnum.nameOf(this)

/**
 * A list of all [TwoObjectSealedClassWithGenericInterface] objects.
 */
val TwoObjectSealedClassWithGenericInterface.Companion.values:
        List<TwoObjectSealedClassWithGenericInterface<TestInterface>>
    get() = TwoObjectSealedClassWithGenericInterfaceSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class
 * [TwoObjectSealedClassWithGenericInterface]
 */
val TwoObjectSealedClassWithGenericInterface.Companion.sealedEnum:
        TwoObjectSealedClassWithGenericInterfaceSealedEnum
    get() = TwoObjectSealedClassWithGenericInterfaceSealedEnum

/**
 * Returns the [TwoObjectSealedClassWithGenericInterface] object for the given [name].
 *
 * If the given name doesn't correspond to any [TwoObjectSealedClassWithGenericInterface], an
 * [IllegalArgumentException] will be thrown.
 */
fun TwoObjectSealedClassWithGenericInterface.Companion.valueOf(name: String):
        TwoObjectSealedClassWithGenericInterface<TestInterface> =
        TwoObjectSealedClassWithGenericInterfaceSealedEnum.valueOf(name)

""".trimIndent()

sealed class SealedClassWithGetterInterface : TestGetterInterface {
    object FirstObject : SealedClassWithGetterInterface() {
        var hasGetBeenCalled = false

        override fun get(): String {
            hasGetBeenCalled = true
            return "First"
        }
    }

    @GenSealedEnum(generateEnum = true)
    companion object
}

@Language("kotlin")
val sealedClassWithGetterInterface = """
package com.livefront.sealedenum.compilation.generics

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [SealedClassWithGetterInterface]
 */
enum class SealedClassWithGetterInterfaceEnum(
    sealedObject: SealedClassWithGetterInterface
) : TestGetterInterface by sealedObject {
    SealedClassWithGetterInterface_FirstObject(com.livefront.sealedenum.compilation.generics.SealedClassWithGetterInterface.FirstObject)
}

/**
 * The isomorphic [SealedClassWithGetterInterfaceEnum] for [this].
 */
val SealedClassWithGetterInterface.enum: SealedClassWithGetterInterfaceEnum
    get() = SealedClassWithGetterInterfaceSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [SealedClassWithGetterInterface] for [this].
 */
val SealedClassWithGetterInterfaceEnum.sealedObject: SealedClassWithGetterInterface
    get() = SealedClassWithGetterInterfaceSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [SealedClassWithGetterInterface]
 */
object SealedClassWithGetterInterfaceSealedEnum : SealedEnum<SealedClassWithGetterInterface>,
        SealedEnumWithEnumProvider<SealedClassWithGetterInterface,
        SealedClassWithGetterInterfaceEnum>,
        EnumForSealedEnumProvider<SealedClassWithGetterInterface,
        SealedClassWithGetterInterfaceEnum> {
    override val values: List<SealedClassWithGetterInterface> = listOf(
        SealedClassWithGetterInterface.FirstObject
    )


    override val enumClass: Class<SealedClassWithGetterInterfaceEnum>
        get() = SealedClassWithGetterInterfaceEnum::class.java

    override fun ordinalOf(obj: SealedClassWithGetterInterface): Int = when (obj) {
        SealedClassWithGetterInterface.FirstObject -> 0
    }

    override fun nameOf(obj: SealedClassWithGetterInterface): String = when (obj) {
        SealedClassWithGetterInterface.FirstObject -> "SealedClassWithGetterInterface_FirstObject"
    }

    override fun valueOf(name: String): SealedClassWithGetterInterface = when (name) {
        "SealedClassWithGetterInterface_FirstObject" -> SealedClassWithGetterInterface.FirstObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    override fun sealedObjectToEnum(obj: SealedClassWithGetterInterface):
            SealedClassWithGetterInterfaceEnum = when (obj) {
        SealedClassWithGetterInterface.FirstObject ->
                SealedClassWithGetterInterfaceEnum.SealedClassWithGetterInterface_FirstObject
    }

    override fun enumToSealedObject(enum: SealedClassWithGetterInterfaceEnum):
            SealedClassWithGetterInterface = when (enum) {
        SealedClassWithGetterInterfaceEnum.SealedClassWithGetterInterface_FirstObject ->
                SealedClassWithGetterInterface.FirstObject
    }
}

/**
 * The index of [this] in the values list.
 */
val SealedClassWithGetterInterface.ordinal: Int
    get() = SealedClassWithGetterInterfaceSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val SealedClassWithGetterInterface.name: String
    get() = SealedClassWithGetterInterfaceSealedEnum.nameOf(this)

/**
 * A list of all [SealedClassWithGetterInterface] objects.
 */
val SealedClassWithGetterInterface.Companion.values: List<SealedClassWithGetterInterface>
    get() = SealedClassWithGetterInterfaceSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [SealedClassWithGetterInterface]
 */
val SealedClassWithGetterInterface.Companion.sealedEnum: SealedClassWithGetterInterfaceSealedEnum
    get() = SealedClassWithGetterInterfaceSealedEnum

/**
 * Returns the [SealedClassWithGetterInterface] object for the given [name].
 *
 * If the given name doesn't correspond to any [SealedClassWithGetterInterface], an
 * [IllegalArgumentException] will be thrown.
 */
fun SealedClassWithGetterInterface.Companion.valueOf(name: String): SealedClassWithGetterInterface =
        SealedClassWithGetterInterfaceSealedEnum.valueOf(name)

""".trimIndent()
