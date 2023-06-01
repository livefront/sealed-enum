package com.livefront.sealedenum.compilation.location

import com.livefront.sealedenum.GenSealedEnum
import org.intellij.lang.annotations.Language

sealed class SplitAcrossFilesSealedClass {

    @GenSealedEnum(generateEnum = true)
    companion object
}

@Language("kotlin")
val splitAcrossFilesSealedClassGenerated = """
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
 * An isomorphic enum for the sealed class [SplitAcrossFilesSealedClass]
 */
public enum class SplitAcrossFilesSealedClassEnum() {
    SplitAcrossFilesSubclassA,
    SplitAcrossFilesSubclassB,
    SplitAcrossFilesSubclassC,
}

/**
 * The isomorphic [SplitAcrossFilesSealedClassEnum] for [this].
 */
public val SplitAcrossFilesSealedClass.`enum`: SplitAcrossFilesSealedClassEnum
    get() = SplitAcrossFilesSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [SplitAcrossFilesSealedClass] for [this].
 */
public val SplitAcrossFilesSealedClassEnum.sealedObject: SplitAcrossFilesSealedClass
    get() = SplitAcrossFilesSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [SplitAcrossFilesSealedClass]
 */
public object SplitAcrossFilesSealedClassSealedEnum : SealedEnum<SplitAcrossFilesSealedClass>,
        SealedEnumWithEnumProvider<SplitAcrossFilesSealedClass, SplitAcrossFilesSealedClassEnum>,
        EnumForSealedEnumProvider<SplitAcrossFilesSealedClass, SplitAcrossFilesSealedClassEnum> {
    public override val values: List<SplitAcrossFilesSealedClass> by lazy(mode =
            LazyThreadSafetyMode.PUBLICATION) {
        listOf(
            SplitAcrossFilesSubclassA,
            SplitAcrossFilesSubclassB,
            SplitAcrossFilesSubclassC
        )
    }


    public override val enumClass: KClass<SplitAcrossFilesSealedClassEnum>
        get() = SplitAcrossFilesSealedClassEnum::class

    public override fun ordinalOf(obj: SplitAcrossFilesSealedClass): Int = when (obj) {
        is SplitAcrossFilesSubclassA -> 0
        is SplitAcrossFilesSubclassB -> 1
        is SplitAcrossFilesSubclassC -> 2
    }

    public override fun nameOf(obj: SplitAcrossFilesSealedClass): String = when (obj) {
        is SplitAcrossFilesSubclassA -> "SplitAcrossFilesSubclassA"
        is SplitAcrossFilesSubclassB -> "SplitAcrossFilesSubclassB"
        is SplitAcrossFilesSubclassC -> "SplitAcrossFilesSubclassC"
    }

    public override fun valueOf(name: String): SplitAcrossFilesSealedClass = when (name) {
        "SplitAcrossFilesSubclassA" -> SplitAcrossFilesSubclassA
        "SplitAcrossFilesSubclassB" -> SplitAcrossFilesSubclassB
        "SplitAcrossFilesSubclassC" -> SplitAcrossFilesSubclassC
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: SplitAcrossFilesSealedClass):
            SplitAcrossFilesSealedClassEnum = when (obj) {
        is SplitAcrossFilesSubclassA -> SplitAcrossFilesSealedClassEnum.SplitAcrossFilesSubclassA
        is SplitAcrossFilesSubclassB -> SplitAcrossFilesSealedClassEnum.SplitAcrossFilesSubclassB
        is SplitAcrossFilesSubclassC -> SplitAcrossFilesSealedClassEnum.SplitAcrossFilesSubclassC
    }

    public override fun enumToSealedObject(`enum`: SplitAcrossFilesSealedClassEnum):
            SplitAcrossFilesSealedClass = when (enum) {
        SplitAcrossFilesSealedClassEnum.SplitAcrossFilesSubclassA -> SplitAcrossFilesSubclassA
        SplitAcrossFilesSealedClassEnum.SplitAcrossFilesSubclassB -> SplitAcrossFilesSubclassB
        SplitAcrossFilesSealedClassEnum.SplitAcrossFilesSubclassC -> SplitAcrossFilesSubclassC
    }
}

/**
 * The index of [this] in the values list.
 */
public val SplitAcrossFilesSealedClass.ordinal: Int
    get() = SplitAcrossFilesSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val SplitAcrossFilesSealedClass.name: String
    get() = SplitAcrossFilesSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [SplitAcrossFilesSealedClass] objects.
 */
public val SplitAcrossFilesSealedClass.Companion.values: List<SplitAcrossFilesSealedClass>
    get() = SplitAcrossFilesSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [SplitAcrossFilesSealedClass]
 */
public val SplitAcrossFilesSealedClass.Companion.sealedEnum: SplitAcrossFilesSealedClassSealedEnum
    get() = SplitAcrossFilesSealedClassSealedEnum

/**
 * Returns the [SplitAcrossFilesSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [SplitAcrossFilesSealedClass], an
 * [IllegalArgumentException] will be thrown.
 */
public fun SplitAcrossFilesSealedClass.Companion.valueOf(name: String): SplitAcrossFilesSealedClass
        = SplitAcrossFilesSealedClassSealedEnum.valueOf(name)

""".trimIndent()
