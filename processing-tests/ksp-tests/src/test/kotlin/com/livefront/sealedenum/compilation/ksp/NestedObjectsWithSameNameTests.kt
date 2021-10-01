package com.livefront.sealedenum.compilation.ksp

import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

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
    fun `empty sealed class`() {
        assertEquals(
            emptyList<NestedObjectsWithSameName.Companion.EmptySealedClass>(),
            NestedObjectsWithSameName.Companion.EmptySealedClass.values
        )
    }

    @Test
    fun `empty enum for sealed class`() {
        assertEquals(
            NestedObjectsWithSameName.Companion.EmptySealedClass.values.map(
                NestedObjectsWithSameName.Companion.EmptySealedClass::enum
            ),
            enumValues<NestedObjectsWithSameName_Companion_EmptySealedClassEnum>().toList()
        )
    }

    @Test
    fun `correct enum class`() {
        assertEquals(
            NestedObjectsWithSameName_Companion_EmptySealedClassEnum::class.java,
            NestedObjectsWithSameName.Companion.EmptySealedClass.sealedEnum.enumClass
        )
    }

    @Test
    fun `compilation generates correct code`() {
        val result = compile(getSourceFile("compilation", "ksp", "NestedObjectsWithSameName.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches(
            "NestedObjectsWithSameName.Companion.EmptySealedClass_SealedEnum.kt",
            nestedObjectsWithSameNameEmptySealedClassGenerated,
            result
        )
    }
}
