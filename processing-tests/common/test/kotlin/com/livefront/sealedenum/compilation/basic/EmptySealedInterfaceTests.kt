package com.livefront.sealedenum.compilation.basic

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
        assertEquals(EmptySealedInterfaceEnum::class, EmptySealedInterface.sealedEnum.enumClass)
    }

    @Test
    fun Approver.`compilation generates correct code`() {
        val result = compile(getCommonSourceFile("compilation", "basic", "EmptySealedInterface.kt"))

        assertCompiles(result)
        assertApprovedGeneratedFile("EmptySealedInterface_SealedEnum.kt", result)
    }
}
