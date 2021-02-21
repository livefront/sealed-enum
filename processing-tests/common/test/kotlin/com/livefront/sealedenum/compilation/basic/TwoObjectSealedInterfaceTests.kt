package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TwoObjectSealedInterfaceTests {
    @Test
    fun `two objects sealed interface`() {
        assertEquals(
            listOf(TwoObjectSealedInterface.FirstObject, TwoObjectSealedInterface.SecondObject),
            TwoObjectSealedInterface.values
        )
    }

    @Test
    fun `two enums for sealed interface`() {
        assertEquals(
            listOf(
                TwoObjectSealedInterfaceEnum.TwoObjectSealedInterface_FirstObject,
                TwoObjectSealedInterfaceEnum.TwoObjectSealedInterface_SecondObject
            ),
            enumValues<TwoObjectSealedInterfaceEnum>().toList()
        )
    }

    @Test
    fun `two enums for sealed interface with mapping`() {
        assertEquals(
            TwoObjectSealedInterface.values.map(TwoObjectSealedInterface::enum),
            enumValues<TwoObjectSealedInterfaceEnum>().toList()
        )
    }

    @Test
    fun `correct enum class`() {
        assertEquals(TwoObjectSealedInterfaceEnum::class.java, TwoObjectSealedInterface.sealedEnum.enumClass)
    }

    @Test
    fun `compilation generates correct code`() {
        val result = compile(getSourceFile("compilation", "basic", "TwoObjectSealedInterface.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches(
            "TwoObjectSealedInterface_SealedEnum.kt",
            twoObjectSealedInterfaceGenerated,
            result
        )
    }
}