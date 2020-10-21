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
import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [PrivateInterfaceSealedClass]
 */
enum class PrivateInterfaceSealedClassEnum {
    PrivateInterfaceSealedClass_FirstObject,

    PrivateInterfaceSealedClass_SecondObject
}

/**
 * The isomorphic [PrivateInterfaceSealedClassEnum] for [this].
 */
val PrivateInterfaceSealedClass.enum: PrivateInterfaceSealedClassEnum
    get() = PrivateInterfaceSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [PrivateInterfaceSealedClass] for [this].
 */
val PrivateInterfaceSealedClassEnum.sealedObject: PrivateInterfaceSealedClass
    get() = PrivateInterfaceSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [PrivateInterfaceSealedClass]
 */
object PrivateInterfaceSealedClassSealedEnum : SealedEnum<PrivateInterfaceSealedClass>,
        SealedEnumWithEnumProvider<PrivateInterfaceSealedClass, PrivateInterfaceSealedClassEnum>,
        EnumForSealedEnumProvider<PrivateInterfaceSealedClass, PrivateInterfaceSealedClassEnum> {
    override val values: List<PrivateInterfaceSealedClass> = listOf(
        PrivateInterfaceSealedClass.FirstObject,
        PrivateInterfaceSealedClass.SecondObject
    )


    override val enumClass: Class<PrivateInterfaceSealedClassEnum>
        get() = PrivateInterfaceSealedClassEnum::class.java

    override fun ordinalOf(obj: PrivateInterfaceSealedClass): Int = when (obj) {
        PrivateInterfaceSealedClass.FirstObject -> 0
        PrivateInterfaceSealedClass.SecondObject -> 1
    }

    override fun nameOf(obj: PrivateInterfaceSealedClass): String = when (obj) {
        PrivateInterfaceSealedClass.FirstObject -> "PrivateInterfaceSealedClass_FirstObject"
        PrivateInterfaceSealedClass.SecondObject -> "PrivateInterfaceSealedClass_SecondObject"
    }

    override fun valueOf(name: String): PrivateInterfaceSealedClass = when (name) {
        "PrivateInterfaceSealedClass_FirstObject" -> PrivateInterfaceSealedClass.FirstObject
        "PrivateInterfaceSealedClass_SecondObject" -> PrivateInterfaceSealedClass.SecondObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    override fun sealedObjectToEnum(obj: PrivateInterfaceSealedClass):
            PrivateInterfaceSealedClassEnum = when (obj) {
        PrivateInterfaceSealedClass.FirstObject ->
                PrivateInterfaceSealedClassEnum.PrivateInterfaceSealedClass_FirstObject
        PrivateInterfaceSealedClass.SecondObject ->
                PrivateInterfaceSealedClassEnum.PrivateInterfaceSealedClass_SecondObject
    }

    override fun enumToSealedObject(enum: PrivateInterfaceSealedClassEnum):
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
val PrivateInterfaceSealedClass.ordinal: Int
    get() = PrivateInterfaceSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val PrivateInterfaceSealedClass.name: String
    get() = PrivateInterfaceSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [PrivateInterfaceSealedClass] objects.
 */
val PrivateInterfaceSealedClass.Companion.values: List<PrivateInterfaceSealedClass>
    get() = PrivateInterfaceSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [PrivateInterfaceSealedClass]
 */
val PrivateInterfaceSealedClass.Companion.sealedEnum: PrivateInterfaceSealedClassSealedEnum
    get() = PrivateInterfaceSealedClassSealedEnum

/**
 * Returns the [PrivateInterfaceSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [PrivateInterfaceSealedClass], an
 * [IllegalArgumentException] will be thrown.
 */
fun PrivateInterfaceSealedClass.Companion.valueOf(name: String): PrivateInterfaceSealedClass =
        PrivateInterfaceSealedClassSealedEnum.valueOf(name)

""".trimIndent()

open class ProtectedInterfaceOuterClass {

    protected interface ProtectedInterface

    sealed class ProtectedInterfaceSealedClass :
        com.livefront.sealedenum.compilation.visibility.JavaProtectedInterfaceSubclass(), ProtectedInterface {

        object FirstObject : ProtectedInterfaceSealedClass()

        object SecondObject : ProtectedInterfaceSealedClass()

        @GenSealedEnum(generateEnum = true)
        companion object
    }
}

@Language("kotlin")
val protectedInterfaceSealedClassGenerated = """
package com.livefront.sealedenum.compilation.visibility

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class
 * [ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass]
 */
enum class ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum(
    sealedObject: ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass
) : JavaProtectedInterfaceBaseClass.ProtectedInterface by sealedObject {
    ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClass_FirstObject(com.livefront.sealedenum.compilation.visibility.ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.FirstObject),

    ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClass_SecondObject(com.livefront.sealedenum.compilation.visibility.ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.SecondObject)
}

/**
 * The isomorphic [ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum] for [this].
 */
val ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.enum:
        ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum
    get() =
            ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass] for [this].
 */
val ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum.sealedObject:
        ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass
    get() =
            ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class
 * [ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass]
 */
object ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassSealedEnum :
        SealedEnum<ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass>,
        SealedEnumWithEnumProvider<ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass,
        ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum>,
        EnumForSealedEnumProvider<ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass,
        ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum> {
    override val values: List<ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass> = listOf(
        ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.FirstObject,
        ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.SecondObject
    )


    override val enumClass: Class<ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum>
        get() = ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum::class.java

    override fun ordinalOf(obj: ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass): Int =
            when (obj) {
        ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.FirstObject -> 0
        ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.SecondObject -> 1
    }

    override fun nameOf(obj: ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass): String =
            when (obj) {
        ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.FirstObject ->
                "ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClass_FirstObject"
        ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.SecondObject ->
                "ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClass_SecondObject"
    }

    override fun valueOf(name: String): ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass =
            when (name) {
        "ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClass_FirstObject" ->
                ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.FirstObject
        "ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClass_SecondObject" ->
                ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.SecondObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    override
            fun sealedObjectToEnum(obj: ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass):
            ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum = when (obj) {
        ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.FirstObject ->
                ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum.ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClass_FirstObject
        ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.SecondObject ->
                ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum.ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClass_SecondObject
    }

    override
            fun enumToSealedObject(enum: ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum):
            ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass = when (enum) {
        ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum.ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClass_FirstObject ->
                ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.FirstObject
        ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum.ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClass_SecondObject ->
                ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.SecondObject
    }
}

/**
 * The index of [this] in the values list.
 */
val ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.ordinal: Int
    get() = ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.name: String
    get() = ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass] objects.
 */
val ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.Companion.values:
        List<ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass>
    get() = ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class
 * [ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass]
 */
val ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.Companion.sealedEnum:
        ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassSealedEnum
    get() = ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassSealedEnum

/**
 * Returns the [ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass] object for the given
 * [name].
 *
 * If the given name doesn't correspond to any
 * [ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass], an [IllegalArgumentException] will be
 * thrown.
 */
fun ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.Companion.valueOf(name: String):
        ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass =
        ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassSealedEnum.valueOf(name)

""".trimIndent()

open class ProtectedInterfaceOuterClassWithDifferentPackageBaseClass {

    protected interface ProtectedInterface

    sealed class ProtectedInterfaceSealedClass :
        com.livefront.sealedenum.compilation.visibility.subpackage.JavaProtectedInterfaceSubclass(),
        ProtectedInterface {

        object FirstObject : ProtectedInterfaceSealedClass()

        object SecondObject : ProtectedInterfaceSealedClass()

        @GenSealedEnum(generateEnum = true)
        companion object
    }
}

@Language("kotlin")
val protectedInterfaceSealedClassWithDifferentPackageBaseClassGenerated = """
package com.livefront.sealedenum.compilation.visibility

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import java.lang.Class
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class
 * [ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass]
 */
enum class
        ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum
        {
    ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClass_FirstObject,

    ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClass_SecondObject
}

/**
 * The isomorphic
 * [ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum] for
 * [this].
 */
val ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.enum:
        ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum
    get() =
            ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic
 * [ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass] for
 * [this].
 */
val ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum.sealedObject:
        ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass
    get() =
            ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class
 * [ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass]
 */
object
        ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassSealedEnum
        :
        SealedEnum<ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass>,
        SealedEnumWithEnumProvider<ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass,
        ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum>,
        EnumForSealedEnumProvider<ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass,
        ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum>
        {
    override val values:
            List<ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass>
            = listOf(
        ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.FirstObject,
        ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.SecondObject
    )


    override val enumClass:
            Class<ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum>
        get() =
                ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum::class.java

    override
            fun ordinalOf(obj: ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass):
            Int = when (obj) {
        ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.FirstObject ->
                0
        ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.SecondObject ->
                1
    }

    override
            fun nameOf(obj: ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass):
            String = when (obj) {
        ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.FirstObject ->
                "ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClass_FirstObject"
        ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.SecondObject ->
                "ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClass_SecondObject"
    }

    override fun valueOf(name: String):
            ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass
            = when (name) {
        "ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClass_FirstObject" ->
                ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.FirstObject
        "ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClass_SecondObject" ->
                ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.SecondObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    override
            fun sealedObjectToEnum(obj: ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass):
            ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum
            = when (obj) {
        ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.FirstObject ->
                ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum.ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClass_FirstObject
        ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.SecondObject ->
                ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum.ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClass_SecondObject
    }

    override
            fun enumToSealedObject(enum: ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum):
            ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass
            = when (enum) {
        ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum.ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClass_FirstObject ->
                ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.FirstObject
        ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum.ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClass_SecondObject ->
                ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.SecondObject
    }
}

/**
 * The index of [this] in the values list.
 */
val ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.ordinal:
        Int
    get() =
            ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.name:
        String
    get() =
            ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassSealedEnum.nameOf(this)

/**
 * A list of all
 * [ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass] objects.
 */
val ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.Companion.values:
        List<ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass>
    get() =
            ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class
 * [ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass]
 */
val ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.Companion.sealedEnum:
        ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassSealedEnum
    get() =
            ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassSealedEnum

/**
 * Returns the
 * [ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass] object for
 * the given [name].
 *
 * If the given name doesn't correspond to any
 * [ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass], an
 * [IllegalArgumentException] will be thrown.
 */
fun ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.Companion.valueOf(name: String):
        ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass =
        ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassSealedEnum.valueOf(name)

""".trimIndent()
