package com.livefront.sealedenum.compilation.generics

import com.livefront.sealedenum.GenSealedEnum
import org.intellij.lang.annotations.Language

interface GenericInterfaceOut<out T>

sealed class OneTypeParameterSealedClass<out T> : GenericInterfaceOut<T> {
    object FirstObject : OneTypeParameterSealedClass<Int>()

    object SecondObject : OneTypeParameterSealedClass<String>()

    object ThirdObject : OneTypeParameterSealedClass<Nothing>()

    @GenSealedEnum(generateEnum = true)
    companion object OneType
}

@Language("kotlin")
val oneTypeParameterSealedClassGenerated = """
package com.livefront.sealedenum.compilation.generics

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [OneTypeParameterSealedClass]
 */
public enum class OneTypeParameterSealedClassEnum() {
    OneTypeParameterSealedClass_FirstObject,
    OneTypeParameterSealedClass_SecondObject,
    OneTypeParameterSealedClass_ThirdObject,
}

/**
 * The isomorphic [OneTypeParameterSealedClassEnum] for [this].
 */
public val OneTypeParameterSealedClass<*>.`enum`: OneTypeParameterSealedClassEnum
    get() = OneTypeParameterSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [OneTypeParameterSealedClass] for [this].
 */
public val OneTypeParameterSealedClassEnum.sealedObject: OneTypeParameterSealedClass<*>
    get() = OneTypeParameterSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [OneTypeParameterSealedClass]
 */
public object OneTypeParameterSealedClassSealedEnum : SealedEnum<OneTypeParameterSealedClass<*>>,
        SealedEnumWithEnumProvider<OneTypeParameterSealedClass<*>, OneTypeParameterSealedClassEnum>,
        EnumForSealedEnumProvider<OneTypeParameterSealedClass<*>, OneTypeParameterSealedClassEnum> {
    public override val values: List<OneTypeParameterSealedClass<*>> = listOf(
        OneTypeParameterSealedClass.FirstObject,
        OneTypeParameterSealedClass.SecondObject,
        OneTypeParameterSealedClass.ThirdObject
    )


    public override val enumClass: Class<OneTypeParameterSealedClassEnum>
        get() = OneTypeParameterSealedClassEnum::class.java

    public override fun ordinalOf(obj: OneTypeParameterSealedClass<*>): Int = when (obj) {
        OneTypeParameterSealedClass.FirstObject -> 0
        OneTypeParameterSealedClass.SecondObject -> 1
        OneTypeParameterSealedClass.ThirdObject -> 2
    }

    public override fun nameOf(obj: OneTypeParameterSealedClass<*>): String = when (obj) {
        OneTypeParameterSealedClass.FirstObject -> "OneTypeParameterSealedClass_FirstObject"
        OneTypeParameterSealedClass.SecondObject -> "OneTypeParameterSealedClass_SecondObject"
        OneTypeParameterSealedClass.ThirdObject -> "OneTypeParameterSealedClass_ThirdObject"
    }

    public override fun valueOf(name: String): OneTypeParameterSealedClass<*> = when (name) {
        "OneTypeParameterSealedClass_FirstObject" -> OneTypeParameterSealedClass.FirstObject
        "OneTypeParameterSealedClass_SecondObject" -> OneTypeParameterSealedClass.SecondObject
        "OneTypeParameterSealedClass_ThirdObject" -> OneTypeParameterSealedClass.ThirdObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: OneTypeParameterSealedClass<*>):
            OneTypeParameterSealedClassEnum = when (obj) {
        OneTypeParameterSealedClass.FirstObject ->
                OneTypeParameterSealedClassEnum.OneTypeParameterSealedClass_FirstObject
        OneTypeParameterSealedClass.SecondObject ->
                OneTypeParameterSealedClassEnum.OneTypeParameterSealedClass_SecondObject
        OneTypeParameterSealedClass.ThirdObject ->
                OneTypeParameterSealedClassEnum.OneTypeParameterSealedClass_ThirdObject
    }

    public override fun enumToSealedObject(`enum`: OneTypeParameterSealedClassEnum):
            OneTypeParameterSealedClass<*> = when (enum) {
        OneTypeParameterSealedClassEnum.OneTypeParameterSealedClass_FirstObject ->
                OneTypeParameterSealedClass.FirstObject
        OneTypeParameterSealedClassEnum.OneTypeParameterSealedClass_SecondObject ->
                OneTypeParameterSealedClass.SecondObject
        OneTypeParameterSealedClassEnum.OneTypeParameterSealedClass_ThirdObject ->
                OneTypeParameterSealedClass.ThirdObject
    }
}

/**
 * The index of [this] in the values list.
 */
public val OneTypeParameterSealedClass<*>.ordinal: Int
    get() = OneTypeParameterSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val OneTypeParameterSealedClass<*>.name: String
    get() = OneTypeParameterSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [OneTypeParameterSealedClass] objects.
 */
public val OneTypeParameterSealedClass.OneType.values: List<OneTypeParameterSealedClass<*>>
    get() = OneTypeParameterSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [OneTypeParameterSealedClass]
 */
public val OneTypeParameterSealedClass.OneType.sealedEnum: OneTypeParameterSealedClassSealedEnum
    get() = OneTypeParameterSealedClassSealedEnum

/**
 * Returns the [OneTypeParameterSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [OneTypeParameterSealedClass], an
 * [IllegalArgumentException] will be thrown.
 */
public fun OneTypeParameterSealedClass.OneType.valueOf(name: String): OneTypeParameterSealedClass<*>
        = OneTypeParameterSealedClassSealedEnum.valueOf(name)

""".trimIndent()

interface GenericInterfaceTwoOut<out A, out B>

sealed class TwoTypeParameterSealedClass<out A, out B> : GenericInterfaceTwoOut<A, B> {
    object FirstObject : TwoTypeParameterSealedClass<Any?, Nothing>()

    object SecondObject : TwoTypeParameterSealedClass<Double, Double>()

    @GenSealedEnum(generateEnum = true)
    companion object TwoType
}

@Language("kotlin")
val twoTypeParameterSealedClassGenerated = """
package com.livefront.sealedenum.compilation.generics

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [TwoTypeParameterSealedClass]
 */
public enum class TwoTypeParameterSealedClassEnum() {
    TwoTypeParameterSealedClass_FirstObject,
    TwoTypeParameterSealedClass_SecondObject,
}

/**
 * The isomorphic [TwoTypeParameterSealedClassEnum] for [this].
 */
public val TwoTypeParameterSealedClass<*, *>.`enum`: TwoTypeParameterSealedClassEnum
    get() = TwoTypeParameterSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [TwoTypeParameterSealedClass] for [this].
 */
public val TwoTypeParameterSealedClassEnum.sealedObject: TwoTypeParameterSealedClass<*, *>
    get() = TwoTypeParameterSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [TwoTypeParameterSealedClass]
 */
public object TwoTypeParameterSealedClassSealedEnum : SealedEnum<TwoTypeParameterSealedClass<*, *>>,
        SealedEnumWithEnumProvider<TwoTypeParameterSealedClass<*, *>, TwoTypeParameterSealedClassEnum>,
        EnumForSealedEnumProvider<TwoTypeParameterSealedClass<*, *>, TwoTypeParameterSealedClassEnum>
        {
    public override val values: List<TwoTypeParameterSealedClass<*, *>> = listOf(
        TwoTypeParameterSealedClass.FirstObject,
        TwoTypeParameterSealedClass.SecondObject
    )


    public override val enumClass: Class<TwoTypeParameterSealedClassEnum>
        get() = TwoTypeParameterSealedClassEnum::class.java

    public override fun ordinalOf(obj: TwoTypeParameterSealedClass<*, *>): Int = when (obj) {
        TwoTypeParameterSealedClass.FirstObject -> 0
        TwoTypeParameterSealedClass.SecondObject -> 1
    }

    public override fun nameOf(obj: TwoTypeParameterSealedClass<*, *>): String = when (obj) {
        TwoTypeParameterSealedClass.FirstObject -> "TwoTypeParameterSealedClass_FirstObject"
        TwoTypeParameterSealedClass.SecondObject -> "TwoTypeParameterSealedClass_SecondObject"
    }

    public override fun valueOf(name: String): TwoTypeParameterSealedClass<*, *> = when (name) {
        "TwoTypeParameterSealedClass_FirstObject" -> TwoTypeParameterSealedClass.FirstObject
        "TwoTypeParameterSealedClass_SecondObject" -> TwoTypeParameterSealedClass.SecondObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: TwoTypeParameterSealedClass<*, *>):
            TwoTypeParameterSealedClassEnum = when (obj) {
        TwoTypeParameterSealedClass.FirstObject ->
                TwoTypeParameterSealedClassEnum.TwoTypeParameterSealedClass_FirstObject
        TwoTypeParameterSealedClass.SecondObject ->
                TwoTypeParameterSealedClassEnum.TwoTypeParameterSealedClass_SecondObject
    }

    public override fun enumToSealedObject(`enum`: TwoTypeParameterSealedClassEnum):
            TwoTypeParameterSealedClass<*, *> = when (enum) {
        TwoTypeParameterSealedClassEnum.TwoTypeParameterSealedClass_FirstObject ->
                TwoTypeParameterSealedClass.FirstObject
        TwoTypeParameterSealedClassEnum.TwoTypeParameterSealedClass_SecondObject ->
                TwoTypeParameterSealedClass.SecondObject
    }
}

/**
 * The index of [this] in the values list.
 */
public val TwoTypeParameterSealedClass<*, *>.ordinal: Int
    get() = TwoTypeParameterSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val TwoTypeParameterSealedClass<*, *>.name: String
    get() = TwoTypeParameterSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [TwoTypeParameterSealedClass] objects.
 */
public val TwoTypeParameterSealedClass.TwoType.values: List<TwoTypeParameterSealedClass<*, *>>
    get() = TwoTypeParameterSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [TwoTypeParameterSealedClass]
 */
public val TwoTypeParameterSealedClass.TwoType.sealedEnum: TwoTypeParameterSealedClassSealedEnum
    get() = TwoTypeParameterSealedClassSealedEnum

/**
 * Returns the [TwoTypeParameterSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [TwoTypeParameterSealedClass], an
 * [IllegalArgumentException] will be thrown.
 */
public fun TwoTypeParameterSealedClass.TwoType.valueOf(name: String):
        TwoTypeParameterSealedClass<*, *> = TwoTypeParameterSealedClassSealedEnum.valueOf(name)

""".trimIndent()

interface GenericInterfaceInOut<in A, out B>

sealed class LimitedTypeParameterSealedClass<in Number, out String> : GenericInterfaceInOut<Number, String> {
    object FirstObject : LimitedTypeParameterSealedClass<Int, String>()

    object SecondObject : LimitedTypeParameterSealedClass<Int, Any>()

    @GenSealedEnum(generateEnum = true)
    companion object LimitedType
}

@Language("kotlin")
val limitedTypeParameterSealedClassGenerated = """
package com.livefront.sealedenum.compilation.generics

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [LimitedTypeParameterSealedClass]
 */
public enum class LimitedTypeParameterSealedClassEnum() {
    LimitedTypeParameterSealedClass_FirstObject,
    LimitedTypeParameterSealedClass_SecondObject,
}

/**
 * The isomorphic [LimitedTypeParameterSealedClassEnum] for [this].
 */
public val LimitedTypeParameterSealedClass<*, *>.`enum`: LimitedTypeParameterSealedClassEnum
    get() = LimitedTypeParameterSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [LimitedTypeParameterSealedClass] for [this].
 */
public val LimitedTypeParameterSealedClassEnum.sealedObject: LimitedTypeParameterSealedClass<*, *>
    get() = LimitedTypeParameterSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [LimitedTypeParameterSealedClass]
 */
public object LimitedTypeParameterSealedClassSealedEnum :
        SealedEnum<LimitedTypeParameterSealedClass<*, *>>,
        SealedEnumWithEnumProvider<LimitedTypeParameterSealedClass<*, *>, LimitedTypeParameterSealedClassEnum>,
        EnumForSealedEnumProvider<LimitedTypeParameterSealedClass<*, *>, LimitedTypeParameterSealedClassEnum>
        {
    public override val values: List<LimitedTypeParameterSealedClass<*, *>> = listOf(
        LimitedTypeParameterSealedClass.FirstObject,
        LimitedTypeParameterSealedClass.SecondObject
    )


    public override val enumClass: Class<LimitedTypeParameterSealedClassEnum>
        get() = LimitedTypeParameterSealedClassEnum::class.java

    public override fun ordinalOf(obj: LimitedTypeParameterSealedClass<*, *>): Int = when (obj) {
        LimitedTypeParameterSealedClass.FirstObject -> 0
        LimitedTypeParameterSealedClass.SecondObject -> 1
    }

    public override fun nameOf(obj: LimitedTypeParameterSealedClass<*, *>): String = when (obj) {
        LimitedTypeParameterSealedClass.FirstObject -> "LimitedTypeParameterSealedClass_FirstObject"
        LimitedTypeParameterSealedClass.SecondObject ->
                "LimitedTypeParameterSealedClass_SecondObject"
    }

    public override fun valueOf(name: String): LimitedTypeParameterSealedClass<*, *> = when (name) {
        "LimitedTypeParameterSealedClass_FirstObject" -> LimitedTypeParameterSealedClass.FirstObject
        "LimitedTypeParameterSealedClass_SecondObject" ->
                LimitedTypeParameterSealedClass.SecondObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: LimitedTypeParameterSealedClass<*, *>):
            LimitedTypeParameterSealedClassEnum = when (obj) {
        LimitedTypeParameterSealedClass.FirstObject ->
                LimitedTypeParameterSealedClassEnum.LimitedTypeParameterSealedClass_FirstObject
        LimitedTypeParameterSealedClass.SecondObject ->
                LimitedTypeParameterSealedClassEnum.LimitedTypeParameterSealedClass_SecondObject
    }

    public override fun enumToSealedObject(`enum`: LimitedTypeParameterSealedClassEnum):
            LimitedTypeParameterSealedClass<*, *> = when (enum) {
        LimitedTypeParameterSealedClassEnum.LimitedTypeParameterSealedClass_FirstObject ->
                LimitedTypeParameterSealedClass.FirstObject
        LimitedTypeParameterSealedClassEnum.LimitedTypeParameterSealedClass_SecondObject ->
                LimitedTypeParameterSealedClass.SecondObject
    }
}

/**
 * The index of [this] in the values list.
 */
public val LimitedTypeParameterSealedClass<*, *>.ordinal: Int
    get() = LimitedTypeParameterSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val LimitedTypeParameterSealedClass<*, *>.name: String
    get() = LimitedTypeParameterSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [LimitedTypeParameterSealedClass] objects.
 */
public val LimitedTypeParameterSealedClass.LimitedType.values:
        List<LimitedTypeParameterSealedClass<*, *>>
    get() = LimitedTypeParameterSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [LimitedTypeParameterSealedClass]
 */
public val LimitedTypeParameterSealedClass.LimitedType.sealedEnum:
        LimitedTypeParameterSealedClassSealedEnum
    get() = LimitedTypeParameterSealedClassSealedEnum

/**
 * Returns the [LimitedTypeParameterSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [LimitedTypeParameterSealedClass], an
 * [IllegalArgumentException] will be thrown.
 */
public fun LimitedTypeParameterSealedClass.LimitedType.valueOf(name: String):
        LimitedTypeParameterSealedClass<*, *> =
        LimitedTypeParameterSealedClassSealedEnum.valueOf(name)

""".trimIndent()

interface MultipleBoundsInterface1

interface MultipleBoundsInterface2

interface MultipleBoundsInterface3 : MultipleBoundsInterface1, MultipleBoundsInterface2

sealed class MultipleBoundsSealedClass<T> where T : MultipleBoundsInterface1, T : MultipleBoundsInterface2 {
    object FirstObject : MultipleBoundsSealedClass<MultipleBoundsInterface3>()

    @GenSealedEnum(generateEnum = true)
    companion object
}

@Language("kotlin")
val multipleBoundsSealedClassGenerated = """
package com.livefront.sealedenum.compilation.generics

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [MultipleBoundsSealedClass]
 */
public enum class MultipleBoundsSealedClassEnum() {
    MultipleBoundsSealedClass_FirstObject,
}

/**
 * The isomorphic [MultipleBoundsSealedClassEnum] for [this].
 */
public val MultipleBoundsSealedClass<*>.`enum`: MultipleBoundsSealedClassEnum
    get() = MultipleBoundsSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [MultipleBoundsSealedClass] for [this].
 */
public val MultipleBoundsSealedClassEnum.sealedObject: MultipleBoundsSealedClass<*>
    get() = MultipleBoundsSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [MultipleBoundsSealedClass]
 */
public object MultipleBoundsSealedClassSealedEnum : SealedEnum<MultipleBoundsSealedClass<*>>,
        SealedEnumWithEnumProvider<MultipleBoundsSealedClass<*>, MultipleBoundsSealedClassEnum>,
        EnumForSealedEnumProvider<MultipleBoundsSealedClass<*>, MultipleBoundsSealedClassEnum> {
    public override val values: List<MultipleBoundsSealedClass<*>> = listOf(
        MultipleBoundsSealedClass.FirstObject
    )


    public override val enumClass: Class<MultipleBoundsSealedClassEnum>
        get() = MultipleBoundsSealedClassEnum::class.java

    public override fun ordinalOf(obj: MultipleBoundsSealedClass<*>): Int = when (obj) {
        MultipleBoundsSealedClass.FirstObject -> 0
    }

    public override fun nameOf(obj: MultipleBoundsSealedClass<*>): String = when (obj) {
        MultipleBoundsSealedClass.FirstObject -> "MultipleBoundsSealedClass_FirstObject"
    }

    public override fun valueOf(name: String): MultipleBoundsSealedClass<*> = when (name) {
        "MultipleBoundsSealedClass_FirstObject" -> MultipleBoundsSealedClass.FirstObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: MultipleBoundsSealedClass<*>):
            MultipleBoundsSealedClassEnum = when (obj) {
        MultipleBoundsSealedClass.FirstObject ->
                MultipleBoundsSealedClassEnum.MultipleBoundsSealedClass_FirstObject
    }

    public override fun enumToSealedObject(`enum`: MultipleBoundsSealedClassEnum):
            MultipleBoundsSealedClass<*> = when (enum) {
        MultipleBoundsSealedClassEnum.MultipleBoundsSealedClass_FirstObject ->
                MultipleBoundsSealedClass.FirstObject
    }
}

/**
 * The index of [this] in the values list.
 */
public val MultipleBoundsSealedClass<*>.ordinal: Int
    get() = MultipleBoundsSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val MultipleBoundsSealedClass<*>.name: String
    get() = MultipleBoundsSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [MultipleBoundsSealedClass] objects.
 */
public val MultipleBoundsSealedClass.Companion.values: List<MultipleBoundsSealedClass<*>>
    get() = MultipleBoundsSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [MultipleBoundsSealedClass]
 */
public val MultipleBoundsSealedClass.Companion.sealedEnum: MultipleBoundsSealedClassSealedEnum
    get() = MultipleBoundsSealedClassSealedEnum

/**
 * Returns the [MultipleBoundsSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [MultipleBoundsSealedClass], an
 * [IllegalArgumentException] will be thrown.
 */
public fun MultipleBoundsSealedClass.Companion.valueOf(name: String): MultipleBoundsSealedClass<*> =
        MultipleBoundsSealedClassSealedEnum.valueOf(name)

""".trimIndent()
