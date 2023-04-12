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
class SplitAcrossFilesSealedClassTests {

    @Test
    fun `object split across files`() {
        assertEquals(
            listOf(
                SplitAcrossFilesSubclassA,
                SplitAcrossFilesSubclassB,
                SplitAcrossFilesSubclassC
            ),
            SplitAcrossFilesSealedClass.values
        )
    }

    @Test
    fun `enum for objects split across files`() {
        assertEquals(
            SplitAcrossFilesSealedClass.values.map(SplitAcrossFilesSealedClass::enum),
            enumValues<SplitAcrossFilesSealedClassEnum>().toList()
        )
    }

    @Test
    fun Approver.`compilation for objects split across files generates correct code`() {
        val result = compile(
            getCommonSourceFile("compilation", "location", "SplitAcrossFilesSealedClass.kt"),
            getCommonSourceFile("compilation", "location", "SplitAcrossFilesSubclassA.kt"),
            getCommonSourceFile("compilation", "location", "SplitAcrossFilesSubclassB.kt"),
            getCommonSourceFile("compilation", "location", "SplitAcrossFilesSubclassC.kt")
        )

        assertCompiles(result)
        assertApprovedGeneratedFile("SplitAcrossFilesSealedClass_SealedEnum.kt", result)
    }
}
