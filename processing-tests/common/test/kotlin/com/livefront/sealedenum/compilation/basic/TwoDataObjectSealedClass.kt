package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.GenSealedEnum
import org.intellij.lang.annotations.Language

sealed class TwoDataObjectSealedClass {
    data object FirstObject : TwoDataObjectSealedClass()

    data object SecondObject : TwoDataObjectSealedClass()

    @GenSealedEnum(generateEnum = true)
    companion object
}

@Language("kotlin")
val twoDataObjectSealedClassGenerated = """
package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import kotlin.Int
import kotlin.LazyThreadSafetyMode
import kotlin.String
import kotlin.collections.List
import kotlin.reflect.KClass

/**
 * An isomorphic enum for the sealed class [TwoDataObjectSealedClass]
 */
public enum class TwoDataObjectSealedClassEnum() {
    TwoDataObjectSealedClass_FirstObject,
    TwoDataObjectSealedClass_SecondObject,
}

/**
 * The isomorphic [TwoDataObjectSealedClassEnum] for [this].
 */
public val TwoDataObjectSealedClass.`enum`: TwoDataObjectSealedClassEnum
    get() = TwoDataObjectSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [TwoDataObjectSealedClass] for [this].
 */
public val TwoDataObjectSealedClassEnum.sealedObject: TwoDataObjectSealedClass
    get() = TwoDataObjectSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [TwoDataObjectSealedClass]
 */
public object TwoDataObjectSealedClassSealedEnum : SealedEnum<TwoDataObjectSealedClass>,
        SealedEnumWithEnumProvider<TwoDataObjectSealedClass, TwoDataObjectSealedClassEnum>,
        EnumForSealedEnumProvider<TwoDataObjectSealedClass, TwoDataObjectSealedClassEnum> {
    public override val values: List<TwoDataObjectSealedClass> by lazy(mode =
            LazyThreadSafetyMode.PUBLICATION) {
        listOf(
            TwoDataObjectSealedClass.FirstObject,
            TwoDataObjectSealedClass.SecondObject
        )
    }


    public override val enumClass: KClass<TwoDataObjectSealedClassEnum>
        get() = TwoDataObjectSealedClassEnum::class

    public override fun ordinalOf(obj: TwoDataObjectSealedClass): Int = when (obj) {
        is TwoDataObjectSealedClass.FirstObject -> 0
        is TwoDataObjectSealedClass.SecondObject -> 1
    }

    public override fun nameOf(obj: TwoDataObjectSealedClass): String = when (obj) {
        is TwoDataObjectSealedClass.FirstObject -> "TwoDataObjectSealedClass_FirstObject"
        is TwoDataObjectSealedClass.SecondObject -> "TwoDataObjectSealedClass_SecondObject"
    }

    public override fun valueOf(name: String): TwoDataObjectSealedClass = when (name) {
        "TwoDataObjectSealedClass_FirstObject" -> TwoDataObjectSealedClass.FirstObject
        "TwoDataObjectSealedClass_SecondObject" -> TwoDataObjectSealedClass.SecondObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: TwoDataObjectSealedClass):
            TwoDataObjectSealedClassEnum = when (obj) {
        is TwoDataObjectSealedClass.FirstObject ->
                TwoDataObjectSealedClassEnum.TwoDataObjectSealedClass_FirstObject
        is TwoDataObjectSealedClass.SecondObject ->
                TwoDataObjectSealedClassEnum.TwoDataObjectSealedClass_SecondObject
    }

    public override fun enumToSealedObject(`enum`: TwoDataObjectSealedClassEnum):
            TwoDataObjectSealedClass = when (enum) {
        TwoDataObjectSealedClassEnum.TwoDataObjectSealedClass_FirstObject ->
                TwoDataObjectSealedClass.FirstObject
        TwoDataObjectSealedClassEnum.TwoDataObjectSealedClass_SecondObject ->
                TwoDataObjectSealedClass.SecondObject
    }
}

/**
 * The index of [this] in the values list.
 */
public val TwoDataObjectSealedClass.ordinal: Int
    get() = TwoDataObjectSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val TwoDataObjectSealedClass.name: String
    get() = TwoDataObjectSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [TwoDataObjectSealedClass] objects.
 */
public val TwoDataObjectSealedClass.Companion.values: List<TwoDataObjectSealedClass>
    get() = TwoDataObjectSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [TwoDataObjectSealedClass]
 */
public val TwoDataObjectSealedClass.Companion.sealedEnum: TwoDataObjectSealedClassSealedEnum
    get() = TwoDataObjectSealedClassSealedEnum

/**
 * Returns the [TwoDataObjectSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [TwoDataObjectSealedClass], an
 * [IllegalArgumentException] will be thrown.
 */
public fun TwoDataObjectSealedClass.Companion.valueOf(name: String): TwoDataObjectSealedClass =
        TwoDataObjectSealedClassSealedEnum.valueOf(name)

""".trimIndent()
