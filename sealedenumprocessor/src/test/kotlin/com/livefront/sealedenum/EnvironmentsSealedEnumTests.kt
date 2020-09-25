package com.livefront.sealedenum

import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.assertGeneratedFileMatches
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EnvironmentManager<T>(
    enumClass: Class<T>,
    defaultEnvironment: T
) where T : Enum<T>, T : Uri {
    val environments = enumClass.enumConstants.map {
        "${it.scheme}://${it.authority}${it.path}"
    }

    var currentEnvironment = defaultEnvironment
}

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
            enumClass = Environments.sealedEnum.enumClass,
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
    fun `compilation generates correct code`() {
        val result = compile(getSourceFile("EnvironmentsSealedEnum.kt"))

        assertCompiles(result)
        assertGeneratedFileMatches("Environments_SealedEnum.kt", environmentsGenerated, result)
    }
}
