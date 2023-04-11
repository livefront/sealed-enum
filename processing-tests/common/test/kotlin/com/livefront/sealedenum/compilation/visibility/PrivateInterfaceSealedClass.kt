package com.livefront.sealedenum.compilation.visibility

import com.livefront.sealedenum.GenSealedEnum

sealed class PrivateInterfaceSealedClass :
    JavaPrivateInterfaceSubclass(JavaPrivateInterfaceOuterClass()), PrivateInterface {

    object FirstObject : PrivateInterfaceSealedClass()

    object SecondObject : PrivateInterfaceSealedClass()

    @GenSealedEnum(generateEnum = true)
    companion object
}

private interface PrivateInterface
