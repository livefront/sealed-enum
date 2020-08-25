package com.livefront.sealedenum

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

sealed class OneObjectSealedClass {
    object FirstObject : OneObjectSealedClass()

    @GenSealedEnum(generateEnum = true)
    companion object
}

class OneObjectSealedClassTests {
    @Test
    fun `one object sealed class`() {
        assertEquals(listOf(OneObjectSealedClass.FirstObject), OneObjectSealedClass.values)
    }

    @Test
    fun `one enum for sealed class`() {
        assertEquals(
            listOf(OneObjectSealedClassEnum.OneObjectSealedClass_FirstObject),
            enumValues<OneObjectSealedClassEnum>().toList()
        )
    }

    @Test
    fun `one enum for sealed class with mapping`() {
        assertEquals(
            OneObjectSealedClass.values.map(OneObjectSealedClass::enum),
            enumValues<OneObjectSealedClassEnum>().toList()
        )
    }

    @Test
    fun `correct enum class`() {
        assertEquals(OneObjectSealedClassEnum::class.java, OneObjectSealedClass.sealedEnum.enumClass)
    }
}
