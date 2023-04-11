package com.livefront.sealedenum.compilation.visibility

import com.livefront.sealedenum.testing.assertApprovedGeneratedFile
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getCommonSourceFile
import com.oneeyedmen.okeydoke.Approver
import com.oneeyedmen.okeydoke.junit5.ApprovalsExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(ApprovalsExtension::class)
class ProtectedInterfaceSealedClassWithDifferentPackageBaseClassTests {

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
            ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.values.map(
                ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass::enum
            ),
            enumValues<ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum>().toList()
        )
    }

    @Test
    fun `sealed class has correct enum class`() {
        assertEquals(
            ProtectedInterfaceOuterClassWithDifferentPackageBaseClass_ProtectedInterfaceSealedClassEnum::class,
            ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass.sealedEnum.enumClass
        )
    }

    @Test
    fun Approver.`compilation generates correct code`() {
        val result = compile(
            getCommonSourceFile(
                "compilation",
                "visibility",
                "ProtectedInterfaceSealedClassWithDifferentPackageBaseClass.kt"
            )
        )

        assertCompiles(result)
        assertApprovedGeneratedFile(
            "ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass_SealedEnum.kt",
            result
        )
    }
}
