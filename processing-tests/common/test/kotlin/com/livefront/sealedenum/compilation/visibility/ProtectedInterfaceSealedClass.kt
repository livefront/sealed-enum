@file:Suppress("MatchingDeclarationName", "Filename")

package com.livefront.sealedenum.compilation.visibility

import com.livefront.sealedenum.GenSealedEnum

open class ProtectedInterfaceOuterClass {

    protected interface ProtectedInterface

    sealed class ProtectedInterfaceSealedClass :
        com.livefront.sealedenum.compilation.visibility.JavaProtectedInterfaceSubclass(), ProtectedInterface {

        object FirstObject : ProtectedInterfaceSealedClass()

        object SecondObject : ProtectedInterfaceSealedClass()

        @GenSealedEnum(generateEnum = true)
        companion object
    }
}
