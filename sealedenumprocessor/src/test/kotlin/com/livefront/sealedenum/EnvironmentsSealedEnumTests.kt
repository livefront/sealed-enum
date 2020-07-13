package com.livefront.sealedenum

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

interface Uri {
    val scheme: String
    val authority: String
    val path: String
}

@GenSealedEnum(generateEnum = true)
sealed class Environments(
    override val scheme: String,
    override val authority: String,
    override val path: String
) : Uri {

    sealed class Http(
        override val authority: String,
        override val path: String
    ) : Environments("http", authority, path) {
        object Livefront : Http("www.livefront.com", "/")
        object Google : Http("www.google.com", "/")
    }

    sealed class Https(
        override val authority: String,
        override val path: String
    ) : Environments("https", authority, path) {
        object Livefront : Https("www.livefront.com", "/")
        object Google : Https("www.google.com", "/")
    }
}

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
            enumClass = EnvironmentsSealedEnum.enumClass,
            defaultEnvironment = EnvironmentsSealedEnum.sealedObjectToEnum(Environments.Https.Livefront)
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
            EnvironmentsSealedEnum.enumToSealedObject(environmentManager.currentEnvironment)
        )
    }
}
