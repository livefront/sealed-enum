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
class EmptySealedClassTests {
    @Test
    fun `empty sealed class`() {
        assertEquals(emptyList<EmptySealedClass>(), EmptySealedClass.values)
    }

    @Test
    fun `empty enum for sealed class`() {
        assertEquals(
            EmptySealedClass.values.map(EmptySealedClass::enum),
            enumValues<EmptySealedClassEnum>().toList()
        )
    }

    @Test
    fun `correct enum class`() {
        assertEquals(EmptySealedClassEnum::class, EmptySealedClass.sealedEnum.enumClass)
    }

    @Test
    fun Approver.`compilation generates correct code`() {
        val result = compile(getCommonSourceFile("compilation", "basic", "EmptySealedClass.kt"))

        assertCompiles(result)
        assertApprovedGeneratedFile("EmptySealedClass_SealedEnum.kt", result)
    }
}
