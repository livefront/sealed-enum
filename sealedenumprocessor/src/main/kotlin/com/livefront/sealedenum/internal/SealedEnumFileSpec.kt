package com.livefront.sealedenum.internal

import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.TreeTraversalOrder
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import javax.lang.model.element.TypeElement

/**
 * A builder for a file containing implementations of [SealedEnum] for the given [sealedClass].
 *
 * Given the [ClassName] for [sealedClass], the [TypeElement] for the [sealedClassElement], a list of [traversalOrders]
 * to create objects for, and the [sealedClassNode] representing the tree of objects for the [sealedClass], [create]
 * will return a [FileSpec] that contains implementations of [SealedEnum] for each of [traversalOrders].
 *
 * The generated file will be the name of the sealed classes, suffixed with "_SealedEnum".
 *
 * If the sealed class is generic, pass in the number of type parameters as [numberOfTypeParameters]. The resulting
 * [SealedEnum] object will be the star-projections of the sealed class's type.
 */
internal data class SealedEnumFileSpec(
    private val sealedClass: ClassName,
    private val sealedClassElement: TypeElement,
    private val traversalOrders: List<TreeTraversalOrder>,
    private val sealedClassNode: SealedClassNode.SealedClass,
    private val numberOfTypeParameters: Int
) {
    fun create(): FileSpec {
        val fileName = "${sealedClass.simpleNames.joinToString(".")}_SealedEnum"
        val fileSpecBuilder = FileSpec.builder(sealedClass.packageName, fileName).indent("    ")

        traversalOrders
            .map {
                SealedEnumTypeSpec(
                    sealedClass = sealedClass,
                    sealedClassElement = sealedClassElement,
                    sealedObjects = getSealedObjectsFromNode(it, sealedClassNode),
                    numberOfTypeParameters = numberOfTypeParameters,
                    enumPrefix = if (traversalOrders.size == 1) {
                        ""
                    } else {
                        when (it) {
                            TreeTraversalOrder.PRE_ORDER -> "PreOrder"
                            TreeTraversalOrder.IN_ORDER -> "InOrder"
                            TreeTraversalOrder.POST_ORDER -> "PostOrder"
                            TreeTraversalOrder.LEVEL_ORDER -> "LevelOrder"
                        }
                    }
                )
            }
            .map(SealedEnumTypeSpec::create)
            .forEach { fileSpecBuilder.addType(it) }

        return fileSpecBuilder.build()
    }
}
