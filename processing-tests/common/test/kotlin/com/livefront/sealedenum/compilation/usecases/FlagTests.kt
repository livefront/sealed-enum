package com.livefront.sealedenum.compilation.usecases

import com.livefront.sealedenum.testing.assertApprovedGeneratedFile
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getCommonSourceFile
import com.oneeyedmen.okeydoke.Approver
import com.oneeyedmen.okeydoke.junit5.ApprovalsExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(ApprovalsExtension::class)
class FlagTests {
    @Test
    fun `two objects sealed class`() {
        assertEquals(
            listOf(Flag.FirstFlag, Flag.SecondFlag),
            FlagSealedEnum.values
        )
    }

    @Test
    fun `two enums for sealed class`() {
        assertEquals(
            listOf(
                FlagEnum.Flag_FirstFlag,
                FlagEnum.Flag_SecondFlag
            ),
            enumValues<FlagEnum>().toList()
        )
    }

    @Test
    fun `two enums for sealed class with mapping`() {
        assertEquals(
            Flag.values.map(Flag::enum),
            enumValues<FlagEnum>().toList()
        )
    }

    @Test
    fun `correct enum class`() {
        assertEquals(FlagEnum::class, FlagSealedEnum.enumClass)
    }

    @Test
    fun Approver.`compilation generates correct code`() {
        val result = compile(getCommonSourceFile("compilation", "usecases", "Flag.kt"))

        assertCompiles(result)
        assertApprovedGeneratedFile("Flag_SealedEnum.kt", result)
    }
}
