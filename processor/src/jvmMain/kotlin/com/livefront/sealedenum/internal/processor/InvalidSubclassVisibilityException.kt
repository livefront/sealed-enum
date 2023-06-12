package com.livefront.sealedenum.internal.processor

import javax.lang.model.element.Element

/**
 * An exception that indicates that a sealed subclass has an invalid visibility.
 */
public class InvalidSubclassVisibilityException(public val element: Element) : Exception()
