@file:Suppress("UnnecessaryAbstractClass")

package com.livefront.sealedenum.compilation.generics

import com.livefront.sealedenum.GenSealedEnum

interface BaseClassInterface1<T>

interface BaseClassInterface2<T>

interface BaseClassInterface3<out T>

abstract class AlphaBase<A> : BaseClassInterface1<A>

abstract class BetaBase<A, B : Any> : AlphaBase<BaseClassInterface1<A>>(), BaseClassInterface2<B>

sealed class SealedEnumWithAbstractBaseClasses : BetaBase<Any?, String>() {
    @GenSealedEnum(generateEnum = true)
    companion object
}

abstract class GammaBase

abstract class DeltaBase<out T : Any> : GammaBase(), BaseClassInterface3<BaseClassInterface3<T>>

sealed class SealedEnumWithAbstractBaseClassesCovariantType<out T : Any> : DeltaBase<T>() {
    @GenSealedEnum(generateEnum = true)
    companion object
}
