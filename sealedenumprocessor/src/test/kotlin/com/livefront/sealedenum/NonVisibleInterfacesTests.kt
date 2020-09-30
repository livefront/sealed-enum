package com.livefront.sealedenum

import com.livefront.sealedenum.ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import com.tschuchort.compiletesting.SourceFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.nio.file.Paths
import kotlin.reflect.full.isSubclassOf

class NonVisibleInterfacesTests {

    @Nested
    inner class PrivateInterfaceSealedClassTests {

        @Test
        fun `sealed class has correct values`() {
            assertEquals(
                listOf(
                    PrivateInterfaceSealedClass.FirstObject,
                    PrivateInterfaceSealedClass.SecondObject
                ),
                PrivateInterfaceSealedClass.values
            )
        }

        @Test
        fun `sealed class has correct enum values`() {
            assertEquals(
                listOf(
                    PrivateInterfaceSealedClassEnum.PrivateInterfaceSealedClass_FirstObject,
                    PrivateInterfaceSealedClassEnum.PrivateInterfaceSealedClass_SecondObject
                ),
                enumValues<PrivateInterfaceSealedClassEnum>().toList()
            )
        }

        @Test
        fun `sealed class has correct enum values with mapping`() {
            assertEquals(
                PrivateInterfaceSealedClass.values.map(PrivateInterfaceSealedClass::enum),
                enumValues<PrivateInterfaceSealedClassEnum>().toList()
            )
        }

        @Test
        fun `sealed class has correct enum class`() {
            assertEquals(
                PrivateInterfaceSealedClassEnum::class.java,
                PrivateInterfaceSealedClass.sealedEnum.enumClass
            )
        }

        @Test
        fun `compilation generates correct code`() {
            val result = compile(
                getSourceFile("NonVisibleInterfaces.kt")
            )

            assertCompiles(result)
            assertGeneratedFileMatches(
                "PrivateInterfaceSealedClass_SealedEnum.kt",
                privateInterfaceSealedClassGenerated,
                result
            )
        }
    }

    @Nested
    inner class ProtectedInterfaceSealedClassTests {

        @Test
        fun `sealed class has correct values`() {
            assertEquals(
                listOf(
                    ProtectedInterfaceSealedClass.FirstObject,
                    ProtectedInterfaceSealedClass.SecondObject
                ),
                ProtectedInterfaceSealedClass.values
            )
        }

        @Test
        fun `sealed class implements interface`() {
            assertTrue(
                ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum::class.isSubclassOf(
                    JavaProtectedInterfaceBaseClass.ProtectedInterface::class
                )
            )
        }

        @Test
        fun `sealed class has correct enum values`() {
            assertEquals(
                listOf(
                    ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum.ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClass_FirstObject,
                    ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum.ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClass_SecondObject
                ),
                enumValues<ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum>().toList()
            )
        }

        @Test
        fun `sealed class has correct enum values with mapping`() {
            assertEquals(
                ProtectedInterfaceSealedClass.values.map(ProtectedInterfaceSealedClass::enum),
                enumValues<ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum>().toList()
            )
        }

        @Test
        fun `sealed class has correct enum class`() {
            assertEquals(
                ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum::class.java,
                ProtectedInterfaceSealedClass.sealedEnum.enumClass
            )
        }

        @Test
        fun `compilation generates correct code`() {
            val result = compile(
                getSourceFile("NonVisibleInterfaces.kt")
            )

            assertCompiles(result)
            assertGeneratedFileMatches(
                "ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass_SealedEnum.kt",
                protectedInterfaceSealedClassGenerated,
                result
            )
        }
    }

    @Nested
    inner class ProtectedInterfaceSealedClassWithDifferentPackageBaseClassTests {

        @Test
        fun `sealed class has correct values`() {
            assertEquals(
                listOf(
                    ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.FirstObject,
                    ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.SecondObject
                ),
                ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.values
            )
        }

        @Test
        fun `sealed class has correct enum values`() {
            assertEquals(
                listOf(
                    ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum.ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClass_FirstObject,
                    ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum.ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClass_SecondObject
                ),
                enumValues<ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum>().toList()
            )
        }

        @Test
        fun `sealed class has correct enum values with mapping`() {
            assertEquals(
                ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.values.map(ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass::enum),
                enumValues<ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum>().toList()
            )
        }

        @Test
        fun `sealed class has correct enum class`() {
            assertEquals(
                ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum::class.java,
                ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.sealedEnum.enumClass
            )
        }

        @Test
        fun `compilation generates correct code`() {
            val result = compile(
                getSourceFile("NonVisibleInterfaces.kt")
            )

            assertCompiles(result)
            assertGeneratedFileMatches(
                "ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass_SealedEnum.kt",
                protectedInterfaceSealedClassWithDifferentPackageBaseClassGenerated,
                result
            )
        }
    }
}
