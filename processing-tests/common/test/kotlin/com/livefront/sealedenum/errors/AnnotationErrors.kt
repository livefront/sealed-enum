package com.livefront.sealedenum.errors

import com.livefront.sealedenum.testing.assertFails
import com.livefront.sealedenum.testing.compile
import com.tschuchort.compiletesting.SourceFile
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledIf

class AnnotationErrors {

    @Test
    fun `element is annotated with the same traversal order multiple times`() {
        @Language("kotlin")
        val source = """
            import com.livefront.sealedenum.GenSealedEnum
            import com.livefront.sealedenum.TreeTraversalOrder
            
            sealed class RepeatedAnnotation {
                @GenSealedEnum(traversalOrder = TreeTraversalOrder.IN_ORDER)
                @GenSealedEnum(traversalOrder = TreeTraversalOrder.IN_ORDER)
                companion object
            }
        """.trimIndent()

        val result = compile(SourceFile.kotlin("RepeatedAnnotation.kt", source))

        assertFails(result)
        assertTrue(result.messages.contains("Element is annotated with the same traversal order multiple times"))
    }

    /**
     * TODO: This should cause an error (and does when actually trying to add a Java file set up like this), but for
     *       some reason with kotlin-compile-testing/ksp `JavaClass` isn't even picked up for processing.
     */
    @Test
    @DisabledIf("com.livefront.sealedenum.testing.ProcessingTypeGetter#isKSP")
    fun `element is not Kotlin class`() {
        @Language("java")
        val source = """
            import com.livefront.sealedenum.GenSealedEnum;
            
            @GenSealedEnum
            public class JavaClass { }
        """.trimIndent()

        val result = compile(SourceFile.java("JavaClass.java", source))

        assertFails(result)
        assertTrue(result.messages.contains("Annotated element is not a Kotlin class"))
    }

    @Test
    fun `annotated element is not a companion object`() {
        @Language("kotlin")
        val source = """
            import com.livefront.sealedenum.GenSealedEnum
            
            @GenSealedEnum
            class NonCompanionObject
        """.trimIndent()

        val result = compile(SourceFile.kotlin("NonCompanionObject.kt", source))

        assertFails(result)
        assertTrue(result.messages.contains("Annotated element is not a companion object"))
    }

    @Test
    fun `companion object is private`() {
        @Language("kotlin")
        val source = """
            import com.livefront.sealedenum.GenSealedEnum
            
            sealed class PrivateCompanionObject {
                @GenSealedEnum
                private companion object
            }
        """.trimIndent()

        val result = compile(SourceFile.kotlin("PrivateCompanionObject.kt", source))

        assertFails(result)
        assertTrue(result.messages.contains("Annotated companion object isn't internal or public"))
    }

    @Test
    fun `companion object is protected`() {
        @Language("kotlin")
        val source = """
            import com.livefront.sealedenum.GenSealedEnum
            
            sealed class ProtectedCompanionObject {
                @GenSealedEnum
                protected companion object
            }
        """.trimIndent()

        val result = compile(SourceFile.kotlin("ProtectedCompanionObject.kt", source))

        assertFails(result)
        assertTrue(result.messages.contains("Annotated companion object isn't internal or public"))
    }

    @Test
    fun `annotation companion object is not for a sealed class`() {
        @Language("kotlin")
        val source = """
            import com.livefront.sealedenum.GenSealedEnum
            
            class NonSealedClass {
                @GenSealedEnum
                companion object
            }
        """.trimIndent()

        val result = compile(SourceFile.kotlin("NonSealedClass.kt", source))

        assertFails(result)
        assertTrue(result.messages.contains("Annotated companion object is not for a sealed class"))
    }

    @Test
    fun `sealed class is private`() {
        @Language("kotlin")
        val source = """
            import com.livefront.sealedenum.GenSealedEnum
            
            class OuterClass {
                private sealed class PrivateSealedClass {
                    @GenSealedEnum
                    companion object
                }
            }
        """.trimIndent()

        val result = compile(SourceFile.kotlin("OuterClass.kt", source))

        assertFails(result)
        assertTrue(result.messages.contains("Annotated sealed class isn't internal or public"))
    }

    @Test
    fun `sealed class is protected`() {
        @Language("kotlin")
        val source = """
            import com.livefront.sealedenum.GenSealedEnum
            
            class OuterClass {
                protected sealed class ProtectedSealedClass {
                    @GenSealedEnum
                    companion object
                }
            }
        """.trimIndent()

        val result = compile(SourceFile.kotlin("OuterClass.kt", source))

        assertFails(result)
        assertTrue(result.messages.contains("Annotated sealed class isn't internal or public"))
    }

    @Test
    fun `subclass sealed class is private`() {
        @Language("kotlin")
        val source = """
            import com.livefront.sealedenum.GenSealedEnum
            
            sealed class OuterSealedClass {
                private sealed class InnerSealedClass : OuterSealedClass() {
                    object FirstObject : InnerSealedClass()
                }
                
                @GenSealedEnum
                companion object
            }
        """.trimIndent()

        val result = compile(SourceFile.kotlin("OuterSealedClass.kt", source))

        assertFails(result)
        assertTrue(result.messages.contains("Subclass of sealed class isn't internal or public"))
    }

    @Test
    fun `subclass sealed class is protected`() {
        @Language("kotlin")
        val source = """
            import com.livefront.sealedenum.GenSealedEnum
            
            sealed class OuterSealedClass {
                protected sealed class InnerSealedClass : OuterSealedClass() {
                    object FirstObject : InnerSealedClass()
                }
                
                @GenSealedEnum
                companion object
            }
        """.trimIndent()

        val result = compile(SourceFile.kotlin("OuterSealedClass.kt", source))

        assertFails(result)
        assertTrue(result.messages.contains("Subclass of sealed class isn't internal or public"))
    }

    @Test
    fun `subclass object is private`() {
        @Language("kotlin")
        val source = """
            import com.livefront.sealedenum.GenSealedEnum
            
            sealed class PrivateObject {
                private object FirstObject : PrivateObject()
                
                @GenSealedEnum
                companion object
            }
        """.trimIndent()

        val result = compile(SourceFile.kotlin("PrivateObject.kt", source))

        assertFails(result)
        assertTrue(result.messages.contains("Subclass of sealed class isn't internal or public"))
    }

    @Test
    fun `subclass object is protected`() {
        @Language("kotlin")
        val source = """
            import com.livefront.sealedenum.GenSealedEnum
            
            sealed class PrivateObject {
                protected object FirstObject : PrivateObject()
                
                @GenSealedEnum
                companion object
            }
        """.trimIndent()

        val result = compile(SourceFile.kotlin("PrivateObject.kt", source))

        assertFails(result)
        assertTrue(result.messages.contains("Subclass of sealed class isn't internal or public"))
    }
}
