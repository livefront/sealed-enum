package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.testing.PlatformSourceType
import com.livefront.sealedenum.testing.SharableProcessingSourceType
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Test

/**
 * @see OneObjectSealedInterfaceTests
 */
class OneObjectSealedInterfaceCompilationTests {
    @Test
    fun `compilation generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "basic",
                "OneObjectSealedInterface.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "OneObjectSealedInterface_SealedEnum.kt",
            oneObjectSealedInterfaceGenerated,
            result
        )
    }
}
