package com.livefront.sealedenum.compilation.usecases

import com.livefront.sealedenum.testing.assertApprovedGeneratedFile
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getCommonSourceFile
import com.oneeyedmen.okeydoke.Approver
import com.oneeyedmen.okeydoke.junit5.ApprovalsExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

class EnvironmentManager<T>(
    enumClass: Class<T>,
    defaultEnvironment: T
) where T : Enum<T>, T : Uri {
    val environments = enumClass.enumConstants.map {
        "${it.scheme}://${it.authority}${it.path}"
    }

    var currentEnvironment = defaultEnvironment
}

@ExtendWith(ApprovalsExtension::class)
class EnvironmentsSealedEnumTests {
    @Test
    fun `environment manager from direct enum`() {
        val environmentManager = EnvironmentManager(
            enumClass = EnvironmentsEnum::class.java,
            defaultEnvironment = EnvironmentsEnum.Environments_Https_Livefront
        )

        assertEquals(
            listOf(
                "http://www.livefront.com/",
                "http://www.google.com/",
                "https://www.livefront.com/",
                "https://www.google.com/"
            ),
            environmentManager.environments
        )

        assertEquals(
            EnvironmentsEnum.Environments_Https_Livefront,
            environmentManager.currentEnvironment
        )
    }

    @Test
    fun `environment manager from sealed enum`() {
        val environmentManager = EnvironmentManager(
            enumClass = Environments.sealedEnum.enumClass.java,
            defaultEnvironment = Environments.Https.Livefront.enum
        )

        assertEquals(
            listOf(
                "http://www.livefront.com/",
                "http://www.google.com/",
                "https://www.livefront.com/",
                "https://www.google.com/"
            ),
            environmentManager.environments
        )

        assertEquals(
            Environments.Https.Livefront,
            environmentManager.currentEnvironment.sealedObject
        )
    }

    @Test
    fun Approver.`compilationgenerates correct code`() {
        val result = compile(getCommonSourceFile("compilation", "usecases", "EnvironmentsSealedEnum.kt"))

        assertCompiles(result)
        assertApprovedGeneratedFile("Environments_SealedEnum.kt", result)
    }
}
