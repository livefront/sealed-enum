package com.livefront.sealedenum.compilation.usecases

import com.livefront.sealedenum.testing.PlatformSourceType
import com.livefront.sealedenum.testing.SharableProcessingSourceType
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Test

class FlagCompilationTests {
    @Test
    fun `compilation generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "usecases",
                "Flag.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches("Flag_SealedEnum.kt", flagGenerated, result)
    }
}
