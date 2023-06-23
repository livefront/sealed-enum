package com.livefront.sealedenum.compilation.kitchensink

import com.livefront.sealedenum.GenSealedEnum

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
