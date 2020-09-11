package com.livefront.sealedenum

import org.intellij.lang.annotations.Language

sealed class OneObjectSealedClass {
    object FirstObject : OneObjectSealedClass()

    @GenSealedEnum(generateEnum = true)
    companion object
}

@Language("kotlin")
val oneObjectSealedClassGenerated = """
package com.livefront.sealedenum

import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [OneObjectSealedClass]
 */
enum class OneObjectSealedClassEnum {
    OneObjectSealedClass_FirstObject
}

/**
 * The isomorphic [OneObjectSealedClassEnum] for [this].
 */
val OneObjectSealedClass.enum: OneObjectSealedClassEnum
    get() = OneObjectSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [OneObjectSealedClass] for [this].
 */
val OneObjectSealedClassEnum.sealedObject: OneObjectSealedClass
    get() = OneObjectSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [OneObjectSealedClass]
 */
object OneObjectSealedClassSealedEnum : SealedEnum<OneObjectSealedClass>,
        SealedEnumWithEnumProvider<OneObjectSealedClass, OneObjectSealedClassEnum>,
        EnumForSealedEnumProvider<OneObjectSealedClass, OneObjectSealedClassEnum> {
    override val values: List<OneObjectSealedClass> = listOf(
        OneObjectSealedClass.FirstObject
    )


    override val enumClass: Class<OneObjectSealedClassEnum>
        get() = OneObjectSealedClassEnum::class.java

    override fun ordinalOf(obj: OneObjectSealedClass): Int = when (obj) {
        OneObjectSealedClass.FirstObject -> 0
    }

    override fun nameOf(obj: OneObjectSealedClass): String = when (obj) {
        OneObjectSealedClass.FirstObject -> "OneObjectSealedClass_FirstObject"
    }

    override fun valueOf(name: String): OneObjectSealedClass = when (name) {
        "OneObjectSealedClass_FirstObject" -> OneObjectSealedClass.FirstObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    override fun sealedObjectToEnum(obj: OneObjectSealedClass): OneObjectSealedClassEnum = when
            (obj) {
        OneObjectSealedClass.FirstObject ->
                OneObjectSealedClassEnum.OneObjectSealedClass_FirstObject
    }

    override fun enumToSealedObject(enum: OneObjectSealedClassEnum): OneObjectSealedClass = when
            (enum) {
        OneObjectSealedClassEnum.OneObjectSealedClass_FirstObject ->
                OneObjectSealedClass.FirstObject
    }
}

/**
 * The index of [this] in the values list.
 */
val OneObjectSealedClass.ordinal: Int
    get() = OneObjectSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val OneObjectSealedClass.name: String
    get() = OneObjectSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [OneObjectSealedClass] objects.
 */
val OneObjectSealedClass.Companion.values: List<OneObjectSealedClass>
    get() = OneObjectSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [OneObjectSealedClass]
 */
val OneObjectSealedClass.Companion.sealedEnum: OneObjectSealedClassSealedEnum
    get() = OneObjectSealedClassSealedEnum

/**
 * Returns the [OneObjectSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [OneObjectSealedClass], an [IllegalArgumentException]
 * will be thrown.
 */
fun OneObjectSealedClass.Companion.valueOf(name: String): OneObjectSealedClass =
        OneObjectSealedClassSealedEnum.valueOf(name)

""".trimIndent()
