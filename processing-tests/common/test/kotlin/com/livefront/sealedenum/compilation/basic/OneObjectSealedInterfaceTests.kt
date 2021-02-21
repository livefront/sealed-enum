package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class OneObjectSealedInterfaceTests {
    @Test
    fun `one object sealed interface`() {
        assertEquals(listOf(OneObjectSealedInterface.FirstObject), OneObjectSealedInterface.values)
    }

    @Test
    fun `one enum for sealed interface`() {
        assertEquals(
            listOf(OneObjectSealedInterfaceEnum.OneObjectSealedInterface_FirstObject),
            enumValues<OneObjectSealedInterfaceEnum>().toList()
        )
    }

    @Test
    fun `one enum for sealed interface with mapping`() {
        assertEquals(
            OneObjectSealedInterface.values.map(OneObjectSealedInterface::enum),
            enumValues<OneObjectSealedInterfaceEnum>().toList()
        )
    }

    @Test
    fun `correct enum class`() {
        assertEquals(OneObjectSealedInterfaceEnum::class.java, OneObjectSealedInterface.sealedEnum.enumClass)
    }

    @Test
    fun `compilation generates correct code`() {
        val result = compile(getSourceFile("compilation", "basic", "OneObjectSealedInterface.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches(
            "OneObjectSealedInterface_SealedEnum.kt",
            oneObjectSealedInterfaceGenerated,
            result
        )
    }
}
