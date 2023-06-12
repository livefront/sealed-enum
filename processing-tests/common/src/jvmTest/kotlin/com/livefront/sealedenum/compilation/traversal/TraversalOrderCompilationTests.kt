package com.livefront.sealedenum.compilation.traversal

import com.livefront.sealedenum.testing.PlatformSourceType
import com.livefront.sealedenum.testing.SharableProcessingSourceType
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Test

/**
 * @see TraversalOrderTests
 */
class TraversalOrderCompilationTests {
    @Test
    fun `compilation generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "traversal",
                "TraversalOrder.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches("Tree_SealedEnum.kt", treeGenerated, result)
    }
}
