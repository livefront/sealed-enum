package com.livefront.sealedenum.compilation.usecases

import com.livefront.sealedenum.GenSealedEnum

public interface Uri {
    public val scheme: String
    public val authority: String
    public val path: String
}

public sealed class Environments(
    override val scheme: String,
    override val authority: String,
    override val path: String
) : Uri {

    public sealed class Http(
        override val authority: String,
        override val path: String
    ) : Environments("http", authority, path) {
        public object Livefront : Http("www.livefront.com", "/")
        public object Google : Http("www.google.com", "/")
    }

    public sealed class Https(
        override val authority: String,
        override val path: String
    ) : Environments("https", authority, path) {
        public object Livefront : Https("www.livefront.com", "/")
        public object Google : Https("www.google.com", "/")
    }

    @GenSealedEnum(generateEnum = true)
    public companion object
}

public val environmentsGenerated: String = """
package com.livefront.sealedenum.compilation.usecases

import com.livefront.sealedenum.EnumForSealedEnumProvider
import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.SealedEnumWithEnumProvider
import kotlin.Int
import kotlin.LazyThreadSafetyMode
import kotlin.String
import kotlin.collections.List
import kotlin.reflect.KClass

/**
 * An isomorphic enum for the sealed class [Environments]
 */
public enum class EnvironmentsEnum(
    sealedObject: Environments,
) : Uri by sealedObject {
    Environments_Http_Livefront(com.livefront.sealedenum.compilation.usecases.Environments.Http.Livefront),
    Environments_Http_Google(com.livefront.sealedenum.compilation.usecases.Environments.Http.Google),
    Environments_Https_Livefront(com.livefront.sealedenum.compilation.usecases.Environments.Https.Livefront),
    Environments_Https_Google(com.livefront.sealedenum.compilation.usecases.Environments.Https.Google),
}

/**
 * The isomorphic [EnvironmentsEnum] for [this].
 */
public val Environments.`enum`: EnvironmentsEnum
    get() = EnvironmentsSealedEnum.sealedObjectToEnum(this)

/**
 * The isomorphic [Environments] for [this].
 */
public val EnvironmentsEnum.sealedObject: Environments
    get() = EnvironmentsSealedEnum.enumToSealedObject(this)

/**
 * An implementation of [SealedEnum] for the sealed class [Environments]
 */
public object EnvironmentsSealedEnum : SealedEnum<Environments>,
        SealedEnumWithEnumProvider<Environments, EnvironmentsEnum>,
        EnumForSealedEnumProvider<Environments, EnvironmentsEnum> {
    public override val values: List<Environments> by lazy(mode =
            LazyThreadSafetyMode.PUBLICATION) {
        listOf(
            Environments.Http.Livefront,
            Environments.Http.Google,
            Environments.Https.Livefront,
            Environments.Https.Google
        )
    }


    public override val enumClass: KClass<EnvironmentsEnum>
        get() = EnvironmentsEnum::class

    public override fun ordinalOf(obj: Environments): Int = when (obj) {
        is Environments.Http.Livefront -> 0
        is Environments.Http.Google -> 1
        is Environments.Https.Livefront -> 2
        is Environments.Https.Google -> 3
    }

    public override fun nameOf(obj: Environments): String = when (obj) {
        is Environments.Http.Livefront -> "Environments_Http_Livefront"
        is Environments.Http.Google -> "Environments_Http_Google"
        is Environments.Https.Livefront -> "Environments_Https_Livefront"
        is Environments.Https.Google -> "Environments_Https_Google"
    }

    public override fun valueOf(name: String): Environments = when (name) {
        "Environments_Http_Livefront" -> Environments.Http.Livefront
        "Environments_Http_Google" -> Environments.Http.Google
        "Environments_Https_Livefront" -> Environments.Https.Livefront
        "Environments_Https_Google" -> Environments.Https.Google
        else -> throw IllegalArgumentException(""${'"'}No sealed enum constant ${'$'}name""${'"'})
    }

    public override fun sealedObjectToEnum(obj: Environments): EnvironmentsEnum = when (obj) {
        is Environments.Http.Livefront -> EnvironmentsEnum.Environments_Http_Livefront
        is Environments.Http.Google -> EnvironmentsEnum.Environments_Http_Google
        is Environments.Https.Livefront -> EnvironmentsEnum.Environments_Https_Livefront
        is Environments.Https.Google -> EnvironmentsEnum.Environments_Https_Google
    }

    public override fun enumToSealedObject(`enum`: EnvironmentsEnum): Environments = when (enum) {
        EnvironmentsEnum.Environments_Http_Livefront -> Environments.Http.Livefront
        EnvironmentsEnum.Environments_Http_Google -> Environments.Http.Google
        EnvironmentsEnum.Environments_Https_Livefront -> Environments.Https.Livefront
        EnvironmentsEnum.Environments_Https_Google -> Environments.Https.Google
    }
}

/**
 * The index of [this] in the values list.
 */
public val Environments.ordinal: Int
    get() = EnvironmentsSealedEnum.ordinalOf(this)

/**
 * The name of [this] for use with valueOf.
 */
public val Environments.name: String
    get() = EnvironmentsSealedEnum.nameOf(this)

/**
 * A list of all [Environments] objects.
 */
public val Environments.Companion.values: List<Environments>
    get() = EnvironmentsSealedEnum.values

/**
 * Returns an implementation of [SealedEnum] for the sealed class [Environments]
 */
public val Environments.Companion.sealedEnum: EnvironmentsSealedEnum
    get() = EnvironmentsSealedEnum

/**
 * Returns the [Environments] object for the given [name].
 *
 * If the given name doesn't correspond to any [Environments], an [IllegalArgumentException] will be
 * thrown.
 */
public fun Environments.Companion.valueOf(name: String): Environments =
        EnvironmentsSealedEnum.valueOf(name)

""".trimIndent()
