package com.livefront.sealedenum.internal

import com.livefront.sealedenum.SealedEnum
import com.livefront.sealedenum.TreeTraversalOrder
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.TypeName
import javax.lang.model.element.TypeElement

/**
 * A builder for a file containing implementations of [SealedEnum] for the given [sealedClass].
 *
 * Given the [ClassName] for [sealedClass], the [TypeElement] for the [sealedClassElement], and the [sealedClassNode]
 * representing the tree of objects for the [sealedClass], [build] will create a [FileSpec] with generated classes
 * given the [sealedEnumOptions].
 *
 * The generated file will be the name of the sealed class, suffixed with "_SealedEnum".
 *
 * If the sealed class is generic, pass in the the sanitized type parameters as [typeParameters]. The resulting
 * [SealedEnum] object use these specifications to parameterize the [sealedClass].
 */
internal data class SealedEnumFileSpec(
    private val sealedClass: ClassName,
    private val sealedClassElement: TypeElement,
    private val sealedClassNode: SealedClassNode.SealedClass,
    private val typeParameters: List<TypeName>,
    private val sealedEnumOptions: Map<TreeTraversalOrder, SealedEnumOption>
) {
    fun build(): FileSpec {
        val fileName = "${sealedClass.simpleNames.joinToString(".")}_SealedEnum"
        val fileSpecBuilder = FileSpec.builder(sealedClass.packageName, fileName).indent("    ")

        val parameterizedSealedClass = if (typeParameters.isEmpty()) {
            sealedClass
        } else {
            sealedClass.parameterizedBy(typeParameters)
        }

        sealedEnumOptions.forEach { (treeTraversalOrder, sealedEnumOption) ->
            val sealedObjects = getSealedObjectsFromNode(treeTraversalOrder, sealedClassNode)

            val enumPrefix = if (sealedEnumOptions.size == 1) {
                ""
            } else {
                when (treeTraversalOrder) {
                    TreeTraversalOrder.PRE_ORDER -> "PreOrder"
                    TreeTraversalOrder.IN_ORDER -> "InOrder"
                    TreeTraversalOrder.POST_ORDER -> "PostOrder"
                    TreeTraversalOrder.LEVEL_ORDER -> "LevelOrder"
                }
            }

            val sealedEnumTypeSpecBuilder = SealedEnumTypeSpec(
                sealedClass = sealedClass,
                parameterizedSealedClass = parameterizedSealedClass,
                sealedClassElement = sealedClassElement,
                sealedObjects = sealedObjects,
                enumPrefix = enumPrefix
            )

            when (sealedEnumOption) {
                is SealedEnumOption.SealedEnumOnly -> Unit
                is SealedEnumOption.SealedEnumWithEnum -> {
                    val enumForSealedEnumTypeSpec = EnumForSealedEnumTypeSpec(
                        sealedClass = sealedClass,
                        parameterizedSealedClass = parameterizedSealedClass,
                        sealedClassElement = sealedClassElement,
                        sealedObjects = sealedObjects,
                        enumPrefix = enumPrefix,
                        sealedClassInterfaces = sealedEnumOption.sealedClassInterfaces
                    )
                        .build()

                    fileSpecBuilder.addType(enumForSealedEnumTypeSpec)

                    sealedEnumTypeSpecBuilder.addEnumForSealedEnumProvider(
                        ClassName(sealedClass.packageName, enumForSealedEnumTypeSpec.name!!)
                    )
                }
            }

            fileSpecBuilder.addType(sealedEnumTypeSpecBuilder.build())
        }

        return fileSpecBuilder.build()
    }

    /**
     * The options for generating classes for a [TreeTraversalOrder].
     */
    sealed class SealedEnumOption {
        /**
         * Generate the [SealedEnum] only.
         */
        object SealedEnumOnly : SealedEnumOption()

        /**
         * Generate the [SealedEnum] and a isomorphic enum class.
         */
        data class SealedEnumWithEnum(
            val sealedClassInterfaces: List<TypeName>
        ) : SealedEnumOption()
    }
}
