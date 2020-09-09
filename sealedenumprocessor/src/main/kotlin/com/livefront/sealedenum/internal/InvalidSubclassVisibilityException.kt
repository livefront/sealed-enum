package com.livefront.sealedenum.internal

import javax.lang.model.element.Element

/**
 * An exception that indicates that a sealed subclass has an invalid visibility.
 */
internal class InvalidSubclassVisibilityException(val element: Element) : Exception()
