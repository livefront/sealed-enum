package com.livefront.sealedenum

import org.intellij.lang.annotations.Language

class OuterClass {
    sealed class InsideOneClassSealedClass {
        object FirstObject : InsideOneClassSealedClass()

        object SecondObject : InsideOneClassSealedClass()

        @GenSealedEnum
        companion object
    }
}

@Language("kotlin")
val insideOneClassSealedClassGenerated = """
package com.livefront.sealedenum

import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An implementation of [SealedEnum] for the sealed class [OuterClass.InsideOneClassSealedClass]
 */
object OuterClass_InsideOneClassSealedClassSealedEnum :
        SealedEnum<OuterClass.InsideOneClassSealedClass> {
    override val values: List<OuterClass.InsideOneClassSealedClass> = listOf(
        OuterClass.InsideOneClassSealedClass.FirstObject,
        OuterClass.InsideOneClassSealedClass.SecondObject
    )


    override fun ordinalOf(obj: OuterClass.InsideOneClassSealedClass): Int = when (obj) {
        OuterClass.InsideOneClassSealedClass.FirstObject -> 0
        OuterClass.InsideOneClassSealedClass.SecondObject -> 1
    }

    override fun nameOf(obj: OuterClass.InsideOneClassSealedClass): String = when (obj) {
        OuterClass.InsideOneClassSealedClass.FirstObject ->
                "OuterClass_InsideOneClassSealedClass_FirstObject"
        OuterClass.InsideOneClassSealedClass.SecondObject ->
                "OuterClass_InsideOneClassSealedClass_SecondObject"
    }

    override fun valueOf(name: String): OuterClass.InsideOneClassSealedClass = when (name) {
        "OuterClass_InsideOneClassSealedClass_FirstObject" ->
                OuterClass.InsideOneClassSealedClass.FirstObject
        "OuterClass_InsideOneClassSealedClass_SecondObject" ->
                OuterClass.InsideOneClassSealedClass.SecondObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }
}

/**
 * The index of [this] in the values list.
 */
val OuterClass.InsideOneClassSealedClass.ordinal: Int
    get() = OuterClass_InsideOneClassSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val OuterClass.InsideOneClassSealedClass.name: String
    get() = OuterClass_InsideOneClassSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [OuterClass.InsideOneClassSealedClass] objects.
 */
val OuterClass.InsideOneClassSealedClass.Companion.values:
        List<OuterClass.InsideOneClassSealedClass>
    get() = OuterClass_InsideOneClassSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class
 * [OuterClass.InsideOneClassSealedClass]
 */
val OuterClass.InsideOneClassSealedClass.Companion.sealedEnum:
        OuterClass_InsideOneClassSealedClassSealedEnum
    get() = OuterClass_InsideOneClassSealedClassSealedEnum

/**
 * Returns the [OuterClass.InsideOneClassSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [OuterClass.InsideOneClassSealedClass], an
 * [IllegalArgumentException] will be thrown.
 */
fun OuterClass.InsideOneClassSealedClass.Companion.valueOf(name: String):
        OuterClass.InsideOneClassSealedClass =
        OuterClass_InsideOneClassSealedClassSealedEnum.valueOf(name)

""".trimIndent()

class FirstOuterClass {
    class SecondOuterClass {
        sealed class InsideTwoClassesSealedClass {
            object FirstObject : InsideTwoClassesSealedClass()

            object SecondObject : InsideTwoClassesSealedClass()

            @GenSealedEnum
            companion object
        }
    }
}

@Language("kotlin")
val insideTwoClassesSealedClassGenerated = """
package com.livefront.sealedenum

import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An implementation of [SealedEnum] for the sealed class
 * [FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass]
 */
object FirstOuterClass_SecondOuterClass_InsideTwoClassesSealedClassSealedEnum :
        SealedEnum<FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass> {
    override val values: List<FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass> =
            listOf(
        FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass.FirstObject,
        FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass.SecondObject
    )


    override fun ordinalOf(obj: FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass): Int =
            when (obj) {
        FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass.FirstObject -> 0
        FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass.SecondObject -> 1
    }

    override fun nameOf(obj: FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass): String =
            when (obj) {
        FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass.FirstObject ->
                "FirstOuterClass_SecondOuterClass_InsideTwoClassesSealedClass_FirstObject"
        FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass.SecondObject ->
                "FirstOuterClass_SecondOuterClass_InsideTwoClassesSealedClass_SecondObject"
    }

    override fun valueOf(name: String): FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass
            = when (name) {
        "FirstOuterClass_SecondOuterClass_InsideTwoClassesSealedClass_FirstObject" ->
                FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass.FirstObject
        "FirstOuterClass_SecondOuterClass_InsideTwoClassesSealedClass_SecondObject" ->
                FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass.SecondObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }
}

/**
 * The index of [this] in the values list.
 */
val FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass.ordinal: Int
    get() = FirstOuterClass_SecondOuterClass_InsideTwoClassesSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass.name: String
    get() = FirstOuterClass_SecondOuterClass_InsideTwoClassesSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass] objects.
 */
val FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass.Companion.values:
        List<FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass>
    get() = FirstOuterClass_SecondOuterClass_InsideTwoClassesSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class
 * [FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass]
 */
val FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass.Companion.sealedEnum:
        FirstOuterClass_SecondOuterClass_InsideTwoClassesSealedClassSealedEnum
    get() = FirstOuterClass_SecondOuterClass_InsideTwoClassesSealedClassSealedEnum

/**
 * Returns the [FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass] object for the given
 * [name].
 *
 * If the given name doesn't correspond to any
 * [FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass], an [IllegalArgumentException] will
 * be thrown.
 */
fun FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass.Companion.valueOf(name: String):
        FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass =
        FirstOuterClass_SecondOuterClass_InsideTwoClassesSealedClassSealedEnum.valueOf(name)

""".trimIndent()
