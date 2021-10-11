package com.livefront.sealedenum.compilation.visibility

import com.livefront.sealedenum.testing.PlatformSourceType
import com.livefront.sealedenum.testing.SharableProcessingSourceType
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledIf
import kotlin.reflect.full.isSubclassOf

class ProtectedInterfaceSealedClassTests {

    @Test
    fun `sealed class has correct values`() {
        assertEquals(
            listOf(
                ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.FirstObject,
                ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.SecondObject
            ),
            ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.values
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
            ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.values.map(ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass::enum),
            enumValues<ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum>().toList()
        )
    }

    @Test
    fun `sealed class has correct enum class`() {
        assertEquals(
            ProtectedInterfaceOuterClass_ProtectedInterfaceSealedClassEnum::class,
            ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.sealedEnum.enumClass
        )
    }

    /**
     * TODO: In kotlin-compile-testing, the enum doesn't implement the interface, even though it does when run with ksp
     *       directly (see above)
     */
    @Test
    @DisabledIf("com.livefront.sealedenum.testing.ProcessingTypeGetter#isKSP")
    fun `compilation generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.JVM,
                "compilation",
                "visibility",
                "ProtectedInterfaceSealedClass.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass_SealedEnum.kt",
            protectedInterfaceSealedClassGenerated,
            result
        )
    }
}
