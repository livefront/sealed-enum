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
class TwoObjectSealedClassTests {
    @Test
    fun `two objects sealed class`() {
        assertEquals(
            listOf(TwoObjectSealedClass.FirstObject, TwoObjectSealedClass.SecondObject),
            TwoObjectSealedClassSealedEnum.values
        )
    }

    @Test
    fun `two enums for sealed class`() {
        assertEquals(
            listOf(
                TwoObjectSealedClassEnum.TwoObjectSealedClass_FirstObject,
                TwoObjectSealedClassEnum.TwoObjectSealedClass_SecondObject
            ),
            enumValues<TwoObjectSealedClassEnum>().toList()
        )
    }

    @Test
    fun `two enums for sealed class with mapping`() {
        assertEquals(
            TwoObjectSealedClass.values.map(TwoObjectSealedClass::enum),
            enumValues<TwoObjectSealedClassEnum>().toList()
        )
    }

    @Test
    fun `correct enum class`() {
        assertEquals(TwoObjectSealedClassEnum::class, TwoObjectSealedClassSealedEnum.enumClass)
    }

    @Test
    fun Approver.`compilation generates correct code`() {
        val result = compile(getCommonSourceFile("compilation", "basic", "TwoObjectSealedClass.kt"))

        assertCompiles(result)
        assertApprovedGeneratedFile("TwoObjectSealedClass_SealedEnum.kt", result)
    }
}
