package com.livefront.sealedenum.internal

import javax.lang.model.element.Element

/**
 * An exception that indicates that sealed class has a non-object subclass.
 */
internal class NonObjectSealedSubclassException(val element: Element) : Exception()
