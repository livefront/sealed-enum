package com.livefront.sealedenum.compilation.generics

import com.livefront.sealedenum.GenSealedEnum

public interface TestInterface

public interface TestGenericInterface<T>

public interface TestGetterInterface {
    public fun get(): String
}

public sealed class EmptySealedClassWithInterface : TestInterface {
    @GenSealedEnum(generateEnum = true)
    public companion object
}

@Suppress("MaxLineLength")
public val emptySealedClassWithInterfaceGenerated: String = """
package com.livefront.sealedenum.compilation.generics

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import kotlin.Int
import kotlin.LazyThreadSafetyMode
import kotlin.String
import kotlin.collections.List
import kotlin.reflect.KClass

/**
 * An isomorphic enum for the sealed class [EmptySealedClassWithInterface]
 */
public enum class EmptySealedClassWithInterfaceEnum(
    sealedObject: EmptySealedClassWithInterface,
) : TestInterface by sealedObject

/**
 * The isomorphic [EmptySealedClassWithInterfaceEnum] for [this].
 */
public val EmptySealedClassWithInterface.`enum`: EmptySealedClassWithInterfaceEnum
    get() = EmptySealedClassWithInterfaceSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [EmptySealedClassWithInterface] for [this].
 */
public val EmptySealedClassWithInterfaceEnum.sealedObject: EmptySealedClassWithInterface
    get() = EmptySealedClassWithInterfaceSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [EmptySealedClassWithInterface]
 */
public object EmptySealedClassWithInterfaceSealedEnum : SealedEnum<EmptySealedClassWithInterface>,
        SealedEnumWithEnumProvider<EmptySealedClassWithInterface, EmptySealedClassWithInterfaceEnum>,
        EnumForSealedEnumProvider<EmptySealedClassWithInterface, EmptySealedClassWithInterfaceEnum>
        {
    public override val values: List<EmptySealedClassWithInterface> by lazy(mode =
            LazyThreadSafetyMode.PUBLICATION) {
        emptyList()
    }


    public override val enumClass: KClass<EmptySealedClassWithInterfaceEnum>
        get() = EmptySealedClassWithInterfaceEnum::class

    public override fun ordinalOf(obj: EmptySealedClassWithInterface): Int = throw
            AssertionError("Constructing a EmptySealedClassWithInterface is impossible, since it has no sealed subclasses")

    public override fun nameOf(obj: EmptySealedClassWithInterface): String = throw
            AssertionError("Constructing a EmptySealedClassWithInterface is impossible, since it has no sealed subclasses")

    public override fun valueOf(name: String): EmptySealedClassWithInterface = throw
            IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})

    public override fun sealedObjectToEnum(obj: EmptySealedClassWithInterface):
            EmptySealedClassWithInterfaceEnum = throw
            AssertionError("Constructing a EmptySealedClassWithInterface is impossible, since it has no sealed subclasses")

    public override fun enumToSealedObject(`enum`: EmptySealedClassWithInterfaceEnum):
            EmptySealedClassWithInterface = throw
            AssertionError("Constructing a EmptySealedClassWithInterface is impossible, since it has no sealed subclasses")
}

/**
 * The index of [this] in the values list.
 */
public val EmptySealedClassWithInterface.ordinal: Int
    get() = EmptySealedClassWithInterfaceSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val EmptySealedClassWithInterface.name: String
    get() = EmptySealedClassWithInterfaceSealedEnum.nameOf(this)

/**
 * A list of all [EmptySealedClassWithInterface] objects.
 */
public val EmptySealedClassWithInterface.Companion.values: List<EmptySealedClassWithInterface>
    get() = EmptySealedClassWithInterfaceSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [EmptySealedClassWithInterface]
 */
public val EmptySealedClassWithInterface.Companion.sealedEnum:
        EmptySealedClassWithInterfaceSealedEnum
    get() = EmptySealedClassWithInterfaceSealedEnum

/**
 * Returns the [EmptySealedClassWithInterface] object for the given [name].
 *
 * If the given name doesn't correspond to any [EmptySealedClassWithInterface], an
 * [IllegalArgumentException] will be thrown.
 */
public fun EmptySealedClassWithInterface.Companion.valueOf(name: String):
        EmptySealedClassWithInterface = EmptySealedClassWithInterfaceSealedEnum.valueOf(name)

""".trimIndent()

public sealed class OneObjectSealedClassWithInterface : TestInterface {
    public object FirstObject : OneObjectSealedClassWithInterface()

    @GenSealedEnum(generateEnum = true)
    public companion object
}

@Suppress("MaxLineLength")
public val oneObjectSealedClassWithInterfaceGenerated: String = """
package com.livefront.sealedenum.compilation.generics

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import kotlin.Int
import kotlin.LazyThreadSafetyMode
import kotlin.String
import kotlin.collections.List
import kotlin.reflect.KClass

/**
 * An isomorphic enum for the sealed class [OneObjectSealedClassWithInterface]
 */
public enum class OneObjectSealedClassWithInterfaceEnum(
    sealedObject: OneObjectSealedClassWithInterface,
) : TestInterface by sealedObject {
    OneObjectSealedClassWithInterface_FirstObject(com.livefront.sealedenum.compilation.generics.OneObjectSealedClassWithInterface.FirstObject),
}

/**
 * The isomorphic [OneObjectSealedClassWithInterfaceEnum] for [this].
 */
public val OneObjectSealedClassWithInterface.`enum`: OneObjectSealedClassWithInterfaceEnum
    get() = OneObjectSealedClassWithInterfaceSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [OneObjectSealedClassWithInterface] for [this].
 */
public val OneObjectSealedClassWithInterfaceEnum.sealedObject: OneObjectSealedClassWithInterface
    get() = OneObjectSealedClassWithInterfaceSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [OneObjectSealedClassWithInterface]
 */
public object OneObjectSealedClassWithInterfaceSealedEnum :
        SealedEnum<OneObjectSealedClassWithInterface>,
        SealedEnumWithEnumProvider<OneObjectSealedClassWithInterface, OneObjectSealedClassWithInterfaceEnum>,
        EnumForSealedEnumProvider<OneObjectSealedClassWithInterface, OneObjectSealedClassWithInterfaceEnum>
        {
    public override val values: List<OneObjectSealedClassWithInterface> by lazy(mode =
            LazyThreadSafetyMode.PUBLICATION) {
        listOf(
            OneObjectSealedClassWithInterface.FirstObject
        )
    }


    public override val enumClass: KClass<OneObjectSealedClassWithInterfaceEnum>
        get() = OneObjectSealedClassWithInterfaceEnum::class

    public override fun ordinalOf(obj: OneObjectSealedClassWithInterface): Int = when (obj) {
        is OneObjectSealedClassWithInterface.FirstObject -> 0
    }

    public override fun nameOf(obj: OneObjectSealedClassWithInterface): String = when (obj) {
        is OneObjectSealedClassWithInterface.FirstObject ->
                "OneObjectSealedClassWithInterface_FirstObject"
    }

    public override fun valueOf(name: String): OneObjectSealedClassWithInterface = when (name) {
        "OneObjectSealedClassWithInterface_FirstObject" ->
                OneObjectSealedClassWithInterface.FirstObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: OneObjectSealedClassWithInterface):
            OneObjectSealedClassWithInterfaceEnum = when (obj) {
        is OneObjectSealedClassWithInterface.FirstObject ->
                OneObjectSealedClassWithInterfaceEnum.OneObjectSealedClassWithInterface_FirstObject
    }

    public override fun enumToSealedObject(`enum`: OneObjectSealedClassWithInterfaceEnum):
            OneObjectSealedClassWithInterface = when (enum) {
        OneObjectSealedClassWithInterfaceEnum.OneObjectSealedClassWithInterface_FirstObject ->
                OneObjectSealedClassWithInterface.FirstObject
    }
}

/**
 * The index of [this] in the values list.
 */
public val OneObjectSealedClassWithInterface.ordinal: Int
    get() = OneObjectSealedClassWithInterfaceSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val OneObjectSealedClassWithInterface.name: String
    get() = OneObjectSealedClassWithInterfaceSealedEnum.nameOf(this)

/**
 * A list of all [OneObjectSealedClassWithInterface] objects.
 */
public val OneObjectSealedClassWithInterface.Companion.values:
        List<OneObjectSealedClassWithInterface>
    get() = OneObjectSealedClassWithInterfaceSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class
 * [OneObjectSealedClassWithInterface]
 */
public val OneObjectSealedClassWithInterface.Companion.sealedEnum:
        OneObjectSealedClassWithInterfaceSealedEnum
    get() = OneObjectSealedClassWithInterfaceSealedEnum

/**
 * Returns the [OneObjectSealedClassWithInterface] object for the given [name].
 *
 * If the given name doesn't correspond to any [OneObjectSealedClassWithInterface], an
 * [IllegalArgumentException] will be thrown.
 */
public fun OneObjectSealedClassWithInterface.Companion.valueOf(name: String):
        OneObjectSealedClassWithInterface =
        OneObjectSealedClassWithInterfaceSealedEnum.valueOf(name)

""".trimIndent()

public sealed class TwoObjectSealedClassWithGenericInterface<T : TestInterface> : TestGenericInterface<T> {
    public object FirstObject : TwoObjectSealedClassWithGenericInterface<TestInterface>()
    public object SecondObject : TwoObjectSealedClassWithGenericInterface<TestInterface>()

    @GenSealedEnum(generateEnum = true)
    public companion object
}

@Suppress("MaxLineLength")
public val twoObjectSealedClassWithGenericInterfaceGenerated: String = """
package com.livefront.sealedenum.compilation.generics

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import kotlin.Int
import kotlin.LazyThreadSafetyMode
import kotlin.String
import kotlin.collections.List
import kotlin.reflect.KClass

/**
 * An isomorphic enum for the sealed class [TwoObjectSealedClassWithGenericInterface]
 */
public enum class TwoObjectSealedClassWithGenericInterfaceEnum(
    sealedObject: TwoObjectSealedClassWithGenericInterface<TestInterface>,
) : TestGenericInterface<TestInterface> by sealedObject {
    TwoObjectSealedClassWithGenericInterface_FirstObject(com.livefront.sealedenum.compilation.generics.TwoObjectSealedClassWithGenericInterface.FirstObject),
    TwoObjectSealedClassWithGenericInterface_SecondObject(com.livefront.sealedenum.compilation.generics.TwoObjectSealedClassWithGenericInterface.SecondObject),
}

/**
 * The isomorphic [TwoObjectSealedClassWithGenericInterfaceEnum] for [this].
 */
public val TwoObjectSealedClassWithGenericInterface<TestInterface>.`enum`:
        TwoObjectSealedClassWithGenericInterfaceEnum
    get() = TwoObjectSealedClassWithGenericInterfaceSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [TwoObjectSealedClassWithGenericInterface] for [this].
 */
public val TwoObjectSealedClassWithGenericInterfaceEnum.sealedObject:
        TwoObjectSealedClassWithGenericInterface<TestInterface>
    get() = TwoObjectSealedClassWithGenericInterfaceSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [TwoObjectSealedClassWithGenericInterface]
 */
public object TwoObjectSealedClassWithGenericInterfaceSealedEnum :
        SealedEnum<TwoObjectSealedClassWithGenericInterface<TestInterface>>,
        SealedEnumWithEnumProvider<TwoObjectSealedClassWithGenericInterface<TestInterface>, TwoObjectSealedClassWithGenericInterfaceEnum>,
        EnumForSealedEnumProvider<TwoObjectSealedClassWithGenericInterface<TestInterface>, TwoObjectSealedClassWithGenericInterfaceEnum>
        {
    public override val values: List<TwoObjectSealedClassWithGenericInterface<TestInterface>> by
            lazy(mode = LazyThreadSafetyMode.PUBLICATION) {
        listOf(
            TwoObjectSealedClassWithGenericInterface.FirstObject,
            TwoObjectSealedClassWithGenericInterface.SecondObject
        )
    }


    public override val enumClass: KClass<TwoObjectSealedClassWithGenericInterfaceEnum>
        get() = TwoObjectSealedClassWithGenericInterfaceEnum::class

    public override fun ordinalOf(obj: TwoObjectSealedClassWithGenericInterface<TestInterface>): Int
            = when (obj) {
        is TwoObjectSealedClassWithGenericInterface.FirstObject -> 0
        is TwoObjectSealedClassWithGenericInterface.SecondObject -> 1
    }

    public override fun nameOf(obj: TwoObjectSealedClassWithGenericInterface<TestInterface>): String
            = when (obj) {
        is TwoObjectSealedClassWithGenericInterface.FirstObject ->
                "TwoObjectSealedClassWithGenericInterface_FirstObject"
        is TwoObjectSealedClassWithGenericInterface.SecondObject ->
                "TwoObjectSealedClassWithGenericInterface_SecondObject"
    }

    public override fun valueOf(name: String):
            TwoObjectSealedClassWithGenericInterface<TestInterface> = when (name) {
        "TwoObjectSealedClassWithGenericInterface_FirstObject" ->
                TwoObjectSealedClassWithGenericInterface.FirstObject
        "TwoObjectSealedClassWithGenericInterface_SecondObject" ->
                TwoObjectSealedClassWithGenericInterface.SecondObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override
            fun sealedObjectToEnum(obj: TwoObjectSealedClassWithGenericInterface<TestInterface>):
            TwoObjectSealedClassWithGenericInterfaceEnum = when (obj) {
        is TwoObjectSealedClassWithGenericInterface.FirstObject ->
                TwoObjectSealedClassWithGenericInterfaceEnum.TwoObjectSealedClassWithGenericInterface_FirstObject
        is TwoObjectSealedClassWithGenericInterface.SecondObject ->
                TwoObjectSealedClassWithGenericInterfaceEnum.TwoObjectSealedClassWithGenericInterface_SecondObject
    }

    public override fun enumToSealedObject(`enum`: TwoObjectSealedClassWithGenericInterfaceEnum):
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
public val TwoObjectSealedClassWithGenericInterface<TestInterface>.ordinal: Int
    get() = TwoObjectSealedClassWithGenericInterfaceSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val TwoObjectSealedClassWithGenericInterface<TestInterface>.name: String
    get() = TwoObjectSealedClassWithGenericInterfaceSealedEnum.nameOf(this)

/**
 * A list of all [TwoObjectSealedClassWithGenericInterface] objects.
 */
public val TwoObjectSealedClassWithGenericInterface.Companion.values:
        List<TwoObjectSealedClassWithGenericInterface<TestInterface>>
    get() = TwoObjectSealedClassWithGenericInterfaceSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class
 * [TwoObjectSealedClassWithGenericInterface]
 */
public val TwoObjectSealedClassWithGenericInterface.Companion.sealedEnum:
        TwoObjectSealedClassWithGenericInterfaceSealedEnum
    get() = TwoObjectSealedClassWithGenericInterfaceSealedEnum

/**
 * Returns the [TwoObjectSealedClassWithGenericInterface] object for the given [name].
 *
 * If the given name doesn't correspond to any [TwoObjectSealedClassWithGenericInterface], an
 * [IllegalArgumentException] will be thrown.
 */
public fun TwoObjectSealedClassWithGenericInterface.Companion.valueOf(name: String):
        TwoObjectSealedClassWithGenericInterface<TestInterface> =
        TwoObjectSealedClassWithGenericInterfaceSealedEnum.valueOf(name)

""".trimIndent()

public var hasGetBeenCalled: Boolean = false

public sealed class SealedClassWithGetterInterface : TestGetterInterface {
    public object FirstObject : SealedClassWithGetterInterface() {

        override fun get(): String {
            hasGetBeenCalled = true
            return "First"
        }
    }

    @GenSealedEnum(generateEnum = true)
    public companion object
}

@Suppress("MaxLineLength")
public val sealedClassWithGetterInterface: String = """
package com.livefront.sealedenum.compilation.generics

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import kotlin.Int
import kotlin.LazyThreadSafetyMode
import kotlin.String
import kotlin.collections.List
import kotlin.reflect.KClass

/**
 * An isomorphic enum for the sealed class [SealedClassWithGetterInterface]
 */
public enum class SealedClassWithGetterInterfaceEnum(
    sealedObject: SealedClassWithGetterInterface,
) : TestGetterInterface by sealedObject {
    SealedClassWithGetterInterface_FirstObject(com.livefront.sealedenum.compilation.generics.SealedClassWithGetterInterface.FirstObject),
}

/**
 * The isomorphic [SealedClassWithGetterInterfaceEnum] for [this].
 */
public val SealedClassWithGetterInterface.`enum`: SealedClassWithGetterInterfaceEnum
    get() = SealedClassWithGetterInterfaceSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [SealedClassWithGetterInterface] for [this].
 */
public val SealedClassWithGetterInterfaceEnum.sealedObject: SealedClassWithGetterInterface
    get() = SealedClassWithGetterInterfaceSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [SealedClassWithGetterInterface]
 */
public object SealedClassWithGetterInterfaceSealedEnum : SealedEnum<SealedClassWithGetterInterface>,
        SealedEnumWithEnumProvider<SealedClassWithGetterInterface, SealedClassWithGetterInterfaceEnum>,
        EnumForSealedEnumProvider<SealedClassWithGetterInterface, SealedClassWithGetterInterfaceEnum>
        {
    public override val values: List<SealedClassWithGetterInterface> by lazy(mode =
            LazyThreadSafetyMode.PUBLICATION) {
        listOf(
            SealedClassWithGetterInterface.FirstObject
        )
    }


    public override val enumClass: KClass<SealedClassWithGetterInterfaceEnum>
        get() = SealedClassWithGetterInterfaceEnum::class

    public override fun ordinalOf(obj: SealedClassWithGetterInterface): Int = when (obj) {
        is SealedClassWithGetterInterface.FirstObject -> 0
    }

    public override fun nameOf(obj: SealedClassWithGetterInterface): String = when (obj) {
        is SealedClassWithGetterInterface.FirstObject ->
                "SealedClassWithGetterInterface_FirstObject"
    }

    public override fun valueOf(name: String): SealedClassWithGetterInterface = when (name) {
        "SealedClassWithGetterInterface_FirstObject" -> SealedClassWithGetterInterface.FirstObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: SealedClassWithGetterInterface):
            SealedClassWithGetterInterfaceEnum = when (obj) {
        is SealedClassWithGetterInterface.FirstObject ->
                SealedClassWithGetterInterfaceEnum.SealedClassWithGetterInterface_FirstObject
    }

    public override fun enumToSealedObject(`enum`: SealedClassWithGetterInterfaceEnum):
            SealedClassWithGetterInterface = when (enum) {
        SealedClassWithGetterInterfaceEnum.SealedClassWithGetterInterface_FirstObject ->
                SealedClassWithGetterInterface.FirstObject
    }
}

/**
 * The index of [this] in the values list.
 */
public val SealedClassWithGetterInterface.ordinal: Int
    get() = SealedClassWithGetterInterfaceSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val SealedClassWithGetterInterface.name: String
    get() = SealedClassWithGetterInterfaceSealedEnum.nameOf(this)

/**
 * A list of all [SealedClassWithGetterInterface] objects.
 */
public val SealedClassWithGetterInterface.Companion.values: List<SealedClassWithGetterInterface>
    get() = SealedClassWithGetterInterfaceSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [SealedClassWithGetterInterface]
 */
public val SealedClassWithGetterInterface.Companion.sealedEnum:
        SealedClassWithGetterInterfaceSealedEnum
    get() = SealedClassWithGetterInterfaceSealedEnum

/**
 * Returns the [SealedClassWithGetterInterface] object for the given [name].
 *
 * If the given name doesn't correspond to any [SealedClassWithGetterInterface], an
 * [IllegalArgumentException] will be thrown.
 */
public fun SealedClassWithGetterInterface.Companion.valueOf(name: String):
        SealedClassWithGetterInterface = SealedClassWithGetterInterfaceSealedEnum.valueOf(name)

""".trimIndent()
