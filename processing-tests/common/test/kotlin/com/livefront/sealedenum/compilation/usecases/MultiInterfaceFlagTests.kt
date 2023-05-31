package com.livefront.sealedenum.compilation.usecases

import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getCommonSourceFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MultiInterfaceFlagTests {
    @Test
    fun `three objects sealed interface`() {
        assertEquals(
            listOf(MultiInterfaceFlag.BothFlags, MultiInterfaceFlag.FirstFlag, MultiInterfaceFlag.SecondFlag),
            MultiInterfaceFlag.values
        )
    }

    @Test
    fun `three enums for sealed class`() {
        assertEquals(
            listOf(
                MultiInterfaceFlagEnum.MultiInterfaceFlag_BothFlags,
                MultiInterfaceFlagEnum.MultiInterfaceFlag_FirstFlag,
                MultiInterfaceFlagEnum.MultiInterfaceFlag_SecondFlag
            ),
            enumValues<MultiInterfaceFlagEnum>().toList()
        )
    }

    @Test
    fun `three enums for sealed interface with mapping`() {
        assertEquals(
            MultiInterfaceFlag.values.map(MultiInterfaceFlag::enum),
            enumValues<MultiInterfaceFlagEnum>().toList()
        )
    }

    @Test
    fun `correct enum class`() {
        assertEquals(MultiInterfaceFlagEnum::class, MultiInterfaceFlagSealedEnum.enumClass)
    }

    @Test
    fun `compilation generates correct code`() {
        val result = compile(getCommonSourceFile("compilation", "usecases", "MultiInterfaceFlag.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches("MultiInterfaceFlag_SealedEnum.kt", multiInterfaceFlagGenerated, result)
    }
}
