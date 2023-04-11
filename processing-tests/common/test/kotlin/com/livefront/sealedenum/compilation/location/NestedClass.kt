package com.livefront.sealedenum.compilation.location

import com.livefront.sealedenum.GenSealedEnum

class OuterClass {
    sealed class InsideOneClassSealedClass {
        object FirstObject : InsideOneClassSealedClass()

        object SecondObject : InsideOneClassSealedClass()

        @GenSealedEnum
        companion object
    }
}

class FirstOuterClass {
    class SecondOuterClass {
        sealed class InsideTwoClassesSealedClass {
            object FirstObject : InsideTwoClassesSealedClass()

            object SecondObject : InsideTwoClassesSealedClass()

            @GenSealedEnum
            companion object
        }
    }
}
