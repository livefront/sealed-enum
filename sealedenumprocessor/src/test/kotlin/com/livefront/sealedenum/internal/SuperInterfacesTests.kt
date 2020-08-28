package com.livefront.sealedenum.internal

import com.squareup.kotlinpoet.ANY
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ITERABLE
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.LIST
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.STAR
import com.squareup.kotlinpoet.STRING
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.TypeVariableName
import com.squareup.kotlinpoet.asTypeName
import com.squareup.kotlinpoet.classinspector.elements.ElementsClassInspector
import com.squareup.kotlinpoet.metadata.KotlinPoetMetadataPreview
import com.squareup.kotlinpoet.metadata.specs.classFor
import com.squareup.kotlinpoet.metadata.specs.toTypeSpec
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

interface SuperInterface1

interface SuperInterface2

interface SuperInterface3<T>

interface SuperInterface4<T>

interface SuperInterface5<out T>

@KotlinPoetMetadataPreview
@ExtendWith(MockKExtension::class)
class SuperInterfacesTests {

    @MockK
    private lateinit var classInspector: ElementsClassInspector

    @BeforeEach
    fun setup() {
        mockkStatic("com.squareup.kotlinpoet.metadata.specs.KotlinPoetMetadataSpecs")
        mockkStatic("com.squareup.kotlinpoet.metadata.specs.ClassInspectorKt")
    }

    @Test
    fun `simple class`() {
        val typeSpec = TypeSpec.classBuilder("Test").build()

        assertEquals(
            emptyList<TypeName>(),
            classInspector.getAllSuperInterfaces(typeSpec)
        )
    }

    @Test
    fun `simple class with single interface`() {
        val typeSpec = TypeSpec.classBuilder("Test")
            .addSuperinterface(SuperInterface1::class)
            .build()

        assertEquals(
            listOf<TypeName>(SuperInterface1::class.asTypeName()),
            classInspector.getAllSuperInterfaces(typeSpec)
        )
    }

    @Test
    fun `subclass with single interface`() {
        val baseTypeSpec = TypeSpec.classBuilder("Base")
            .addSuperinterface(SuperInterface1::class)
            .build()

        val subclassTypeSpec = TypeSpec.classBuilder("Subclass")
            .superclass(ClassName("", baseTypeSpec.name!!))
            .build()

        every {
            classInspector.classFor(ClassName("", "Base"))
                .toTypeSpec(classInspector, ClassName("", "Base"))
        } returns baseTypeSpec

        assertEquals(
            listOf<TypeName>(SuperInterface1::class.asTypeName()),
            classInspector.getAllSuperInterfaces(subclassTypeSpec)
        )
    }

    @Test
    fun `subclass with multiple interfaces`() {
        val baseTypeSpec = TypeSpec.classBuilder("Base")
            .addSuperinterface(SuperInterface1::class)
            .build()

        val subclassTypeSpec = TypeSpec.classBuilder("Subclass")
            .superclass(ClassName("", baseTypeSpec.name!!))
            .addSuperinterface(SuperInterface2::class)
            .build()

        every {
            classInspector.classFor(ClassName("", "Base"))
                .toTypeSpec(classInspector, ClassName("", "Base"))
        } returns baseTypeSpec

        assertEquals(
            listOf<TypeName>(
                SuperInterface2::class.asTypeName(),
                SuperInterface1::class.asTypeName()
            ),
            classInspector.getAllSuperInterfaces(subclassTypeSpec)
        )
    }

    @Test
    fun `generic class with Any? single bound with non-generic interface`() {
        val typeSpec = TypeSpec.classBuilder("Test")
            .addTypeVariable(TypeVariableName(name = "T", bounds = listOf(ANY.copy(nullable = true))))
            .addSuperinterface(SuperInterface1::class)
            .build()

        assertEquals(
            listOf<TypeName>(SuperInterface1::class.asTypeName()),
            classInspector.getAllSuperInterfaces(typeSpec)
        )
    }

    @Test
    fun `generic class with String single bound with non-generic interface`() {
        val typeSpec = TypeSpec.classBuilder("Test")
            .addTypeVariable(TypeVariableName(name = "T", bounds = listOf(STRING)))
            .addSuperinterface(SuperInterface1::class)
            .build()

        assertEquals(
            listOf<TypeName>(SuperInterface1::class.asTypeName()),
            classInspector.getAllSuperInterfaces(typeSpec)
        )
    }

    @Test
    fun `generic class with out Any? with non-generic interface`() {
        val typeSpec = TypeSpec.classBuilder("Test")
            .addTypeVariable(
                TypeVariableName(
                    name = "T",
                    bounds = listOf(ANY.copy(nullable = true)),
                    variance = KModifier.OUT
                )
            )
            .addSuperinterface(SuperInterface1::class)
            .build()

        assertEquals(
            listOf<TypeName>(SuperInterface1::class.asTypeName()),
            classInspector.getAllSuperInterfaces(typeSpec)
        )
    }

    @Test
    fun `generic class with in Any? with non-generic interface`() {
        val typeSpec = TypeSpec.classBuilder("Test")
            .addTypeVariable(
                TypeVariableName(
                    name = "T",
                    bounds = listOf(ANY.copy(nullable = true)),
                    variance = KModifier.IN
                )
            )
            .addSuperinterface(SuperInterface1::class)
            .build()

        assertEquals(
            listOf<TypeName>(SuperInterface1::class.asTypeName()),
            classInspector.getAllSuperInterfaces(typeSpec)
        )
    }

    @Test
    fun `generic class with multiple bounds with non-generic interface`() {
        val typeSpec = TypeSpec.classBuilder("Test")
            .addTypeVariable(
                TypeVariableName(
                    name = "T",
                    bounds = listOf(
                        ITERABLE,
                        LIST

                    )
                )
            )
            .addSuperinterface(SuperInterface1::class)
            .build()

        assertEquals(
            listOf<TypeName>(SuperInterface1::class.asTypeName()),
            classInspector.getAllSuperInterfaces(typeSpec)
        )
    }

    @Test
    fun `generic class with Any? single bound with generic interface`() {
        val typeSpec = TypeSpec.classBuilder("Test")
            .addTypeVariable(TypeVariableName(name = "T", bounds = listOf(ANY.copy(nullable = true))))
            .addSuperinterface(
                SuperInterface3::class.asTypeName()
                    .parameterizedBy(
                        TypeVariableName(name = "T")
                    )
            )
            .build()

        assertEquals(
            listOf<TypeName>(SuperInterface3::class.asTypeName().parameterizedBy(ANY.copy(nullable = true))),
            classInspector.getAllSuperInterfaces(typeSpec)
        )
    }

    @Test
    fun `generic class with String single bound with generic interface`() {
        val typeSpec = TypeSpec.classBuilder("Test")
            .addTypeVariable(TypeVariableName("T", bounds = listOf(STRING)))
            .addSuperinterface(
                SuperInterface3::class.asTypeName()
                    .parameterizedBy(
                        TypeVariableName(name = "T")
                    )
            )
            .build()

        assertEquals(
            listOf<TypeName>(SuperInterface3::class.asTypeName().parameterizedBy(STRING)),
            classInspector.getAllSuperInterfaces(typeSpec)
        )
    }

    @Test
    fun `generic class with out Any? with generic interface`() {
        val typeSpec = TypeSpec.classBuilder("Test")
            .addTypeVariable(
                TypeVariableName(
                    name = "T",
                    bounds = listOf(ANY.copy(nullable = true)),
                    variance = KModifier.OUT
                )
            )
            .addSuperinterface(
                SuperInterface3::class.asTypeName()
                    .parameterizedBy(
                        TypeVariableName(name = "T")
                    )
            )
            .build()

        assertEquals(
            emptyList<TypeName>(),
            classInspector.getAllSuperInterfaces(typeSpec)
        )
    }

    @Test
    fun `generic class with in Any? with generic interface`() {
        val typeSpec = TypeSpec.classBuilder("Test")
            .addTypeVariable(
                TypeVariableName(
                    name = "T",
                    bounds = listOf(ANY.copy(nullable = true)),
                    variance = KModifier.IN
                )
            )
            .addSuperinterface(
                SuperInterface3::class.asTypeName()
                    .parameterizedBy(
                        TypeVariableName(name = "T")
                    )
            )
            .build()

        assertEquals(
            emptyList<TypeName>(),
            classInspector.getAllSuperInterfaces(typeSpec)
        )
    }

    @Test
    fun `generic class with multiple bounds with generic interface`() {
        val typeSpec = TypeSpec.classBuilder("Test")
            .addTypeVariable(
                TypeVariableName(
                    name = "T",
                    bounds = listOf(
                        SuperInterface1::class,
                        SuperInterface2::class
                    )
                )
            )
            .addSuperinterface(
                SuperInterface3::class.asTypeName()
                    .parameterizedBy(
                        TypeVariableName(name = "T")
                    )
            )
            .build()

        assertEquals(
            emptyList<TypeName>(),
            classInspector.getAllSuperInterfaces(typeSpec)
        )
    }

    @Test
    fun `Any? bound on generic base class with generic interface`() {
        val baseTypeSpec = TypeSpec.classBuilder("Base")
            .addTypeVariable(
                TypeVariableName(
                    name = "T",
                    bounds = listOf(ANY.copy(nullable = true))
                )
            )
            .addSuperinterface(
                SuperInterface3::class.asTypeName()
                    .parameterizedBy(
                        TypeVariableName(name = "T")
                    )
            )
            .build()

        val subclassTypeSpec = TypeSpec.classBuilder("Subclass")
            .superclass(
                ClassName("", baseTypeSpec.name!!).parameterizedBy(ANY.copy(nullable = true))
            )
            .build()

        every {
            classInspector.classFor(ClassName("", "Base"))
                .toTypeSpec(classInspector, ClassName("", "Base"))
        } returns baseTypeSpec

        assertEquals(
            listOf<TypeName>(SuperInterface3::class.asTypeName().parameterizedBy(ANY.copy(nullable = true))),
            classInspector.getAllSuperInterfaces(subclassTypeSpec)
        )
    }

    @Test
    fun `String bound on generic base class with generic interface`() {
        val baseTypeSpec = TypeSpec.classBuilder("Base")
            .addTypeVariable(
                TypeVariableName(
                    name = "T",
                    bounds = listOf(ANY.copy(nullable = true))
                )
            )
            .addSuperinterface(
                SuperInterface3::class.asTypeName()
                    .parameterizedBy(
                        TypeVariableName(name = "T")
                    )
            )
            .build()

        val subclassTypeSpec = TypeSpec.classBuilder("Subclass")
            .superclass(
                ClassName("", baseTypeSpec.name!!).parameterizedBy(STRING)
            )
            .build()

        every {
            classInspector.classFor(ClassName("", "Base"))
                .toTypeSpec(classInspector, ClassName("", "Base"))
        } returns baseTypeSpec

        assertEquals(
            listOf<TypeName>(SuperInterface3::class.asTypeName().parameterizedBy(STRING)),
            classInspector.getAllSuperInterfaces(subclassTypeSpec)
        )
    }

    @Test
    fun `Generic base class with generic subclass and multiple generic interfaces`() {
        val baseTypeSpec = TypeSpec.classBuilder("Base")
            .addTypeVariable(
                TypeVariableName(
                    name = "A",
                    bounds = listOf(ANY.copy(nullable = true))
                )
            )
            .addSuperinterface(
                SuperInterface3::class.asTypeName()
                    .parameterizedBy(
                        TypeVariableName(name = "A")
                    )
            )
            .build()

        val firstSubclassTypeSpec = TypeSpec.classBuilder("FirstSubclass")
            .addTypeVariable(
                TypeVariableName(
                    name = "B",
                    bounds = listOf(ANY.copy(nullable = true))
                )
            )
            .addTypeVariable(
                TypeVariableName(
                    name = "C",
                    bounds = listOf(ANY)
                )
            )
            .superclass(
                ClassName("", baseTypeSpec.name!!)
                    .parameterizedBy(
                        TypeVariableName(name = "B")
                    )
            )
            .addSuperinterface(
                SuperInterface4::class.asTypeName()
                    .parameterizedBy(
                        TypeVariableName(name = "C")
                    )
            )
            .build()

        val secondSubclassTypeSpec = TypeSpec.classBuilder("SecondSubclass")
            .superclass(
                ClassName("", firstSubclassTypeSpec.name!!)
                    .parameterizedBy(ANY.copy(nullable = true), STRING)
            )
            .build()

        every {
            classInspector.classFor(ClassName("", "Base"))
                .toTypeSpec(classInspector, ClassName("", "Base"))
        } returns baseTypeSpec

        every {
            classInspector.classFor(ClassName("", "FirstSubclass"))
                .toTypeSpec(classInspector, ClassName("", "FirstSubclass"))
        } returns firstSubclassTypeSpec

        assertEquals(
            listOf<TypeName>(
                SuperInterface4::class.asTypeName().parameterizedBy(STRING),
                SuperInterface3::class.asTypeName().parameterizedBy(ANY.copy(nullable = true))
            ),
            classInspector.getAllSuperInterfaces(secondSubclassTypeSpec)
        )
    }

    @Test
    fun `Generic base class with generic interface and covariant type argument doesn't produce interface`() {
        val baseTypeSpec = TypeSpec.classBuilder("Base")
            .addTypeVariable(
                TypeVariableName(
                    name = "T",
                    bounds = listOf(ANY.copy(nullable = true)),
                    variance = KModifier.OUT
                )
            )
            .addSuperinterface(
                SuperInterface5::class.asTypeName()
                    .parameterizedBy(
                        TypeVariableName(name = "T")
                    )
            )
            .build()

        val subclassTypeSpec = TypeSpec.classBuilder("Subclass")
            .addTypeVariable(
                TypeVariableName(
                    name = "T",
                    bounds = listOf(ANY.copy(nullable = true)),
                    variance = KModifier.OUT
                )
            )
            .superclass(
                ClassName("", baseTypeSpec.name!!).parameterizedBy(TypeVariableName(name = "T"))
            )
            .build()

        every {
            classInspector.classFor(ClassName("", "Base"))
                .toTypeSpec(classInspector, ClassName("", "Base"))
        } returns baseTypeSpec

        assertEquals(
            emptyList<TypeName>(),
            classInspector.getAllSuperInterfaces(subclassTypeSpec)
        )
    }

    @Test
    fun `Generic base class with nested generic interface and covariant type argument produces interface`() {
        val baseTypeSpec = TypeSpec.classBuilder("Base")
            .addTypeVariable(
                TypeVariableName(
                    name = "T",
                    bounds = listOf(ANY.copy(nullable = true)),
                    variance = KModifier.OUT
                )
            )
            .addSuperinterface(
                SuperInterface5::class.asTypeName()
                    .parameterizedBy(
                        SuperInterface5::class.asTypeName()
                            .parameterizedBy(
                                TypeVariableName(name = "T")
                            )
                    )
            )
            .build()

        val subclassTypeSpec = TypeSpec.classBuilder("Subclass")
            .addTypeVariable(
                TypeVariableName(
                    name = "T",
                    bounds = listOf(ANY.copy(nullable = true)),
                    variance = KModifier.OUT
                )
            )
            .superclass(
                ClassName("", baseTypeSpec.name!!).parameterizedBy(TypeVariableName(name = "T"))
            )
            .build()

        every {
            classInspector.classFor(ClassName("", "Base"))
                .toTypeSpec(classInspector, ClassName("", "Base"))
        } returns baseTypeSpec

        assertEquals(
            listOf<TypeName>(
                SuperInterface5::class.asTypeName().parameterizedBy(
                    SuperInterface5::class.asTypeName().parameterizedBy(
                        STAR
                    )
                )
            ),
            classInspector.getAllSuperInterfaces(subclassTypeSpec)
        )
    }

    @Test
    fun `Generic base class with generic subclass and nested generic interface and covariant type argument produces interface`() {
        val baseTypeSpec = TypeSpec.classBuilder("Base")
            .addTypeVariable(
                TypeVariableName(
                    name = "T",
                    bounds = listOf(ANY.copy(nullable = true)),
                    variance = KModifier.OUT
                )
            )
            .addSuperinterface(
                SuperInterface5::class.asTypeName()
                    .parameterizedBy(
                        SuperInterface5::class.asTypeName()
                            .parameterizedBy(
                                TypeVariableName(name = "T")
                            )
                    )
            )
            .build()

        val firstSubclassTypeSpec= TypeSpec.classBuilder("FirstSubclass")
            .addTypeVariable(
                TypeVariableName(
                    name = "T",
                    bounds = listOf(ANY.copy(nullable = true)),
                    variance = KModifier.OUT
                )
            )
            .superclass(
                ClassName("", baseTypeSpec.name!!).parameterizedBy(TypeVariableName(name = "T"))
            )
            .build()


        val secondSubclassTypeSpec = TypeSpec.classBuilder("SecondSubclass")
            .addTypeVariable(
                TypeVariableName(
                    name = "T",
                    bounds = listOf(ANY.copy(nullable = true)),
                    variance = KModifier.OUT
                )
            )
            .superclass(
                ClassName("", firstSubclassTypeSpec.name!!).parameterizedBy(TypeVariableName(name = "T"))
            )
            .build()

        every {
            classInspector.classFor(ClassName("", "Base"))
                .toTypeSpec(classInspector, ClassName("", "Base"))
        } returns baseTypeSpec

        every {
            classInspector.classFor(ClassName("", "FirstSubclass"))
                .toTypeSpec(classInspector, ClassName("", "FirstSubclass"))
        } returns firstSubclassTypeSpec

        assertEquals(
            listOf<TypeName>(
                SuperInterface5::class.asTypeName().parameterizedBy(
                    SuperInterface5::class.asTypeName().parameterizedBy(
                        STAR
                    )
                )
            ),
            classInspector.getAllSuperInterfaces(secondSubclassTypeSpec)
        )
    }

    @AfterEach
    fun teardown() {
        unmockkAll()
    }
}
