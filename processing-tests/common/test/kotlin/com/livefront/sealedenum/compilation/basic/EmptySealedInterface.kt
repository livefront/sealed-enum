package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.GenSealedEnum

sealed interface EmptySealedInterface {
    @GenSealedEnum(generateEnum = true)
    companion object
}
