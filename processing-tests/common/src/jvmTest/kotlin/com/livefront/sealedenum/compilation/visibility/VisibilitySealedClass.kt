package com.livefront.sealedenum.compilation.visibility

import com.livefront.sealedenum.GenSealedEnum
import org.intellij.lang.annotations.Language

sealed class InternalObjectsSealedClass {
    internal object FirstObject : InternalObjectsSealedClass()

    internal object SecondObject : InternalObjectsSealedClass()

    internal sealed class InnerSealedClass : InternalObjectsSealedClass() {
        internal object ThirdObject : InnerSealedClass()
    }

    @GenSealedEnum(generateEnum = true)
    companion object
}

@Language("kotlin")
val internalObjectsSealedClassGenerated = """
package com.livefront.sealedenum.compilation.visibility

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlin.reflect.KClass

/**
 * An isomorphic enum for the sealed class [InternalObjectsSealedClass]
 */
public enum class InternalObjectsSealedClassEnum() {
    InternalObjectsSealedClass_FirstObject,
    InternalObjectsSealedClass_SecondObject,
    InternalObjectsSealedClass_InnerSealedClass_ThirdObject,
}

/**
 * The isomorphic [InternalObjectsSealedClassEnum] for [this].
 */
public val InternalObjectsSealedClass.`enum`: InternalObjectsSealedClassEnum
    get() = InternalObjectsSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [InternalObjectsSealedClass] for [this].
 */
public val InternalObjectsSealedClassEnum.sealedObject: InternalObjectsSealedClass
    get() = InternalObjectsSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [InternalObjectsSealedClass]
 */
public object InternalObjectsSealedClassSealedEnum : SealedEnum<InternalObjectsSealedClass>,
        SealedEnumWithEnumProvider<InternalObjectsSealedClass, InternalObjectsSealedClassEnum>,
        EnumForSealedEnumProvider<InternalObjectsSealedClass, InternalObjectsSealedClassEnum> {
    public override val values: List<InternalObjectsSealedClass> = listOf(
        InternalObjectsSealedClass.FirstObject,
        InternalObjectsSealedClass.SecondObject,
        InternalObjectsSealedClass.InnerSealedClass.ThirdObject
    )


    public override val enumClass: KClass<InternalObjectsSealedClassEnum>
        get() = InternalObjectsSealedClassEnum::class

    public override fun ordinalOf(obj: InternalObjectsSealedClass): Int = when (obj) {
        InternalObjectsSealedClass.FirstObject -> 0
        InternalObjectsSealedClass.SecondObject -> 1
        InternalObjectsSealedClass.InnerSealedClass.ThirdObject -> 2
    }

    public override fun nameOf(obj: InternalObjectsSealedClass): String = when (obj) {
        InternalObjectsSealedClass.FirstObject -> "InternalObjectsSealedClass_FirstObject"
        InternalObjectsSealedClass.SecondObject -> "InternalObjectsSealedClass_SecondObject"
        InternalObjectsSealedClass.InnerSealedClass.ThirdObject ->
                "InternalObjectsSealedClass_InnerSealedClass_ThirdObject"
    }

    public override fun valueOf(name: String): InternalObjectsSealedClass = when (name) {
        "InternalObjectsSealedClass_FirstObject" -> InternalObjectsSealedClass.FirstObject
        "InternalObjectsSealedClass_SecondObject" -> InternalObjectsSealedClass.SecondObject
        "InternalObjectsSealedClass_InnerSealedClass_ThirdObject" ->
                InternalObjectsSealedClass.InnerSealedClass.ThirdObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: InternalObjectsSealedClass):
            InternalObjectsSealedClassEnum = when (obj) {
        InternalObjectsSealedClass.FirstObject ->
                InternalObjectsSealedClassEnum.InternalObjectsSealedClass_FirstObject
        InternalObjectsSealedClass.SecondObject ->
                InternalObjectsSealedClassEnum.InternalObjectsSealedClass_SecondObject
        InternalObjectsSealedClass.InnerSealedClass.ThirdObject ->
                InternalObjectsSealedClassEnum.InternalObjectsSealedClass_InnerSealedClass_ThirdObject
    }

    public override fun enumToSealedObject(`enum`: InternalObjectsSealedClassEnum):
            InternalObjectsSealedClass = when (enum) {
        InternalObjectsSealedClassEnum.InternalObjectsSealedClass_FirstObject ->
                InternalObjectsSealedClass.FirstObject
        InternalObjectsSealedClassEnum.InternalObjectsSealedClass_SecondObject ->
                InternalObjectsSealedClass.SecondObject
        InternalObjectsSealedClassEnum.InternalObjectsSealedClass_InnerSealedClass_ThirdObject ->
                InternalObjectsSealedClass.InnerSealedClass.ThirdObject
    }
}

/**
 * The index of [this] in the values list.
 */
public val InternalObjectsSealedClass.ordinal: Int
    get() = InternalObjectsSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val InternalObjectsSealedClass.name: String
    get() = InternalObjectsSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [InternalObjectsSealedClass] objects.
 */
public val InternalObjectsSealedClass.Companion.values: List<InternalObjectsSealedClass>
    get() = InternalObjectsSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [InternalObjectsSealedClass]
 */
public val InternalObjectsSealedClass.Companion.sealedEnum: InternalObjectsSealedClassSealedEnum
    get() = InternalObjectsSealedClassSealedEnum

/**
 * Returns the [InternalObjectsSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [InternalObjectsSealedClass], an
 * [IllegalArgumentException] will be thrown.
 */
public fun InternalObjectsSealedClass.Companion.valueOf(name: String): InternalObjectsSealedClass =
        InternalObjectsSealedClassSealedEnum.valueOf(name)

""".trimIndent()

internal sealed class InternalSealedClass {
    object FirstObject : InternalSealedClass()

    internal object SecondObject : InternalSealedClass()

    @GenSealedEnum(generateEnum = true)
    companion object
}

@Language("kotlin")
val internalSealedClassGenerated = """
package com.livefront.sealedenum.compilation.visibility

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlin.reflect.KClass

/**
 * An isomorphic enum for the sealed class [InternalSealedClass]
 */
internal enum class InternalSealedClassEnum() {
    InternalSealedClass_FirstObject,
    InternalSealedClass_SecondObject,
}

/**
 * The isomorphic [InternalSealedClassEnum] for [this].
 */
internal val InternalSealedClass.`enum`: InternalSealedClassEnum
    get() = InternalSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [InternalSealedClass] for [this].
 */
internal val InternalSealedClassEnum.sealedObject: InternalSealedClass
    get() = InternalSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [InternalSealedClass]
 */
internal object InternalSealedClassSealedEnum : SealedEnum<InternalSealedClass>,
        SealedEnumWithEnumProvider<InternalSealedClass, InternalSealedClassEnum>,
        EnumForSealedEnumProvider<InternalSealedClass, InternalSealedClassEnum> {
    public override val values: List<InternalSealedClass> = listOf(
        InternalSealedClass.FirstObject,
        InternalSealedClass.SecondObject
    )


    public override val enumClass: KClass<InternalSealedClassEnum>
        get() = InternalSealedClassEnum::class

    public override fun ordinalOf(obj: InternalSealedClass): Int = when (obj) {
        InternalSealedClass.FirstObject -> 0
        InternalSealedClass.SecondObject -> 1
    }

    public override fun nameOf(obj: InternalSealedClass): String = when (obj) {
        InternalSealedClass.FirstObject -> "InternalSealedClass_FirstObject"
        InternalSealedClass.SecondObject -> "InternalSealedClass_SecondObject"
    }

    public override fun valueOf(name: String): InternalSealedClass = when (name) {
        "InternalSealedClass_FirstObject" -> InternalSealedClass.FirstObject
        "InternalSealedClass_SecondObject" -> InternalSealedClass.SecondObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: InternalSealedClass): InternalSealedClassEnum = when
            (obj) {
        InternalSealedClass.FirstObject -> InternalSealedClassEnum.InternalSealedClass_FirstObject
        InternalSealedClass.SecondObject -> InternalSealedClassEnum.InternalSealedClass_SecondObject
    }

    public override fun enumToSealedObject(`enum`: InternalSealedClassEnum): InternalSealedClass =
            when (enum) {
        InternalSealedClassEnum.InternalSealedClass_FirstObject -> InternalSealedClass.FirstObject
        InternalSealedClassEnum.InternalSealedClass_SecondObject -> InternalSealedClass.SecondObject
    }
}

/**
 * The index of [this] in the values list.
 */
internal val InternalSealedClass.ordinal: Int
    get() = InternalSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
internal val InternalSealedClass.name: String
    get() = InternalSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [InternalSealedClass] objects.
 */
internal val InternalSealedClass.Companion.values: List<InternalSealedClass>
    get() = InternalSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [InternalSealedClass]
 */
internal val InternalSealedClass.Companion.sealedEnum: InternalSealedClassSealedEnum
    get() = InternalSealedClassSealedEnum

/**
 * Returns the [InternalSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [InternalSealedClass], an [IllegalArgumentException]
 * will be thrown.
 */
internal fun InternalSealedClass.Companion.valueOf(name: String): InternalSealedClass =
        InternalSealedClassSealedEnum.valueOf(name)

""".trimIndent()

sealed class InternalCompanionSealedClass {
    object FirstObject : InternalCompanionSealedClass()

    internal object SecondObject : InternalCompanionSealedClass()

    @GenSealedEnum(generateEnum = true)
    internal companion object
}

@Language("kotlin")
val internalCompanionSealedClassGenerated = """
package com.livefront.sealedenum.compilation.visibility

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlin.reflect.KClass

/**
 * An isomorphic enum for the sealed class [InternalCompanionSealedClass]
 */
public enum class InternalCompanionSealedClassEnum() {
    InternalCompanionSealedClass_FirstObject,
    InternalCompanionSealedClass_SecondObject,
}

/**
 * The isomorphic [InternalCompanionSealedClassEnum] for [this].
 */
public val InternalCompanionSealedClass.`enum`: InternalCompanionSealedClassEnum
    get() = InternalCompanionSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [InternalCompanionSealedClass] for [this].
 */
public val InternalCompanionSealedClassEnum.sealedObject: InternalCompanionSealedClass
    get() = InternalCompanionSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [InternalCompanionSealedClass]
 */
public object InternalCompanionSealedClassSealedEnum : SealedEnum<InternalCompanionSealedClass>,
        SealedEnumWithEnumProvider<InternalCompanionSealedClass, InternalCompanionSealedClassEnum>,
        EnumForSealedEnumProvider<InternalCompanionSealedClass, InternalCompanionSealedClassEnum> {
    public override val values: List<InternalCompanionSealedClass> = listOf(
        InternalCompanionSealedClass.FirstObject,
        InternalCompanionSealedClass.SecondObject
    )


    public override val enumClass: KClass<InternalCompanionSealedClassEnum>
        get() = InternalCompanionSealedClassEnum::class

    public override fun ordinalOf(obj: InternalCompanionSealedClass): Int = when (obj) {
        InternalCompanionSealedClass.FirstObject -> 0
        InternalCompanionSealedClass.SecondObject -> 1
    }

    public override fun nameOf(obj: InternalCompanionSealedClass): String = when (obj) {
        InternalCompanionSealedClass.FirstObject -> "InternalCompanionSealedClass_FirstObject"
        InternalCompanionSealedClass.SecondObject -> "InternalCompanionSealedClass_SecondObject"
    }

    public override fun valueOf(name: String): InternalCompanionSealedClass = when (name) {
        "InternalCompanionSealedClass_FirstObject" -> InternalCompanionSealedClass.FirstObject
        "InternalCompanionSealedClass_SecondObject" -> InternalCompanionSealedClass.SecondObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: InternalCompanionSealedClass):
            InternalCompanionSealedClassEnum = when (obj) {
        InternalCompanionSealedClass.FirstObject ->
                InternalCompanionSealedClassEnum.InternalCompanionSealedClass_FirstObject
        InternalCompanionSealedClass.SecondObject ->
                InternalCompanionSealedClassEnum.InternalCompanionSealedClass_SecondObject
    }

    public override fun enumToSealedObject(`enum`: InternalCompanionSealedClassEnum):
            InternalCompanionSealedClass = when (enum) {
        InternalCompanionSealedClassEnum.InternalCompanionSealedClass_FirstObject ->
                InternalCompanionSealedClass.FirstObject
        InternalCompanionSealedClassEnum.InternalCompanionSealedClass_SecondObject ->
                InternalCompanionSealedClass.SecondObject
    }
}

/**
 * The index of [this] in the values list.
 */
public val InternalCompanionSealedClass.ordinal: Int
    get() = InternalCompanionSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val InternalCompanionSealedClass.name: String
    get() = InternalCompanionSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [InternalCompanionSealedClass] objects.
 */
internal val InternalCompanionSealedClass.Companion.values: List<InternalCompanionSealedClass>
    get() = InternalCompanionSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [InternalCompanionSealedClass]
 */
internal val InternalCompanionSealedClass.Companion.sealedEnum:
        InternalCompanionSealedClassSealedEnum
    get() = InternalCompanionSealedClassSealedEnum

/**
 * Returns the [InternalCompanionSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [InternalCompanionSealedClass], an
 * [IllegalArgumentException] will be thrown.
 */
internal fun InternalCompanionSealedClass.Companion.valueOf(name: String):
        InternalCompanionSealedClass = InternalCompanionSealedClassSealedEnum.valueOf(name)

""".trimIndent()

internal sealed class InternalSealedAndCompanionSealedClass {
    object FirstObject : InternalSealedAndCompanionSealedClass()

    internal object SecondObject : InternalSealedAndCompanionSealedClass()

    @GenSealedEnum(generateEnum = true)
    internal companion object
}

@Language("kotlin")
val internalSealedAndCompanionSealedClassGenerated = """
package com.livefront.sealedenum.compilation.visibility

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlin.reflect.KClass

/**
 * An isomorphic enum for the sealed class [InternalSealedAndCompanionSealedClass]
 */
internal enum class InternalSealedAndCompanionSealedClassEnum() {
    InternalSealedAndCompanionSealedClass_FirstObject,
    InternalSealedAndCompanionSealedClass_SecondObject,
}

/**
 * The isomorphic [InternalSealedAndCompanionSealedClassEnum] for [this].
 */
internal val InternalSealedAndCompanionSealedClass.`enum`: InternalSealedAndCompanionSealedClassEnum
    get() = InternalSealedAndCompanionSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [InternalSealedAndCompanionSealedClass] for [this].
 */
internal val InternalSealedAndCompanionSealedClassEnum.sealedObject:
        InternalSealedAndCompanionSealedClass
    get() = InternalSealedAndCompanionSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [InternalSealedAndCompanionSealedClass]
 */
internal object InternalSealedAndCompanionSealedClassSealedEnum :
        SealedEnum<InternalSealedAndCompanionSealedClass>,
        SealedEnumWithEnumProvider<InternalSealedAndCompanionSealedClass,
        InternalSealedAndCompanionSealedClassEnum>,
        EnumForSealedEnumProvider<InternalSealedAndCompanionSealedClass,
        InternalSealedAndCompanionSealedClassEnum> {
    public override val values: List<InternalSealedAndCompanionSealedClass> = listOf(
        InternalSealedAndCompanionSealedClass.FirstObject,
        InternalSealedAndCompanionSealedClass.SecondObject
    )


    public override val enumClass: KClass<InternalSealedAndCompanionSealedClassEnum>
        get() = InternalSealedAndCompanionSealedClassEnum::class

    public override fun ordinalOf(obj: InternalSealedAndCompanionSealedClass): Int = when (obj) {
        InternalSealedAndCompanionSealedClass.FirstObject -> 0
        InternalSealedAndCompanionSealedClass.SecondObject -> 1
    }

    public override fun nameOf(obj: InternalSealedAndCompanionSealedClass): String = when (obj) {
        InternalSealedAndCompanionSealedClass.FirstObject ->
                "InternalSealedAndCompanionSealedClass_FirstObject"
        InternalSealedAndCompanionSealedClass.SecondObject ->
                "InternalSealedAndCompanionSealedClass_SecondObject"
    }

    public override fun valueOf(name: String): InternalSealedAndCompanionSealedClass = when (name) {
        "InternalSealedAndCompanionSealedClass_FirstObject" ->
                InternalSealedAndCompanionSealedClass.FirstObject
        "InternalSealedAndCompanionSealedClass_SecondObject" ->
                InternalSealedAndCompanionSealedClass.SecondObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: InternalSealedAndCompanionSealedClass):
            InternalSealedAndCompanionSealedClassEnum = when (obj) {
        InternalSealedAndCompanionSealedClass.FirstObject ->
                InternalSealedAndCompanionSealedClassEnum.InternalSealedAndCompanionSealedClass_FirstObject
        InternalSealedAndCompanionSealedClass.SecondObject ->
                InternalSealedAndCompanionSealedClassEnum.InternalSealedAndCompanionSealedClass_SecondObject
    }

    public override fun enumToSealedObject(`enum`: InternalSealedAndCompanionSealedClassEnum):
            InternalSealedAndCompanionSealedClass = when (enum) {
        InternalSealedAndCompanionSealedClassEnum.InternalSealedAndCompanionSealedClass_FirstObject ->
                InternalSealedAndCompanionSealedClass.FirstObject
        InternalSealedAndCompanionSealedClassEnum.InternalSealedAndCompanionSealedClass_SecondObject ->
                InternalSealedAndCompanionSealedClass.SecondObject
    }
}

/**
 * The index of [this] in the values list.
 */
internal val InternalSealedAndCompanionSealedClass.ordinal: Int
    get() = InternalSealedAndCompanionSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
internal val InternalSealedAndCompanionSealedClass.name: String
    get() = InternalSealedAndCompanionSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [InternalSealedAndCompanionSealedClass] objects.
 */
internal val InternalSealedAndCompanionSealedClass.Companion.values:
        List<InternalSealedAndCompanionSealedClass>
    get() = InternalSealedAndCompanionSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class
 * [InternalSealedAndCompanionSealedClass]
 */
internal val InternalSealedAndCompanionSealedClass.Companion.sealedEnum:
        InternalSealedAndCompanionSealedClassSealedEnum
    get() = InternalSealedAndCompanionSealedClassSealedEnum

/**
 * Returns the [InternalSealedAndCompanionSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [InternalSealedAndCompanionSealedClass], an
 * [IllegalArgumentException] will be thrown.
 */
internal fun InternalSealedAndCompanionSealedClass.Companion.valueOf(name: String):
        InternalSealedAndCompanionSealedClass =
        InternalSealedAndCompanionSealedClassSealedEnum.valueOf(name)

""".trimIndent()
