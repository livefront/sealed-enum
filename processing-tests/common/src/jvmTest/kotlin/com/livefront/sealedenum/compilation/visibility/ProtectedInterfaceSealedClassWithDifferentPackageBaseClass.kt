@file:Suppress("MatchingDeclarationName")

package com.livefront.sealedenum.compilation.visibility

import com.livefront.sealedenum.GenSealedEnum
import org.intellij.lang.annotations.Language

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
import kotlin.Int
import kotlin.LazyThreadSafetyMode
import kotlin.String
import kotlin.collections.List
import kotlin.reflect.KClass

/**
 * An isomorphic enum for the sealed class
 * [ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass]
 */
public enum class
        ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum()
        {
    ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClass_FirstObject,
    ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClass_SecondObject,
}

/**
 * The isomorphic
 * [ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum] for
 * [this].
 */
public
        val ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.`enum`:
        ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum
    get() =
            ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic
 * [ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass] for
 * [this].
 */
public
        val ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum.sealedObject:
        ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass
    get() =
            ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class
 * [ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass]
 */
public object
        ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassSealedEnum
        :
        SealedEnum<ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass>,
        SealedEnumWithEnumProvider<ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass, ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum>,
        EnumForSealedEnumProvider<ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass, ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum>
        {
    public override val values:
            List<ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass>
            by lazy(mode = LazyThreadSafetyMode.PUBLICATION) {
        listOf(
            ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.FirstObject,
            ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.SecondObject
        )
    }


    public override val enumClass:
            KClass<ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum>
        get() =
                ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum::class

    public override
            fun ordinalOf(obj: ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass):
            Int = when (obj) {
        is
                ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.FirstObject ->
                0
        is
                ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.SecondObject ->
                1
    }

    public override
            fun nameOf(obj: ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass):
            String = when (obj) {
        is
                ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.FirstObject ->
                "ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClass_FirstObject"
        is
                ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.SecondObject ->
                "ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClass_SecondObject"
    }

    public override fun valueOf(name: String):
            ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass
            = when (name) {
        "ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClass_FirstObject" ->
                ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.FirstObject
        "ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClass_SecondObject" ->
                ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.SecondObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override
            fun sealedObjectToEnum(obj: ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass):
            ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum
            = when (obj) {
        is
                ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.FirstObject ->
                ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum.ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClass_FirstObject
        is
                ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.SecondObject ->
                ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum.ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClass_SecondObject
    }

    public override
            fun enumToSealedObject(`enum`: ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum):
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
public
        val ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.ordinal:
        Int
    get() =
            ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public
        val ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.name:
        String
    get() =
            ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassSealedEnum.nameOf(this)

/**
 * A list of all
 * [ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass] objects.
 */
public
        val ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.Companion.values:
        List<ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass>
    get() =
            ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class
 * [ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass]
 */
public
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
public
        fun ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.Companion.valueOf(name: String):
        ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass =
        ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassSealedEnum.valueOf(name)

""".trimIndent()
