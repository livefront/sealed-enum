package com.livefront.sealedenum

import org.intellij.lang.annotations.Language

sealed class EmptySealedClass {
    @GenSealedEnum(generateEnum = true)
    companion object
}

@Language("kotlin")
val emptySealedClassGenerated = """
package com.livefront.sealedenum

import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [com.livefront.sealedenum.EmptySealedClass]
 */
enum class EmptySealedClassEnum

/**
 * The isomorphic [EmptySealedClassEnum] for [this].
 */
val EmptySealedClass.enum: EmptySealedClassEnum
    get() = EmptySealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [EmptySealedClass] for [this].
 */
val EmptySealedClassEnum.sealedObject: EmptySealedClass
    get() = EmptySealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [EmptySealedClass]
 */
object EmptySealedClassSealedEnum : SealedEnum<EmptySealedClass>,
        SealedEnumWithEnumProvider<EmptySealedClass, EmptySealedClassEnum>,
        EnumForSealedEnumProvider<EmptySealedClass, EmptySealedClassEnum> {
    override val values: List<EmptySealedClass> = emptyList()


    override val enumClass: Class<EmptySealedClassEnum>
        get() = EmptySealedClassEnum::class.java

    override fun ordinalOf(obj: EmptySealedClass): Int = throw
            AssertionError("Constructing a EmptySealedClass is impossible, since it has no sealed subclasses")

    override fun nameOf(obj: EmptySealedClass): String = throw
            AssertionError("Constructing a EmptySealedClass is impossible, since it has no sealed subclasses")

    override fun valueOf(name: String): EmptySealedClass = throw
            IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})

    override fun sealedObjectToEnum(obj: EmptySealedClass): EmptySealedClassEnum = throw
            AssertionError("Constructing a EmptySealedClass is impossible, since it has no sealed subclasses")

    override fun enumToSealedObject(enum: EmptySealedClassEnum): EmptySealedClass = throw
            AssertionError("Constructing a EmptySealedClass is impossible, since it has no sealed subclasses")
}

/**
 * The index of [this] in the values list.
 */
val EmptySealedClass.ordinal: Int
    get() = EmptySealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val EmptySealedClass.name: String
    get() = EmptySealedClassSealedEnum.nameOf(this)

/**
 * A list of all [EmptySealedClass] objects.
 */
val EmptySealedClass.Companion.values: List<EmptySealedClass>
    get() = EmptySealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [EmptySealedClass]
 */
val EmptySealedClass.Companion.sealedEnum: EmptySealedClassSealedEnum
    get() = EmptySealedClassSealedEnum

/**
 * Returns the [EmptySealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [EmptySealedClass], an [IllegalArgumentException]
 * will be thrown.
 */
fun EmptySealedClass.Companion.valueOf(name: String): EmptySealedClass =
        EmptySealedClassSealedEnum.valueOf(name)

""".trimIndent()
