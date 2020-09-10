package com.livefront.sealedenum.errors

import com.livefront.sealedenum.testing.assertFails
import com.livefront.sealedenum.testing.compile
import com.squareup.kotlinpoet.metadata.KotlinPoetMetadataPreview
import com.tschuchort.compiletesting.SourceFile
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

@KotlinPoetMetadataPreview
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

    @Test
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
}
