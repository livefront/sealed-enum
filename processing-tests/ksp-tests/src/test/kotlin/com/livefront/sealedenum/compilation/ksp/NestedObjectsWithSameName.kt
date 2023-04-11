package com.livefront.sealedenum.compilation.ksp

import com.livefront.sealedenum.GenSealedEnum

@Suppress("UtilityClassWithPublicConstructor")
class NestedObjectsWithSameName {
    companion object {
        sealed class EmptySealedClass {
            @GenSealedEnum(generateEnum = true)
            companion object
        }
    }
}
