package com.livefront.sealedenum.internal

import com.squareup.kotlinpoet.KModifier

/**
 * A restricted enumeration of visibilities for use with sealed classes. Due to generating code that is in a different
 * file, we can only reasonably work with sealed classes and companion objects that are public or internal.
 */
internal enum class Visibility(val kModifier: KModifier) {
    PUBLIC(KModifier.PUBLIC),
    INTERNAL(KModifier.INTERNAL);
}
