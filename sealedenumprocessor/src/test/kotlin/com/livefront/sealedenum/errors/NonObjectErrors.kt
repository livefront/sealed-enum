package com.livefront.sealedenum.errors

import com.livefront.sealedenum.testing.assertFails
import com.livefront.sealedenum.testing.compile
import com.tschuchort.compiletesting.SourceFile
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class NonObjectErrors {

    @Test
    fun `direct child fails with non object error`() {
        @Language("kotlin")
        val source = """
            import com.livefront.sealedenum.GenSealedEnum
            
            sealed class NonObjectSealedClass {
            
                class NonObject : NonObjectSealedClass()
            
                @GenSealedEnum
                companion object
            }
        """.trimIndent()

        val result = compile(SourceFile.kotlin("NonObjectSealedClass.kt", source))

        assertFails(result)
        assertTrue(result.messages.contains("Annotated sealed class has a non-object subclass"))
    }

    @Test
    fun `first level child fails with non object error`() {
        @Language("kotlin")
        val source = """
            import com.livefront.sealedenum.GenSealedEnum
            
            sealed class NonObjectSealedClass {
            
                object FirstObject : NonObjectSealedClass()
                
                sealed class InnerSealedClass : NonObjectSealedClass() {
                    data class NonObject(val unit: Unit) : InnerSealedClass()
                }
            
                @GenSealedEnum
                companion object
            }
        """.trimIndent()

        val result = compile(SourceFile.kotlin("NonObjectSealedClass.kt", source))

        assertFails(result)
        assertTrue(result.messages.contains("Annotated sealed class has a non-object subclass"))
    }

    @Test
    fun `second level child fails with non object error`() {
        @Language("kotlin")
        val source = """
            import com.livefront.sealedenum.GenSealedEnum
            
            sealed class NonObjectSealedClass {
            
                object FirstObject : NonObjectSealedClass()
                
                sealed class FirstInnerSealedClass : NonObjectSealedClass() {
                    
                    object SecondObject : FirstInnerSealedClass()
                    
                    sealed class SecondInnerSealedClass : FirstInnerSealedClass() {
                        class NonObject(val unit: Unit) : SecondInnerSealedClass()
                    }
                }
            
                @GenSealedEnum
                companion object
            }
        """.trimIndent()

        val result = compile(SourceFile.kotlin("NonObjectSealedClass.kt", source))

        assertFails(result)
        assertTrue(result.messages.contains("Annotated sealed class has a non-object subclass"))
    }
}
