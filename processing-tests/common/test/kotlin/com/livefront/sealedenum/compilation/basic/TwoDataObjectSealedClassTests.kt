package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getCommonSourceFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TwoDataObjectSealedClassTests {
    @Test
    fun `two data objects sealed class`() {
        assertEquals(
            listOf(TwoDataObjectSealedClass.FirstObject, TwoDataObjectSealedClass.SecondObject),
            TwoDataObjectSealedClassSealedEnum.values
        )
    }

    @Test
    fun `two enums for sealed class`() {
        assertEquals(
            listOf(
                TwoDataObjectSealedClassEnum.TwoDataObjectSealedClass_FirstObject,
                TwoDataObjectSealedClassEnum.TwoDataObjectSealedClass_SecondObject
            ),
            enumValues<TwoDataObjectSealedClassEnum>().toList()
        )
    }

    @Test
    fun `two enums for sealed class with mapping`() {
        assertEquals(
            TwoDataObjectSealedClass.values.map(TwoDataObjectSealedClass::enum),
            enumValues<TwoDataObjectSealedClassEnum>().toList()
        )
    }

    @Test
    fun `correct enum class`() {
        assertEquals(TwoDataObjectSealedClassEnum::class, TwoDataObjectSealedClassSealedEnum.enumClass)
    }

    @Test
    fun `compilation generates correct code`() {
        val result = compile(getCommonSourceFile("compilation", "basic", "TwoDataObjectSealedClass.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches(
            "TwoDataObjectSealedClass_SealedEnum.kt",
            twoDataObjectSealedClassGenerated,
            result
        )
    }
}
