package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.GenSealedEnum
import org.intellij.lang.annotations.Language

sealed class EmptySealedClass {
    @GenSealedEnum(generateEnum = true)
    companion object
}

@Language("kotlin")
val emptySealedClassGenerated = """
package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [EmptySealedClass]
 */
public enum class EmptySealedClassEnum

/**
 * The isomorphic [EmptySealedClassEnum] for [this].
 */
public val EmptySealedClass.`enum`: EmptySealedClassEnum
    get() = EmptySealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [EmptySealedClass] for [this].
 */
public val EmptySealedClassEnum.sealedObject: EmptySealedClass
    get() = EmptySealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [EmptySealedClass]
 */
public object EmptySealedClassSealedEnum : SealedEnum<EmptySealedClass>,
        SealedEnumWithEnumProvider<EmptySealedClass, EmptySealedClassEnum>,
        EnumForSealedEnumProvider<EmptySealedClass, EmptySealedClassEnum> {
    public override val values: List<EmptySealedClass> = emptyList()


    public override val enumClass: Class<EmptySealedClassEnum>
        get() = EmptySealedClassEnum::class.java

    public override fun ordinalOf(obj: EmptySealedClass): Int = throw
            AssertionError("Constructing a EmptySealedClass is impossible, since it has no sealed subclasses")

    public override fun nameOf(obj: EmptySealedClass): String = throw
            AssertionError("Constructing a EmptySealedClass is impossible, since it has no sealed subclasses")

    public override fun valueOf(name: String): EmptySealedClass = throw
            IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})

    public override fun sealedObjectToEnum(obj: EmptySealedClass): EmptySealedClassEnum = throw
            AssertionError("Constructing a EmptySealedClass is impossible, since it has no sealed subclasses")

    public override fun enumToSealedObject(`enum`: EmptySealedClassEnum): EmptySealedClass = throw
            AssertionError("Constructing a EmptySealedClass is impossible, since it has no sealed subclasses")
}

/**
 * The index of [this] in the values list.
 */
public val EmptySealedClass.ordinal: Int
    get() = EmptySealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val EmptySealedClass.name: String
    get() = EmptySealedClassSealedEnum.nameOf(this)

/**
 * A list of all [EmptySealedClass] objects.
 */
public val EmptySealedClass.Companion.values: List<EmptySealedClass>
    get() = EmptySealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [EmptySealedClass]
 */
public val EmptySealedClass.Companion.sealedEnum: EmptySealedClassSealedEnum
    get() = EmptySealedClassSealedEnum

/**
 * Returns the [EmptySealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [EmptySealedClass], an [IllegalArgumentException]
 * will be thrown.
 */
public fun EmptySealedClass.Companion.valueOf(name: String): EmptySealedClass =
        EmptySealedClassSealedEnum.valueOf(name)

""".trimIndent()
