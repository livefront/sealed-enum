package com.livefront.sealedenum.compilation.usecases

import com.livefront.sealedenum.GenSealedEnum

interface Uri {
    val scheme: String
    val authority: String
    val path: String
}

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

    @GenSealedEnum(generateEnum = true)
    companion object
}
