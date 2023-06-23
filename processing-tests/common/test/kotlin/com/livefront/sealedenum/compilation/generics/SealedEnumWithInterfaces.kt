package com.livefront.sealedenum.compilation.generics

import com.livefront.sealedenum.GenSealedEnum

interface TestInterface

interface TestGenericInterface<T>

interface TestGetterInterface {
    fun get(): String
}

sealed class EmptySealedClassWithInterface : TestInterface {
    @GenSealedEnum(generateEnum = true)
    companion object
}

sealed class OneObjectSealedClassWithInterface : TestInterface {
    object FirstObject : OneObjectSealedClassWithInterface()

    @GenSealedEnum(generateEnum = true)
    companion object
}

sealed class TwoObjectSealedClassWithGenericInterface<T : TestInterface> : TestGenericInterface<T> {
    object FirstObject : TwoObjectSealedClassWithGenericInterface<TestInterface>()
    object SecondObject : TwoObjectSealedClassWithGenericInterface<TestInterface>()

    @GenSealedEnum(generateEnum = true)
    companion object
}

sealed class SealedClassWithGetterInterface : TestGetterInterface {
    object FirstObject : SealedClassWithGetterInterface() {
        var hasGetBeenCalled = false

        override fun get(): String {
            hasGetBeenCalled = true
            return "First"
        }
    }

    @GenSealedEnum(generateEnum = true)
    companion object
}
