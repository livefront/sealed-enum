package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.GenSealedEnum

sealed class OneObjectSealedClass {
    object FirstObject : OneObjectSealedClass()

    @GenSealedEnum(generateEnum = true)
    companion object
}
