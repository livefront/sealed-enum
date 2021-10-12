package com.livefront.sealedenum.compilation.ksp

import com.livefront.sealedenum.testing.PlatformSourceType
import com.livefront.sealedenum.testing.SharableProcessingSourceType
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Test

/**
 * @see NestedObjectsWithSameNameTests
 */
class NestedObjectsWithSameNameCompilationTests {

    @Test
    fun `compilation generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.UNIQUE,
                PlatformSourceType.COMMON,
                "compilation",
                "ksp",
                "NestedObjectsWithSameName.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "NestedObjectsWithSameName.Companion.EmptySealedClass_SealedEnum.kt",
            nestedObjectsWithSameNameEmptySealedClassGenerated,
            result
        )
    }
}
