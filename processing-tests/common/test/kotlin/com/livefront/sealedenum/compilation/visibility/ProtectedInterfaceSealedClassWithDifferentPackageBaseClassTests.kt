package com.livefront.sealedenum.compilation.visibility

import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledIf

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

    /**
     * TODO: kotlin-compile-testing seems to only allow KSP to view Java files with an Origin of CLASS, which causes
     *       issues when trying to look at the PROTECTED modifier. Per above, in real use this usage still compiles.
     */
    @Test
    @DisabledIf("com.livefront.sealedenum.testing.ProcessingTypeGetter#isKSP")
    fun `compilation generates correct code`() {
        val result = compile(
            getSourceFile("compilation", "visibility", "ProtectedInterfaceSealedClassWithDifferentPackageBaseClass.kt")
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "ProtectedInterfaceOuterClassWithDifferentPackageBaseClass.ProtectedInterfaceSealedClass_SealedEnum.kt",
            protectedInterfaceSealedClassWithDifferentPackageBaseClassGenerated,
            result
        )
    }
}
