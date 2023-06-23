package com.livefront.sealedenum.compilation.hierarchy

import com.livefront.sealedenum.GenSealedEnum

class FirstInterfaceHierarchy {

    sealed interface A {

        sealed interface B : A {
            object C : B

            @GenSealedEnum(generateEnum = true)
            companion object
        }

        @GenSealedEnum(generateEnum = true)
        companion object
    }
}

class SecondInterfaceHierarchy {

    sealed interface A {

        object B : A

        sealed interface C : A {

            object D : C

            object E : C

            sealed interface F : C {
                object G : F

                @GenSealedEnum
                companion object
            }

            sealed interface H : C {
                object I : H

                @GenSealedEnum
                companion object
            }

            @GenSealedEnum
            companion object
        }

        sealed interface J : A {

            object K : J

            @GenSealedEnum
            companion object
        }

        object L : A

        @GenSealedEnum
        companion object
    }
}
