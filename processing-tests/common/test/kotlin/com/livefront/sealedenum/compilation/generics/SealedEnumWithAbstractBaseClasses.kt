@file:Suppress("UnnecessaryAbstractClass")

package com.livefront.sealedenum.compilation.generics

import com.livefront.sealedenum.GenSealedEnum
import org.intellij.lang.annotations.Language

interface BaseClassInterface1<T>

interface BaseClassInterface2<T>

interface BaseClassInterface3<out T>

abstract class AlphaBase<A> : BaseClassInterface1<A>

abstract class BetaBase<A, B : Any> : AlphaBase<BaseClassInterface1<A>>(), BaseClassInterface2<B>

sealed class SealedEnumWithAbstractBaseClasses : BetaBase<Any?, String>() {
    @GenSealedEnum(generateEnum = true)
    companion object
}

@Language("kotlin")
val sealedEnumWithAbstractBaseClassesGenerated = """
package com.livefront.sealedenum.compilation.generics

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import kotlin.Any
import kotlin.Int
import kotlin.LazyThreadSafetyMode
import kotlin.String
import kotlin.collections.List
import kotlin.reflect.KClass

/**
 * An isomorphic enum for the sealed class [SealedEnumWithAbstractBaseClasses]
 */
public enum class SealedEnumWithAbstractBaseClassesEnum(
    sealedObject: SealedEnumWithAbstractBaseClasses,
) : BaseClassInterface2<String> by sealedObject, BaseClassInterface1<BaseClassInterface1<Any?>> by
        sealedObject

/**
 * The isomorphic [SealedEnumWithAbstractBaseClassesEnum] for [this].
 */
public val SealedEnumWithAbstractBaseClasses.`enum`: SealedEnumWithAbstractBaseClassesEnum
    get() = SealedEnumWithAbstractBaseClassesSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [SealedEnumWithAbstractBaseClasses] for [this].
 */
public val SealedEnumWithAbstractBaseClassesEnum.sealedObject: SealedEnumWithAbstractBaseClasses
    get() = SealedEnumWithAbstractBaseClassesSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [SealedEnumWithAbstractBaseClasses]
 */
public object SealedEnumWithAbstractBaseClassesSealedEnum :
        SealedEnum<SealedEnumWithAbstractBaseClasses>,
        SealedEnumWithEnumProvider<SealedEnumWithAbstractBaseClasses, SealedEnumWithAbstractBaseClassesEnum>,
        EnumForSealedEnumProvider<SealedEnumWithAbstractBaseClasses, SealedEnumWithAbstractBaseClassesEnum>
        {
    public override val values: List<SealedEnumWithAbstractBaseClasses> by lazy(mode =
            LazyThreadSafetyMode.PUBLICATION) {
        emptyList()
    }


    public override val enumClass: KClass<SealedEnumWithAbstractBaseClassesEnum>
        get() = SealedEnumWithAbstractBaseClassesEnum::class

    public override fun ordinalOf(obj: SealedEnumWithAbstractBaseClasses): Int = throw
            AssertionError("Constructing a SealedEnumWithAbstractBaseClasses is impossible, since it has no sealed subclasses")

    public override fun nameOf(obj: SealedEnumWithAbstractBaseClasses): String = throw
            AssertionError("Constructing a SealedEnumWithAbstractBaseClasses is impossible, since it has no sealed subclasses")

    public override fun valueOf(name: String): SealedEnumWithAbstractBaseClasses = throw
            IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})

    public override fun sealedObjectToEnum(obj: SealedEnumWithAbstractBaseClasses):
            SealedEnumWithAbstractBaseClassesEnum = throw
            AssertionError("Constructing a SealedEnumWithAbstractBaseClasses is impossible, since it has no sealed subclasses")

    public override fun enumToSealedObject(`enum`: SealedEnumWithAbstractBaseClassesEnum):
            SealedEnumWithAbstractBaseClasses = throw
            AssertionError("Constructing a SealedEnumWithAbstractBaseClasses is impossible, since it has no sealed subclasses")
}

/**
 * The index of [this] in the values list.
 */
public val SealedEnumWithAbstractBaseClasses.ordinal: Int
    get() = SealedEnumWithAbstractBaseClassesSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val SealedEnumWithAbstractBaseClasses.name: String
    get() = SealedEnumWithAbstractBaseClassesSealedEnum.nameOf(this)

/**
 * A list of all [SealedEnumWithAbstractBaseClasses] objects.
 */
public val SealedEnumWithAbstractBaseClasses.Companion.values:
        List<SealedEnumWithAbstractBaseClasses>
    get() = SealedEnumWithAbstractBaseClassesSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class
 * [SealedEnumWithAbstractBaseClasses]
 */
public val SealedEnumWithAbstractBaseClasses.Companion.sealedEnum:
        SealedEnumWithAbstractBaseClassesSealedEnum
    get() = SealedEnumWithAbstractBaseClassesSealedEnum

/**
 * Returns the [SealedEnumWithAbstractBaseClasses] object for the given [name].
 *
 * If the given name doesn't correspond to any [SealedEnumWithAbstractBaseClasses], an
 * [IllegalArgumentException] will be thrown.
 */
public fun SealedEnumWithAbstractBaseClasses.Companion.valueOf(name: String):
        SealedEnumWithAbstractBaseClasses =
        SealedEnumWithAbstractBaseClassesSealedEnum.valueOf(name)

""".trimIndent()

abstract class GammaBase

abstract class DeltaBase<out T : Any> : GammaBase(), BaseClassInterface3<BaseClassInterface3<T>>

sealed class SealedEnumWithAbstractBaseClassesCovariantType<out T : Any> : DeltaBase<T>() {
    @GenSealedEnum(generateEnum = true)
    companion object
}

@Language("kotlin")
val sealedEnumWithAbstractBaseClassesCovariantTypeGenerated = """
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
 * An isomorphic enum for the sealed class [SealedEnumWithAbstractBaseClassesCovariantType]
 */
public enum class SealedEnumWithAbstractBaseClassesCovariantTypeEnum(
    sealedObject: SealedEnumWithAbstractBaseClassesCovariantType<*>,
) : BaseClassInterface3<BaseClassInterface3<*>> by sealedObject

/**
 * The isomorphic [SealedEnumWithAbstractBaseClassesCovariantTypeEnum] for [this].
 */
public val SealedEnumWithAbstractBaseClassesCovariantType<*>.`enum`:
        SealedEnumWithAbstractBaseClassesCovariantTypeEnum
    get() = SealedEnumWithAbstractBaseClassesCovariantTypeSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [SealedEnumWithAbstractBaseClassesCovariantType] for [this].
 */
public val SealedEnumWithAbstractBaseClassesCovariantTypeEnum.sealedObject:
        SealedEnumWithAbstractBaseClassesCovariantType<*>
    get() = SealedEnumWithAbstractBaseClassesCovariantTypeSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class
 * [SealedEnumWithAbstractBaseClassesCovariantType]
 */
public object SealedEnumWithAbstractBaseClassesCovariantTypeSealedEnum :
        SealedEnum<SealedEnumWithAbstractBaseClassesCovariantType<*>>,
        SealedEnumWithEnumProvider<SealedEnumWithAbstractBaseClassesCovariantType<*>, SealedEnumWithAbstractBaseClassesCovariantTypeEnum>,
        EnumForSealedEnumProvider<SealedEnumWithAbstractBaseClassesCovariantType<*>, SealedEnumWithAbstractBaseClassesCovariantTypeEnum>
        {
    public override val values: List<SealedEnumWithAbstractBaseClassesCovariantType<*>> by lazy(mode
            = LazyThreadSafetyMode.PUBLICATION) {
        emptyList()
    }


    public override val enumClass: KClass<SealedEnumWithAbstractBaseClassesCovariantTypeEnum>
        get() = SealedEnumWithAbstractBaseClassesCovariantTypeEnum::class

    public override fun ordinalOf(obj: SealedEnumWithAbstractBaseClassesCovariantType<*>): Int =
            throw
            AssertionError("Constructing a SealedEnumWithAbstractBaseClassesCovariantType is impossible, since it has no sealed subclasses")

    public override fun nameOf(obj: SealedEnumWithAbstractBaseClassesCovariantType<*>): String =
            throw
            AssertionError("Constructing a SealedEnumWithAbstractBaseClassesCovariantType is impossible, since it has no sealed subclasses")

    public override fun valueOf(name: String): SealedEnumWithAbstractBaseClassesCovariantType<*> =
            throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})

    public override fun sealedObjectToEnum(obj: SealedEnumWithAbstractBaseClassesCovariantType<*>):
            SealedEnumWithAbstractBaseClassesCovariantTypeEnum = throw
            AssertionError("Constructing a SealedEnumWithAbstractBaseClassesCovariantType is impossible, since it has no sealed subclasses")

    public override
            fun enumToSealedObject(`enum`: SealedEnumWithAbstractBaseClassesCovariantTypeEnum):
            SealedEnumWithAbstractBaseClassesCovariantType<*> = throw
            AssertionError("Constructing a SealedEnumWithAbstractBaseClassesCovariantType is impossible, since it has no sealed subclasses")
}

/**
 * The index of [this] in the values list.
 */
public val SealedEnumWithAbstractBaseClassesCovariantType<*>.ordinal: Int
    get() = SealedEnumWithAbstractBaseClassesCovariantTypeSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val SealedEnumWithAbstractBaseClassesCovariantType<*>.name: String
    get() = SealedEnumWithAbstractBaseClassesCovariantTypeSealedEnum.nameOf(this)

/**
 * A list of all [SealedEnumWithAbstractBaseClassesCovariantType] objects.
 */
public val SealedEnumWithAbstractBaseClassesCovariantType.Companion.values:
        List<SealedEnumWithAbstractBaseClassesCovariantType<*>>
    get() = SealedEnumWithAbstractBaseClassesCovariantTypeSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class
 * [SealedEnumWithAbstractBaseClassesCovariantType]
 */
public val SealedEnumWithAbstractBaseClassesCovariantType.Companion.sealedEnum:
        SealedEnumWithAbstractBaseClassesCovariantTypeSealedEnum
    get() = SealedEnumWithAbstractBaseClassesCovariantTypeSealedEnum

/**
 * Returns the [SealedEnumWithAbstractBaseClassesCovariantType] object for the given [name].
 *
 * If the given name doesn't correspond to any [SealedEnumWithAbstractBaseClassesCovariantType], an
 * [IllegalArgumentException] will be thrown.
 */
public fun SealedEnumWithAbstractBaseClassesCovariantType.Companion.valueOf(name: String):
        SealedEnumWithAbstractBaseClassesCovariantType<*> =
        SealedEnumWithAbstractBaseClassesCovariantTypeSealedEnum.valueOf(name)

""".trimIndent()
