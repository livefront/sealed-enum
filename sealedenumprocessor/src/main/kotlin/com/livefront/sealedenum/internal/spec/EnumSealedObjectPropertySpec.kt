package com.livefront.sealedenum.internal.spec

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName
import javax.lang.model.element.TypeElement

internal data class EnumSealedObjectPropertySpec(
    private val sealedClass: SealedClass,
    private val parameterizedSealedClass: TypeName,
    private val sealedClassCompanionObjectElement: TypeElement,
    private val sealedEnum: ClassName,
    private val enumForSealedEnum: ClassName
) {
    fun build(): PropertySpec {
        val propertySpecBuilder = PropertySpec.builder("sealedObject", parameterizedSealedClass)
            .addOriginatingElement(sealedClassCompanionObjectElement)
            .addKdoc("The isomorphic [%T] for [this].", sealedClass)
            .receiver(enumForSealedEnum)
            .getter(
                FunSpec.getterBuilder()
                    .addStatement("return %T.enumToSealedObject(this)", sealedEnum)
                    .build()
            )

        return propertySpecBuilder.build()
    }
}
