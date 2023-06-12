@file:Suppress("MatchingDeclarationName")

package com.livefront.sealedenum.compilation.visibility

import com.livefront.sealedenum.GenSealedEnum
import org.intellij.lang.annotations.Language

public open class ProtectedInterfaceOuterClass {

    protected interface ProtectedInterface

    public sealed class ProtectedInterfaceSealedClass :
        JavaProtectedInterfaceSubclass(), ProtectedInterface {

        public object FirstObject : ProtectedInterfaceSealedClass()

        public object SecondObject : ProtectedInterfaceSealedClass()

        @GenSealedEnum(generateEnum = true)
        public companion object
    }
}

@Suppress("MaxLineLength")
@Language("kotlin")
public val protectedInterfaceSealedClassGenerated: String = """
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
 * [ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass]
 */
public enum class ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum(
    sealedObject: ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass,
) : JavaProtectedInterfaceBaseClass.ProtectedInterface by sealedObject {
    ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClass_FirstObject(com.livefront.sealedenum.compilation.visibility.ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.FirstObject),
    ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClass_SecondObject(com.livefront.sealedenum.compilation.visibility.ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.SecondObject),
}

/**
 * The isomorphic [ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum] for [this].
 */
public val ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.`enum`:
        ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum
    get() =
            ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass] for [this].
 */
public val ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum.sealedObject:
        ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass
    get() =
            ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class
 * [ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass]
 */
public object ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassSealedEnum :
        SealedEnum<ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass>,
        SealedEnumWithEnumProvider<ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass, ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum>,
        EnumForSealedEnumProvider<ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass, ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum>
        {
    public override val values: List<ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass> by
            lazy(mode = LazyThreadSafetyMode.PUBLICATION) {
        listOf(
            ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.FirstObject,
            ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.SecondObject
        )
    }


    public override val enumClass:
            KClass<ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum>
        get() = ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum::class

    public override fun ordinalOf(obj: ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass):
            Int = when (obj) {
        is ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.FirstObject -> 0
        is ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.SecondObject -> 1
    }

    public override fun nameOf(obj: ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass):
            String = when (obj) {
        is ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.FirstObject ->
                "ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClass_FirstObject"
        is ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.SecondObject ->
                "ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClass_SecondObject"
    }

    public override fun valueOf(name: String):
            ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass = when (name) {
        "ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClass_FirstObject" ->
                ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.FirstObject
        "ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClass_SecondObject" ->
                ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.SecondObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override
            fun sealedObjectToEnum(obj: ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass):
            ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum = when (obj) {
        is ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.FirstObject ->
                ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum.ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClass_FirstObject
        is ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.SecondObject ->
                ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum.ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClass_SecondObject
    }

    public override
            fun enumToSealedObject(`enum`: ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum):
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
public val ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.ordinal: Int
    get() = ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.name: String
    get() = ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass] objects.
 */
public val ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.Companion.values:
        List<ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass>
    get() = ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class
 * [ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass]
 */
public val ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.Companion.sealedEnum:
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
public
        fun ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.Companion.valueOf(name: String):
        ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass =
        ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassSealedEnum.valueOf(name)

""".trimIndent()
