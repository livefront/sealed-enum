package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.GenSealedEnum
import org.intellij.lang.annotations.Language

sealed class TwoObjectSealedClass {
    object FirstObject : TwoObjectSealedClass()

    object SecondObject : TwoObjectSealedClass()

    @GenSealedEnum(generateEnum = true)
    companion object
}

@Language("kotlin")
val twoObjectSealedClassGenerated = """
package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [TwoObjectSealedClass]
 */
enum class TwoObjectSealedClassEnum {
    TwoObjectSealedClass_FirstObject,

    TwoObjectSealedClass_SecondObject
}

/**
 * The isomorphic [TwoObjectSealedClassEnum] for [this].
 */
val TwoObjectSealedClass.enum: TwoObjectSealedClassEnum
    get() = TwoObjectSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [TwoObjectSealedClass] for [this].
 */
val TwoObjectSealedClassEnum.sealedObject: TwoObjectSealedClass
    get() = TwoObjectSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [TwoObjectSealedClass]
 */
object TwoObjectSealedClassSealedEnum : SealedEnum<TwoObjectSealedClass>,
        SealedEnumWithEnumProvider<TwoObjectSealedClass, TwoObjectSealedClassEnum>,
        EnumForSealedEnumProvider<TwoObjectSealedClass, TwoObjectSealedClassEnum> {
    override val values: List<TwoObjectSealedClass> = listOf(
        TwoObjectSealedClass.FirstObject,
        TwoObjectSealedClass.SecondObject
    )


    override val enumClass: Class<TwoObjectSealedClassEnum>
        get() = TwoObjectSealedClassEnum::class.java

    override fun ordinalOf(obj: TwoObjectSealedClass): Int = when (obj) {
        TwoObjectSealedClass.FirstObject -> 0
        TwoObjectSealedClass.SecondObject -> 1
    }

    override fun nameOf(obj: TwoObjectSealedClass): String = when (obj) {
        TwoObjectSealedClass.FirstObject -> "TwoObjectSealedClass_FirstObject"
        TwoObjectSealedClass.SecondObject -> "TwoObjectSealedClass_SecondObject"
    }

    override fun valueOf(name: String): TwoObjectSealedClass = when (name) {
        "TwoObjectSealedClass_FirstObject" -> TwoObjectSealedClass.FirstObject
        "TwoObjectSealedClass_SecondObject" -> TwoObjectSealedClass.SecondObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    override fun sealedObjectToEnum(obj: TwoObjectSealedClass): TwoObjectSealedClassEnum = when
            (obj) {
        TwoObjectSealedClass.FirstObject ->
                TwoObjectSealedClassEnum.TwoObjectSealedClass_FirstObject
        TwoObjectSealedClass.SecondObject ->
                TwoObjectSealedClassEnum.TwoObjectSealedClass_SecondObject
    }

    override fun enumToSealedObject(enum: TwoObjectSealedClassEnum): TwoObjectSealedClass = when
            (enum) {
        TwoObjectSealedClassEnum.TwoObjectSealedClass_FirstObject ->
                TwoObjectSealedClass.FirstObject
        TwoObjectSealedClassEnum.TwoObjectSealedClass_SecondObject ->
                TwoObjectSealedClass.SecondObject
    }
}

/**
 * The index of [this] in the values list.
 */
val TwoObjectSealedClass.ordinal: Int
    get() = TwoObjectSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val TwoObjectSealedClass.name: String
    get() = TwoObjectSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [TwoObjectSealedClass] objects.
 */
val TwoObjectSealedClass.Companion.values: List<TwoObjectSealedClass>
    get() = TwoObjectSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [TwoObjectSealedClass]
 */
val TwoObjectSealedClass.Companion.sealedEnum: TwoObjectSealedClassSealedEnum
    get() = TwoObjectSealedClassSealedEnum

/**
 * Returns the [TwoObjectSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [TwoObjectSealedClass], an [IllegalArgumentException]
 * will be thrown.
 */
fun TwoObjectSealedClass.Companion.valueOf(name: String): TwoObjectSealedClass =
        TwoObjectSealedClassSealedEnum.valueOf(name)

""".trimIndent()
