package com.livefront.sealedenum.compilation.visibility

import com.livefront.sealedenum.SealedEnum
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Verifies that generated [SealedEnum] implementations, extension properties and methods have valid visibilities.
 */
class VisibilitySealedClassTests {
    @Test
    fun internal_objects_values_are_correct() {
        assertEquals(
            listOf(
                InternalObjectsSealedClass.FirstObject,
                InternalObjectsSealedClass.SecondObject,
                InternalObjectsSealedClass.InnerSealedClass.ThirdObject
            ),
            InternalObjectsSealedClass.values
        )
    }

    @Test
    fun internal_objects_enums_values_are_correct() {
        assertEquals(
            listOf(
                InternalObjectsSealedClassEnum.InternalObjectsSealedClass_FirstObject,
                InternalObjectsSealedClassEnum.InternalObjectsSealedClass_SecondObject,
                InternalObjectsSealedClassEnum.InternalObjectsSealedClass_InnerSealedClass_ThirdObject
            ),
            enumValues<InternalObjectsSealedClassEnum>().toList()
        )
    }

    @Test
    fun internal_objects_enums_values_are_correct_with_mapping() {
        assertEquals(
            InternalObjectsSealedClass.values.map(InternalObjectsSealedClass::enum),
            enumValues<InternalObjectsSealedClassEnum>().toList()
        )
    }

    @Test
    fun internal_objects_correct_enum_class() {
        assertEquals(InternalObjectsSealedClassEnum::class, InternalObjectsSealedClass.sealedEnum.enumClass)
    }

    @Test
    fun internal_sealed_class_values_are_correct() {
        assertEquals(
            listOf(InternalSealedClass.FirstObject, InternalSealedClass.SecondObject),
            InternalSealedClass.values
        )
    }

    @Test
    fun internal_seald_class_enums_values_are_correct() {
        assertEquals(
            listOf(
                InternalSealedClassEnum.InternalSealedClass_FirstObject,
                InternalSealedClassEnum.InternalSealedClass_SecondObject
            ),
            enumValues<InternalSealedClassEnum>().toList()
        )
    }

    @Test
    fun internal_sealed_class_enums_values_are_correct_with_mapping() {
        assertEquals(
            InternalSealedClass.values.map(InternalSealedClass::enum),
            enumValues<InternalSealedClassEnum>().toList()
        )
    }

    @Test
    fun internal_sealed_class_correct_enum_class() {
        assertEquals(InternalSealedClassEnum::class, InternalSealedClass.sealedEnum.enumClass)
    }

    @Test
    fun internal_companion_object_internal_values_are_correct() {
        assertEquals(
            listOf(InternalCompanionSealedClass.FirstObject, InternalCompanionSealedClass.SecondObject),
            InternalCompanionSealedClass.values
        )
    }

    @Test
    fun internal_companion_object_enums_values_are_correct() {
        assertEquals(
            listOf(
                InternalCompanionSealedClassEnum.InternalCompanionSealedClass_FirstObject,
                InternalCompanionSealedClassEnum.InternalCompanionSealedClass_SecondObject
            ),
            enumValues<InternalCompanionSealedClassEnum>().toList()
        )
    }

    @Test
    fun internal_companion_object_enums_values_are_correct_with_mapping() {
        assertEquals(
            InternalCompanionSealedClass.values.map(InternalCompanionSealedClass::enum),
            enumValues<InternalCompanionSealedClassEnum>().toList()
        )
    }

    @Test
    fun internal_companion_object_correct_enum_class() {
        assertEquals(
            InternalCompanionSealedClassEnum::class,
            InternalCompanionSealedClass.sealedEnum.enumClass
        )
    }

    @Test
    fun internal_sealed_class_and_companion_object_values_are_correct() {
        assertEquals(
            listOf(
                InternalSealedAndCompanionSealedClass.FirstObject,
                InternalSealedAndCompanionSealedClass.SecondObject
            ),
            InternalSealedAndCompanionSealedClass.values
        )
    }

    @Test
    fun internal_sealed_class_and_companion_object_enums_values_are_correct() {
        assertEquals(
            listOf(
                InternalSealedAndCompanionSealedClassEnum.InternalSealedAndCompanionSealedClass_FirstObject,
                InternalSealedAndCompanionSealedClassEnum.InternalSealedAndCompanionSealedClass_SecondObject
            ),
            enumValues<InternalSealedAndCompanionSealedClassEnum>().toList()
        )
    }

    @Test
    fun internal_sealed_class_and_companion_object_enums_values_are_correct_with_mapping() {
        assertEquals(
            InternalSealedAndCompanionSealedClass.values.map(InternalSealedAndCompanionSealedClass::enum),
            enumValues<InternalSealedAndCompanionSealedClassEnum>().toList()
        )
    }

    @Test
    fun internal_sealed_class_and_companion_object_correct_enum_class() {
        assertEquals(
            InternalSealedAndCompanionSealedClassEnum::class,
            InternalSealedAndCompanionSealedClass.sealedEnum.enumClass
        )
    }
}
