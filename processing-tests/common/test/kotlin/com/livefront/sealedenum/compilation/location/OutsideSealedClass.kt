package com.livefront.sealedenum.compilation.location

import com.livefront.sealedenum.GenSealedEnum
import org.intellij.lang.annotations.Language

sealed class AlphaOutsideSealedClass {
    @GenSealedEnum(generateEnum = true)
    companion object
}

object AlphaFirstObject : AlphaOutsideSealedClass()

@Language("kotlin")
val alphaOutsideSealedClassGenerated = """
package com.livefront.sealedenum.compilation.location

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import kotlin.Int
import kotlin.LazyThreadSafetyMode
import kotlin.String
import kotlin.collections.List
import kotlin.reflect.KClass

/**
 * An isomorphic enum for the sealed class [AlphaOutsideSealedClass]
 */
public enum class AlphaOutsideSealedClassEnum() {
    AlphaFirstObject,
}

/**
 * The isomorphic [AlphaOutsideSealedClassEnum] for [this].
 */
public val AlphaOutsideSealedClass.`enum`: AlphaOutsideSealedClassEnum
    get() = AlphaOutsideSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [AlphaOutsideSealedClass] for [this].
 */
public val AlphaOutsideSealedClassEnum.sealedObject: AlphaOutsideSealedClass
    get() = AlphaOutsideSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [AlphaOutsideSealedClass]
 */
public object AlphaOutsideSealedClassSealedEnum : SealedEnum<AlphaOutsideSealedClass>,
        SealedEnumWithEnumProvider<AlphaOutsideSealedClass, AlphaOutsideSealedClassEnum>,
        EnumForSealedEnumProvider<AlphaOutsideSealedClass, AlphaOutsideSealedClassEnum> {
    public override val values: List<AlphaOutsideSealedClass> by lazy(mode =
            LazyThreadSafetyMode.PUBLICATION) {
        listOf(
            AlphaFirstObject
        )
    }


    public override val enumClass: KClass<AlphaOutsideSealedClassEnum>
        get() = AlphaOutsideSealedClassEnum::class

    public override fun ordinalOf(obj: AlphaOutsideSealedClass): Int = when (obj) {
        is AlphaFirstObject -> 0
    }

    public override fun nameOf(obj: AlphaOutsideSealedClass): String = when (obj) {
        is AlphaFirstObject -> "AlphaFirstObject"
    }

    public override fun valueOf(name: String): AlphaOutsideSealedClass = when (name) {
        "AlphaFirstObject" -> AlphaFirstObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: AlphaOutsideSealedClass):
            AlphaOutsideSealedClassEnum = when (obj) {
        is AlphaFirstObject -> AlphaOutsideSealedClassEnum.AlphaFirstObject
    }

    public override fun enumToSealedObject(`enum`: AlphaOutsideSealedClassEnum):
            AlphaOutsideSealedClass = when (enum) {
        AlphaOutsideSealedClassEnum.AlphaFirstObject -> AlphaFirstObject
    }
}

/**
 * The index of [this] in the values list.
 */
public val AlphaOutsideSealedClass.ordinal: Int
    get() = AlphaOutsideSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val AlphaOutsideSealedClass.name: String
    get() = AlphaOutsideSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [AlphaOutsideSealedClass] objects.
 */
public val AlphaOutsideSealedClass.Companion.values: List<AlphaOutsideSealedClass>
    get() = AlphaOutsideSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [AlphaOutsideSealedClass]
 */
public val AlphaOutsideSealedClass.Companion.sealedEnum: AlphaOutsideSealedClassSealedEnum
    get() = AlphaOutsideSealedClassSealedEnum

/**
 * Returns the [AlphaOutsideSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [AlphaOutsideSealedClass], an
 * [IllegalArgumentException] will be thrown.
 */
public fun AlphaOutsideSealedClass.Companion.valueOf(name: String): AlphaOutsideSealedClass =
        AlphaOutsideSealedClassSealedEnum.valueOf(name)

""".trimIndent()

sealed class BetaOutsideSealedClass {
    @GenSealedEnum(generateEnum = true)
    companion object
}

object BetaFirstObject : BetaOutsideSealedClass()

object BetaSecondObject : BetaOutsideSealedClass()

object BetaThirdObject : BetaOutsideSealedClass()

object BetaFourthObject : BetaOutsideSealedClass()

@Language("kotlin")
val betaOutsideSealedClassGenerated = """
package com.livefront.sealedenum.compilation.location

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import kotlin.Int
import kotlin.LazyThreadSafetyMode
import kotlin.String
import kotlin.collections.List
import kotlin.reflect.KClass

/**
 * An isomorphic enum for the sealed class [BetaOutsideSealedClass]
 */
public enum class BetaOutsideSealedClassEnum() {
    BetaFirstObject,
    BetaFourthObject,
    BetaSecondObject,
    BetaThirdObject,
}

/**
 * The isomorphic [BetaOutsideSealedClassEnum] for [this].
 */
public val BetaOutsideSealedClass.`enum`: BetaOutsideSealedClassEnum
    get() = BetaOutsideSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [BetaOutsideSealedClass] for [this].
 */
public val BetaOutsideSealedClassEnum.sealedObject: BetaOutsideSealedClass
    get() = BetaOutsideSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [BetaOutsideSealedClass]
 */
public object BetaOutsideSealedClassSealedEnum : SealedEnum<BetaOutsideSealedClass>,
        SealedEnumWithEnumProvider<BetaOutsideSealedClass, BetaOutsideSealedClassEnum>,
        EnumForSealedEnumProvider<BetaOutsideSealedClass, BetaOutsideSealedClassEnum> {
    public override val values: List<BetaOutsideSealedClass> by lazy(mode =
            LazyThreadSafetyMode.PUBLICATION) {
        listOf(
            BetaFirstObject,
            BetaFourthObject,
            BetaSecondObject,
            BetaThirdObject
        )
    }


    public override val enumClass: KClass<BetaOutsideSealedClassEnum>
        get() = BetaOutsideSealedClassEnum::class

    public override fun ordinalOf(obj: BetaOutsideSealedClass): Int = when (obj) {
        is BetaFirstObject -> 0
        is BetaFourthObject -> 1
        is BetaSecondObject -> 2
        is BetaThirdObject -> 3
    }

    public override fun nameOf(obj: BetaOutsideSealedClass): String = when (obj) {
        is BetaFirstObject -> "BetaFirstObject"
        is BetaFourthObject -> "BetaFourthObject"
        is BetaSecondObject -> "BetaSecondObject"
        is BetaThirdObject -> "BetaThirdObject"
    }

    public override fun valueOf(name: String): BetaOutsideSealedClass = when (name) {
        "BetaFirstObject" -> BetaFirstObject
        "BetaFourthObject" -> BetaFourthObject
        "BetaSecondObject" -> BetaSecondObject
        "BetaThirdObject" -> BetaThirdObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: BetaOutsideSealedClass): BetaOutsideSealedClassEnum
            = when (obj) {
        is BetaFirstObject -> BetaOutsideSealedClassEnum.BetaFirstObject
        is BetaFourthObject -> BetaOutsideSealedClassEnum.BetaFourthObject
        is BetaSecondObject -> BetaOutsideSealedClassEnum.BetaSecondObject
        is BetaThirdObject -> BetaOutsideSealedClassEnum.BetaThirdObject
    }

    public override fun enumToSealedObject(`enum`: BetaOutsideSealedClassEnum):
            BetaOutsideSealedClass = when (enum) {
        BetaOutsideSealedClassEnum.BetaFirstObject -> BetaFirstObject
        BetaOutsideSealedClassEnum.BetaFourthObject -> BetaFourthObject
        BetaOutsideSealedClassEnum.BetaSecondObject -> BetaSecondObject
        BetaOutsideSealedClassEnum.BetaThirdObject -> BetaThirdObject
    }
}

/**
 * The index of [this] in the values list.
 */
public val BetaOutsideSealedClass.ordinal: Int
    get() = BetaOutsideSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val BetaOutsideSealedClass.name: String
    get() = BetaOutsideSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [BetaOutsideSealedClass] objects.
 */
public val BetaOutsideSealedClass.Companion.values: List<BetaOutsideSealedClass>
    get() = BetaOutsideSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [BetaOutsideSealedClass]
 */
public val BetaOutsideSealedClass.Companion.sealedEnum: BetaOutsideSealedClassSealedEnum
    get() = BetaOutsideSealedClassSealedEnum

/**
 * Returns the [BetaOutsideSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [BetaOutsideSealedClass], an
 * [IllegalArgumentException] will be thrown.
 */
public fun BetaOutsideSealedClass.Companion.valueOf(name: String): BetaOutsideSealedClass =
        BetaOutsideSealedClassSealedEnum.valueOf(name)

""".trimIndent()

object GammaFirstObject : GammaOutsideSealedClass()

sealed class GammaOutsideSealedClass {

    object GammaSecondObject : GammaOutsideSealedClass()

    @GenSealedEnum(generateEnum = true)
    companion object
}

object GammaThirdObject : GammaOutsideSealedClass()

object GammaFourthObject : GammaOutsideSealedClass()

@Language("kotlin")
val gammaOutsideSealedClassGenerated = """
package com.livefront.sealedenum.compilation.location

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import kotlin.Int
import kotlin.LazyThreadSafetyMode
import kotlin.String
import kotlin.collections.List
import kotlin.reflect.KClass

/**
 * An isomorphic enum for the sealed class [GammaOutsideSealedClass]
 */
public enum class GammaOutsideSealedClassEnum() {
    GammaOutsideSealedClass_GammaSecondObject,
    GammaFirstObject,
    GammaFourthObject,
    GammaThirdObject,
}

/**
 * The isomorphic [GammaOutsideSealedClassEnum] for [this].
 */
public val GammaOutsideSealedClass.`enum`: GammaOutsideSealedClassEnum
    get() = GammaOutsideSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [GammaOutsideSealedClass] for [this].
 */
public val GammaOutsideSealedClassEnum.sealedObject: GammaOutsideSealedClass
    get() = GammaOutsideSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [GammaOutsideSealedClass]
 */
public object GammaOutsideSealedClassSealedEnum : SealedEnum<GammaOutsideSealedClass>,
        SealedEnumWithEnumProvider<GammaOutsideSealedClass, GammaOutsideSealedClassEnum>,
        EnumForSealedEnumProvider<GammaOutsideSealedClass, GammaOutsideSealedClassEnum> {
    public override val values: List<GammaOutsideSealedClass> by lazy(mode =
            LazyThreadSafetyMode.PUBLICATION) {
        listOf(
            GammaOutsideSealedClass.GammaSecondObject,
            GammaFirstObject,
            GammaFourthObject,
            GammaThirdObject
        )
    }


    public override val enumClass: KClass<GammaOutsideSealedClassEnum>
        get() = GammaOutsideSealedClassEnum::class

    public override fun ordinalOf(obj: GammaOutsideSealedClass): Int = when (obj) {
        is GammaOutsideSealedClass.GammaSecondObject -> 0
        is GammaFirstObject -> 1
        is GammaFourthObject -> 2
        is GammaThirdObject -> 3
    }

    public override fun nameOf(obj: GammaOutsideSealedClass): String = when (obj) {
        is GammaOutsideSealedClass.GammaSecondObject -> "GammaOutsideSealedClass_GammaSecondObject"
        is GammaFirstObject -> "GammaFirstObject"
        is GammaFourthObject -> "GammaFourthObject"
        is GammaThirdObject -> "GammaThirdObject"
    }

    public override fun valueOf(name: String): GammaOutsideSealedClass = when (name) {
        "GammaOutsideSealedClass_GammaSecondObject" -> GammaOutsideSealedClass.GammaSecondObject
        "GammaFirstObject" -> GammaFirstObject
        "GammaFourthObject" -> GammaFourthObject
        "GammaThirdObject" -> GammaThirdObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: GammaOutsideSealedClass):
            GammaOutsideSealedClassEnum = when (obj) {
        is GammaOutsideSealedClass.GammaSecondObject ->
                GammaOutsideSealedClassEnum.GammaOutsideSealedClass_GammaSecondObject
        is GammaFirstObject -> GammaOutsideSealedClassEnum.GammaFirstObject
        is GammaFourthObject -> GammaOutsideSealedClassEnum.GammaFourthObject
        is GammaThirdObject -> GammaOutsideSealedClassEnum.GammaThirdObject
    }

    public override fun enumToSealedObject(`enum`: GammaOutsideSealedClassEnum):
            GammaOutsideSealedClass = when (enum) {
        GammaOutsideSealedClassEnum.GammaOutsideSealedClass_GammaSecondObject ->
                GammaOutsideSealedClass.GammaSecondObject
        GammaOutsideSealedClassEnum.GammaFirstObject -> GammaFirstObject
        GammaOutsideSealedClassEnum.GammaFourthObject -> GammaFourthObject
        GammaOutsideSealedClassEnum.GammaThirdObject -> GammaThirdObject
    }
}

/**
 * The index of [this] in the values list.
 */
public val GammaOutsideSealedClass.ordinal: Int
    get() = GammaOutsideSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val GammaOutsideSealedClass.name: String
    get() = GammaOutsideSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [GammaOutsideSealedClass] objects.
 */
public val GammaOutsideSealedClass.Companion.values: List<GammaOutsideSealedClass>
    get() = GammaOutsideSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [GammaOutsideSealedClass]
 */
public val GammaOutsideSealedClass.Companion.sealedEnum: GammaOutsideSealedClassSealedEnum
    get() = GammaOutsideSealedClassSealedEnum

/**
 * Returns the [GammaOutsideSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [GammaOutsideSealedClass], an
 * [IllegalArgumentException] will be thrown.
 */
public fun GammaOutsideSealedClass.Companion.valueOf(name: String): GammaOutsideSealedClass =
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
package com.livefront.sealedenum.compilation.location

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import kotlin.Int
import kotlin.LazyThreadSafetyMode
import kotlin.String
import kotlin.collections.List
import kotlin.reflect.KClass

/**
 * An isomorphic enum for the sealed class [DeltaOutsideSealedClass]
 */
public enum class DeltaOutsideSealedClassEnum() {
    DeltaOutsideSealedClass_DeltaObject,
    DeltaObject,
}

/**
 * The isomorphic [DeltaOutsideSealedClassEnum] for [this].
 */
public val DeltaOutsideSealedClass.`enum`: DeltaOutsideSealedClassEnum
    get() = DeltaOutsideSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [DeltaOutsideSealedClass] for [this].
 */
public val DeltaOutsideSealedClassEnum.sealedObject: DeltaOutsideSealedClass
    get() = DeltaOutsideSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [DeltaOutsideSealedClass]
 */
public object DeltaOutsideSealedClassSealedEnum : SealedEnum<DeltaOutsideSealedClass>,
        SealedEnumWithEnumProvider<DeltaOutsideSealedClass, DeltaOutsideSealedClassEnum>,
        EnumForSealedEnumProvider<DeltaOutsideSealedClass, DeltaOutsideSealedClassEnum> {
    public override val values: List<DeltaOutsideSealedClass> by lazy(mode =
            LazyThreadSafetyMode.PUBLICATION) {
        listOf(
            DeltaOutsideSealedClass.DeltaObject,
            DeltaObject
        )
    }


    public override val enumClass: KClass<DeltaOutsideSealedClassEnum>
        get() = DeltaOutsideSealedClassEnum::class

    public override fun ordinalOf(obj: DeltaOutsideSealedClass): Int = when (obj) {
        is DeltaOutsideSealedClass.DeltaObject -> 0
        is DeltaObject -> 1
    }

    public override fun nameOf(obj: DeltaOutsideSealedClass): String = when (obj) {
        is DeltaOutsideSealedClass.DeltaObject -> "DeltaOutsideSealedClass_DeltaObject"
        is DeltaObject -> "DeltaObject"
    }

    public override fun valueOf(name: String): DeltaOutsideSealedClass = when (name) {
        "DeltaOutsideSealedClass_DeltaObject" -> DeltaOutsideSealedClass.DeltaObject
        "DeltaObject" -> DeltaObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: DeltaOutsideSealedClass):
            DeltaOutsideSealedClassEnum = when (obj) {
        is DeltaOutsideSealedClass.DeltaObject ->
                DeltaOutsideSealedClassEnum.DeltaOutsideSealedClass_DeltaObject
        is DeltaObject -> DeltaOutsideSealedClassEnum.DeltaObject
    }

    public override fun enumToSealedObject(`enum`: DeltaOutsideSealedClassEnum):
            DeltaOutsideSealedClass = when (enum) {
        DeltaOutsideSealedClassEnum.DeltaOutsideSealedClass_DeltaObject ->
                DeltaOutsideSealedClass.DeltaObject
        DeltaOutsideSealedClassEnum.DeltaObject -> DeltaObject
    }
}

/**
 * The index of [this] in the values list.
 */
public val DeltaOutsideSealedClass.ordinal: Int
    get() = DeltaOutsideSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val DeltaOutsideSealedClass.name: String
    get() = DeltaOutsideSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [DeltaOutsideSealedClass] objects.
 */
public val DeltaOutsideSealedClass.Companion.values: List<DeltaOutsideSealedClass>
    get() = DeltaOutsideSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [DeltaOutsideSealedClass]
 */
public val DeltaOutsideSealedClass.Companion.sealedEnum: DeltaOutsideSealedClassSealedEnum
    get() = DeltaOutsideSealedClassSealedEnum

/**
 * Returns the [DeltaOutsideSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [DeltaOutsideSealedClass], an
 * [IllegalArgumentException] will be thrown.
 */
public fun DeltaOutsideSealedClass.Companion.valueOf(name: String): DeltaOutsideSealedClass =
        DeltaOutsideSealedClassSealedEnum.valueOf(name)

""".trimIndent()
