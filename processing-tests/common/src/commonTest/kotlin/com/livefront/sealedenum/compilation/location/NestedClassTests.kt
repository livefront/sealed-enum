package com.livefront.sealedenum.compilation.location

import kotlin.test.Test
import kotlin.test.assertEquals

class NestedClassTests {
    @Test
    fun inside_one_class() {
        assertEquals(
            listOf(
                OuterClass.InsideOneClassSealedClass.FirstObject,
                OuterClass.InsideOneClassSealedClass.SecondObject
            ),
            OuterClass.InsideOneClassSealedClass.values
        )
    }

    @Test
    fun inside_two_classes() {
        assertEquals(
            listOf(
                FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass.FirstObject,
                FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass.SecondObject
            ),
            FirstOuterClass.SecondOuterClass.InsideTwoClassesSealedClass.values
        )
    }
}
