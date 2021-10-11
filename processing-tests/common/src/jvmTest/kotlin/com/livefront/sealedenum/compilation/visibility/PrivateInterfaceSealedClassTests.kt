package com.livefront.sealedenum.compilation.visibility

import com.livefront.sealedenum.testing.PlatformSourceType
import com.livefront.sealedenum.testing.SharableProcessingSourceType
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PrivateInterfaceSealedClassTests {

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
            PrivateInterfaceSealedClassEnum::class,
            PrivateInterfaceSealedClass.sealedEnum.enumClass
        )
    }

    @Test
    fun `compilation generates correct code`() {
        val result = compile(
            getSourceFile(
                SharableProcessingSourceType.COMMMON,
                PlatformSourceType.JVM,
                "compilation",
                "visibility",
                "PrivateInterfaceSealedClass.kt"
            )
        )

        assertCompiles(result)
        assertGeneratedFileMatches(
            "PrivateInterfaceSealedClass_SealedEnum.kt",
            privateInterfaceSealedClassGenerated,
            result
        )
    }
}
