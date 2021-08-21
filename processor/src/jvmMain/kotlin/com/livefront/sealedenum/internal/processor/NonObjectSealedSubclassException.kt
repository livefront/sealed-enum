package com.livefront.sealedenum.internal.processor

import javax.lang.model.element.Element

/**
 * An exception that indicates that sealed class has a non-object subclass.
 */
public class NonObjectSealedSubclassException(public val element: Element) : Exception()
