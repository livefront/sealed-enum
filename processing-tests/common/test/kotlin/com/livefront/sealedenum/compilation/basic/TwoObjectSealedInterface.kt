package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.GenSealedEnum

sealed interface TwoObjectSealedInterface {
    object FirstObject : TwoObjectSealedInterface

    object SecondObject : TwoObjectSealedInterface

    @GenSealedEnum(generateEnum = true)
    companion object
}
