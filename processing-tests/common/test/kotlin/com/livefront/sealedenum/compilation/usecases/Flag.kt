package com.livefront.sealedenum.compilation.usecases

import com.livefront.sealedenum.GenSealedEnum

sealed class Flag {
    val i: Int = 1 shl ordinal
    object FirstFlag : Flag()

    object SecondFlag : Flag()

    @GenSealedEnum(generateEnum = true)
    companion object
}
