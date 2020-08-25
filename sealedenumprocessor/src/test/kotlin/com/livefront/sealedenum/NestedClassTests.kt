package com.livefront.sealedenum

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

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

class NestedClassTests {

    @Test
    fun `inside one class`() {
        assertEquals(
            listOf(
                OuterClass.InsideOneClassSealedClass.FirstObject,
                OuterClass.InsideOneClassSealedClass.SecondObject
            ),
            OuterClass.InsideOneClassSealedClass.values
        )
    }

    @Test
    fun `inside two classes`() {
        assertEquals(
            listOf(
                FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass.FirstObject,
                FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass.SecondObject
            ),
            FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass.values
        )
    }
}
