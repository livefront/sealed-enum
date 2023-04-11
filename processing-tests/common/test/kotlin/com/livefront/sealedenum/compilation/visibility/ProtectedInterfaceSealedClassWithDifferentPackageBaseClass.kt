@file:Suppress("MatchingDeclarationName", "Filename")

package com.livefront.sealedenum.compilation.visibility

import com.livefront.sealedenum.GenSealedEnum

open class ProtectedInterfaceOuterClassWithDifferentPackageBaseClass {

    protected interface ProtectedInterface

    sealed class ProtectedInterfaceSealedClass :
        com.livefront.sealedenum.compilation.visibility.subpackage.JavaProtectedInterfaceSubclass(),
        ProtectedInterface {

        object FirstObject : ProtectedInterfaceSealedClass()

        object SecondObject : ProtectedInterfaceSealedClass()

        @GenSealedEnum(generateEnum = true)
        companion object
    }
}
