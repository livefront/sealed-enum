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
        assertEquals(OneObjectSealedClassEnum::class, OneObjectSealedClass.sealedEnum.enumClass)
    }

    @Test
    fun Approver.`compilation generates correct code`() {
        val result = compile(getCommonSourceFile("compilation", "basic", "OneObjectSealedClass.kt"))

        assertCompiles(result)
        assertApprovedGeneratedFile("OneObjectSealedClass_SealedEnum.kt", result)
    }
}
