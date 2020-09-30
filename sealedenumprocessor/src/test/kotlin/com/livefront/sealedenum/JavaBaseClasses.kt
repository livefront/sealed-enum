package com.livefront.sealedenum

import org.intellij.lang.annotations.Language

interface JavaBaseClassesInterface1

interface JavaBaseClassesInterface2<T>

interface JavaBaseClassesInterface3<out T>

internal interface JavaBaseClassesInterface4<T>

internal interface JavaBaseClassesInterface5<T>

internal interface JavaBaseClassesInterface6<T>

interface JavaBaseClassesInterface7<out T>

open class KotlinFirstBaseClass<out C : Collection<String>> :
    JavaBaseClassesInterface1, JavaBaseClassesInterface3<C>, JavaInterface4

open class KotlinSecondBaseClass<A, B, C : Collection<String>, D> :
    JavaFirstBaseClass<B, C>(),
    JavaBaseClassesInterface4<Double>,
    JavaBaseClassesInterface6<A>,
    JavaInterface2<C>,
    JavaInterface5<JavaBaseClassesInterface5<JavaInterface2<JavaBaseClassesInterface7<D>>>>

sealed class JavaBaseClassesSealedClass<out A : Collection<Int>> :
    JavaSecondBaseClass<Int, String, List<String>>(), JavaBaseClassesInterface7<A> {

    object FirstObject : JavaBaseClassesSealedClass<List<Int>>()

    object SecondObject : JavaBaseClassesSealedClass<Set<Int>>()

    @GenSealedEnum(generateEnum = true)
    companion object
}

@Language("kotlin")
val javaBaseClassesSealedClassGenerated = """
package com.livefront.sealedenum

import java.lang.Class
import kotlin.Double
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [JavaBaseClassesSealedClass]
 */
enum class JavaBaseClassesSealedClassEnum(
    sealedObject: JavaBaseClassesSealedClass<*>
) : JavaBaseClassesInterface5<JavaBaseClassesInterface1> by sealedObject, JavaInterface3<Int> by
        sealedObject, JavaBaseClassesInterface4<Double> by sealedObject,
        JavaBaseClassesInterface6<Int> by sealedObject, JavaInterface2<List<String>> by
        sealedObject,
        JavaInterface5<JavaBaseClassesInterface5<JavaInterface2<JavaBaseClassesInterface7<JavaInterface3<List<String>>>>>>
        by sealedObject, JavaBaseClassesInterface2<String> by sealedObject, JavaInterface1 by
        sealedObject, JavaBaseClassesInterface1 by sealedObject,
        JavaBaseClassesInterface3<List<String>> by sealedObject, JavaInterface4 by sealedObject {
    JavaBaseClassesSealedClass_FirstObject(com.livefront.sealedenum.JavaBaseClassesSealedClass.FirstObject),

    JavaBaseClassesSealedClass_SecondObject(com.livefront.sealedenum.JavaBaseClassesSealedClass.SecondObject)
}

/**
 * The isomorphic [JavaBaseClassesSealedClassEnum] for [this].
 */
val JavaBaseClassesSealedClass<*>.enum: JavaBaseClassesSealedClassEnum
    get() = JavaBaseClassesSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [JavaBaseClassesSealedClass] for [this].
 */
val JavaBaseClassesSealedClassEnum.sealedObject: JavaBaseClassesSealedClass<*>
    get() = JavaBaseClassesSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [JavaBaseClassesSealedClass]
 */
object JavaBaseClassesSealedClassSealedEnum : SealedEnum<JavaBaseClassesSealedClass<*>>,
        SealedEnumWithEnumProvider<JavaBaseClassesSealedClass<*>, JavaBaseClassesSealedClassEnum>,
        EnumForSealedEnumProvider<JavaBaseClassesSealedClass<*>, JavaBaseClassesSealedClassEnum> {
    override val values: List<JavaBaseClassesSealedClass<*>> = listOf(
        JavaBaseClassesSealedClass.FirstObject,
        JavaBaseClassesSealedClass.SecondObject
    )


    override val enumClass: Class<JavaBaseClassesSealedClassEnum>
        get() = JavaBaseClassesSealedClassEnum::class.java

    override fun ordinalOf(obj: JavaBaseClassesSealedClass<*>): Int = when (obj) {
        JavaBaseClassesSealedClass.FirstObject -> 0
        JavaBaseClassesSealedClass.SecondObject -> 1
    }

    override fun nameOf(obj: JavaBaseClassesSealedClass<*>): String = when (obj) {
        JavaBaseClassesSealedClass.FirstObject -> "JavaBaseClassesSealedClass_FirstObject"
        JavaBaseClassesSealedClass.SecondObject -> "JavaBaseClassesSealedClass_SecondObject"
    }

    override fun valueOf(name: String): JavaBaseClassesSealedClass<*> = when (name) {
        "JavaBaseClassesSealedClass_FirstObject" -> JavaBaseClassesSealedClass.FirstObject
        "JavaBaseClassesSealedClass_SecondObject" -> JavaBaseClassesSealedClass.SecondObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    override fun sealedObjectToEnum(obj: JavaBaseClassesSealedClass<*>):
            JavaBaseClassesSealedClassEnum = when (obj) {
        JavaBaseClassesSealedClass.FirstObject ->
                JavaBaseClassesSealedClassEnum.JavaBaseClassesSealedClass_FirstObject
        JavaBaseClassesSealedClass.SecondObject ->
                JavaBaseClassesSealedClassEnum.JavaBaseClassesSealedClass_SecondObject
    }

    override fun enumToSealedObject(enum: JavaBaseClassesSealedClassEnum):
            JavaBaseClassesSealedClass<*> = when (enum) {
        JavaBaseClassesSealedClassEnum.JavaBaseClassesSealedClass_FirstObject ->
                JavaBaseClassesSealedClass.FirstObject
        JavaBaseClassesSealedClassEnum.JavaBaseClassesSealedClass_SecondObject ->
                JavaBaseClassesSealedClass.SecondObject
    }
}

/**
 * The index of [this] in the values list.
 */
val JavaBaseClassesSealedClass<*>.ordinal: Int
    get() = JavaBaseClassesSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
val JavaBaseClassesSealedClass<*>.name: String
    get() = JavaBaseClassesSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [JavaBaseClassesSealedClass] objects.
 */
val JavaBaseClassesSealedClass.Companion.values: List<JavaBaseClassesSealedClass<*>>
    get() = JavaBaseClassesSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [JavaBaseClassesSealedClass]
 */
val JavaBaseClassesSealedClass.Companion.sealedEnum: JavaBaseClassesSealedClassSealedEnum
    get() = JavaBaseClassesSealedClassSealedEnum

/**
 * Returns the [JavaBaseClassesSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [JavaBaseClassesSealedClass], an
 * [IllegalArgumentException] will be thrown.
 */
fun JavaBaseClassesSealedClass.Companion.valueOf(name: String): JavaBaseClassesSealedClass<*> =
        JavaBaseClassesSealedClassSealedEnum.valueOf(name)

""".trimIndent()
