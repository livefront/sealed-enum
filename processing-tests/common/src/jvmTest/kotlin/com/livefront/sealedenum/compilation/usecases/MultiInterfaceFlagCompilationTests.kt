package com.livefront.sealedenum.compilation.usecases

import com.livefront.sealedenum.testing.PlatformSourceType
import com.livefront.sealedenum.testing.SharableProcessingSourceType
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Test

class MultiInterfaceFlagCompilationTests {
    @Test
    fun `compilation generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "usecases",
                "MultiInterfaceFlag.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches("MultiInterfaceFlag_SealedEnum.kt", multiInterfaceFlagGenerated, result)
    }
}
