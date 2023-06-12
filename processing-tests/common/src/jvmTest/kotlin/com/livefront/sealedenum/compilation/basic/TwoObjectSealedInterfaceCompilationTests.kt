package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.testing.PlatformSourceType
import com.livefront.sealedenum.testing.SharableProcessingSourceType
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Test

/**
 * @see TwoObjectSealedInterfaceTests
 */
class TwoObjectSealedInterfaceCompilationTests {
    @Test
    fun `compilation generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "basic",
                "TwoObjectSealedInterface.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "TwoObjectSealedInterface_SealedEnum.kt",
            twoObjectSealedInterfaceGenerated,
            result
        )
    }
}
