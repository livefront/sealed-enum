package com.livefront.sealedenum.internal

import com.livefront.sealedenum.internal.SealedClassNode.Object
import com.livefront.sealedenum.internal.SealedClassNode.SealedClass

/**
 * An internal tree structure representing a sealed class and its sealed subclasses.
 *
 * A node can either be a [Object] (a leaf node) or a [SealedClass] (which might be a non-leaf node).
 */
internal sealed class SealedClassNode {

    /**
     * The leaf node of the sealed class tree, which represents an object subclass.
     *
     * The object's name is [className]
     */
    internal data class Object(val className: SealedObject) : SealedClassNode()

    /**
     * A node representing a subclass sealed class. This node may have additional children in [sealedSubclasses],
     * or could be a leaf itself if [sealedSubclasses] is empty.
     */
    internal data class SealedClass(val sealedSubclasses: List<SealedClassNode>) : SealedClassNode()
}
