package com.livefront.sealedenum.internal

import com.livefront.sealedenum.SealedEnum
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.STAR
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.buildCodeBlock
import javax.lang.model.element.TypeElement

/**
 * A builder for a [SealedEnum] object for the given [sealedClass].
 *
 * Given the [ClassName] of the [sealedClass], the [TypeElement] for the [sealedClassElement], and the
 * list of [ClassName]s for the sealed subclasses, [create] will generate a [TypeSpec] for an implementation of
 * [SealedEnum].
 *
 * If the sealed class is generic, pass in the number of type parameters as [numberOfTypeParameters]. The resulting
 * [SealedEnum] object will be the star-projections of the sealed class's type.
 *
 * The name of the created object will be the name of the sealed class concatenated with [enumPrefix] concatenated with
 * "SealedEnum".
 */
internal data class SealedEnumTypeSpec(
    val sealedClass: ClassName,
    val sealedClassElement: TypeElement,
    val sealedObjects: List<ClassName>,
    val numberOfTypeParameters: Int,
    val enumPrefix: String
) {

    private val parameterizedSealedClass = if (numberOfTypeParameters > 0) {
        sealedClass.parameterizedBy((1..numberOfTypeParameters).map { STAR })
    } else {
        sealedClass
    }
    private val listOfSealedClass = List::class.asClassName().parameterizedBy(parameterizedSealedClass)
    private val sealedEnum = SealedEnum::class.asClassName()
    private val parameterizedSealedClassEnum = sealedEnum.parameterizedBy(parameterizedSealedClass)

    fun create(): TypeSpec {
        return TypeSpec.objectBuilder("${sealedClass.simpleNames.joinToString("_")}${enumPrefix}SealedEnum")
            .addSuperinterface(parameterizedSealedClassEnum)
            .addOriginatingElement(sealedClassElement)
            .addKdoc("An implementation of [$sealedEnum] for the sealed class [$sealedClass]")
            .addProperty(createObjectsProperty())
            .addFunction(createOrdinalOfFunction())
            .build()
    }

    private fun createObjectsProperty(): PropertySpec = PropertySpec.builder("values", listOfSealedClass)
        .addModifiers(KModifier.OVERRIDE)
        .initializer(
            buildCodeBlock {
                if (sealedObjects.isEmpty()) {
                    addStatement("emptyList()")
                } else {
                    addStatement("listOf(")
                    indent()
                    sealedObjects.dropLast(1).forEach {
                        addStatement("%T,", it)
                    }
                    addStatement("%T", sealedObjects.last())
                    unindent()
                    addStatement(")")
                }
            }
        )
        .build()

    private fun createOrdinalOfFunction(): FunSpec = FunSpec.builder("ordinalOf")
        .addParameter("obj", parameterizedSealedClass)
        .addModifiers(KModifier.OVERRIDE)
        .returns(Int::class)
        .apply {
            if (sealedObjects.isEmpty()) {
                addStatement(
                    "throw AssertionError(%S)",
                    "Constructing a ${sealedClass.simpleName} is impossible, since it has no sealed subclasses"
                )
            } else {
                beginControlFlow("return when (obj)")
                sealedObjects.forEachIndexed { index, obj ->
                    addStatement("%T -> $index", obj)
                }
                endControlFlow()
            }
        }
        .build()
}
