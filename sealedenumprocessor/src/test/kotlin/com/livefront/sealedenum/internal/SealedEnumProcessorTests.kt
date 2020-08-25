package com.livefront.sealedenum.internal

import com.livefront.sealedenum.GenSealedEnum
import com.livefront.sealedenum.GenSealedEnums
import com.livefront.sealedenum.TreeTraversalOrder
import com.livefront.sealedenum.internal.SealedEnumFileSpec.SealedEnumOption.SealedEnumOnly
import com.livefront.sealedenum.internal.SealedEnumFileSpec.SealedEnumOption.SealedEnumWithEnum
import com.squareup.kotlinpoet.ANY
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.classinspector.elements.ElementsClassInspector
import com.squareup.kotlinpoet.metadata.ImmutableKmClass
import com.squareup.kotlinpoet.metadata.KotlinPoetMetadataPreview
import com.squareup.kotlinpoet.metadata.isCompanionObject
import com.squareup.kotlinpoet.metadata.isObject
import com.squareup.kotlinpoet.metadata.isSealed
import com.squareup.kotlinpoet.metadata.specs.internal.ClassInspectorUtil
import com.squareup.kotlinpoet.metadata.specs.toTypeSpec
import com.squareup.kotlinpoet.metadata.toImmutableKmClass
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.mockkStatic
import io.mockk.spyk
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import javax.annotation.processing.Filer
import javax.annotation.processing.Messager
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.PackageElement
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.lang.model.util.Types
import javax.tools.Diagnostic.Kind.ERROR

@KotlinPoetMetadataPreview
@ExtendWith(MockKExtension::class)
class SealedEnumProcessorTests {

    @MockK
    private lateinit var processingEnvironment: ProcessingEnvironment

    @MockK
    private lateinit var messager: Messager

    @MockK
    private lateinit var elementUtils: Elements

    @MockK
    private lateinit var typeUtils: Types

    private lateinit var processor: SealedEnumProcessor

    @BeforeEach
    fun setup() {
        processor = spyk(SealedEnumProcessor(processingEnvironment))
    }

    @Test
    fun `getSupportedOptions returns correct options`() {
        assertEquals(
            mutableSetOf(OPTION_AUTO_GENERATE_SEALED_ENUMS),
            processor.supportedOptions
        )
    }

    @Nested
    @DisplayName("GetSupportedAnnotationTypes")
    inner class GSAT {

        @Test
        fun `when autogenerate is true verify SealedEnum, SealedEnums and Metadata`() {
            every { processingEnvironment.options } returns mapOf(OPTION_AUTO_GENERATE_SEALED_ENUMS to "true")

            assertEquals(
                mutableSetOf(
                    Metadata::class.java.name,
                    GenSealedEnum::class.java.name,
                    GenSealedEnums::class.java.name
                ),
                processor.supportedAnnotationTypes
            )
        }

        @Test
        fun `when autogenerate is false verify SealedEnum and SealedEnums`() {
            every { processingEnvironment.options } returns mapOf(OPTION_AUTO_GENERATE_SEALED_ENUMS to "false")

            assertEquals(
                mutableSetOf(
                    GenSealedEnum::class.java.name,
                    GenSealedEnums::class.java.name
                ),
                processor.supportedAnnotationTypes
            )
        }

        @Test
        fun `when autogenerate is missing verify SealedEnum and SealedEnums`() {
            every { processingEnvironment.options } returns emptyMap()

            assertEquals(
                mutableSetOf(
                    GenSealedEnum::class.java.name,
                    GenSealedEnums::class.java.name
                ),
                processor.supportedAnnotationTypes
            )
        }
    }

    @Test
    fun `verify process calls createSealedObjectsFileSpec and writes to filer`() {
        val filer = mockk<Filer>()
        every { processingEnvironment.filer } returns filer

        val annotation = mockk<TypeElement>()
        val annotations = mutableSetOf(annotation)

        val roundEnvironment = mockk<RoundEnvironment>()

        val packageElement = mockk<PackageElement>()
        val firstTypeElement = mockk<TypeElement>()
        val secondTypeElement = mockk<TypeElement>()

        val firstFileSpec = mockk<FileSpec> {
            every { writeTo(filer) } returns Unit
        }
        val firstSealedEnumFileSpec = mockk<SealedEnumFileSpec> {
            every { build() } returns firstFileSpec
        }

        every { processor.createSealedEnumFileSpec(firstTypeElement) } returns firstSealedEnumFileSpec
        every { processor.createSealedEnumFileSpec(secondTypeElement) } returns null

        every {
            roundEnvironment.getElementsAnnotatedWith(annotation)
        } returns setOf(packageElement, firstTypeElement, secondTypeElement)

        assertTrue(processor.process(annotations, roundEnvironment))

        verify { firstFileSpec.writeTo(filer) }
    }

    @Nested
    @DisplayName("CreateSealedEnumFileSpec")
    inner class CSEFS {

        private val sealedClassCompanionObjectElement = mockk<TypeElement>()

        @BeforeEach
        fun setup() {
            mockkStatic("com.squareup.kotlinpoet.metadata.KotlinPoetMetadata")
            every { processingEnvironment.messager } returns messager
        }

        @Test
        fun `annotated with duplicate traversal orders`() {
            val sealedEnumAnnotations = arrayOf<GenSealedEnum>(
                mockk {
                    every { traversalOrder } returns TreeTraversalOrder.IN_ORDER
                    every { generateEnum } returns false
                },
                mockk {
                    every { traversalOrder } returns TreeTraversalOrder.IN_ORDER
                    every { generateEnum } returns false
                }
            )

            every {
                sealedClassCompanionObjectElement.getAnnotationsByType(GenSealedEnum::class.java)
            } returns sealedEnumAnnotations

            every {
                messager.printMessage(
                    ERROR,
                    ERROR_ELEMENT_IS_ANNOTATED_WITH_REPEATED_TRAVERSAL_ORDER,
                    sealedClassCompanionObjectElement
                )
            } returns Unit

            assertNull(processor.createSealedEnumFileSpec(sealedClassCompanionObjectElement))
        }

        @Nested
        @DisplayName("AnnotatedByMetadataOnly")
        inner class ABMO {

            private val sealedEnumAnnotations = emptyArray<GenSealedEnum>()

            @BeforeEach
            fun setup() {
                every {
                    sealedClassCompanionObjectElement.getAnnotationsByType(GenSealedEnum::class.java)
                } returns sealedEnumAnnotations
            }

            @Test
            fun `annotated element is not Kotlin class`() {
                every { sealedClassCompanionObjectElement.toImmutableKmClass() } throws IllegalStateException()

                assertNull(processor.createSealedEnumFileSpec(sealedClassCompanionObjectElement))
            }

            @Nested
            @DisplayName("AnnotatedElementIsKotlinClass")
            inner class AEIKC {

                private val sealedClassCompanionObjectKmClass = mockk<ImmutableKmClass>()

                @BeforeEach
                fun setup() {
                    mockkStatic("com.squareup.kotlinpoet.metadata.FlagsKt")
                    every {
                        sealedClassCompanionObjectElement.toImmutableKmClass()
                    } returns sealedClassCompanionObjectKmClass
                }

                @Test
                fun `annotated class is not a companion object`() {
                    every { sealedClassCompanionObjectKmClass.isCompanionObject } returns false

                    assertNull(processor.createSealedEnumFileSpec(sealedClassCompanionObjectElement))
                }

                @Nested
                @DisplayName("AnnotatedElementIsCompanionObject")
                inner class AEICO {

                    private val sealedClassElement = mockk<TypeElement>()

                    @BeforeEach
                    fun setup() {
                        every { sealedClassCompanionObjectKmClass.isCompanionObject } returns true
                        every { sealedClassCompanionObjectElement.enclosingElement } returns sealedClassElement
                    }

                    @Test
                    fun `enclosing element is not Kotlin class`() {
                        every { sealedClassElement.toImmutableKmClass() } throws IllegalStateException()

                        assertNull(processor.createSealedEnumFileSpec(sealedClassCompanionObjectElement))
                    }

                    @Nested
                    @DisplayName("EnclosingElementIsKotlinClass")
                    inner class EEIKC {

                        private val sealedClassKmClass = mockk<ImmutableKmClass>()

                        @BeforeEach
                        fun setup() {
                            every { sealedClassElement.toImmutableKmClass() } returns sealedClassKmClass
                        }

                        @Test
                        fun `annotated class is not sealed`() {
                            every { sealedClassKmClass.isSealed } returns false

                            assertNull(processor.createSealedEnumFileSpec(sealedClassCompanionObjectElement))
                        }

                        @Nested
                        @DisplayName("EnclosingElementIsKotlinClass")
                        inner class ACIS {

                            @BeforeEach
                            fun setup() {
                                every { sealedClassKmClass.isSealed } returns true
                            }

                            @Test
                            fun `annotated sealed class has non-object subclasses`() {
                                every {
                                    processor.createSealedClassNode(sealedClassKmClass)
                                } throws NonObjectSealedSubclassException()

                                assertNull(processor.createSealedEnumFileSpec(sealedClassCompanionObjectElement))
                            }

                            @Test
                            fun `annotated sealed class only has object subclasses`() {
                                mockkObject(ClassInspectorUtil)
                                mockkObject(ElementsClassInspector)
                                mockkStatic("com.squareup.kotlinpoet.metadata.specs.KotlinPoetMetadataSpecs")

                                val elementsClassInspector = mockk<ElementsClassInspector>()
                                val sealedClassTypeSpec = mockk<TypeSpec> {
                                    every { typeVariables } returns listOf(
                                        mockk {
                                            every { variance } returns null
                                            every { bounds } returns listOf(ANY)
                                        }
                                    )
                                }
                                val sealedClassNode = mockk<SealedClassNode.SealedClass>()
                                val sealedClass = mockk<ClassName>()
                                val sealedClassCompanion = mockk<ClassName>()

                                every { processingEnvironment.elementUtils } returns elementUtils
                                every { processingEnvironment.typeUtils } returns typeUtils
                                every {
                                    ElementsClassInspector.create(
                                        elementUtils,
                                        typeUtils
                                    )
                                } returns elementsClassInspector
                                every { sealedClassElement.toTypeSpec(elementsClassInspector) } returns sealedClassTypeSpec
                                every { processor.createSealedClassNode(sealedClassKmClass) } returns sealedClassNode
                                every { sealedClassKmClass.name } returns "SealedClassName"
                                every { sealedClassKmClass.typeParameters } returns listOf(mockk())
                                every { sealedClassCompanionObjectKmClass.name } returns "SealedClassName.Companion"
                                every { ClassInspectorUtil.createClassName("SealedClassName") } returns sealedClass
                                every { ClassInspectorUtil.createClassName("SealedClassName.Companion") } returns sealedClassCompanion

                                assertEquals(
                                    SealedEnumFileSpec(
                                        sealedClass = sealedClass,
                                        sealedClassCompanionObjectElement = sealedClassCompanionObjectElement,
                                        sealedClassNode = sealedClassNode,
                                        typeParameters = listOf(ANY),
                                        sealedEnumOptions = mapOf(
                                            TreeTraversalOrder.IN_ORDER to SealedEnumOnly
                                        ),
                                        sealedClassCompanionObject = sealedClassCompanion
                                    ),
                                    processor.createSealedEnumFileSpec(sealedClassCompanionObjectElement)
                                )
                            }
                        }
                    }
                }
            }
        }

        @Nested
        @DisplayName("AnnotatedByGenSealedEnums")
        inner class ANGSE {

            private val sealedEnumAnnotations = arrayOf<GenSealedEnum>(
                mockk {
                    every { traversalOrder } returns TreeTraversalOrder.IN_ORDER
                    every { generateEnum } returns false
                },
                mockk {
                    every { traversalOrder } returns TreeTraversalOrder.LEVEL_ORDER
                    every { generateEnum } returns false
                },
                mockk {
                    every { traversalOrder } returns TreeTraversalOrder.PRE_ORDER
                    every { generateEnum } returns false
                },
                mockk {
                    every { traversalOrder } returns TreeTraversalOrder.POST_ORDER
                    every { generateEnum } returns false
                }
            )

            @BeforeEach
            fun setup() {
                every {
                    sealedClassCompanionObjectElement.getAnnotationsByType(GenSealedEnum::class.java)
                } returns sealedEnumAnnotations
            }

            @Test
            fun `annotated element is not Kotlin class`() {
                every { sealedClassCompanionObjectElement.toImmutableKmClass() } throws IllegalStateException()
                every {
                    messager.printMessage(ERROR, ERROR_ELEMENT_IS_NOT_KOTLIN_CLASS, sealedClassCompanionObjectElement)
                } returns Unit

                assertNull(processor.createSealedEnumFileSpec(sealedClassCompanionObjectElement))
                verify {
                    messager.printMessage(ERROR, ERROR_ELEMENT_IS_NOT_KOTLIN_CLASS, sealedClassCompanionObjectElement)
                }
            }

            @Nested
            @DisplayName("AnnotatedElementIsKotlinClass")
            inner class AEIKC {

                private val sealedClassCompanionObjectKmClass = mockk<ImmutableKmClass>()

                @BeforeEach
                fun setup() {
                    mockkStatic("com.squareup.kotlinpoet.metadata.FlagsKt")
                    every {
                        sealedClassCompanionObjectElement.toImmutableKmClass()
                    } returns sealedClassCompanionObjectKmClass
                }

                @Test
                fun `annotated class is not a companion object`() {
                    every { sealedClassCompanionObjectKmClass.isCompanionObject } returns false
                    every {
                        messager.printMessage(ERROR, ERROR_ELEMENT_IS_NOT_COMPANION_OBJECT, sealedClassCompanionObjectElement)
                    } returns Unit

                    assertNull(processor.createSealedEnumFileSpec(sealedClassCompanionObjectElement))
                    verify {
                        messager.printMessage(ERROR, ERROR_ELEMENT_IS_NOT_COMPANION_OBJECT, sealedClassCompanionObjectElement)
                    }
                }

                @Nested
                @DisplayName("AnnotatedElementIsCompanionObject")
                inner class AEICO {

                    private val sealedClassElement = mockk<TypeElement>()

                    @BeforeEach
                    fun setup() {
                        every { sealedClassCompanionObjectKmClass.isCompanionObject } returns true
                        every { sealedClassCompanionObjectElement.enclosingElement } returns sealedClassElement
                    }

                    @Test
                    fun `enclosing element is not Kotlin class`() {
                        every { sealedClassElement.toImmutableKmClass() } throws IllegalStateException()
                        every {
                            messager.printMessage(ERROR, ERROR_ENCLOSING_ELEMENT_IS_NOT_KOTLIN_CLASS, sealedClassElement)
                        } returns Unit

                        assertNull(processor.createSealedEnumFileSpec(sealedClassCompanionObjectElement))
                        verify { messager.printMessage(ERROR, ERROR_ENCLOSING_ELEMENT_IS_NOT_KOTLIN_CLASS, sealedClassElement) }
                    }

                    @Nested
                    @DisplayName("EnclosingElementIsKotlinClass")
                    inner class EEIKC {

                        private val sealedClassKmClass = mockk<ImmutableKmClass>()

                        @BeforeEach
                        fun setup() {
                            every { sealedClassElement.toImmutableKmClass() } returns sealedClassKmClass
                        }

                        @Test
                        fun `annotated class is not sealed`() {
                            every { sealedClassKmClass.isSealed } returns false
                            every {
                                messager.printMessage(
                                    ERROR,
                                    ERROR_CLASS_IS_NOT_SEALED,
                                    sealedClassElement
                                )
                            } returns Unit

                            assertNull(processor.createSealedEnumFileSpec(sealedClassCompanionObjectElement))
                            verify { messager.printMessage(ERROR, ERROR_CLASS_IS_NOT_SEALED, sealedClassElement) }
                        }

                        @Nested
                        @DisplayName("AnnotatedClassIsSealed")
                        inner class ACIS {

                            @BeforeEach
                            fun setup() {
                                every { sealedClassKmClass.isSealed } returns true
                            }

                            @Test
                            fun `annotated sealed class has non-object subclasses`() {
                                every {
                                    processor.createSealedClassNode(sealedClassKmClass)
                                } throws NonObjectSealedSubclassException()
                                every {
                                    messager.printMessage(ERROR, ERROR_NON_OBJECT_SEALED_SUBCLASSES, sealedClassElement)
                                } returns Unit

                                assertNull(processor.createSealedEnumFileSpec(sealedClassCompanionObjectElement))
                                verify {
                                    messager.printMessage(
                                        ERROR,
                                        ERROR_NON_OBJECT_SEALED_SUBCLASSES,
                                        sealedClassElement
                                    )
                                }
                            }

                            @Test
                            fun `annotated class is sealed`() {
                                mockkObject(ClassInspectorUtil)
                                mockkObject(ElementsClassInspector)
                                mockkStatic("com.squareup.kotlinpoet.metadata.specs.KotlinPoetMetadataSpecs")

                                val elementsClassInspector = mockk<ElementsClassInspector>()
                                val sealedClassTypeSpec = mockk<TypeSpec> {
                                    every { typeVariables } returns listOf(
                                        mockk {
                                            every { variance } returns null
                                            every { bounds } returns listOf(ANY)
                                        }
                                    )
                                }
                                val sealedClassNode = mockk<SealedClassNode.SealedClass>()
                                val sealedClass = mockk<ClassName>()
                                val sealedClassCompanion = mockk<ClassName>()

                                every { processingEnvironment.elementUtils } returns elementUtils
                                every { processingEnvironment.typeUtils } returns typeUtils
                                every {
                                    ElementsClassInspector.create(
                                        elementUtils,
                                        typeUtils
                                    )
                                } returns elementsClassInspector
                                every { sealedClassElement.toTypeSpec(elementsClassInspector) } returns sealedClassTypeSpec
                                every { processor.createSealedClassNode(sealedClassKmClass) } returns sealedClassNode
                                every { sealedClassKmClass.name } returns "SealedClassName"
                                every { sealedClassKmClass.typeParameters } returns listOf(mockk())
                                every { sealedClassCompanionObjectKmClass.name } returns "SealedClassName.Companion"
                                every { ClassInspectorUtil.createClassName("SealedClassName") } returns sealedClass
                                every { ClassInspectorUtil.createClassName("SealedClassName.Companion") } returns sealedClassCompanion

                                assertEquals(
                                    SealedEnumFileSpec(
                                        sealedClass = sealedClass,
                                        sealedClassCompanionObjectElement = sealedClassCompanionObjectElement,
                                        sealedClassNode = sealedClassNode,
                                        typeParameters = listOf(ANY),
                                        sealedEnumOptions = mapOf(
                                            TreeTraversalOrder.IN_ORDER to SealedEnumOnly,
                                            TreeTraversalOrder.LEVEL_ORDER to SealedEnumOnly,
                                            TreeTraversalOrder.PRE_ORDER to SealedEnumOnly,
                                            TreeTraversalOrder.POST_ORDER to SealedEnumOnly
                                        ),
                                        sealedClassCompanionObject = sealedClassCompanion
                                    ),
                                    processor.createSealedEnumFileSpec(sealedClassCompanionObjectElement)
                                )
                            }
                        }
                    }
                }
            }
        }

        @Nested
        @DisplayName("AnnotatedByGenSealedEnumWithEnum")
        inner class ABSEWE {

            private val sealedEnumAnnotations = arrayOf<GenSealedEnum>(
                mockk {
                    every { traversalOrder } returns TreeTraversalOrder.IN_ORDER
                    every { generateEnum } returns true
                }
            )

            @BeforeEach
            fun setup() {
                every {
                    sealedClassCompanionObjectElement.getAnnotationsByType(GenSealedEnum::class.java)
                } returns sealedEnumAnnotations
            }

            @Test
            fun `annotated element is not Kotlin class`() {
                every { sealedClassCompanionObjectElement.toImmutableKmClass() } throws IllegalStateException()
                every {
                    messager.printMessage(ERROR, ERROR_ELEMENT_IS_NOT_KOTLIN_CLASS, sealedClassCompanionObjectElement)
                } returns Unit

                assertNull(processor.createSealedEnumFileSpec(sealedClassCompanionObjectElement))
                verify {
                    messager.printMessage(ERROR, ERROR_ELEMENT_IS_NOT_KOTLIN_CLASS, sealedClassCompanionObjectElement)
                }
            }

            @Nested
            @DisplayName("AnnotatedElementIsKotlinClass")
            inner class AEIKC {


                private val sealedClassCompanionObjectKmClass = mockk<ImmutableKmClass>()

                @BeforeEach
                fun setup() {
                    mockkStatic("com.squareup.kotlinpoet.metadata.FlagsKt")
                    every {
                        sealedClassCompanionObjectElement.toImmutableKmClass()
                    } returns sealedClassCompanionObjectKmClass
                }

                @Test
                fun `annotated class is not a companion object`() {
                    every { sealedClassCompanionObjectKmClass.isCompanionObject } returns false
                    every {
                        messager.printMessage(ERROR, ERROR_ELEMENT_IS_NOT_COMPANION_OBJECT, sealedClassCompanionObjectElement)
                    } returns Unit

                    assertNull(processor.createSealedEnumFileSpec(sealedClassCompanionObjectElement))
                    verify {
                        messager.printMessage(ERROR, ERROR_ELEMENT_IS_NOT_COMPANION_OBJECT, sealedClassCompanionObjectElement)
                    }
                }

                @Nested
                @DisplayName("AnnotatedElementIsCompanionObject")
                inner class AEICO {

                    private val sealedClassElement = mockk<TypeElement>()

                    @BeforeEach
                    fun setup() {
                        every { sealedClassCompanionObjectKmClass.isCompanionObject } returns true
                        every { sealedClassCompanionObjectElement.enclosingElement } returns sealedClassElement
                    }

                    @Test
                    fun `enclosing element is not Kotlin class`() {
                        every { sealedClassElement.toImmutableKmClass() } throws IllegalStateException()
                        every {
                            messager.printMessage(
                                ERROR,
                                ERROR_ENCLOSING_ELEMENT_IS_NOT_KOTLIN_CLASS,
                                sealedClassElement
                            )
                        } returns Unit

                        assertNull(processor.createSealedEnumFileSpec(sealedClassCompanionObjectElement))
                        verify {
                            messager.printMessage(
                                ERROR,
                                ERROR_ENCLOSING_ELEMENT_IS_NOT_KOTLIN_CLASS,
                                sealedClassElement
                            )
                        }
                    }

                    @Nested
                    @DisplayName("EnclosingElementIsKotlinClass")
                    inner class EEIKC {

                        private val sealedClassKmClass = mockk<ImmutableKmClass>()

                        @BeforeEach
                        fun setup() {
                            every { sealedClassElement.toImmutableKmClass() } returns sealedClassKmClass
                        }

                        @Test
                        fun `annotated class is not sealed`() {
                            every { sealedClassKmClass.isSealed } returns false
                            every {
                                messager.printMessage(
                                    ERROR,
                                    ERROR_CLASS_IS_NOT_SEALED,
                                    sealedClassElement
                                )
                            } returns Unit

                            assertNull(processor.createSealedEnumFileSpec(sealedClassCompanionObjectElement))
                            verify { messager.printMessage(ERROR, ERROR_CLASS_IS_NOT_SEALED, sealedClassElement) }
                        }

                        @Nested
                        @DisplayName("AnnotatedClassIsSealed")
                        inner class ACIS {

                            @BeforeEach
                            fun setup() {
                                every { sealedClassKmClass.isSealed } returns true
                            }

                            @Test
                            fun `annotated sealed class has non-object subclasses`() {
                                every {
                                    processor.createSealedClassNode(sealedClassKmClass)
                                } throws NonObjectSealedSubclassException()
                                every {
                                    messager.printMessage(ERROR, ERROR_NON_OBJECT_SEALED_SUBCLASSES, sealedClassElement)
                                } returns Unit

                                assertNull(processor.createSealedEnumFileSpec(sealedClassCompanionObjectElement))
                                verify {
                                    messager.printMessage(
                                        ERROR,
                                        ERROR_NON_OBJECT_SEALED_SUBCLASSES,
                                        sealedClassElement
                                    )
                                }
                            }

                            @Test
                            fun `annotated class is sealed`() {
                                mockkObject(ClassInspectorUtil)
                                mockkObject(ElementsClassInspector)
                                mockkStatic("com.squareup.kotlinpoet.metadata.specs.KotlinPoetMetadataSpecs")
                                mockkStatic("com.livefront.sealedenum.internal.SuperInterfacesKt")

                                val elementsClassInspector = mockk<ElementsClassInspector>()
                                val sealedClassTypeSpec = mockk<TypeSpec> {
                                    every { typeVariables } returns listOf(
                                        mockk {
                                            every { variance } returns null
                                            every { bounds } returns listOf(ANY)
                                        }
                                    )
                                }
                                val sealedClassNode = mockk<SealedClassNode.SealedClass>()
                                val sealedClass = mockk<ClassName>()
                                val sealedClassCompanion = mockk<ClassName>()
                                val superInterfaces = mockk<List<TypeName>>()

                                every { processingEnvironment.elementUtils } returns elementUtils
                                every { processingEnvironment.typeUtils } returns typeUtils
                                every {
                                    ElementsClassInspector.create(
                                        elementUtils,
                                        typeUtils
                                    )
                                } returns elementsClassInspector
                                every { sealedClassElement.toTypeSpec(elementsClassInspector) } returns sealedClassTypeSpec
                                every { processor.createSealedClassNode(sealedClassKmClass) } returns sealedClassNode
                                every { sealedClassKmClass.name } returns "SealedClassName"
                                every { sealedClassKmClass.typeParameters } returns listOf(mockk())
                                every { sealedClassCompanionObjectKmClass.name } returns "SealedClassName.Companion"
                                every { ClassInspectorUtil.createClassName("SealedClassName") } returns sealedClass
                                every { ClassInspectorUtil.createClassName("SealedClassName.Companion") } returns sealedClassCompanion
                                every { elementsClassInspector.getAllSuperInterfaces(sealedClassTypeSpec) } returns superInterfaces

                                assertEquals(
                                    SealedEnumFileSpec(
                                        sealedClass = sealedClass,
                                        sealedClassCompanionObjectElement = sealedClassCompanionObjectElement,
                                        sealedClassNode = sealedClassNode,
                                        typeParameters = listOf(ANY),
                                        sealedEnumOptions = mapOf(
                                            TreeTraversalOrder.IN_ORDER to SealedEnumWithEnum(superInterfaces)
                                        ),
                                        sealedClassCompanionObject = sealedClassCompanion
                                    ),
                                    processor.createSealedEnumFileSpec(sealedClassCompanionObjectElement)
                                )
                            }
                        }
                    }
                }
            }
        }

        @AfterEach
        fun teardown() {
            unmockkAll()
        }
    }

    @Nested
    inner class CreateSealedClassNode {

        private val sealedClassKmClass = mockk<ImmutableKmClass>()

        @BeforeEach
        fun setup() {
            mockkStatic("com.squareup.kotlinpoet.metadata.KotlinPoetMetadata")
            mockkStatic("com.squareup.kotlinpoet.metadata.FlagsKt")
            mockkObject(ClassInspectorUtil)
            every { processingEnvironment.elementUtils } returns elementUtils
        }

        @Test
        fun `empty sealed class`() {
            every { sealedClassKmClass.sealedSubclasses } returns emptyList()

            assertEquals(
                SealedClassNode.SealedClass(emptyList()),
                processor.createSealedClassNode(sealedClassKmClass)
            )
        }

        @Test
        fun `sealed class with non-object`() {
            val firstClassName = mockk<ClassName> {
                every { canonicalName } returns "FirstCanonical"
            }
            val firstTypeElement = mockk<TypeElement>()
            val firstKmClass = mockk<ImmutableKmClass> {
                every { isObject } returns false
                every { isSealed } returns false
            }

            every { ClassInspectorUtil.createClassName("First") } returns firstClassName
            every { elementUtils.getTypeElement("FirstCanonical") } returns firstTypeElement
            every { firstTypeElement.toImmutableKmClass() } returns firstKmClass

            every { sealedClassKmClass.sealedSubclasses } returns listOf("First")

            assertThrows<NonObjectSealedSubclassException> {
                processor.createSealedClassNode(sealedClassKmClass)
            }
        }

        @Test
        fun `sealed class with one object`() {
            val firstObjectClassName = mockk<ClassName> {
                every { canonicalName } returns "FirstObjectCanonical"
            }
            val firstObjectTypeElement = mockk<TypeElement>()
            val firstObjectKmClass = mockk<ImmutableKmClass> {
                every { isObject } returns true
                every { isSealed } returns false
            }

            every { ClassInspectorUtil.createClassName("FirstObject") } returns firstObjectClassName
            every { elementUtils.getTypeElement("FirstObjectCanonical") } returns firstObjectTypeElement
            every { firstObjectTypeElement.toImmutableKmClass() } returns firstObjectKmClass

            every { sealedClassKmClass.sealedSubclasses } returns listOf("FirstObject")

            assertEquals(
                SealedClassNode.SealedClass(
                    listOf(
                        SealedClassNode.Object(firstObjectClassName)
                    )
                ),
                processor.createSealedClassNode(sealedClassKmClass)
            )
        }

        @Test
        fun `sealed class with two objects`() {
            val firstObjectClassName = mockk<ClassName> {
                every { canonicalName } returns "FirstObjectCanonical"
            }
            val firstObjectTypeElement = mockk<TypeElement>()
            val firstObjectKmClass = mockk<ImmutableKmClass> {
                every { isObject } returns true
                every { isSealed } returns false
            }

            every { ClassInspectorUtil.createClassName("FirstObject") } returns firstObjectClassName
            every { elementUtils.getTypeElement("FirstObjectCanonical") } returns firstObjectTypeElement
            every { firstObjectTypeElement.toImmutableKmClass() } returns firstObjectKmClass

            val secondObjectClassName = mockk<ClassName> {
                every { canonicalName } returns "SecondObjectCanonical"
            }
            val secondObjectTypeElement = mockk<TypeElement>()
            val secondObjectKmClass = mockk<ImmutableKmClass> {
                every { isObject } returns true
                every { isSealed } returns false
            }

            every { ClassInspectorUtil.createClassName("SecondObject") } returns secondObjectClassName
            every { elementUtils.getTypeElement("SecondObjectCanonical") } returns secondObjectTypeElement
            every { secondObjectTypeElement.toImmutableKmClass() } returns secondObjectKmClass

            every { sealedClassKmClass.sealedSubclasses } returns listOf("FirstObject", "SecondObject")

            assertEquals(
                SealedClassNode.SealedClass(
                    listOf(
                        SealedClassNode.Object(firstObjectClassName),
                        SealedClassNode.Object(secondObjectClassName)
                    )
                ),
                processor.createSealedClassNode(sealedClassKmClass)
            )
        }

        @Test
        fun `sealed class with one sealed class`() {
            val firstSealedClassName = mockk<ClassName> {
                every { canonicalName } returns "FirstSealedCanonical"
            }
            val firstSealedTypeElement = mockk<TypeElement>()
            val firstSealedKmClass = mockk<ImmutableKmClass> {
                every { isObject } returns false
                every { isSealed } returns true
            }

            every { ClassInspectorUtil.createClassName("FirstSealed") } returns firstSealedClassName
            every { elementUtils.getTypeElement("FirstSealedCanonical") } returns firstSealedTypeElement
            every { firstSealedTypeElement.toImmutableKmClass() } returns firstSealedKmClass

            val firstObjectClassName = mockk<ClassName> {
                every { canonicalName } returns "FirstObjectCanonical"
            }
            val firstObjectTypeElement = mockk<TypeElement>()
            val firstObjectKmClass = mockk<ImmutableKmClass> {
                every { isObject } returns true
                every { isSealed } returns false
            }

            every { ClassInspectorUtil.createClassName("FirstObject") } returns firstObjectClassName
            every { elementUtils.getTypeElement("FirstObjectCanonical") } returns firstObjectTypeElement
            every { firstObjectTypeElement.toImmutableKmClass() } returns firstObjectKmClass

            val secondObjectClassName = mockk<ClassName> {
                every { canonicalName } returns "SecondObjectCanonical"
            }
            val secondObjectTypeElement = mockk<TypeElement>()
            val secondObjectKmClass = mockk<ImmutableKmClass> {
                every { isObject } returns true
                every { isSealed } returns false
            }

            every { ClassInspectorUtil.createClassName("SecondObject") } returns secondObjectClassName
            every { elementUtils.getTypeElement("SecondObjectCanonical") } returns firstObjectTypeElement
            every { secondObjectTypeElement.toImmutableKmClass() } returns secondObjectKmClass

            every { sealedClassKmClass.sealedSubclasses } returns listOf("FirstSealed", "SecondObject")
            every { firstSealedKmClass.sealedSubclasses } returns listOf("FirstObject")

            assertEquals(
                SealedClassNode.SealedClass(
                    listOf(
                        SealedClassNode.SealedClass(
                            listOf(
                                SealedClassNode.Object(firstObjectClassName)
                            )
                        ),
                        SealedClassNode.Object(secondObjectClassName)
                    )
                ),
                processor.createSealedClassNode(sealedClassKmClass)
            )
        }

        @AfterEach
        fun teardown() {
            unmockkAll()
        }
    }
}
