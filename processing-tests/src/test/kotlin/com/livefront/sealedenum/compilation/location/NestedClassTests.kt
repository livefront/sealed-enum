package com.livefront.sealedenum.compilation.location

import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NestedClassTests {

    @Test
    fun `inside one class`() {
        assertEquals(
            listOf(
                OuterClass.InsideOneClassSealedClass.FirstObject,
                OuterClass.InsideOneClassSealedClass.SecondObject
            ),
            OuterClass.InsideOneClassSealedClass.values
        )
    }

    @Test
    fun `compilation for inside one class generates correct code`() {
        val result = compile(getSourceFile("compilation", "location", "NestedClass.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches(
            "OuterClass.InsideOneClassSealedClass_SealedEnum.kt",
            insideOneClassSealedClassGenerated,
            result
        )
    }

    @Test
    fun `inside two classes`() {
        assertEquals(
            listOf(
                FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass.FirstObject,
                FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass.SecondObject
            ),
            FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass.values
        )
    }

    @Test
    fun `compilation for inside two classes generates correct code`() {
        val result = compile(getSourceFile("compilation", "location", "NestedClass.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches(
            "FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass_SealedEnum.kt",
            insideTwoClassesSealedClassGenerated,
            result
        )
    }
}
