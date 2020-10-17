package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.GenSealedEnum
import org.intellij.lang.annotations.Language

sealed class OneObjectSealedClass {
    object FirstObject : OneObjectSealedClass()

    @GenSealedEnum(generateEnum = true)
    companion object
}

@Language("kotlin")
val oneObjectSealedClassGenerated = """
package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [OneObjectSealedClass]
 */
public enum class OneObjectSealedClassEnum {
    OneObjectSealedClass_FirstObject,
}

/**
 * The isomorphic [OneObjectSealedClassEnum] for [this].
 */
public val OneObjectSealedClass.`enum`: OneObjectSealedClassEnum
    get() = OneObjectSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [OneObjectSealedClass] for [this].
 */
public val OneObjectSealedClassEnum.sealedObject: OneObjectSealedClass
    get() = OneObjectSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [OneObjectSealedClass]
 */
public object OneObjectSealedClassSealedEnum : SealedEnum<OneObjectSealedClass>,
        SealedEnumWithEnumProvider<OneObjectSealedClass, OneObjectSealedClassEnum>,
        EnumForSealedEnumProvider<OneObjectSealedClass, OneObjectSealedClassEnum> {
    public override val values: List<OneObjectSealedClass> = listOf(
        OneObjectSealedClass.FirstObject
    )


    public override val enumClass: Class<OneObjectSealedClassEnum>
        get() = OneObjectSealedClassEnum::class.java

    public override fun ordinalOf(obj: OneObjectSealedClass): Int = when (obj) {
        OneObjectSealedClass.FirstObject -> 0
    }

    public override fun nameOf(obj: OneObjectSealedClass): String = when (obj) {
        OneObjectSealedClass.FirstObject -> "OneObjectSealedClass_FirstObject"
    }

    public override fun valueOf(name: String): OneObjectSealedClass = when (name) {
        "OneObjectSealedClass_FirstObject" -> OneObjectSealedClass.FirstObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: OneObjectSealedClass): OneObjectSealedClassEnum =
            when (obj) {
        OneObjectSealedClass.FirstObject ->
                OneObjectSealedClassEnum.OneObjectSealedClass_FirstObject
    }

    public override fun enumToSealedObject(`enum`: OneObjectSealedClassEnum): OneObjectSealedClass =
            when (enum) {
        OneObjectSealedClassEnum.OneObjectSealedClass_FirstObject ->
                OneObjectSealedClass.FirstObject
    }
}

/**
 * The index of [this] in the values list.
 */
public val OneObjectSealedClass.ordinal: Int
    get() = OneObjectSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val OneObjectSealedClass.name: String
    get() = OneObjectSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [OneObjectSealedClass] objects.
 */
public val OneObjectSealedClass.Companion.values: List<OneObjectSealedClass>
    get() = OneObjectSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [OneObjectSealedClass]
 */
public val OneObjectSealedClass.Companion.sealedEnum: OneObjectSealedClassSealedEnum
    get() = OneObjectSealedClassSealedEnum

/**
 * Returns the [OneObjectSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [OneObjectSealedClass], an [IllegalArgumentException]
 * will be thrown.
 */
public fun OneObjectSealedClass.Companion.valueOf(name: String): OneObjectSealedClass =
        OneObjectSealedClassSealedEnum.valueOf(name)

""".trimIndent()
