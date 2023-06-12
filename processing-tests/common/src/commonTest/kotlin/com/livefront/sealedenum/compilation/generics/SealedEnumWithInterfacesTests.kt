package com.livefront.sealedenum.compilation.generics

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SealedEnumWithInterfacesTests {
    @Test
    fun empty_sealed_enum_implements_interface() {
        // Check for compilation
        val emptyValues: List<TestInterface> = EmptySealedClassWithInterface.values

        assertEquals(
            emptyList<TestInterface>(),
            emptyValues
        )
    }

    @Test
    fun empty_enum_implements_interface() {
        // Check for compilation
        val emptyValues: Array<out TestInterface> = EmptySealedClassWithInterfaceEnum.values()

        assertEquals(
            emptyList<TestInterface>(),
            emptyValues.toList()
        )
    }

    @Test
    fun single_object_sealed_enum_implements_interface() {
        // Check for compilation
        val oneObjectValues: List<TestInterface> = OneObjectSealedClassWithInterface.values

        assertEquals(
            listOf(OneObjectSealedClassWithInterface.FirstObject),
            oneObjectValues
        )
    }

    @Test
    fun single_object_enum_implements_interface() {
        val oneObjectValues: Array<out TestInterface> = OneObjectSealedClassWithInterfaceEnum.values()

        assertEquals(
            listOf(OneObjectSealedClassWithInterfaceEnum.OneObjectSealedClassWithInterface_FirstObject),
            oneObjectValues.toList()
        )
    }

    @Test
    fun two_object_sealed_enum_implements_generic_interface() {
        // Check for compilation
        val twoObjectValues: List<TestGenericInterface<TestInterface>> =
            TwoObjectSealedClassWithGenericInterface.values

        assertEquals(
            listOf(
                TwoObjectSealedClassWithGenericInterface.FirstObject,
                TwoObjectSealedClassWithGenericInterface.SecondObject
            ),
            twoObjectValues
        )
    }

    @Test
    fun two_object_enum_implements_generic_interface() {
        val twoObjectValues: Array<out TestGenericInterface<TestInterface>> =
            TwoObjectSealedClassWithGenericInterfaceEnum.values()

        assertEquals(
            listOf(
                TwoObjectSealedClassWithGenericInterfaceEnum.TwoObjectSealedClassWithGenericInterface_FirstObject,
                TwoObjectSealedClassWithGenericInterfaceEnum.TwoObjectSealedClassWithGenericInterface_SecondObject
            ),
            twoObjectValues.toList()
        )
    }

    @Test
    fun enum_delegates_to_sealed_class() {
        val enumValue = SealedClassWithGetterInterfaceEnum.SealedClassWithGetterInterface_FirstObject

        assertFalse(hasGetBeenCalled)

        assertEquals("First", enumValue.get())

        assertTrue(hasGetBeenCalled)
    }
}
