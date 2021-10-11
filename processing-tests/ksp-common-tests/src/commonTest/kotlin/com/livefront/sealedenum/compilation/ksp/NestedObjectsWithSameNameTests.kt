package com.livefront.sealedenum.compilation.ksp

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Verifies that a sealed class in a companion object (or more precisely, an annotation that appears on a nested object
 * with the same name) still generates a sealed enum.
 *
 * Note that this only works with KSP, due to a limitation with KAPT.
 * Because the generated stubs for such an object wouldn't be valid Java, they are skipped, meaning that the annotation
 * wouldn't be processed.
 *
 * @see <a href="https://github.com/livefront/sealed-enum/issues/60">sealed-enum/issues/60</a>
 */
class NestedObjectsWithSameNameTests {
    @Test
    fun empty_sealed_class() {
        assertEquals(
            emptyList<NestedObjectsWithSameName.Companion.EmptySealedClass>(),
            NestedObjectsWithSameName.Companion.EmptySealedClass.values
        )
    }

    @Test
    fun empty_enum_for_sealed_class() {
        assertEquals(
            NestedObjectsWithSameName.Companion.EmptySealedClass.values.map(
                NestedObjectsWithSameName.Companion.EmptySealedClass::enum
            ),
            enumValues<NestedObjectsWithSameName_Companion_EmptySealedClassEnum>().toList()
        )
    }

    @Test
    fun correct_enum_class() {
        assertEquals(
            NestedObjectsWithSameName_Companion_EmptySealedClassEnum::class,
            NestedObjectsWithSameName.Companion.EmptySealedClass.sealedEnum.enumClass
        )
    }
}
