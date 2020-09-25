package com.livefront.sealedenum

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
package com.livefront.sealedenum

import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [OneTypeParameterSealedClass]
 */
enum class OneTypeParameterSealedClassEnum {
    OneTypeParameterSealedClass_FirstObject,

    OneTypeParameterSealedClass_SecondObject,

    OneTypeParameterSealedClass_ThirdObject
}

/**
 * The isomorphic [OneTypeParameterSealedClassEnum] for [this].
 */
val OneTypeParameterSealedClass<*>.enum: OneTypeParameterSealedClassEnum
    get() = OneTypeParameterSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [OneTypeParameterSealedClass] for [this].
 */
val OneTypeParameterSealedClassEnum.sealedObject: OneTypeParameterSealedClass<*>
    get() = OneTypeParameterSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [OneTypeParameterSealedClass]
 */
object OneTypeParameterSealedClassSealedEnum : SealedEnum<OneTypeParameterSealedClass<*>>,
        SealedEnumWithEnumProvider<OneTypeParameterSealedClass<*>, OneTypeParameterSealedClassEnum>,
        EnumForSealedEnumProvider<OneTypeParameterSealedClass<*>, OneTypeParameterSealedClassEnum> {
    override val values: List<OneTypeParameterSealedClass<*>> = listOf(
        OneTypeParameterSealedClass.FirstObject,
        OneTypeParameterSealedClass.SecondObject,
        OneTypeParameterSealedClass.ThirdObject
    )


    override val enumClass: Class<OneTypeParameterSealedClassEnum>
        get() = OneTypeParameterSealedClassEnum::class.java

    override fun ordinalOf(obj: OneTypeParameterSealedClass<*>): Int = when (obj) {
        OneTypeParameterSealedClass.FirstObject -> 0
        OneTypeParameterSealedClass.SecondObject -> 1
        OneTypeParameterSealedClass.ThirdObject -> 2
    }

    override fun nameOf(obj: OneTypeParameterSealedClass<*>): String = when (obj) {
        OneTypeParameterSealedClass.FirstObject -> "OneTypeParameterSealedClass_FirstObject"
        OneTypeParameterSealedClass.SecondObject -> "OneTypeParameterSealedClass_SecondObject"
        OneTypeParameterSealedClass.ThirdObject -> "OneTypeParameterSealedClass_ThirdObject"
    }

    override fun valueOf(name: String): OneTypeParameterSealedClass<*> = when (name) {
        "OneTypeParameterSealedClass_FirstObject" -> OneTypeParameterSealedClass.FirstObject
        "OneTypeParameterSealedClass_SecondObject" -> OneTypeParameterSealedClass.SecondObject
        "OneTypeParameterSealedClass_ThirdObject" -> OneTypeParameterSealedClass.ThirdObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    override fun sealedObjectToEnum(obj: OneTypeParameterSealedClass<*>):
            OneTypeParameterSealedClassEnum = when (obj) {
        OneTypeParameterSealedClass.FirstObject ->
                OneTypeParameterSealedClassEnum.OneTypeParameterSealedClass_FirstObject
        OneTypeParameterSealedClass.SecondObject ->
                OneTypeParameterSealedClassEnum.OneTypeParameterSealedClass_SecondObject
        OneTypeParameterSealedClass.ThirdObject ->
                OneTypeParameterSealedClassEnum.OneTypeParameterSealedClass_ThirdObject
    }

    override fun enumToSealedObject(enum: OneTypeParameterSealedClassEnum):
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
val OneTypeParameterSealedClass<*>.ordinal: Int
    get() = OneTypeParameterSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val OneTypeParameterSealedClass<*>.name: String
    get() = OneTypeParameterSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [OneTypeParameterSealedClass] objects.
 */
val OneTypeParameterSealedClass.OneType.values: List<OneTypeParameterSealedClass<*>>
    get() = OneTypeParameterSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [OneTypeParameterSealedClass]
 */
val OneTypeParameterSealedClass.OneType.sealedEnum: OneTypeParameterSealedClassSealedEnum
    get() = OneTypeParameterSealedClassSealedEnum

/**
 * Returns the [OneTypeParameterSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [OneTypeParameterSealedClass], an
 * [IllegalArgumentException] will be thrown.
 */
fun OneTypeParameterSealedClass.OneType.valueOf(name: String): OneTypeParameterSealedClass<*> =
        OneTypeParameterSealedClassSealedEnum.valueOf(name)

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
package com.livefront.sealedenum

import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [TwoTypeParameterSealedClass]
 */
enum class TwoTypeParameterSealedClassEnum {
    TwoTypeParameterSealedClass_FirstObject,

    TwoTypeParameterSealedClass_SecondObject
}

/**
 * The isomorphic [TwoTypeParameterSealedClassEnum] for [this].
 */
val TwoTypeParameterSealedClass<*, *>.enum: TwoTypeParameterSealedClassEnum
    get() = TwoTypeParameterSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [TwoTypeParameterSealedClass] for [this].
 */
val TwoTypeParameterSealedClassEnum.sealedObject: TwoTypeParameterSealedClass<*, *>
    get() = TwoTypeParameterSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [TwoTypeParameterSealedClass]
 */
object TwoTypeParameterSealedClassSealedEnum : SealedEnum<TwoTypeParameterSealedClass<*, *>>,
        SealedEnumWithEnumProvider<TwoTypeParameterSealedClass<*, *>,
        TwoTypeParameterSealedClassEnum>, EnumForSealedEnumProvider<TwoTypeParameterSealedClass<*,
        *>, TwoTypeParameterSealedClassEnum> {
    override val values: List<TwoTypeParameterSealedClass<*, *>> = listOf(
        TwoTypeParameterSealedClass.FirstObject,
        TwoTypeParameterSealedClass.SecondObject
    )


    override val enumClass: Class<TwoTypeParameterSealedClassEnum>
        get() = TwoTypeParameterSealedClassEnum::class.java

    override fun ordinalOf(obj: TwoTypeParameterSealedClass<*, *>): Int = when (obj) {
        TwoTypeParameterSealedClass.FirstObject -> 0
        TwoTypeParameterSealedClass.SecondObject -> 1
    }

    override fun nameOf(obj: TwoTypeParameterSealedClass<*, *>): String = when (obj) {
        TwoTypeParameterSealedClass.FirstObject -> "TwoTypeParameterSealedClass_FirstObject"
        TwoTypeParameterSealedClass.SecondObject -> "TwoTypeParameterSealedClass_SecondObject"
    }

    override fun valueOf(name: String): TwoTypeParameterSealedClass<*, *> = when (name) {
        "TwoTypeParameterSealedClass_FirstObject" -> TwoTypeParameterSealedClass.FirstObject
        "TwoTypeParameterSealedClass_SecondObject" -> TwoTypeParameterSealedClass.SecondObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    override fun sealedObjectToEnum(obj: TwoTypeParameterSealedClass<*, *>):
            TwoTypeParameterSealedClassEnum = when (obj) {
        TwoTypeParameterSealedClass.FirstObject ->
                TwoTypeParameterSealedClassEnum.TwoTypeParameterSealedClass_FirstObject
        TwoTypeParameterSealedClass.SecondObject ->
                TwoTypeParameterSealedClassEnum.TwoTypeParameterSealedClass_SecondObject
    }

    override fun enumToSealedObject(enum: TwoTypeParameterSealedClassEnum):
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
val TwoTypeParameterSealedClass<*, *>.ordinal: Int
    get() = TwoTypeParameterSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val TwoTypeParameterSealedClass<*, *>.name: String
    get() = TwoTypeParameterSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [TwoTypeParameterSealedClass] objects.
 */
val TwoTypeParameterSealedClass.TwoType.values: List<TwoTypeParameterSealedClass<*, *>>
    get() = TwoTypeParameterSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [TwoTypeParameterSealedClass]
 */
val TwoTypeParameterSealedClass.TwoType.sealedEnum: TwoTypeParameterSealedClassSealedEnum
    get() = TwoTypeParameterSealedClassSealedEnum

/**
 * Returns the [TwoTypeParameterSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [TwoTypeParameterSealedClass], an
 * [IllegalArgumentException] will be thrown.
 */
fun TwoTypeParameterSealedClass.TwoType.valueOf(name: String): TwoTypeParameterSealedClass<*, *> =
        TwoTypeParameterSealedClassSealedEnum.valueOf(name)

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
package com.livefront.sealedenum

import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [LimitedTypeParameterSealedClass]
 */
enum class LimitedTypeParameterSealedClassEnum {
    LimitedTypeParameterSealedClass_FirstObject,

    LimitedTypeParameterSealedClass_SecondObject
}

/**
 * The isomorphic [LimitedTypeParameterSealedClassEnum] for [this].
 */
val LimitedTypeParameterSealedClass<*, *>.enum: LimitedTypeParameterSealedClassEnum
    get() = LimitedTypeParameterSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [LimitedTypeParameterSealedClass] for [this].
 */
val LimitedTypeParameterSealedClassEnum.sealedObject: LimitedTypeParameterSealedClass<*, *>
    get() = LimitedTypeParameterSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [LimitedTypeParameterSealedClass]
 */
object LimitedTypeParameterSealedClassSealedEnum : SealedEnum<LimitedTypeParameterSealedClass<*,
        *>>, SealedEnumWithEnumProvider<LimitedTypeParameterSealedClass<*, *>,
        LimitedTypeParameterSealedClassEnum>,
        EnumForSealedEnumProvider<LimitedTypeParameterSealedClass<*, *>,
        LimitedTypeParameterSealedClassEnum> {
    override val values: List<LimitedTypeParameterSealedClass<*, *>> = listOf(
        LimitedTypeParameterSealedClass.FirstObject,
        LimitedTypeParameterSealedClass.SecondObject
    )


    override val enumClass: Class<LimitedTypeParameterSealedClassEnum>
        get() = LimitedTypeParameterSealedClassEnum::class.java

    override fun ordinalOf(obj: LimitedTypeParameterSealedClass<*, *>): Int = when (obj) {
        LimitedTypeParameterSealedClass.FirstObject -> 0
        LimitedTypeParameterSealedClass.SecondObject -> 1
    }

    override fun nameOf(obj: LimitedTypeParameterSealedClass<*, *>): String = when (obj) {
        LimitedTypeParameterSealedClass.FirstObject -> "LimitedTypeParameterSealedClass_FirstObject"
        LimitedTypeParameterSealedClass.SecondObject ->
                "LimitedTypeParameterSealedClass_SecondObject"
    }

    override fun valueOf(name: String): LimitedTypeParameterSealedClass<*, *> = when (name) {
        "LimitedTypeParameterSealedClass_FirstObject" -> LimitedTypeParameterSealedClass.FirstObject
        "LimitedTypeParameterSealedClass_SecondObject" ->
                LimitedTypeParameterSealedClass.SecondObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    override fun sealedObjectToEnum(obj: LimitedTypeParameterSealedClass<*, *>):
            LimitedTypeParameterSealedClassEnum = when (obj) {
        LimitedTypeParameterSealedClass.FirstObject ->
                LimitedTypeParameterSealedClassEnum.LimitedTypeParameterSealedClass_FirstObject
        LimitedTypeParameterSealedClass.SecondObject ->
                LimitedTypeParameterSealedClassEnum.LimitedTypeParameterSealedClass_SecondObject
    }

    override fun enumToSealedObject(enum: LimitedTypeParameterSealedClassEnum):
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
val LimitedTypeParameterSealedClass<*, *>.ordinal: Int
    get() = LimitedTypeParameterSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val LimitedTypeParameterSealedClass<*, *>.name: String
    get() = LimitedTypeParameterSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [LimitedTypeParameterSealedClass] objects.
 */
val LimitedTypeParameterSealedClass.LimitedType.values: List<LimitedTypeParameterSealedClass<*, *>>
    get() = LimitedTypeParameterSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [LimitedTypeParameterSealedClass]
 */
val LimitedTypeParameterSealedClass.LimitedType.sealedEnum:
        LimitedTypeParameterSealedClassSealedEnum
    get() = LimitedTypeParameterSealedClassSealedEnum

/**
 * Returns the [LimitedTypeParameterSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [LimitedTypeParameterSealedClass], an
 * [IllegalArgumentException] will be thrown.
 */
fun LimitedTypeParameterSealedClass.LimitedType.valueOf(name: String):
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
package com.livefront.sealedenum

import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [MultipleBoundsSealedClass]
 */
enum class MultipleBoundsSealedClassEnum {
    MultipleBoundsSealedClass_FirstObject
}

/**
 * The isomorphic [MultipleBoundsSealedClassEnum] for [this].
 */
val MultipleBoundsSealedClass<*>.enum: MultipleBoundsSealedClassEnum
    get() = MultipleBoundsSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [MultipleBoundsSealedClass] for [this].
 */
val MultipleBoundsSealedClassEnum.sealedObject: MultipleBoundsSealedClass<*>
    get() = MultipleBoundsSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [MultipleBoundsSealedClass]
 */
object MultipleBoundsSealedClassSealedEnum : SealedEnum<MultipleBoundsSealedClass<*>>,
        SealedEnumWithEnumProvider<MultipleBoundsSealedClass<*>, MultipleBoundsSealedClassEnum>,
        EnumForSealedEnumProvider<MultipleBoundsSealedClass<*>, MultipleBoundsSealedClassEnum> {
    override val values: List<MultipleBoundsSealedClass<*>> = listOf(
        MultipleBoundsSealedClass.FirstObject
    )


    override val enumClass: Class<MultipleBoundsSealedClassEnum>
        get() = MultipleBoundsSealedClassEnum::class.java

    override fun ordinalOf(obj: MultipleBoundsSealedClass<*>): Int = when (obj) {
        MultipleBoundsSealedClass.FirstObject -> 0
    }

    override fun nameOf(obj: MultipleBoundsSealedClass<*>): String = when (obj) {
        MultipleBoundsSealedClass.FirstObject -> "MultipleBoundsSealedClass_FirstObject"
    }

    override fun valueOf(name: String): MultipleBoundsSealedClass<*> = when (name) {
        "MultipleBoundsSealedClass_FirstObject" -> MultipleBoundsSealedClass.FirstObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    override fun sealedObjectToEnum(obj: MultipleBoundsSealedClass<*>):
            MultipleBoundsSealedClassEnum = when (obj) {
        MultipleBoundsSealedClass.FirstObject ->
                MultipleBoundsSealedClassEnum.MultipleBoundsSealedClass_FirstObject
    }

    override fun enumToSealedObject(enum: MultipleBoundsSealedClassEnum):
            MultipleBoundsSealedClass<*> = when (enum) {
        MultipleBoundsSealedClassEnum.MultipleBoundsSealedClass_FirstObject ->
                MultipleBoundsSealedClass.FirstObject
    }
}

/**
 * The index of [this] in the values list.
 */
val MultipleBoundsSealedClass<*>.ordinal: Int
    get() = MultipleBoundsSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val MultipleBoundsSealedClass<*>.name: String
    get() = MultipleBoundsSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [MultipleBoundsSealedClass] objects.
 */
val MultipleBoundsSealedClass.Companion.values: List<MultipleBoundsSealedClass<*>>
    get() = MultipleBoundsSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [MultipleBoundsSealedClass]
 */
val MultipleBoundsSealedClass.Companion.sealedEnum: MultipleBoundsSealedClassSealedEnum
    get() = MultipleBoundsSealedClassSealedEnum

/**
 * Returns the [MultipleBoundsSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [MultipleBoundsSealedClass], an
 * [IllegalArgumentException] will be thrown.
 */
fun MultipleBoundsSealedClass.Companion.valueOf(name: String): MultipleBoundsSealedClass<*> =
        MultipleBoundsSealedClassSealedEnum.valueOf(name)

""".trimIndent()
