package com.livefront.sealedenum.testing

import com.livefront.sealedenum.GenSealedEnum
import kotlin.jvm.JvmStatic

sealed class ProcessingType {

    object AnnotationProcessing : ProcessingType()

    object KotlinSymbolProcessing : ProcessingType()

    @GenSealedEnum(generateEnum = true)
    companion object
}

object ProcessingTypeGetter {
    @JvmStatic
    fun isKSP(): Boolean = ProcessingType.currentType is ProcessingType.KotlinSymbolProcessing
}
