package com.livefront.sealedenum.compilation.location

import com.livefront.sealedenum.testing.PlatformSourceType
import com.livefront.sealedenum.testing.SharableProcessingSourceType
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Test

/**
 * @see NestedClassTests
 */
class NestedClassCompilationTests {
    @Test
    fun `compilation for inside one class generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "location",
                "NestedClass.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "OuterClass.InsideOneClassSealedClass_SealedEnum.kt",
            insideOneClassSealedClassGenerated,
            result
        )
    }

    @Test
    fun `compilation for inside two classes generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "location",
                "NestedClass.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass_SealedEnum.kt",
            insideTwoClassesSealedClassGenerated,
            result
        )
    }
}
