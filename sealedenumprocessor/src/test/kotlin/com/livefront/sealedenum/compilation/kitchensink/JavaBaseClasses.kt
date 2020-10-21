package com.livefront.sealedenum.compilation.kitchensink

import com.livefront.sealedenum.GenSealedEnum
import org.intellij.lang.annotations.Language

interface KotlinInterface1

interface KotlinInterface2<T>

interface KotlinInterface3<out T>

internal interface KotlinInterface4<T>

internal interface KotlinInterface5<T>

internal interface KotlinInterface6<T>

interface KotlinInterface7<out T>

open class KotlinFirstBaseClass<out C : Collection<String>> :
    KotlinInterface1, KotlinInterface3<C>, JavaInterface4

open class KotlinSecondBaseClass<A, B, C : Collection<String>, D> :
    JavaFirstBaseClass<B, C>(),
    KotlinInterface4<Double>,
    KotlinInterface6<A>,
    JavaInterface2<C>,
    JavaInterface5<KotlinInterface5<JavaInterface2<KotlinInterface7<D>>>>

sealed class JavaBaseClassesSealedClass<out A : Collection<Int>> :
    JavaSecondBaseClass<Int, String, List<String>>(), KotlinInterface7<A> {

    object FirstObject : JavaBaseClassesSealedClass<List<Int>>()

    object SecondObject : JavaBaseClassesSealedClass<Set<Int>>()

    @GenSealedEnum(generateEnum = true)
    companion object
}

@Language("kotlin")
val javaBaseClassesSealedClassGenerated = """
package com.livefront.sealedenum.compilation.kitchensink

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import java.lang.Class
import kotlin.Double
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An isomorphic enum for the sealed class [JavaBaseClassesSealedClass]
 */
public enum class JavaBaseClassesSealedClassEnum(
    sealedObject: JavaBaseClassesSealedClass<*>
) : KotlinInterface5<KotlinInterface1> by sealedObject, JavaInterface3<Int> by sealedObject,
        KotlinInterface4<Double> by sealedObject, KotlinInterface6<Int> by sealedObject,
        JavaInterface2<List<String>> by sealedObject,
        JavaInterface5<KotlinInterface5<JavaInterface2<KotlinInterface7<JavaInterface3<List<String>>>>>>
        by sealedObject, KotlinInterface2<String> by sealedObject, JavaInterface1 by sealedObject,
        KotlinInterface1 by sealedObject, KotlinInterface3<List<String>> by sealedObject,
        JavaInterface4 by sealedObject {
    JavaBaseClassesSealedClass_FirstObject(com.livefront.sealedenum.compilation.kitchensink.JavaBaseClassesSealedClass.FirstObject),
    JavaBaseClassesSealedClass_SecondObject(com.livefront.sealedenum.compilation.kitchensink.JavaBaseClassesSealedClass.SecondObject),
}

/**
 * The isomorphic [JavaBaseClassesSealedClassEnum] for [this].
 */
public val JavaBaseClassesSealedClass<*>.`enum`: JavaBaseClassesSealedClassEnum
    get() = JavaBaseClassesSealedClassSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [JavaBaseClassesSealedClass] for [this].
 */
public val JavaBaseClassesSealedClassEnum.sealedObject: JavaBaseClassesSealedClass<*>
    get() = JavaBaseClassesSealedClassSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [JavaBaseClassesSealedClass]
 */
public object JavaBaseClassesSealedClassSealedEnum : SealedEnum<JavaBaseClassesSealedClass<*>>,
        SealedEnumWithEnumProvider<JavaBaseClassesSealedClass<*>, JavaBaseClassesSealedClassEnum>,
        EnumForSealedEnumProvider<JavaBaseClassesSealedClass<*>, JavaBaseClassesSealedClassEnum> {
    public override val values: List<JavaBaseClassesSealedClass<*>> = listOf(
        JavaBaseClassesSealedClass.FirstObject,
        JavaBaseClassesSealedClass.SecondObject
    )


    public override val enumClass: Class<JavaBaseClassesSealedClassEnum>
        get() = JavaBaseClassesSealedClassEnum::class.java

    public override fun ordinalOf(obj: JavaBaseClassesSealedClass<*>): Int = when (obj) {
        JavaBaseClassesSealedClass.FirstObject -> 0
        JavaBaseClassesSealedClass.SecondObject -> 1
    }

    public override fun nameOf(obj: JavaBaseClassesSealedClass<*>): String = when (obj) {
        JavaBaseClassesSealedClass.FirstObject -> "JavaBaseClassesSealedClass_FirstObject"
        JavaBaseClassesSealedClass.SecondObject -> "JavaBaseClassesSealedClass_SecondObject"
    }

    public override fun valueOf(name: String): JavaBaseClassesSealedClass<*> = when (name) {
        "JavaBaseClassesSealedClass_FirstObject" -> JavaBaseClassesSealedClass.FirstObject
        "JavaBaseClassesSealedClass_SecondObject" -> JavaBaseClassesSealedClass.SecondObject
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: JavaBaseClassesSealedClass<*>):
            JavaBaseClassesSealedClassEnum = when (obj) {
        JavaBaseClassesSealedClass.FirstObject ->
                JavaBaseClassesSealedClassEnum.JavaBaseClassesSealedClass_FirstObject
        JavaBaseClassesSealedClass.SecondObject ->
                JavaBaseClassesSealedClassEnum.JavaBaseClassesSealedClass_SecondObject
    }

    public override fun enumToSealedObject(`enum`: JavaBaseClassesSealedClassEnum):
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
public val JavaBaseClassesSealedClass<*>.ordinal: Int
    get() = JavaBaseClassesSealedClassSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val JavaBaseClassesSealedClass<*>.name: String
    get() = JavaBaseClassesSealedClassSealedEnum.nameOf(this)

/**
 * A list of all [JavaBaseClassesSealedClass] objects.
 */
public val JavaBaseClassesSealedClass.Companion.values: List<JavaBaseClassesSealedClass<*>>
    get() = JavaBaseClassesSealedClassSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [JavaBaseClassesSealedClass]
 */
public val JavaBaseClassesSealedClass.Companion.sealedEnum: JavaBaseClassesSealedClassSealedEnum
    get() = JavaBaseClassesSealedClassSealedEnum

/**
 * Returns the [JavaBaseClassesSealedClass] object for the given [name].
 *
 * If the given name doesn't correspond to any [JavaBaseClassesSealedClass], an
 * [IllegalArgumentException] will be thrown.
 */
public fun JavaBaseClassesSealedClass.Companion.valueOf(name: String): JavaBaseClassesSealedClass<*>
        = JavaBaseClassesSealedClassSealedEnum.valueOf(name)

""".trimIndent()
