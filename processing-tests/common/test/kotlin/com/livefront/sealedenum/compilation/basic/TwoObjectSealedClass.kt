package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.GenSealedEnum

sealed class TwoObjectSealedClass {
    object FirstObject : TwoObjectSealedClass()

    object SecondObject : TwoObjectSealedClass()

    @GenSealedEnum(generateEnum = true)
    companion object
}
