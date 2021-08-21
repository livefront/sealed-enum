package com.livefront.sealedenum.compilation.visibility

import com.livefront.sealedenum.GenSealedEnum
import org.intellij.lang.annotations.Language

sealed class PrivateInterfaceSealedClass :
    JavaPrivateInterfaceSubclass(JavaPrivateInterfaceOuterClass()), PrivateInterface {

    object FirstObject : PrivateInterfaceSealedClass()

    object SecondObject : PrivateInterfaceSealedClass()

    @GenSealedEnum(generateEnum = true)
    companion object
}

private interface PrivateInterface

@Language("kotlin")
val privateInterfaceSealedClassGenerated = """
package com.livefront.sealedenum.compilation.visibility

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlin.reflect.KClass

/**
 * An isomorphic enum for the sealed class [PrivateInterfaceSealedClass]
 */
public enum class PrivateInterfaceSealedClassEnum() {
    PrivateInterfaceSealedClass_FirstObject,
    PrivateInterfaceSealedClass_SecondObject,
}

/**
 * The isomorphic [PrivateInterfaceSealedClassEnum] for [this].
 */
public val PrivateInterfaceSealedClass.`enum`: PrivateInterfaceSealedClassEnum
    get() = PrivateInterfaceSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [PrivateInterfaceSealedClass] for [this].
 */
public val PrivateInterfaceSealedClassEnum.sealedObject: PrivateInterfaceSealedClass
    get() = PrivateInterfaceSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [PrivateInterfaceSealedClass]
 */
public object PrivateInterfaceSealedClassSealedEnum : SealedEnum<PrivateInterfaceSealedClass>,
        SealedEnumWithEnumProvider<PrivateInterfaceSealedClass, PrivateInterfaceSealedClassEnum>,
        EnumForSealedEnumProvider<PrivateInterfaceSealedClass, PrivateInterfaceSealedClassEnum> {
    public override val values: List<PrivateInterfaceSealedClass> = listOf(
        PrivateInterfaceSealedClass.FirstObject,
        PrivateInterfaceSealedClass.SecondObject
    )


    public override val enumClass: KClass<PrivateInterfaceSealedClassEnum>
        get() = PrivateInterfaceSealedClassEnum::class

    public override fun ordinalOf(obj: PrivateInterfaceSealedClass): Int = when (obj) {
        PrivateInterfaceSealedClass.FirstObject -> 0
        PrivateInterfaceSealedClass.SecondObject -> 1
    }

    public override fun nameOf(obj: PrivateInterfaceSealedClass): String = when (obj) {
        PrivateInterfaceSealedClass.FirstObject -> "PrivateInterfaceSealedClass_FirstObject"
        PrivateInterfaceSealedClass.SecondObject -> "PrivateInterfaceSealedClass_SecondObject"
    }

    public override fun valueOf(name: String): PrivateInterfaceSealedClass = when (name) {
        "PrivateInterfaceSealedClass_FirstObject" -> PrivateInterfaceSealedClass.FirstObject
        "PrivateInterfaceSealedClass_SecondObject" -> PrivateInterfaceSealedClass.SecondObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: PrivateInterfaceSealedClass):
            PrivateInterfaceSealedClassEnum = when (obj) {
        PrivateInterfaceSealedClass.FirstObject ->
                PrivateInterfaceSealedClassEnum.PrivateInterfaceSealedClass_FirstObject
        PrivateInterfaceSealedClass.SecondObject ->
                PrivateInterfaceSealedClassEnum.PrivateInterfaceSealedClass_SecondObject
    }

    public override fun enumToSealedObject(`enum`: PrivateInterfaceSealedClassEnum):
            PrivateInterfaceSealedClass = when (enum) {
        PrivateInterfaceSealedClassEnum.PrivateInterfaceSealedClass_FirstObject ->
                PrivateInterfaceSealedClass.FirstObject
        PrivateInterfaceSealedClassEnum.PrivateInterfaceSealedClass_SecondObject ->
                PrivateInterfaceSealedClass.SecondObject
    }
}

/**
 * The index of [this] in the values list.
 */
public val PrivateInterfaceSealedClass.ordinal: Int
    get() = PrivateInterfaceSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val PrivateInterfaceSealedClass.name: String
    get() = PrivateInterfaceSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [PrivateInterfaceSealedClass] objects.
 */
public val PrivateInterfaceSealedClass.Companion.values: List<PrivateInterfaceSealedClass>
    get() = PrivateInterfaceSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [PrivateInterfaceSealedClass]
 */
public val PrivateInterfaceSealedClass.Companion.sealedEnum: PrivateInterfaceSealedClassSealedEnum
    get() = PrivateInterfaceSealedClassSealedEnum

/**
 * Returns the [PrivateInterfaceSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [PrivateInterfaceSealedClass], an
 * [IllegalArgumentException] will be thrown.
 */
public fun PrivateInterfaceSealedClass.Companion.valueOf(name: String): PrivateInterfaceSealedClass
        = PrivateInterfaceSealedClassSealedEnum.valueOf(name)

""".trimIndent()
