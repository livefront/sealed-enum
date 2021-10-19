package com.livefront.sealedenum.compilation.location

import com.livefront.sealedenum.testing.PlatformSourceType
import com.livefront.sealedenum.testing.SharableProcessingSourceType
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Test

class SplitAcrossFilesSealedClassCompilationTests {
    @Test
    fun `compilation for objects split across files generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "location",
                "SplitAcrossFilesSealedClass.kt"
            ),
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "location",
                "SplitAcrossFilesSubclassA.kt"
            ),
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "location",
                "SplitAcrossFilesSubclassB.kt"
            ),
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "location",
                "SplitAcrossFilesSubclassC.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "SplitAcrossFilesSealedClass_SealedEnum.kt",
            splitAcrossFilesSealedClassGenerated,
            result
        )
    }
}
