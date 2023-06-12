package com.livefront.sealedenum.compilation.generics

import com.livefront.sealedenum.testing.PlatformSourceType
import com.livefront.sealedenum.testing.SharableProcessingSourceType
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Test

/**
 * @see SealedEnumWithInterfacesTests
 */
class SealedEnumWithInterfacesCompilationTests {
    @Test
    fun `compilation for empty sealed class generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "generics",
                "SealedEnumWithInterfaces.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "EmptySealedClassWithInterface_SealedEnum.kt",
            emptySealedClassWithInterfaceGenerated,
            result
        )
    }

    @Test
    fun `compilation for one object sealed class generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "generics",
                "SealedEnumWithInterfaces.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "OneObjectSealedClassWithInterface_SealedEnum.kt",
            oneObjectSealedClassWithInterfaceGenerated,
            result
        )
    }

    @Test
    fun `compilation for two object sealed class generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "generics",
                "SealedEnumWithInterfaces.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "TwoObjectSealedClassWithGenericInterface_SealedEnum.kt",
            twoObjectSealedClassWithGenericInterfaceGenerated,
            result
        )
    }

    @Test
    fun `compilation for sealed class with getter interface generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.COMMON,
                "compilation",
                "generics",
                "SealedEnumWithInterfaces.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "SealedClassWithGetterInterface_SealedEnum.kt",
            sealedClassWithGetterInterface,
            result
        )
    }
}
