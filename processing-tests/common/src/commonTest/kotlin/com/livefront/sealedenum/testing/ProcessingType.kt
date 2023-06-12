package com.livefront.sealedenum.testing

import kotlin.jvm.JvmStatic

enum class ProcessingType {
    AnnotationProcessing, KotlinSymbolProcessing;
    companion object
}

object ProcessingTypeGetter {
    @JvmStatic
    fun isKSP(): Boolean = ProcessingType.currentType == ProcessingType.KotlinSymbolProcessing
}
