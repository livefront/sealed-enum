package com.livefront.sealedenum.compilation.generics

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
class GenericSealedClassTests {

    @Test
    fun `one type parameter sealed class`() {
        assertEquals(
            listOf(
                OneTypeParameterSealedClass.FirstObject,
                OneTypeParameterSealedClass.SecondObject,
                OneTypeParameterSealedClass.ThirdObject
            ),
            OneTypeParameterSealedClass.values
        )
    }

    @Test
    fun Approver.`compilation for one type parameter generates correct code`() {
        val result = compile(getCommonSourceFile("compilation", "generics", "GenericSealedClass.kt"))

        assertCompiles(result)
        assertApprovedGeneratedFile("OneTypeParameterSealedClass_SealedEnum.kt", result)
    }

    @Test
    fun `two type parameter sealed class`() {
        assertEquals(
            listOf(
                TwoTypeParameterSealedClass.FirstObject,
                TwoTypeParameterSealedClass.SecondObject
            ),
            TwoTypeParameterSealedClass.values
        )
    }

    @Test
    fun Approver.`compilation for two type parameter generates correct code`() {
        val result = compile(getCommonSourceFile("compilation", "generics", "GenericSealedClass.kt"))

        assertCompiles(result)
        assertApprovedGeneratedFile("TwoTypeParameterSealedClass_SealedEnum.kt", result)
    }

    @Test
    fun `limited type parameter sealed class`() {
        assertEquals(
            listOf(
                LimitedTypeParameterSealedClass.FirstObject,
                LimitedTypeParameterSealedClass.SecondObject
            ),
            LimitedTypeParameterSealedClass.values
        )
    }

    @Test
    fun Approver.`compilation for limited type parameter generates correct code`() {
        val result = compile(getCommonSourceFile("compilation", "generics", "GenericSealedClass.kt"))

        assertCompiles(result)
        assertApprovedGeneratedFile("LimitedTypeParameterSealedClass_SealedEnum.kt", result)
    }

    @Test
    fun `multiple bounds sealed class`() {
        assertEquals(
            listOf(
                MultipleBoundsSealedClass.FirstObject
            ),
            MultipleBoundsSealedClass.values
        )
    }

    @Test
    fun Approver.`compilation for multiple bounds sealed class generates correct code`() {
        val result = compile(getCommonSourceFile("compilation", "generics", "GenericSealedClass.kt"))

        assertCompiles(result)
        assertApprovedGeneratedFile("MultipleBoundsSealedClass_SealedEnum.kt", result)
    }
}
