package com.livefront.sealedenum

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class OuterClass {
    @GenSealedEnum
    sealed class InsideOneClassSealedClass {
        object FirstObject : InsideOneClassSealedClass()

        object SecondObject : InsideOneClassSealedClass()
    }
}

class FirstOuterClass {
    class SecondOuterClass {
        @GenSealedEnum
        sealed class InsideTwoClassesSealedClass {
            object FirstObject : InsideTwoClassesSealedClass()

            object SecondObject : InsideTwoClassesSealedClass()
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
            OuterClass_InsideOneClassSealedClassSealedEnum.values
        )
    }

    @Test
    fun `inside two classes`() {
        assertEquals(
            listOf(
                FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass.FirstObject,
                FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass.SecondObject
            ),
            FirstOuterClass_SecondOuterClass_InsideTwoClassesSealedClassSealedEnum.values
        )
    }
}
