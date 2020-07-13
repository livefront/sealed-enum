package com.livefront.sealedenum

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@GenSealedEnum(generateEnum = true)
sealed class OneObjectSealedClass {
    object FirstObject : OneObjectSealedClass()
}

class OneObjectSealedClassTests {
    @Test
    fun `one object sealed class`() {
        assertEquals(listOf(OneObjectSealedClass.FirstObject), OneObjectSealedClassSealedEnum.values)
    }

    @Test
    fun `one enum for sealed class`() {
        assertEquals(
            listOf(OneObjectSealedClassEnum.OneObjectSealedClass_FirstObject),
            enumValues<OneObjectSealedClassEnum>().toList()
        )
    }

    @Test
    fun `correct enum class`() {
        assertEquals(OneObjectSealedClassEnum::class.java, OneObjectSealedClassSealedEnum.enumClass)
    }
}
