package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.GenSealedEnum

sealed class EmptySealedClass {
    @GenSealedEnum(generateEnum = true)
    companion object
}
