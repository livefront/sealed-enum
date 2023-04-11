package com.livefront.sealedenum.compilation.visibility

import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.testing.assertApprovedGeneratedFile
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getCommonSourceFile
import com.oneeyedmen.okeydoke.Approver
import com.oneeyedmen.okeydoke.junit5.ApprovalsExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.reflect.KVisibility

/**
 * Verifies that generated [SealedEnum] implementations, extension properties and methods have valid visibilities.
 */
@ExtendWith(ApprovalsExtension::class)
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
            assertEquals(InternalObjectsSealedClassEnum::class, InternalObjectsSealedClass.sealedEnum.enumClass)
        }

        @Test
        fun Approver.`compilation generates correct code`() {
            val result = compile(getCommonSourceFile("compilation", "visibility", "VisibilitySealedClass.kt"))

            assertCompiles(result)
            assertApprovedGeneratedFile("InternalObjectsSealedClass_SealedEnum.kt", result)
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
            assertEquals(InternalSealedClassEnum::class, InternalSealedClass.sealedEnum.enumClass)
        }

        @Test
        fun Approver.`compilation generates correct code`() {
            val result = compile(getCommonSourceFile("compilation", "visibility", "VisibilitySealedClass.kt"))

            assertCompiles(result)
            assertApprovedGeneratedFile("InternalSealedClass_SealedEnum.kt", result)
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
                InternalCompanionSealedClassEnum::class,
                InternalCompanionSealedClass.sealedEnum.enumClass
            )
        }

        @Test
        fun Approver.`compilation generates correct code`() {
            val result = compile(getCommonSourceFile("compilation", "visibility", "VisibilitySealedClass.kt"))

            assertCompiles(result)
            assertApprovedGeneratedFile("InternalCompanionSealedClass_SealedEnum.kt", result)
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
                InternalSealedAndCompanionSealedClassEnum::class,
                InternalSealedAndCompanionSealedClass.sealedEnum.enumClass
            )
        }

        @Test
        fun Approver.`compilation generates correct code`() {
            val result = compile(getCommonSourceFile("compilation", "visibility", "VisibilitySealedClass.kt"))

            assertCompiles(result)
            assertApprovedGeneratedFile("InternalSealedAndCompanionSealedClass_SealedEnum.kt", result)
        }
    }
}
