package com.livefront.sealedenum.compilation.generics

import com.livefront.sealedenum.GenSealedEnum

interface GenericInterfaceOut<out T>

sealed class OneTypeParameterSealedClass<out T> : GenericInterfaceOut<T> {
    object FirstObject : OneTypeParameterSealedClass<Int>()

    object SecondObject : OneTypeParameterSealedClass<String>()

    object ThirdObject : OneTypeParameterSealedClass<Nothing>()

    @GenSealedEnum(generateEnum = true)
    companion object OneType
}

interface GenericInterfaceTwoOut<out A, out B>

sealed class TwoTypeParameterSealedClass<out A, out B> : GenericInterfaceTwoOut<A, B> {
    object FirstObject : TwoTypeParameterSealedClass<Any?, Nothing>()

    object SecondObject : TwoTypeParameterSealedClass<Double, Double>()

    @GenSealedEnum(generateEnum = true)
    companion object TwoType
}

interface GenericInterfaceInOut<in A, out B>

sealed class LimitedTypeParameterSealedClass<in Number, out String> : GenericInterfaceInOut<Number, String> {
    object FirstObject : LimitedTypeParameterSealedClass<Int, String>()

    object SecondObject : LimitedTypeParameterSealedClass<Int, Any>()

    @GenSealedEnum(generateEnum = true)
    companion object LimitedType
}

interface MultipleBoundsInterface1

interface MultipleBoundsInterface2

interface MultipleBoundsInterface3 : MultipleBoundsInterface1, MultipleBoundsInterface2

sealed class MultipleBoundsSealedClass<T> where T : MultipleBoundsInterface1, T : MultipleBoundsInterface2 {
    object FirstObject : MultipleBoundsSealedClass<MultipleBoundsInterface3>()

    @GenSealedEnum(generateEnum = true)
    companion object
}
