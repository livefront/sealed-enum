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
        assertEquals(OneObjectSealedInterfaceEnum::class, OneObjectSealedInterface.sealedEnum.enumClass)
    }

    @Test
    fun Approver.`compilation generates correct code`() {
        val result = compile(getCommonSourceFile("compilation", "basic", "OneObjectSealedInterface.kt"))

        assertCompiles(result)
        assertApprovedGeneratedFile("OneObjectSealedInterface_SealedEnum.kt", result)
    }
}
