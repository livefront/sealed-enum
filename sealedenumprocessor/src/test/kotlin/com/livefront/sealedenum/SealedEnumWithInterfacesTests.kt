package com.livefront.sealedenum

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.reflect.KTypeProjection
import kotlin.reflect.full.createType
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.isSubtypeOf

interface TestInterface

interface TestGenericInterface<T>

interface TestGetterInterface {
    fun get(): String
}

sealed class EmptySealedClassWithInterface : TestInterface {
    @GenSealedEnum(generateEnum = true)
    companion object
}

sealed class OneObjectSealedClassWithInterface : TestInterface {
    object FirstObject : OneObjectSealedClassWithInterface()

    @GenSealedEnum(generateEnum = true)
    companion object
}

sealed class TwoObjectSealedClassWithGenericInterface<T : TestInterface> : TestGenericInterface<T> {
    object FirstObject : TwoObjectSealedClassWithGenericInterface<TestInterface>()
    object SecondObject : TwoObjectSealedClassWithGenericInterface<TestInterface>()

    @GenSealedEnum(generateEnum = true)
    companion object
}

sealed class SealedClassWithGetterInterface : TestGetterInterface {
    object FirstObject : SealedClassWithGetterInterface() {
        var hasGetBeenCalled = false

        override fun get(): String {
            hasGetBeenCalled = true
            return "First"
        }
    }

    @GenSealedEnum(generateEnum = true)
    companion object
}

class SealedEnumWithInterfacesTests {

    @Test
    fun `empty sealed enum implements interface`() {
        assertTrue(
            EmptySealedClassWithInterfaceSealedEnum::class.createType().isSubtypeOf(
                SealedEnum::class.createType(
                    arguments = listOf(
                        KTypeProjection.invariant(EmptySealedClassWithInterface::class.createType())
                    )
                )
            )
        )

        // Check for compilation
        val emptyValues: List<TestInterface> = EmptySealedClassWithInterface.values

        assertEquals(
            emptyList<TestInterface>(),
            emptyValues
        )
    }

    @Test
    fun `empty enum implements interface`() {
        assertTrue(EmptySealedClassWithInterfaceEnum::class.isSubclassOf(TestInterface::class))

        // Check for compilation
        val emptyValues: Array<out TestInterface> = EmptySealedClassWithInterfaceEnum.values()

        assertEquals(
            emptyList<TestInterface>(),
            emptyValues.toList()
        )
    }

    @Test
    fun `single object sealed enum implements interface`() {
        assertTrue(
            OneObjectSealedClassWithInterfaceSealedEnum::class.createType().isSubtypeOf(
                SealedEnum::class.createType(
                    arguments = listOf(
                        KTypeProjection.invariant(OneObjectSealedClassWithInterface::class.createType())
                    )
                )
            )
        )

        // Check for compilation
        val oneObjectValues: List<TestInterface> = OneObjectSealedClassWithInterface.values

        assertEquals(
            listOf(OneObjectSealedClassWithInterface.FirstObject),
            oneObjectValues
        )
    }

    @Test
    fun `single object enum implements interface`() {
        assertTrue(OneObjectSealedClassWithInterfaceEnum::class.isSubclassOf(TestInterface::class))

        val oneObjectValues: Array<out TestInterface> = OneObjectSealedClassWithInterfaceEnum.values()

        assertEquals(
            listOf(OneObjectSealedClassWithInterfaceEnum.OneObjectSealedClassWithInterface_FirstObject),
            oneObjectValues.toList()
        )
    }

    @Test
    fun `two object sealed enum implements generic interface`() {
        assertTrue(
            TwoObjectSealedClassWithGenericInterfaceSealedEnum::class.createType().isSubtypeOf(
                SealedEnum::class.createType(
                    arguments = listOf(
                        KTypeProjection.invariant(
                            TwoObjectSealedClassWithGenericInterface::class.createType(
                                arguments = listOf(
                                    KTypeProjection.invariant(TestInterface::class.createType())
                                )
                            )
                        )
                    )
                )
            )
        )

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
    fun `two object enum implements generic interface`() {
        assertTrue(
            TwoObjectSealedClassWithGenericInterfaceEnum::class.createType().isSubtypeOf(
                TestGenericInterface::class.createType(
                    arguments = listOf(
                        KTypeProjection.invariant(TestInterface::class.createType())
                    )
                )
            )
        )

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
    fun `enum delegates to sealed class`() {
        val sealedObject = SealedClassWithGetterInterface.FirstObject

        val enumValue = SealedClassWithGetterInterfaceEnum.SealedClassWithGetterInterface_FirstObject

        assertFalse(sealedObject.hasGetBeenCalled)

        assertEquals("First", enumValue.get())

        assertTrue(sealedObject.hasGetBeenCalled)
    }
}
