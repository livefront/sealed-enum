package com.livefront.sealedenum.compilation.location

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
    fun Approver.`compilation for inside one class generates correct code`() {
        val result = compile(getCommonSourceFile("compilation", "location", "NestedClass.kt"))

        assertCompiles(result)
        assertApprovedGeneratedFile("OuterClass.InsideOneClassSealedClass_SealedEnum.kt", result)
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
    fun Approver.`compilation for inside two classes generates correct code`() {
        val result = compile(getCommonSourceFile("compilation", "location", "NestedClass.kt"))

        assertCompiles(result)
        assertApprovedGeneratedFile(
            "FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass_SealedEnum.kt",
            result
        )
    }
}
