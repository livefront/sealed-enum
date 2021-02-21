package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EmptySealedInterfaceTests {
    @Test
    fun `empty sealed interface`() {
        assertEquals(emptyList<EmptySealedInterface>(), EmptySealedInterface.values)
    }

    @Test
    fun `empty enum for sealed interface`() {
        assertEquals(
            EmptySealedInterface.values.map(EmptySealedInterface::enum),
            enumValues<EmptySealedInterfaceEnum>().toList()
        )
    }

    @Test
    fun `correct enum class`() {
        assertEquals(EmptySealedInterfaceEnum::class.java, EmptySealedInterface.sealedEnum.enumClass)
    }

    @Test
    fun `compilation generates correct code`() {
        val result = compile(getSourceFile("compilation", "basic", "EmptySealedInterface.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches("EmptySealedInterface_SealedEnum.kt", emptySealedInterfaceGenerated, result)
    }
}
