package com.livefront.sealedenum.internal.spec

import com.livefront.sealedenum.SealedEnum
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import javax.lang.model.element.TypeElement

internal data class SealedEnumSealedEnumPropertySpec(
    private val sealedClass: SealedClass,
    private val sealedClassCompanionObject: ClassName,
    private val sealedClassCompanionObjectElement: TypeElement,
    private val sealedEnum: ClassName,
    private val enumPrefix: String
) {
    fun build(): PropertySpec {
        val propertySpecBuilder = PropertySpec.builder(pascalCaseToCamelCase(enumPrefix + "SealedEnum"), sealedEnum)
            .addOriginatingElement(sealedClassCompanionObjectElement)
            .addKdoc("Returns an implementation of [%T] for the sealed class [%T]", SealedEnum::class, sealedClass)
            .receiver(sealedClassCompanionObject)
            .getter(
                FunSpec.getterBuilder()
                    .addStatement("return %T", sealedEnum)
                    .build()
            )

        return propertySpecBuilder.build()
    }
}
