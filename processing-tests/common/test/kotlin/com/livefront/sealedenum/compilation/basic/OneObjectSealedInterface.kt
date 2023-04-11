package com.livefront.sealedenum.compilation.basic

import com.livefront.sealedenum.GenSealedEnum

sealed interface OneObjectSealedInterface {
    object FirstObject : OneObjectSealedInterface

    @GenSealedEnum(generateEnum = true)
    companion object
}
