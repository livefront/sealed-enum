package com.livefront.sealedenum.compilation.visibility

import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.reflect.KVisibility

/**
 * Verifies that generated [SealedEnum] implementations, extension properties and methods have valid visibilities.
 */
class VisibilitySealedClassTests {

    @Nested
    inner class InternalObjects {

        @Test
        fun `sealed enum class has correct visibility`() {
            assertEquals(
                KVisibility.PUBLIC,
                InternalObjectsSealedClassSealedEnum::class.visibility
            )
        }

        @Test
        fun `internal objects have correct values`() {
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
        fun `enums values are correct`() {
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
        fun `enums values are correct with mapping`() {
            assertEquals(
                InternalObjectsSealedClass.values.map(InternalObjectsSealedClass::enum),
                enumValues<InternalObjectsSealedClassEnum>().toList()
            )
        }

        @Test
        fun `correct enum class`() {
            assertEquals(InternalObjectsSealedClassEnum::class.java, InternalObjectsSealedClass.sealedEnum.enumClass)
        }

        @Test
        fun `compilation generates correct code`() {
            val result = compile(getSourceFile("compilation", "visibility", "VisibilitySealedClass.kt"))

            assertCompiles(result)
            assertGeneratedFileMatches(
                "InternalObjectsSealedClass_SealedEnum.kt",
                internalObjectsSealedClassGenerated,
                result
            )
        }
    }

    @Nested
    inner class Internal {

        @Test
        fun `sealed enum class has correct visibility`() {
            assertEquals(
                KVisibility.INTERNAL,
                InternalSealedClassSealedEnum::class.visibility
            )
        }

        @Test
        fun `internal objects have correct values`() {
            assertEquals(
                listOf(InternalSealedClass.FirstObject, InternalSealedClass.SecondObject),
                InternalSealedClass.values
            )
        }

        @Test
        fun `enums values are correct`() {
            assertEquals(
                listOf(
                    InternalSealedClassEnum.InternalSealedClass_FirstObject,
                    InternalSealedClassEnum.InternalSealedClass_SecondObject
                ),
                enumValues<InternalSealedClassEnum>().toList()
            )
        }

        @Test
        fun `enums values are correct with mapping`() {
            assertEquals(
                InternalSealedClass.values.map(InternalSealedClass::enum),
                enumValues<InternalSealedClassEnum>().toList()
            )
        }

        @Test
        fun `correct enum class`() {
            assertEquals(InternalSealedClassEnum::class.java, InternalSealedClass.sealedEnum.enumClass)
        }

        @Test
        fun `compilation generates correct code`() {
            val result = compile(getSourceFile("compilation", "visibility", "VisibilitySealedClass.kt"))

            assertCompiles(result)
            assertGeneratedFileMatches("InternalSealedClass_SealedEnum.kt", internalSealedClassGenerated, result)
        }
    }

    @Nested
    inner class InternalCompanion {

        @Test
        fun `sealed enum class has correct visibility`() {
            assertEquals(
                KVisibility.PUBLIC,
                InternalCompanionSealedClassSealedEnum::class.visibility
            )
        }

        @Test
        fun `internal objects have correct values`() {
            assertEquals(
                listOf(InternalCompanionSealedClass.FirstObject, InternalCompanionSealedClass.SecondObject),
                InternalCompanionSealedClass.values
            )
        }

        @Test
        fun `enums values are correct`() {
            assertEquals(
                listOf(
                    InternalCompanionSealedClassEnum.InternalCompanionSealedClass_FirstObject,
                    InternalCompanionSealedClassEnum.InternalCompanionSealedClass_SecondObject
                ),
                enumValues<InternalCompanionSealedClassEnum>().toList()
            )
        }

        @Test
        fun `enums values are correct with mapping`() {
            assertEquals(
                InternalCompanionSealedClass.values.map(InternalCompanionSealedClass::enum),
                enumValues<InternalCompanionSealedClassEnum>().toList()
            )
        }

        @Test
        fun `correct enum class`() {
            assertEquals(
                InternalCompanionSealedClassEnum::class.java,
                InternalCompanionSealedClass.sealedEnum.enumClass
            )
        }

        @Test
        fun `compilation generates correct code`() {
            val result = compile(getSourceFile("compilation", "visibility", "VisibilitySealedClass.kt"))

            assertCompiles(result)
            assertGeneratedFileMatches(
                "InternalCompanionSealedClass_SealedEnum.kt",
                internalCompanionSealedClassGenerated,
                result
            )
        }
    }

    @Nested
    inner class InternalSealedAndCompanion {

        @Test
        fun `sealed enum class has correct visibility`() {
            assertEquals(
                KVisibility.INTERNAL,
                InternalSealedAndCompanionSealedClassSealedEnum::class.visibility
            )
        }

        @Test
        fun `internal objects have correct values`() {
            assertEquals(
                listOf(
                    InternalSealedAndCompanionSealedClass.FirstObject,
                    InternalSealedAndCompanionSealedClass.SecondObject
                ),
                InternalSealedAndCompanionSealedClass.values
            )
        }

        @Test
        fun `enums values are correct`() {
            assertEquals(
                listOf(
                    InternalSealedAndCompanionSealedClassEnum.InternalSealedAndCompanionSealedClass_FirstObject,
                    InternalSealedAndCompanionSealedClassEnum.InternalSealedAndCompanionSealedClass_SecondObject
                ),
                enumValues<InternalSealedAndCompanionSealedClassEnum>().toList()
            )
        }

        @Test
        fun `enums values are correct with mapping`() {
            assertEquals(
                InternalSealedAndCompanionSealedClass.values.map(InternalSealedAndCompanionSealedClass::enum),
                enumValues<InternalSealedAndCompanionSealedClassEnum>().toList()
            )
        }

        @Test
        fun `correct enum class`() {
            assertEquals(
                InternalSealedAndCompanionSealedClassEnum::class.java,
                InternalSealedAndCompanionSealedClass.sealedEnum.enumClass
            )
        }

        @Test
        fun `compilation generates correct code`() {
            val result = compile(getSourceFile("compilation", "visibility", "VisibilitySealedClass.kt"))

            assertCompiles(result)
            assertGeneratedFileMatches(
                "InternalSealedAndCompanionSealedClass_SealedEnum.kt",
                internalSealedAndCompanionSealedClassGenerated,
                result
            )
        }
    }
}
