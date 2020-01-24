package com.livefront.sealedenum

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@GenSealedEnum
sealed class EmptySealedClass

@GenSealedEnum
sealed class OneObjectSealedClass {
    object FirstObject : OneObjectSealedClass()
}

@GenSealedEnum
sealed class TwoObjectSealedClass {

    object FirstObject : TwoObjectSealedClass()

    object SecondObject : TwoObjectSealedClass()
}

class BasicSealedClassTests {

    @Test
    fun `empty sealed class`() {
        assertEquals(emptyList<EmptySealedClass>(), EmptySealedClassSealedEnum.values)
    }

    @Test
    fun `one object sealed class`() {
        assertEquals(listOf(OneObjectSealedClass.FirstObject), OneObjectSealedClassSealedEnum.values)
    }

    @Test
    fun `two objects sealed class`() {
        assertEquals(
            listOf(TwoObjectSealedClass.FirstObject, TwoObjectSealedClass.SecondObject),
            TwoObjectSealedClassSealedEnum.values
        )
    }

}
