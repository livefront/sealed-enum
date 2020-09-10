package com.livefront.sealedenum

import org.intellij.lang.annotations.Language

sealed class AlphaOutsideSealedClass {
    @GenSealedEnum(generateEnum = true)
    companion object
}

object AlphaFirstObject : AlphaOutsideSealedClass()

@Language("kotlin")
val alphaOutsideSealedClassGenerated = """
package com.livefront.sealedenum

import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [com.livefront.sealedenum.AlphaOutsideSealedClass]
 */
enum class AlphaOutsideSealedClassEnum {
    AlphaFirstObject
}

/**
 * The isomorphic [AlphaOutsideSealedClassEnum] for [this].
 */
val AlphaOutsideSealedClass.enum: AlphaOutsideSealedClassEnum
    get() = AlphaOutsideSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [AlphaOutsideSealedClass] for [this].
 */
val AlphaOutsideSealedClassEnum.sealedObject: AlphaOutsideSealedClass
    get() = AlphaOutsideSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [AlphaOutsideSealedClass]
 */
object AlphaOutsideSealedClassSealedEnum : SealedEnum<AlphaOutsideSealedClass>,
        SealedEnumWithEnumProvider<AlphaOutsideSealedClass, AlphaOutsideSealedClassEnum>,
        EnumForSealedEnumProvider<AlphaOutsideSealedClass, AlphaOutsideSealedClassEnum> {
    override val values: List<AlphaOutsideSealedClass> = listOf(
        AlphaFirstObject
    )


    override val enumClass: Class<AlphaOutsideSealedClassEnum>
        get() = AlphaOutsideSealedClassEnum::class.java

    override fun ordinalOf(obj: AlphaOutsideSealedClass): Int = when (obj) {
        AlphaFirstObject -> 0
    }

    override fun nameOf(obj: AlphaOutsideSealedClass): String = when (obj) {
        AlphaFirstObject -> "AlphaFirstObject"
    }

    override fun valueOf(name: String): AlphaOutsideSealedClass = when (name) {
        "AlphaFirstObject" -> AlphaFirstObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    override fun sealedObjectToEnum(obj: AlphaOutsideSealedClass): AlphaOutsideSealedClassEnum =
            when (obj) {
        AlphaFirstObject -> AlphaOutsideSealedClassEnum.AlphaFirstObject
    }

    override fun enumToSealedObject(enum: AlphaOutsideSealedClassEnum): AlphaOutsideSealedClass =
            when (enum) {
        AlphaOutsideSealedClassEnum.AlphaFirstObject -> AlphaFirstObject
    }
}

/**
 * The index of [this] in the values list.
 */
val AlphaOutsideSealedClass.ordinal: Int
    get() = AlphaOutsideSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val AlphaOutsideSealedClass.name: String
    get() = AlphaOutsideSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [AlphaOutsideSealedClass] objects.
 */
val AlphaOutsideSealedClass.Companion.values: List<AlphaOutsideSealedClass>
    get() = AlphaOutsideSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [AlphaOutsideSealedClass]
 */
val AlphaOutsideSealedClass.Companion.sealedEnum: AlphaOutsideSealedClassSealedEnum
    get() = AlphaOutsideSealedClassSealedEnum

/**
 * Returns the [AlphaOutsideSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [AlphaOutsideSealedClass], an
 * [IllegalArgumentException] will be thrown.
 */
fun AlphaOutsideSealedClass.Companion.valueOf(name: String): AlphaOutsideSealedClass =
        AlphaOutsideSealedClassSealedEnum.valueOf(name)

""".trimIndent()

sealed class BetaOutsideSealedClass {
    @GenSealedEnum(generateEnum = true)
    companion object
}

object BetaFirstObject : BetaOutsideSealedClass()

object BetaSecondObject : BetaOutsideSealedClass()

@Language("kotlin")
val betaOutsideSealedClassGenerated = """
package com.livefront.sealedenum

import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [com.livefront.sealedenum.BetaOutsideSealedClass]
 */
enum class BetaOutsideSealedClassEnum {
    BetaFirstObject,

    BetaSecondObject
}

/**
 * The isomorphic [BetaOutsideSealedClassEnum] for [this].
 */
val BetaOutsideSealedClass.enum: BetaOutsideSealedClassEnum
    get() = BetaOutsideSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [BetaOutsideSealedClass] for [this].
 */
val BetaOutsideSealedClassEnum.sealedObject: BetaOutsideSealedClass
    get() = BetaOutsideSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [BetaOutsideSealedClass]
 */
object BetaOutsideSealedClassSealedEnum : SealedEnum<BetaOutsideSealedClass>,
        SealedEnumWithEnumProvider<BetaOutsideSealedClass, BetaOutsideSealedClassEnum>,
        EnumForSealedEnumProvider<BetaOutsideSealedClass, BetaOutsideSealedClassEnum> {
    override val values: List<BetaOutsideSealedClass> = listOf(
        BetaFirstObject,
        BetaSecondObject
    )


    override val enumClass: Class<BetaOutsideSealedClassEnum>
        get() = BetaOutsideSealedClassEnum::class.java

    override fun ordinalOf(obj: BetaOutsideSealedClass): Int = when (obj) {
        BetaFirstObject -> 0
        BetaSecondObject -> 1
    }

    override fun nameOf(obj: BetaOutsideSealedClass): String = when (obj) {
        BetaFirstObject -> "BetaFirstObject"
        BetaSecondObject -> "BetaSecondObject"
    }

    override fun valueOf(name: String): BetaOutsideSealedClass = when (name) {
        "BetaFirstObject" -> BetaFirstObject
        "BetaSecondObject" -> BetaSecondObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    override fun sealedObjectToEnum(obj: BetaOutsideSealedClass): BetaOutsideSealedClassEnum = when
            (obj) {
        BetaFirstObject -> BetaOutsideSealedClassEnum.BetaFirstObject
        BetaSecondObject -> BetaOutsideSealedClassEnum.BetaSecondObject
    }

    override fun enumToSealedObject(enum: BetaOutsideSealedClassEnum): BetaOutsideSealedClass = when
            (enum) {
        BetaOutsideSealedClassEnum.BetaFirstObject -> BetaFirstObject
        BetaOutsideSealedClassEnum.BetaSecondObject -> BetaSecondObject
    }
}

/**
 * The index of [this] in the values list.
 */
val BetaOutsideSealedClass.ordinal: Int
    get() = BetaOutsideSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val BetaOutsideSealedClass.name: String
    get() = BetaOutsideSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [BetaOutsideSealedClass] objects.
 */
val BetaOutsideSealedClass.Companion.values: List<BetaOutsideSealedClass>
    get() = BetaOutsideSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [BetaOutsideSealedClass]
 */
val BetaOutsideSealedClass.Companion.sealedEnum: BetaOutsideSealedClassSealedEnum
    get() = BetaOutsideSealedClassSealedEnum

/**
 * Returns the [BetaOutsideSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [BetaOutsideSealedClass], an
 * [IllegalArgumentException] will be thrown.
 */
fun BetaOutsideSealedClass.Companion.valueOf(name: String): BetaOutsideSealedClass =
        BetaOutsideSealedClassSealedEnum.valueOf(name)

""".trimIndent()

object GammaFirstObject : GammaOutsideSealedClass()

sealed class GammaOutsideSealedClass {

    object GammaSecondObject : GammaOutsideSealedClass()

    @GenSealedEnum(generateEnum = true)
    companion object
}

object GammaThirdObject : GammaOutsideSealedClass()

@Language("kotlin")
val gammaOutsideSealedClassGenerated = """
package com.livefront.sealedenum

import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [com.livefront.sealedenum.GammaOutsideSealedClass]
 */
enum class GammaOutsideSealedClassEnum {
    GammaFirstObject,

    GammaThirdObject,

    GammaOutsideSealedClass_GammaSecondObject
}

/**
 * The isomorphic [GammaOutsideSealedClassEnum] for [this].
 */
val GammaOutsideSealedClass.enum: GammaOutsideSealedClassEnum
    get() = GammaOutsideSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [GammaOutsideSealedClass] for [this].
 */
val GammaOutsideSealedClassEnum.sealedObject: GammaOutsideSealedClass
    get() = GammaOutsideSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [GammaOutsideSealedClass]
 */
object GammaOutsideSealedClassSealedEnum : SealedEnum<GammaOutsideSealedClass>,
        SealedEnumWithEnumProvider<GammaOutsideSealedClass, GammaOutsideSealedClassEnum>,
        EnumForSealedEnumProvider<GammaOutsideSealedClass, GammaOutsideSealedClassEnum> {
    override val values: List<GammaOutsideSealedClass> = listOf(
        GammaFirstObject,
        GammaThirdObject,
        GammaOutsideSealedClass.GammaSecondObject
    )


    override val enumClass: Class<GammaOutsideSealedClassEnum>
        get() = GammaOutsideSealedClassEnum::class.java

    override fun ordinalOf(obj: GammaOutsideSealedClass): Int = when (obj) {
        GammaFirstObject -> 0
        GammaThirdObject -> 1
        GammaOutsideSealedClass.GammaSecondObject -> 2
    }

    override fun nameOf(obj: GammaOutsideSealedClass): String = when (obj) {
        GammaFirstObject -> "GammaFirstObject"
        GammaThirdObject -> "GammaThirdObject"
        GammaOutsideSealedClass.GammaSecondObject -> "GammaOutsideSealedClass_GammaSecondObject"
    }

    override fun valueOf(name: String): GammaOutsideSealedClass = when (name) {
        "GammaFirstObject" -> GammaFirstObject
        "GammaThirdObject" -> GammaThirdObject
        "GammaOutsideSealedClass_GammaSecondObject" -> GammaOutsideSealedClass.GammaSecondObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    override fun sealedObjectToEnum(obj: GammaOutsideSealedClass): GammaOutsideSealedClassEnum =
            when (obj) {
        GammaFirstObject -> GammaOutsideSealedClassEnum.GammaFirstObject
        GammaThirdObject -> GammaOutsideSealedClassEnum.GammaThirdObject
        GammaOutsideSealedClass.GammaSecondObject ->
                GammaOutsideSealedClassEnum.GammaOutsideSealedClass_GammaSecondObject
    }

    override fun enumToSealedObject(enum: GammaOutsideSealedClassEnum): GammaOutsideSealedClass =
            when (enum) {
        GammaOutsideSealedClassEnum.GammaFirstObject -> GammaFirstObject
        GammaOutsideSealedClassEnum.GammaThirdObject -> GammaThirdObject
        GammaOutsideSealedClassEnum.GammaOutsideSealedClass_GammaSecondObject ->
                GammaOutsideSealedClass.GammaSecondObject
    }
}

/**
 * The index of [this] in the values list.
 */
val GammaOutsideSealedClass.ordinal: Int
    get() = GammaOutsideSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val GammaOutsideSealedClass.name: String
    get() = GammaOutsideSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [GammaOutsideSealedClass] objects.
 */
val GammaOutsideSealedClass.Companion.values: List<GammaOutsideSealedClass>
    get() = GammaOutsideSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [GammaOutsideSealedClass]
 */
val GammaOutsideSealedClass.Companion.sealedEnum: GammaOutsideSealedClassSealedEnum
    get() = GammaOutsideSealedClassSealedEnum

/**
 * Returns the [GammaOutsideSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [GammaOutsideSealedClass], an
 * [IllegalArgumentException] will be thrown.
 */
fun GammaOutsideSealedClass.Companion.valueOf(name: String): GammaOutsideSealedClass =
        GammaOutsideSealedClassSealedEnum.valueOf(name)

""".trimIndent()

sealed class DeltaOutsideSealedClass {
    object DeltaObject : DeltaOutsideSealedClass()

    @GenSealedEnum(generateEnum = true)
    companion object
}

object DeltaObject : DeltaOutsideSealedClass()

@Language("kotlin")
val deltaOutsideSealedClassGenerated = """
package com.livefront.sealedenum

import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [com.livefront.sealedenum.DeltaOutsideSealedClass]
 */
enum class DeltaOutsideSealedClassEnum {
    DeltaObject,

    DeltaOutsideSealedClass_DeltaObject
}

/**
 * The isomorphic [DeltaOutsideSealedClassEnum] for [this].
 */
val DeltaOutsideSealedClass.enum: DeltaOutsideSealedClassEnum
    get() = DeltaOutsideSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [DeltaOutsideSealedClass] for [this].
 */
val DeltaOutsideSealedClassEnum.sealedObject: DeltaOutsideSealedClass
    get() = DeltaOutsideSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [DeltaOutsideSealedClass]
 */
object DeltaOutsideSealedClassSealedEnum : SealedEnum<DeltaOutsideSealedClass>,
        SealedEnumWithEnumProvider<DeltaOutsideSealedClass, DeltaOutsideSealedClassEnum>,
        EnumForSealedEnumProvider<DeltaOutsideSealedClass, DeltaOutsideSealedClassEnum> {
    override val values: List<DeltaOutsideSealedClass> = listOf(
        DeltaObject,
        DeltaOutsideSealedClass.DeltaObject
    )


    override val enumClass: Class<DeltaOutsideSealedClassEnum>
        get() = DeltaOutsideSealedClassEnum::class.java

    override fun ordinalOf(obj: DeltaOutsideSealedClass): Int = when (obj) {
        DeltaObject -> 0
        DeltaOutsideSealedClass.DeltaObject -> 1
    }

    override fun nameOf(obj: DeltaOutsideSealedClass): String = when (obj) {
        DeltaObject -> "DeltaObject"
        DeltaOutsideSealedClass.DeltaObject -> "DeltaOutsideSealedClass_DeltaObject"
    }

    override fun valueOf(name: String): DeltaOutsideSealedClass = when (name) {
        "DeltaObject" -> DeltaObject
        "DeltaOutsideSealedClass_DeltaObject" -> DeltaOutsideSealedClass.DeltaObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    override fun sealedObjectToEnum(obj: DeltaOutsideSealedClass): DeltaOutsideSealedClassEnum =
            when (obj) {
        DeltaObject -> DeltaOutsideSealedClassEnum.DeltaObject
        DeltaOutsideSealedClass.DeltaObject ->
                DeltaOutsideSealedClassEnum.DeltaOutsideSealedClass_DeltaObject
    }

    override fun enumToSealedObject(enum: DeltaOutsideSealedClassEnum): DeltaOutsideSealedClass =
            when (enum) {
        DeltaOutsideSealedClassEnum.DeltaObject -> DeltaObject
        DeltaOutsideSealedClassEnum.DeltaOutsideSealedClass_DeltaObject ->
                DeltaOutsideSealedClass.DeltaObject
    }
}

/**
 * The index of [this] in the values list.
 */
val DeltaOutsideSealedClass.ordinal: Int
    get() = DeltaOutsideSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val DeltaOutsideSealedClass.name: String
    get() = DeltaOutsideSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [DeltaOutsideSealedClass] objects.
 */
val DeltaOutsideSealedClass.Companion.values: List<DeltaOutsideSealedClass>
    get() = DeltaOutsideSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [DeltaOutsideSealedClass]
 */
val DeltaOutsideSealedClass.Companion.sealedEnum: DeltaOutsideSealedClassSealedEnum
    get() = DeltaOutsideSealedClassSealedEnum

/**
 * Returns the [DeltaOutsideSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [DeltaOutsideSealedClass], an
 * [IllegalArgumentException] will be thrown.
 */
fun DeltaOutsideSealedClass.Companion.valueOf(name: String): DeltaOutsideSealedClass =
        DeltaOutsideSealedClassSealedEnum.valueOf(name)

""".trimIndent()
