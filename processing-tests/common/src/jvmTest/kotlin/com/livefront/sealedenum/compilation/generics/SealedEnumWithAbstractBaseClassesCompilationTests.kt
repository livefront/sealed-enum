package com.livefront.sealedenum.compilation.generics

import com.livefront.sealedenum.testing.PlatformSourceType
import com.livefront.sealedenum.testing.SharableProcessingSourceType
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Test

/**
 * @see SealedEnumWithAbstractBaseClassesTests
 */
class SealedEnumWithAbstractBaseClassesCompilationTests {
    @Test
    fun `compilation for invariant type generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "generics",
                "SealedEnumWithAbstractBaseClasses.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "SealedEnumWithAbstractBaseClasses_SealedEnum.kt",
            sealedEnumWithAbstractBaseClassesGenerated,
            result
        )
    }

    @Test
    fun `compilation for covariant type generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "generics",
                "SealedEnumWithAbstractBaseClasses.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "SealedEnumWithAbstractBaseClassesCovariantType_SealedEnum.kt",
            sealedEnumWithAbstractBaseClassesCovariantTypeGenerated,
            result
        )
    }
}
