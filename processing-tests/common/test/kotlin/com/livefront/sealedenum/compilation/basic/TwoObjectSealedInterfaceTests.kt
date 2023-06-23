package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.testing.SealedEnumApprovalsExtension
import com.livefront.sealedenum.testing.assertApprovedGeneratedFile
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getCommonSourceFile
import com.oneeyedmen.okeydoke.Approver
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(SealedEnumApprovalsExtension::class)
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
        assertEquals(TwoObjectSealedInterfaceEnum::class, TwoObjectSealedInterface.sealedEnum.enumClass)
    }

    @Test
    fun Approver.`compilation generates correct code`() {
        val result = compile(getCommonSourceFile("compilation", "basic", "TwoObjectSealedInterface.kt"))

        assertCompiles(result)
        assertApprovedGeneratedFile("TwoObjectSealedInterface_SealedEnum.kt", result)
    }
}
