package com.livefront.sealedenum.compilation.generics

import kotlin.test.Test
import kotlin.test.assertEquals

class SealedEnumWithAbstractBaseClassesTests {
    @Test
    fun enum_implements_correct_interfaces_with_type_arguments() {
        // Check for compilation
        val emptyValues1: Array<out BaseClassInterface1<BaseClassInterface1<Any?>>> =
            SealedEnumWithAbstractBaseClassesEnum.values()
        val emptyValues2: Array<out BaseClassInterface2<String>> = SealedEnumWithAbstractBaseClassesEnum.values()

        assertEquals(
            emptyList<BaseClassInterface1<BaseClassInterface1<Any?>>>(),
            emptyValues1.toList()
        )

        assertEquals(
            emptyList<BaseClassInterface2<String>>(),
            emptyValues2.toList()
        )
    }

    @Test
    fun covariant_type_enum_implements_correct_interfaces_with_type_arguments() {
        val emptyValues: Array<out BaseClassInterface3<BaseClassInterface3<*>>> =
            SealedEnumWithAbstractBaseClassesCovariantTypeEnum.values()

        assertEquals(
            emptyList<BaseClassInterface3<BaseClassInterface3<*>>>(),
            emptyValues.toList()
        )
    }
}
