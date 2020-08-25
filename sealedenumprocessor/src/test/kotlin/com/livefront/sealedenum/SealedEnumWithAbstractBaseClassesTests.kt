package com.livefront.sealedenum

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.reflect.KTypeProjection
import kotlin.reflect.full.createType
import kotlin.reflect.full.isSubtypeOf

interface BaseClassInterface1<T>

interface BaseClassInterface2<T>

interface BaseClassInterface3<out T>

abstract class AlphaBase<A> : BaseClassInterface1<A>

abstract class BetaBase<A, B : Any> : AlphaBase<A>(), BaseClassInterface2<B>

sealed class SealedEnumWithAbstractBaseClasses : BetaBase<Any?, String>() {
    @GenSealedEnum(generateEnum = true)
    companion object
}

abstract class GammaBase<out T : Any> : BaseClassInterface3<BaseClassInterface3<T>>

sealed class SealedEnumWithAbstractBaseClassesCovariantType<out T : Any> : GammaBase<T>() {
    @GenSealedEnum(generateEnum = true)
    companion object
}

class SealedEnumWithAbstractBaseClassesTests {
    @Test
    fun `enum implements correct interfaces with type arguments`() {
        assertTrue(
            SealedEnumWithAbstractBaseClassesEnum::class.createType().isSubtypeOf(
                BaseClassInterface1::class.createType(
                    arguments = listOf(
                        KTypeProjection.invariant(Any::class.createType(nullable = true))
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
        val emptyValues1: Array<out BaseClassInterface1<Any?>> = SealedEnumWithAbstractBaseClassesEnum.values()
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
}
