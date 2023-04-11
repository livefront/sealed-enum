package com.livefront.sealedenum.compilation.kitchensink

import com.livefront.sealedenum.testing.assertApprovedGeneratedFile
import com.livefront.sealedenum.testing.assertCompiles
import com.livefront.sealedenum.testing.compile
import com.livefront.sealedenum.testing.getSourceFile
import com.oneeyedmen.okeydoke.Approver
import com.oneeyedmen.okeydoke.junit5.ApprovalsExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.reflect.KTypeProjection
import kotlin.reflect.full.createType
import kotlin.reflect.full.isSubtypeOf

@ExtendWith(ApprovalsExtension::class)
class JavaBaseClassesTests {

    @Suppress("LongMethod")
    @Test
    fun `enum implements correct interfaces with type arguments`() {
        assertTrue(
            JavaBaseClassesSealedClassEnum::class.createType().isSubtypeOf(
                KotlinInterface1::class.createType()
            )
        )

        assertTrue(
            JavaBaseClassesSealedClassEnum::class.createType().isSubtypeOf(
                KotlinInterface2::class.createType(
                    arguments = listOf(
                        KTypeProjection.invariant(String::class.createType())
                    )
                )
            )
        )

        assertTrue(
            JavaBaseClassesSealedClassEnum::class.createType().isSubtypeOf(
                KotlinInterface3::class.createType(
                    arguments = listOf(
                        KTypeProjection.invariant(
                            List::class.createType(
                                arguments = listOf(
                                    KTypeProjection.invariant(String::class.createType())
                                )
                            )
                        )
                    )
                )
            )
        )

        assertTrue(
            JavaBaseClassesSealedClassEnum::class.createType().isSubtypeOf(
                KotlinInterface4::class.createType(
                    arguments = listOf(
                        KTypeProjection.invariant(Double::class.createType())
                    )
                )
            )
        )

        assertTrue(
            JavaBaseClassesSealedClassEnum::class.createType().isSubtypeOf(
                KotlinInterface5::class.createType(
                    arguments = listOf(
                        KTypeProjection.invariant(KotlinInterface1::class.createType())
                    )
                )
            )
        )

        assertTrue(
            JavaBaseClassesSealedClassEnum::class.createType().isSubtypeOf(
                KotlinInterface6::class.createType(
                    arguments = listOf(
                        KTypeProjection.invariant(Int::class.createType())
                    )
                )
            )
        )

        // Check for compilation
        val values1: Array<out KotlinInterface1> = JavaBaseClassesSealedClassEnum.values()
        val values2: Array<out KotlinInterface2<String>> = JavaBaseClassesSealedClassEnum.values()
        val values3: Array<out KotlinInterface3<List<String>>> = JavaBaseClassesSealedClassEnum.values()
        val values4: Array<out KotlinInterface4<Double>> = JavaBaseClassesSealedClassEnum.values()
        val values5: Array<out KotlinInterface5<KotlinInterface1>> =
            JavaBaseClassesSealedClassEnum.values()
        val values6: Array<out KotlinInterface6<Int>> = JavaBaseClassesSealedClassEnum.values()
        val values7: Array<out JavaInterface1> = JavaBaseClassesSealedClassEnum.values()
        val values8: Array<out JavaInterface2<List<String>>> = JavaBaseClassesSealedClassEnum.values()
        val values9: Array<out JavaInterface3<Int>> = JavaBaseClassesSealedClassEnum.values()
        val values10: Array<out JavaInterface4> = JavaBaseClassesSealedClassEnum.values()
        val values11: Array<out JavaInterface5<KotlinInterface5<JavaInterface2<KotlinInterface7<JavaInterface3<List<String>>>>>>> = JavaBaseClassesSealedClassEnum.values()

        assertEquals(
            JavaBaseClassesSealedClass.values.map(JavaBaseClassesSealedClass<*>::enum),
            values1.toList()
        )

        assertEquals(
            JavaBaseClassesSealedClass.values.map(JavaBaseClassesSealedClass<*>::enum),
            values2.toList()
        )

        assertEquals(
            JavaBaseClassesSealedClass.values.map(JavaBaseClassesSealedClass<*>::enum),
            values3.toList()
        )

        assertEquals(
            JavaBaseClassesSealedClass.values.map(JavaBaseClassesSealedClass<*>::enum),
            values4.toList()
        )

        assertEquals(
            JavaBaseClassesSealedClass.values.map(JavaBaseClassesSealedClass<*>::enum),
            values5.toList()
        )

        assertEquals(
            JavaBaseClassesSealedClass.values.map(JavaBaseClassesSealedClass<*>::enum),
            values6.toList()
        )

        assertEquals(
            JavaBaseClassesSealedClass.values.map(JavaBaseClassesSealedClass<*>::enum),
            values7.toList()
        )

        assertEquals(
            JavaBaseClassesSealedClass.values.map(JavaBaseClassesSealedClass<*>::enum),
            values8.toList()
        )

        assertEquals(
            JavaBaseClassesSealedClass.values.map(JavaBaseClassesSealedClass<*>::enum),
            values9.toList()
        )

        assertEquals(
            JavaBaseClassesSealedClass.values.map(JavaBaseClassesSealedClass<*>::enum),
            values10.toList()
        )

        assertEquals(
            JavaBaseClassesSealedClass.values.map(JavaBaseClassesSealedClass<*>::enum),
            values11.toList()
        )
    }

    @Test
    fun Approver.`compilation generates correct code`() {
        val result = compile(
            getSourceFile("compilation", "kitchensink", "JavaInterface1.java"),
            getSourceFile("compilation", "kitchensink", "JavaInterface2.java"),
            getSourceFile("compilation", "kitchensink", "JavaInterface3.java"),
            getSourceFile("compilation", "kitchensink", "JavaInterface4.java"),
            getSourceFile("compilation", "kitchensink", "JavaInterface5.java"),
            getSourceFile("compilation", "kitchensink", "JavaFirstBaseClass.java"),
            getSourceFile("compilation", "kitchensink", "JavaSecondBaseClass.java"),
            getSourceFile("compilation", "kitchensink", "JavaBaseClasses.kt")
        )

        assertCompiles(result)
        assertApprovedGeneratedFile("JavaBaseClassesSealedClass_SealedEnum.kt", result)
    }
}
