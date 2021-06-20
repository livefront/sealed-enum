package com.livefront.sealedenum.compilation.generics

import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getCommonSourceFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.reflect.KTypeProjection
import kotlin.reflect.full.createType
import kotlin.reflect.full.isSubtypeOf

class SealedEnumWithAbstractBaseClassesTests {
    @Test
    fun `enum implements correct interfaces with type arguments`() {
        assertTrue(
            SealedEnumWithAbstractBaseClassesEnum::class.createType().isSubtypeOf(
                BaseClassInterface1::class.createType(
                    arguments = listOf(
                        KTypeProjection.invariant(
                            BaseClassInterface1::class.createType(
                                arguments = listOf(
                                    KTypeProjection.invariant(Any::class.createType(nullable = true))
                                )
                            )
                        )
                    )
                )
            )
        )

        assertTrue(
            SealedEnumWithAbstractBaseClassesEnum::class.createType().isSubtypeOf(
                BaseClassInterface2::class.createType(
                    arguments = listOf(
                        KTypeProjection.invariant(String::class.createType())
                    )
                )
            )
        )

        // Check for compilation
        val emptyValues1: Array<out BaseClassInterface1<BaseClassInterface1<Any?>>> = SealedEnumWithAbstractBaseClassesEnum.values()
        val emptyValues2: Array<out BaseClassInterface2<String>> = SealedEnumWithAbstractBaseClassesEnum.values()

        assertEquals(
            emptyList<BaseClassInterface1<Any?>>(),
            emptyValues1.toList()
        )

        assertEquals(
            emptyList<BaseClassInterface2<String>>(),
            emptyValues2.toList()
        )
    }

    @Test
    fun `compilation for invariant type generates correct code`() {
        val result = compile(getCommonSourceFile("compilation", "generics", "SealedEnumWithAbstractBaseClasses.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches(
            "SealedEnumWithAbstractBaseClasses_SealedEnum.kt",
            sealedEnumWithAbstractBaseClassesGenerated,
            result
        )
    }

    @Test
    fun `covariant type enum implements correct interfaces with type arguments`() {
        assertTrue(
            SealedEnumWithAbstractBaseClassesCovariantTypeEnum::class.createType().isSubtypeOf(
                BaseClassInterface3::class.createType(
                    arguments = listOf(
                        KTypeProjection.covariant(
                            BaseClassInterface3::class.createType(
                                arguments = listOf(
                                    KTypeProjection.covariant(Any::class.createType(nullable = true))
                                )
                            )
                        )
                    )
                )
            )
        )

        val emptyValues: Array<out BaseClassInterface3<BaseClassInterface3<*>>> =
            SealedEnumWithAbstractBaseClassesCovariantTypeEnum.values()

        assertEquals(
            emptyList<BaseClassInterface3<BaseClassInterface3<*>>>(),
            emptyValues.toList()
        )
    }

    @Test
    fun `compilation for covariant type generates correct code`() {
        val result = compile(getCommonSourceFile("compilation", "generics", "SealedEnumWithAbstractBaseClasses.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches(
            "SealedEnumWithAbstractBaseClassesCovariantType_SealedEnum.kt",
            sealedEnumWithAbstractBaseClassesCovariantTypeGenerated,
            result
        )
    }
}
