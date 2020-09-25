package com.livefront.sealedenum

import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

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

    @Test
    fun `compilation generates correct code`() {
        val result = compile(getSourceFile("OneObjectSealedClass.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches("OneObjectSealedClass_SealedEnum.kt", oneObjectSealedClassGenerated, result)
    }
}
