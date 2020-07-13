package com.livefront.sealedenum.internal

import com.livefront.sealedenum.GenSealedEnum
import com.livefront.sealedenum.TreeTraversalOrder

/**
 * A constructable version of [GenSealedEnum].
 *
 * Useful for providing a default value of [GenSealedEnum], since you can't construct one manually.
 */
internal data class SealedEnumSeed(
    val treeTraversalOrder: TreeTraversalOrder,
    val generateEnum: Boolean
)
