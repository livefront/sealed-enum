package com.livefront.sealedenum.compilation.ksp

import com.livefront.sealedenum.GenSealedEnum

@Suppress("UtilityClassWithPublicConstructor")
public class NestedObjectsWithSameName {
    public companion object {
        public sealed class EmptySealedClass {
            @GenSealedEnum(generateEnum = true)
            public companion object
        }
    }
}

@Suppress("MaxLineLength")
public val nestedObjectsWithSameNameEmptySealedClassGenerated: String = """
package com.livefront.sealedenum.compilation.ksp

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import kotlin.Int
import kotlin.LazyThreadSafetyMode
import kotlin.String
import kotlin.collections.List
import kotlin.reflect.KClass

/**
 * An isomorphic enum for the sealed class [NestedObjectsWithSameName.Companion.EmptySealedClass]
 */
public enum class NestedObjectsWithSameName_Companion_EmptySealedClassEnum()

/**
 * The isomorphic [NestedObjectsWithSameName_Companion_EmptySealedClassEnum] for [this].
 */
public val NestedObjectsWithSameName.Companion.EmptySealedClass.`enum`:
        NestedObjectsWithSameName_Companion_EmptySealedClassEnum
    get() = NestedObjectsWithSameName_Companion_EmptySealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [NestedObjectsWithSameName.Companion.EmptySealedClass] for [this].
 */
public val NestedObjectsWithSameName_Companion_EmptySealedClassEnum.sealedObject:
        NestedObjectsWithSameName.Companion.EmptySealedClass
    get() = NestedObjectsWithSameName_Companion_EmptySealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class
 * [NestedObjectsWithSameName.Companion.EmptySealedClass]
 */
public object NestedObjectsWithSameName_Companion_EmptySealedClassSealedEnum :
        SealedEnum<NestedObjectsWithSameName.Companion.EmptySealedClass>,
        SealedEnumWithEnumProvider<NestedObjectsWithSameName.Companion.EmptySealedClass, NestedObjectsWithSameName_Companion_EmptySealedClassEnum>,
        EnumForSealedEnumProvider<NestedObjectsWithSameName.Companion.EmptySealedClass, NestedObjectsWithSameName_Companion_EmptySealedClassEnum>
        {
    public override val values: List<NestedObjectsWithSameName.Companion.EmptySealedClass> by
            lazy(mode = LazyThreadSafetyMode.PUBLICATION) {
        emptyList()
    }


    public override val enumClass: KClass<NestedObjectsWithSameName_Companion_EmptySealedClassEnum>
        get() = NestedObjectsWithSameName_Companion_EmptySealedClassEnum::class

    public override fun ordinalOf(obj: NestedObjectsWithSameName.Companion.EmptySealedClass): Int =
            throw
            AssertionError("Constructing a EmptySealedClass is impossible, since it has no sealed subclasses")

    public override fun nameOf(obj: NestedObjectsWithSameName.Companion.EmptySealedClass): String =
            throw
            AssertionError("Constructing a EmptySealedClass is impossible, since it has no sealed subclasses")

    public override fun valueOf(name: String): NestedObjectsWithSameName.Companion.EmptySealedClass
            = throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})

    public override
            fun sealedObjectToEnum(obj: NestedObjectsWithSameName.Companion.EmptySealedClass):
            NestedObjectsWithSameName_Companion_EmptySealedClassEnum = throw
            AssertionError("Constructing a EmptySealedClass is impossible, since it has no sealed subclasses")

    public override
            fun enumToSealedObject(`enum`: NestedObjectsWithSameName_Companion_EmptySealedClassEnum):
            NestedObjectsWithSameName.Companion.EmptySealedClass = throw
            AssertionError("Constructing a EmptySealedClass is impossible, since it has no sealed subclasses")
}

/**
 * The index of [this] in the values list.
 */
public val NestedObjectsWithSameName.Companion.EmptySealedClass.ordinal: Int
    get() = NestedObjectsWithSameName_Companion_EmptySealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val NestedObjectsWithSameName.Companion.EmptySealedClass.name: String
    get() = NestedObjectsWithSameName_Companion_EmptySealedClassSealedEnum.nameOf(this)

/**
 * A list of all [NestedObjectsWithSameName.Companion.EmptySealedClass] objects.
 */
public val NestedObjectsWithSameName.Companion.EmptySealedClass.Companion.values:
        List<NestedObjectsWithSameName.Companion.EmptySealedClass>
    get() = NestedObjectsWithSameName_Companion_EmptySealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class
 * [NestedObjectsWithSameName.Companion.EmptySealedClass]
 */
public val NestedObjectsWithSameName.Companion.EmptySealedClass.Companion.sealedEnum:
        NestedObjectsWithSameName_Companion_EmptySealedClassSealedEnum
    get() = NestedObjectsWithSameName_Companion_EmptySealedClassSealedEnum

/**
 * Returns the [NestedObjectsWithSameName.Companion.EmptySealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any
 * [NestedObjectsWithSameName.Companion.EmptySealedClass], an [IllegalArgumentException] will be
 * thrown.
 */
public fun NestedObjectsWithSameName.Companion.EmptySealedClass.Companion.valueOf(name: String):
        NestedObjectsWithSameName.Companion.EmptySealedClass =
        NestedObjectsWithSameName_Companion_EmptySealedClassSealedEnum.valueOf(name)

""".trimIndent()
