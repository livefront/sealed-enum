package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.GenSealedEnum

class FirstClassHierarchy {

    sealed class A {

        sealed class B : A() {
            object C : B()

            @GenSealedEnum(generateEnum = true)
            companion object
        }

        @GenSealedEnum(generateEnum = true)
        companion object
    }
}

class SecondClassHierarchy {

    sealed class Z {

        object Y : Z()

        sealed class X : Z() {

            object W : X()

            object V : X()

            sealed class U : X() {
                object T : U()

                @GenSealedEnum
                companion object
            }

            sealed class S : X() {
                object R : S()

                @GenSealedEnum
                companion object
            }

            @GenSealedEnum
            companion object
        }

        sealed class Q : Z() {

            object P : Q()

            @GenSealedEnum
            companion object
        }

        object O : Z()

        @GenSealedEnum
        companion object
    }
}
