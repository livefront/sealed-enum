package com.livefront.sealedenum.compilation.visibility

import com.livefront.sealedenum.testing.assertApprovedGeneratedFile
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getCommonSourceFile
import com.oneeyedmen.okeydoke.Approver
import com.oneeyedmen.okeydoke.junit5.ApprovalsExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledIf
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.reflect.full.isSubclassOf

@ExtendWith(ApprovalsExtension::class)
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
            ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass.values.map(
                ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass::enum
            ),
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
    fun Approver.`compilation generates correct code`() {
        val result = compile(
            getCommonSourceFile("compilation", "visibility", "ProtectedInterfaceSealedClass.kt")
        )

        assertCompiles(result)
        assertApprovedGeneratedFile("ProtectedInterfaceOuterClass.ProtectedInterfaceSealedClass_SealedEnum.kt", result)
    }
}
