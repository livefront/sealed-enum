package com.livefront.sealedenum.internal

import com.livefront.sealedenum.TreeTraversalOrder
import com.livefront.sealedenum.internal.SealedEnumFileSpec.SealedEnumOption.SealedEnumOnly
import com.livefront.sealedenum.internal.SealedEnumFileSpec.SealedEnumOption.SealedEnumWithEnum
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.STAR
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * The String for three quotes: `"""`
 */
private const val qqq = "\"\"\""

/**
 * The string for the dollar sign: `$`
 */
private const val d = "\$"

class SealedEnumFileSpecTests {

    @Test
    fun `empty sealed objects`() {
        val fileSpec = SealedEnumFileSpec(
            sealedClass = ClassName("com.livefront", "TestSealedClass"),
            sealedClassCompanionObjectElement = mockk(),
            sealedClassNode = SealedClassNode.SealedClass(emptyList()),
            typeParameters = emptyList(),
            sealedEnumOptions = mapOf(TreeTraversalOrder.IN_ORDER to SealedEnumOnly),
            sealedClassCompanionObject = ClassName("com.livefront", "TestSealedClass", "Companion")
        )

        val expected = """
            package com.livefront
            
            import com.livefront.sealedenum.SealedEnum
            import kotlin.Int
            import kotlin.String
            import kotlin.collections.List
            
            /**
             * An implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            object TestSealedClassSealedEnum : SealedEnum<TestSealedClass> {
                override val values: List<TestSealedClass> = emptyList()
            
            
                override fun ordinalOf(obj: TestSealedClass): Int = throw
                        AssertionError("Constructing a TestSealedClass is impossible, since it has no sealed subclasses")
            
                override fun nameOf(obj: TestSealedClass): String = throw
                        AssertionError("Constructing a TestSealedClass is impossible, since it has no sealed subclasses")
            
                override fun valueOf(name: String): TestSealedClass = throw
                        IllegalArgumentException(${qqq}No sealed enum constant ${d}name${qqq})
            }
            
            /**
             * The index of [this] in the values list.
             */
            val TestSealedClass.ordinal: Int
                get() = TestSealedClassSealedEnum.ordinalOf(this)
            
            /**
             * The name of [this] for use with valueOf.
             */
            val TestSealedClass.name: String
                get() = TestSealedClassSealedEnum.nameOf(this)
            
            /**
             * A list of all [TestSealedClass] objects.
             */
            val TestSealedClass.Companion.values: List<TestSealedClass>
                get() = TestSealedClassSealedEnum.values
            
            /**
             * Returns an implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            val TestSealedClass.Companion.sealedEnum: TestSealedClassSealedEnum
                get() = TestSealedClassSealedEnum
            
            /**
             * Returns the [TestSealedClass] object for the given [name].
             *
             * If the given name doesn't correspond to any [TestSealedClass], an [IllegalArgumentException] will
             * be thrown.
             */
            fun TestSealedClass.Companion.valueOf(name: String): TestSealedClass =
                    TestSealedClassSealedEnum.valueOf(name)

        """.trimIndent()

        assertEquals(expected, fileSpec.build().toString())
    }

    @Test
    fun `empty sealed objects with enum`() {
        val fileSpec = SealedEnumFileSpec(
            sealedClass = ClassName("com.livefront", "TestSealedClass"),
            sealedClassCompanionObjectElement = mockk(),
            sealedClassNode = SealedClassNode.SealedClass(emptyList()),
            typeParameters = emptyList(),
            sealedEnumOptions = mapOf(
                TreeTraversalOrder.IN_ORDER to SealedEnumWithEnum(emptyList())
            ),
            sealedClassCompanionObject = ClassName("com.livefront", "TestSealedClass", "Companion")
        )

        val expected = """
            package com.livefront
            
            import com.livefront.sealedenum.EnumForSealedEnumProvider
            import com.livefront.sealedenum.SealedEnum
            import com.livefront.sealedenum.SealedEnumWithEnumProvider
            import java.lang.Class
            import kotlin.Int
            import kotlin.String
            import kotlin.collections.List
            
            /**
             * An isomorphic enum for the sealed class [com.livefront.TestSealedClass]
             */
            enum class TestSealedClassEnum
            
            /**
             * The isomorphic [TestSealedClassEnum] for [this].
             */
            val TestSealedClass.enum: TestSealedClassEnum
                get() = TestSealedClassSealedEnum.sealedObjectToEnum(this)
            
            /**
             * The isomorphic [TestSealedClass] for [this].
             */
            val TestSealedClassEnum.sealedObject: TestSealedClass
                get() = TestSealedClassSealedEnum.enumToSealedObject(this)
            
            /**
             * An implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            object TestSealedClassSealedEnum : SealedEnum<TestSealedClass>,
                    SealedEnumWithEnumProvider<TestSealedClass, TestSealedClassEnum>,
                    EnumForSealedEnumProvider<TestSealedClass, TestSealedClassEnum> {
                override val values: List<TestSealedClass> = emptyList()
            
            
                override val enumClass: Class<TestSealedClassEnum>
                    get() = TestSealedClassEnum::class.java
            
                override fun ordinalOf(obj: TestSealedClass): Int = throw
                        AssertionError("Constructing a TestSealedClass is impossible, since it has no sealed subclasses")
            
                override fun nameOf(obj: TestSealedClass): String = throw
                        AssertionError("Constructing a TestSealedClass is impossible, since it has no sealed subclasses")
            
                override fun valueOf(name: String): TestSealedClass = throw
                        IllegalArgumentException(${qqq}No sealed enum constant ${d}name${qqq})
            
                override fun sealedObjectToEnum(obj: TestSealedClass): TestSealedClassEnum = throw
                        AssertionError("Constructing a TestSealedClass is impossible, since it has no sealed subclasses")
            
                override fun enumToSealedObject(enum: TestSealedClassEnum): TestSealedClass = throw
                        AssertionError("Constructing a TestSealedClass is impossible, since it has no sealed subclasses")
            }
            
            /**
             * The index of [this] in the values list.
             */
            val TestSealedClass.ordinal: Int
                get() = TestSealedClassSealedEnum.ordinalOf(this)
            
            /**
             * The name of [this] for use with valueOf.
             */
            val TestSealedClass.name: String
                get() = TestSealedClassSealedEnum.nameOf(this)
            
            /**
             * A list of all [TestSealedClass] objects.
             */
            val TestSealedClass.Companion.values: List<TestSealedClass>
                get() = TestSealedClassSealedEnum.values
            
            /**
             * Returns an implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            val TestSealedClass.Companion.sealedEnum: TestSealedClassSealedEnum
                get() = TestSealedClassSealedEnum
            
            /**
             * Returns the [TestSealedClass] object for the given [name].
             *
             * If the given name doesn't correspond to any [TestSealedClass], an [IllegalArgumentException] will
             * be thrown.
             */
            fun TestSealedClass.Companion.valueOf(name: String): TestSealedClass =
                    TestSealedClassSealedEnum.valueOf(name)

        """.trimIndent()

        assertEquals(expected, fileSpec.build().toString())
    }

    @Test
    fun `empty sealed objects with enum and interface`() {
        val fileSpec = SealedEnumFileSpec(
            sealedClass = ClassName("com.livefront", "TestSealedClass"),
            sealedClassCompanionObjectElement = mockk(),
            sealedClassNode = SealedClassNode.SealedClass(emptyList()),
            typeParameters = emptyList(),
            sealedEnumOptions = mapOf(
                TreeTraversalOrder.IN_ORDER to SealedEnumWithEnum(
                    listOf(ClassName("com.livefront", "TestA"))
                )
            ),
            sealedClassCompanionObject = ClassName("com.livefront", "TestSealedClass", "Companion")
        )

        val expected = """
            package com.livefront
            
            import com.livefront.sealedenum.EnumForSealedEnumProvider
            import com.livefront.sealedenum.SealedEnum
            import com.livefront.sealedenum.SealedEnumWithEnumProvider
            import java.lang.Class
            import kotlin.Int
            import kotlin.String
            import kotlin.collections.List
            
            /**
             * An isomorphic enum for the sealed class [com.livefront.TestSealedClass]
             */
            enum class TestSealedClassEnum(
                sealedObject: TestSealedClass
            ) : TestA by sealedObject
            
            /**
             * The isomorphic [TestSealedClassEnum] for [this].
             */
            val TestSealedClass.enum: TestSealedClassEnum
                get() = TestSealedClassSealedEnum.sealedObjectToEnum(this)
            
            /**
             * The isomorphic [TestSealedClass] for [this].
             */
            val TestSealedClassEnum.sealedObject: TestSealedClass
                get() = TestSealedClassSealedEnum.enumToSealedObject(this)
            
            /**
             * An implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            object TestSealedClassSealedEnum : SealedEnum<TestSealedClass>,
                    SealedEnumWithEnumProvider<TestSealedClass, TestSealedClassEnum>,
                    EnumForSealedEnumProvider<TestSealedClass, TestSealedClassEnum> {
                override val values: List<TestSealedClass> = emptyList()
            
            
                override val enumClass: Class<TestSealedClassEnum>
                    get() = TestSealedClassEnum::class.java
            
                override fun ordinalOf(obj: TestSealedClass): Int = throw
                        AssertionError("Constructing a TestSealedClass is impossible, since it has no sealed subclasses")
            
                override fun nameOf(obj: TestSealedClass): String = throw
                        AssertionError("Constructing a TestSealedClass is impossible, since it has no sealed subclasses")
            
                override fun valueOf(name: String): TestSealedClass = throw
                        IllegalArgumentException(${qqq}No sealed enum constant ${d}name${qqq})
            
                override fun sealedObjectToEnum(obj: TestSealedClass): TestSealedClassEnum = throw
                        AssertionError("Constructing a TestSealedClass is impossible, since it has no sealed subclasses")
            
                override fun enumToSealedObject(enum: TestSealedClassEnum): TestSealedClass = throw
                        AssertionError("Constructing a TestSealedClass is impossible, since it has no sealed subclasses")
            }
            
            /**
             * The index of [this] in the values list.
             */
            val TestSealedClass.ordinal: Int
                get() = TestSealedClassSealedEnum.ordinalOf(this)
            
            /**
             * The name of [this] for use with valueOf.
             */
            val TestSealedClass.name: String
                get() = TestSealedClassSealedEnum.nameOf(this)
            
            /**
             * A list of all [TestSealedClass] objects.
             */
            val TestSealedClass.Companion.values: List<TestSealedClass>
                get() = TestSealedClassSealedEnum.values
            
            /**
             * Returns an implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            val TestSealedClass.Companion.sealedEnum: TestSealedClassSealedEnum
                get() = TestSealedClassSealedEnum
            
            /**
             * Returns the [TestSealedClass] object for the given [name].
             *
             * If the given name doesn't correspond to any [TestSealedClass], an [IllegalArgumentException] will
             * be thrown.
             */
            fun TestSealedClass.Companion.valueOf(name: String): TestSealedClass =
                    TestSealedClassSealedEnum.valueOf(name)

        """.trimIndent()

        assertEquals(expected, fileSpec.build().toString())
    }

    @Test
    fun `one object sealed class`() {
        val fileSpec = SealedEnumFileSpec(
            sealedClass = ClassName("com.livefront", "TestSealedClass"),
            sealedClassCompanionObjectElement = mockk(),
            sealedClassNode = SealedClassNode.SealedClass(
                listOf(
                    SealedClassNode.Object(ClassName("com.livefront", "TestSealedClass", "FirstObject"))
                )
            ),
            typeParameters = emptyList(),
            sealedEnumOptions = mapOf(TreeTraversalOrder.IN_ORDER to SealedEnumOnly),
            sealedClassCompanionObject = ClassName("com.livefront", "TestSealedClass", "Companion")
        )

        val expected = """
            package com.livefront
            
            import com.livefront.sealedenum.SealedEnum
            import kotlin.Int
            import kotlin.String
            import kotlin.collections.List
            
            /**
             * An implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            object TestSealedClassSealedEnum : SealedEnum<TestSealedClass> {
                override val values: List<TestSealedClass> = listOf(
                    TestSealedClass.FirstObject
                )
            
            
                override fun ordinalOf(obj: TestSealedClass): Int = when (obj) {
                    TestSealedClass.FirstObject -> 0
                }
            
                override fun nameOf(obj: TestSealedClass): String = when (obj) {
                    TestSealedClass.FirstObject -> "TestSealedClass_FirstObject"
                }
            
                override fun valueOf(name: String): TestSealedClass = when (name) {
                    "TestSealedClass_FirstObject" -> TestSealedClass.FirstObject
                    else -> throw IllegalArgumentException(${qqq}No sealed enum constant ${d}name${qqq})
                }
            }
            
            /**
             * The index of [this] in the values list.
             */
            val TestSealedClass.ordinal: Int
                get() = TestSealedClassSealedEnum.ordinalOf(this)
            
            /**
             * The name of [this] for use with valueOf.
             */
            val TestSealedClass.name: String
                get() = TestSealedClassSealedEnum.nameOf(this)
            
            /**
             * A list of all [TestSealedClass] objects.
             */
            val TestSealedClass.Companion.values: List<TestSealedClass>
                get() = TestSealedClassSealedEnum.values
            
            /**
             * Returns an implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            val TestSealedClass.Companion.sealedEnum: TestSealedClassSealedEnum
                get() = TestSealedClassSealedEnum
            
            /**
             * Returns the [TestSealedClass] object for the given [name].
             *
             * If the given name doesn't correspond to any [TestSealedClass], an [IllegalArgumentException] will
             * be thrown.
             */
            fun TestSealedClass.Companion.valueOf(name: String): TestSealedClass =
                    TestSealedClassSealedEnum.valueOf(name)

        """.trimIndent()

        assertEquals(expected, fileSpec.build().toString())
    }

    @Test
    fun `two object sealed class`() {
        val fileSpec = SealedEnumFileSpec(
            sealedClass = ClassName("com.livefront", "TestSealedClass"),
            sealedClassCompanionObjectElement = mockk(),
            sealedClassNode = SealedClassNode.SealedClass(
                listOf(
                    SealedClassNode.Object(ClassName("com.livefront", "TestSealedClass", "FirstObject")),
                    SealedClassNode.Object(ClassName("com.livefront", "TestSealedClass", "SecondObject"))
                )
            ),
            typeParameters = emptyList(),
            sealedEnumOptions = mapOf(TreeTraversalOrder.IN_ORDER to SealedEnumOnly),
            sealedClassCompanionObject = ClassName("com.livefront", "TestSealedClass", "Companion")
        )

        val expected = """
            package com.livefront
            
            import com.livefront.sealedenum.SealedEnum
            import kotlin.Int
            import kotlin.String
            import kotlin.collections.List
            
            /**
             * An implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            object TestSealedClassSealedEnum : SealedEnum<TestSealedClass> {
                override val values: List<TestSealedClass> = listOf(
                    TestSealedClass.FirstObject,
                    TestSealedClass.SecondObject
                )
            
            
                override fun ordinalOf(obj: TestSealedClass): Int = when (obj) {
                    TestSealedClass.FirstObject -> 0
                    TestSealedClass.SecondObject -> 1
                }
            
                override fun nameOf(obj: TestSealedClass): String = when (obj) {
                    TestSealedClass.FirstObject -> "TestSealedClass_FirstObject"
                    TestSealedClass.SecondObject -> "TestSealedClass_SecondObject"
                }
            
                override fun valueOf(name: String): TestSealedClass = when (name) {
                    "TestSealedClass_FirstObject" -> TestSealedClass.FirstObject
                    "TestSealedClass_SecondObject" -> TestSealedClass.SecondObject
                    else -> throw IllegalArgumentException(${qqq}No sealed enum constant ${d}name${qqq})
                }
            }
            
            /**
             * The index of [this] in the values list.
             */
            val TestSealedClass.ordinal: Int
                get() = TestSealedClassSealedEnum.ordinalOf(this)
            
            /**
             * The name of [this] for use with valueOf.
             */
            val TestSealedClass.name: String
                get() = TestSealedClassSealedEnum.nameOf(this)
            
            /**
             * A list of all [TestSealedClass] objects.
             */
            val TestSealedClass.Companion.values: List<TestSealedClass>
                get() = TestSealedClassSealedEnum.values
            
            /**
             * Returns an implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            val TestSealedClass.Companion.sealedEnum: TestSealedClassSealedEnum
                get() = TestSealedClassSealedEnum
            
            /**
             * Returns the [TestSealedClass] object for the given [name].
             *
             * If the given name doesn't correspond to any [TestSealedClass], an [IllegalArgumentException] will
             * be thrown.
             */
            fun TestSealedClass.Companion.valueOf(name: String): TestSealedClass =
                    TestSealedClassSealedEnum.valueOf(name)

        """.trimIndent()

        assertEquals(expected, fileSpec.build().toString())
    }

    @Test
    fun `two object sealed class with enum`() {
        val fileSpec = SealedEnumFileSpec(
            sealedClass = ClassName("com.livefront", "TestSealedClass"),
            sealedClassCompanionObjectElement = mockk(),
            sealedClassNode = SealedClassNode.SealedClass(
                listOf(
                    SealedClassNode.Object(ClassName("com.livefront", "TestSealedClass", "FirstObject")),
                    SealedClassNode.Object(ClassName("com.livefront", "TestSealedClass", "SecondObject"))
                )
            ),
            typeParameters = emptyList(),
            sealedEnumOptions = mapOf(TreeTraversalOrder.IN_ORDER to SealedEnumWithEnum(emptyList())),
            sealedClassCompanionObject = ClassName("com.livefront", "TestSealedClass", "Companion")
        )

        val expected = """
            package com.livefront
            
            import com.livefront.sealedenum.EnumForSealedEnumProvider
            import com.livefront.sealedenum.SealedEnum
            import com.livefront.sealedenum.SealedEnumWithEnumProvider
            import java.lang.Class
            import kotlin.Int
            import kotlin.String
            import kotlin.collections.List
            
            /**
             * An isomorphic enum for the sealed class [com.livefront.TestSealedClass]
             */
            enum class TestSealedClassEnum {
                TestSealedClass_FirstObject,
            
                TestSealedClass_SecondObject
            }
            
            /**
             * The isomorphic [TestSealedClassEnum] for [this].
             */
            val TestSealedClass.enum: TestSealedClassEnum
                get() = TestSealedClassSealedEnum.sealedObjectToEnum(this)
            
            /**
             * The isomorphic [TestSealedClass] for [this].
             */
            val TestSealedClassEnum.sealedObject: TestSealedClass
                get() = TestSealedClassSealedEnum.enumToSealedObject(this)
            
            /**
             * An implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            object TestSealedClassSealedEnum : SealedEnum<TestSealedClass>,
                    SealedEnumWithEnumProvider<TestSealedClass, TestSealedClassEnum>,
                    EnumForSealedEnumProvider<TestSealedClass, TestSealedClassEnum> {
                override val values: List<TestSealedClass> = listOf(
                    TestSealedClass.FirstObject,
                    TestSealedClass.SecondObject
                )
            
            
                override val enumClass: Class<TestSealedClassEnum>
                    get() = TestSealedClassEnum::class.java
            
                override fun ordinalOf(obj: TestSealedClass): Int = when (obj) {
                    TestSealedClass.FirstObject -> 0
                    TestSealedClass.SecondObject -> 1
                }
            
                override fun nameOf(obj: TestSealedClass): String = when (obj) {
                    TestSealedClass.FirstObject -> "TestSealedClass_FirstObject"
                    TestSealedClass.SecondObject -> "TestSealedClass_SecondObject"
                }
            
                override fun valueOf(name: String): TestSealedClass = when (name) {
                    "TestSealedClass_FirstObject" -> TestSealedClass.FirstObject
                    "TestSealedClass_SecondObject" -> TestSealedClass.SecondObject
                    else -> throw IllegalArgumentException(${qqq}No sealed enum constant ${d}name${qqq})
                }
            
                override fun sealedObjectToEnum(obj: TestSealedClass): TestSealedClassEnum = when (obj) {
                    TestSealedClass.FirstObject -> TestSealedClassEnum.TestSealedClass_FirstObject
                    TestSealedClass.SecondObject -> TestSealedClassEnum.TestSealedClass_SecondObject
                }
            
                override fun enumToSealedObject(enum: TestSealedClassEnum): TestSealedClass = when (enum) {
                    TestSealedClassEnum.TestSealedClass_FirstObject -> TestSealedClass.FirstObject
                    TestSealedClassEnum.TestSealedClass_SecondObject -> TestSealedClass.SecondObject
                }
            }
            
            /**
             * The index of [this] in the values list.
             */
            val TestSealedClass.ordinal: Int
                get() = TestSealedClassSealedEnum.ordinalOf(this)
            
            /**
             * The name of [this] for use with valueOf.
             */
            val TestSealedClass.name: String
                get() = TestSealedClassSealedEnum.nameOf(this)
            
            /**
             * A list of all [TestSealedClass] objects.
             */
            val TestSealedClass.Companion.values: List<TestSealedClass>
                get() = TestSealedClassSealedEnum.values
            
            /**
             * Returns an implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            val TestSealedClass.Companion.sealedEnum: TestSealedClassSealedEnum
                get() = TestSealedClassSealedEnum
            
            /**
             * Returns the [TestSealedClass] object for the given [name].
             *
             * If the given name doesn't correspond to any [TestSealedClass], an [IllegalArgumentException] will
             * be thrown.
             */
            fun TestSealedClass.Companion.valueOf(name: String): TestSealedClass =
                    TestSealedClassSealedEnum.valueOf(name)

        """.trimIndent()

        assertEquals(expected, fileSpec.build().toString())
    }

    @Test
    fun `two object sealed class with enum and interface`() {
        val fileSpec = SealedEnumFileSpec(
            sealedClass = ClassName("com.livefront", "TestSealedClass"),
            sealedClassCompanionObjectElement = mockk(),
            sealedClassNode = SealedClassNode.SealedClass(
                listOf(
                    SealedClassNode.Object(ClassName("com.livefront", "TestSealedClass", "FirstObject")),
                    SealedClassNode.Object(ClassName("com.livefront", "TestSealedClass", "SecondObject"))
                )
            ),
            typeParameters = emptyList(),
            sealedEnumOptions = mapOf(
                TreeTraversalOrder.IN_ORDER to SealedEnumWithEnum(
                    listOf(ClassName("com.livefront", "TestA"))
                )
            ),
            sealedClassCompanionObject = ClassName("com.livefront", "TestSealedClass", "Companion")
        )

        val expected = """
            package com.livefront
            
            import com.livefront.sealedenum.EnumForSealedEnumProvider
            import com.livefront.sealedenum.SealedEnum
            import com.livefront.sealedenum.SealedEnumWithEnumProvider
            import java.lang.Class
            import kotlin.Int
            import kotlin.String
            import kotlin.collections.List
            
            /**
             * An isomorphic enum for the sealed class [com.livefront.TestSealedClass]
             */
            enum class TestSealedClassEnum(
                sealedObject: TestSealedClass
            ) : TestA by sealedObject {
                TestSealedClass_FirstObject(com.livefront.TestSealedClass.FirstObject),
            
                TestSealedClass_SecondObject(com.livefront.TestSealedClass.SecondObject)
            }
            
            /**
             * The isomorphic [TestSealedClassEnum] for [this].
             */
            val TestSealedClass.enum: TestSealedClassEnum
                get() = TestSealedClassSealedEnum.sealedObjectToEnum(this)
            
            /**
             * The isomorphic [TestSealedClass] for [this].
             */
            val TestSealedClassEnum.sealedObject: TestSealedClass
                get() = TestSealedClassSealedEnum.enumToSealedObject(this)
            
            /**
             * An implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            object TestSealedClassSealedEnum : SealedEnum<TestSealedClass>,
                    SealedEnumWithEnumProvider<TestSealedClass, TestSealedClassEnum>,
                    EnumForSealedEnumProvider<TestSealedClass, TestSealedClassEnum> {
                override val values: List<TestSealedClass> = listOf(
                    TestSealedClass.FirstObject,
                    TestSealedClass.SecondObject
                )
            
            
                override val enumClass: Class<TestSealedClassEnum>
                    get() = TestSealedClassEnum::class.java
            
                override fun ordinalOf(obj: TestSealedClass): Int = when (obj) {
                    TestSealedClass.FirstObject -> 0
                    TestSealedClass.SecondObject -> 1
                }
            
                override fun nameOf(obj: TestSealedClass): String = when (obj) {
                    TestSealedClass.FirstObject -> "TestSealedClass_FirstObject"
                    TestSealedClass.SecondObject -> "TestSealedClass_SecondObject"
                }
            
                override fun valueOf(name: String): TestSealedClass = when (name) {
                    "TestSealedClass_FirstObject" -> TestSealedClass.FirstObject
                    "TestSealedClass_SecondObject" -> TestSealedClass.SecondObject
                    else -> throw IllegalArgumentException(${qqq}No sealed enum constant ${d}name${qqq})
                }
            
                override fun sealedObjectToEnum(obj: TestSealedClass): TestSealedClassEnum = when (obj) {
                    TestSealedClass.FirstObject -> TestSealedClassEnum.TestSealedClass_FirstObject
                    TestSealedClass.SecondObject -> TestSealedClassEnum.TestSealedClass_SecondObject
                }
            
                override fun enumToSealedObject(enum: TestSealedClassEnum): TestSealedClass = when (enum) {
                    TestSealedClassEnum.TestSealedClass_FirstObject -> TestSealedClass.FirstObject
                    TestSealedClassEnum.TestSealedClass_SecondObject -> TestSealedClass.SecondObject
                }
            }
            
            /**
             * The index of [this] in the values list.
             */
            val TestSealedClass.ordinal: Int
                get() = TestSealedClassSealedEnum.ordinalOf(this)
            
            /**
             * The name of [this] for use with valueOf.
             */
            val TestSealedClass.name: String
                get() = TestSealedClassSealedEnum.nameOf(this)
            
            /**
             * A list of all [TestSealedClass] objects.
             */
            val TestSealedClass.Companion.values: List<TestSealedClass>
                get() = TestSealedClassSealedEnum.values
            
            /**
             * Returns an implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            val TestSealedClass.Companion.sealedEnum: TestSealedClassSealedEnum
                get() = TestSealedClassSealedEnum
            
            /**
             * Returns the [TestSealedClass] object for the given [name].
             *
             * If the given name doesn't correspond to any [TestSealedClass], an [IllegalArgumentException] will
             * be thrown.
             */
            fun TestSealedClass.Companion.valueOf(name: String): TestSealedClass =
                    TestSealedClassSealedEnum.valueOf(name)

        """.trimIndent()

        assertEquals(expected, fileSpec.build().toString())
    }

    @Test
    fun `inner sealed class`() {
        val fileSpec = SealedEnumFileSpec(
            sealedClass = ClassName("com.livefront", "OuterClass", "TestSealedClass"),
            sealedClassCompanionObjectElement = mockk(),
            sealedClassNode = SealedClassNode.SealedClass(
                listOf(
                    SealedClassNode.Object(ClassName("com.livefront", "OuterClass", "TestSealedClass", "FirstObject"))
                )
            ),
            typeParameters = emptyList(),
            sealedEnumOptions = mapOf(TreeTraversalOrder.IN_ORDER to SealedEnumOnly),
            sealedClassCompanionObject = ClassName("com.livefront", "TestSealedClass", "Companion")
        )

        val expected = """
            package com.livefront
            
            import com.livefront.sealedenum.SealedEnum
            import kotlin.Int
            import kotlin.String
            import kotlin.collections.List
            
            /**
             * An implementation of [SealedEnum] for the sealed class [OuterClass.TestSealedClass]
             */
            object OuterClass_TestSealedClassSealedEnum : SealedEnum<OuterClass.TestSealedClass> {
                override val values: List<OuterClass.TestSealedClass> = listOf(
                    OuterClass.TestSealedClass.FirstObject
                )
            
            
                override fun ordinalOf(obj: OuterClass.TestSealedClass): Int = when (obj) {
                    OuterClass.TestSealedClass.FirstObject -> 0
                }
            
                override fun nameOf(obj: OuterClass.TestSealedClass): String = when (obj) {
                    OuterClass.TestSealedClass.FirstObject -> "OuterClass_TestSealedClass_FirstObject"
                }
            
                override fun valueOf(name: String): OuterClass.TestSealedClass = when (name) {
                    "OuterClass_TestSealedClass_FirstObject" -> OuterClass.TestSealedClass.FirstObject
                    else -> throw IllegalArgumentException(${qqq}No sealed enum constant ${d}name${qqq})
                }
            }
            
            /**
             * The index of [this] in the values list.
             */
            val OuterClass.TestSealedClass.ordinal: Int
                get() = OuterClass_TestSealedClassSealedEnum.ordinalOf(this)
            
            /**
             * The name of [this] for use with valueOf.
             */
            val OuterClass.TestSealedClass.name: String
                get() = OuterClass_TestSealedClassSealedEnum.nameOf(this)
            
            /**
             * A list of all [OuterClass.TestSealedClass] objects.
             */
            val TestSealedClass.Companion.values: List<OuterClass.TestSealedClass>
                get() = OuterClass_TestSealedClassSealedEnum.values
            
            /**
             * Returns an implementation of [SealedEnum] for the sealed class [OuterClass.TestSealedClass]
             */
            val TestSealedClass.Companion.sealedEnum: OuterClass_TestSealedClassSealedEnum
                get() = OuterClass_TestSealedClassSealedEnum
            
            /**
             * Returns the [OuterClass.TestSealedClass] object for the given [name].
             *
             * If the given name doesn't correspond to any [OuterClass.TestSealedClass], an
             * [IllegalArgumentException] will be thrown.
             */
            fun TestSealedClass.Companion.valueOf(name: String): OuterClass.TestSealedClass =
                    OuterClass_TestSealedClassSealedEnum.valueOf(name)

        """.trimIndent()

        assertEquals(expected, fileSpec.build().toString())
    }

    @Test
    fun `generic sealed class`() {
        val fileSpec = SealedEnumFileSpec(
            sealedClass = ClassName("com.livefront", "TestSealedClass"),
            sealedClassCompanionObjectElement = mockk(),
            sealedClassNode = SealedClassNode.SealedClass(
                listOf(
                    SealedClassNode.Object(ClassName("com.livefront", "TestSealedClass", "FirstObject"))
                )
            ),
            typeParameters = listOf(STAR),
            sealedEnumOptions = mapOf(TreeTraversalOrder.IN_ORDER to SealedEnumOnly),
            sealedClassCompanionObject = ClassName("com.livefront", "TestSealedClass", "Companion")
        )

        val expected = """
            package com.livefront
            
            import com.livefront.sealedenum.SealedEnum
            import kotlin.Int
            import kotlin.String
            import kotlin.collections.List
            
            /**
             * An implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            object TestSealedClassSealedEnum : SealedEnum<TestSealedClass<*>> {
                override val values: List<TestSealedClass<*>> = listOf(
                    TestSealedClass.FirstObject
                )
            
            
                override fun ordinalOf(obj: TestSealedClass<*>): Int = when (obj) {
                    TestSealedClass.FirstObject -> 0
                }
            
                override fun nameOf(obj: TestSealedClass<*>): String = when (obj) {
                    TestSealedClass.FirstObject -> "TestSealedClass_FirstObject"
                }
            
                override fun valueOf(name: String): TestSealedClass<*> = when (name) {
                    "TestSealedClass_FirstObject" -> TestSealedClass.FirstObject
                    else -> throw IllegalArgumentException(${qqq}No sealed enum constant ${d}name${qqq})
                }
            }
            
            /**
             * The index of [this] in the values list.
             */
            val TestSealedClass<*>.ordinal: Int
                get() = TestSealedClassSealedEnum.ordinalOf(this)
            
            /**
             * The name of [this] for use with valueOf.
             */
            val TestSealedClass<*>.name: String
                get() = TestSealedClassSealedEnum.nameOf(this)
            
            /**
             * A list of all [TestSealedClass] objects.
             */
            val TestSealedClass.Companion.values: List<TestSealedClass<*>>
                get() = TestSealedClassSealedEnum.values
            
            /**
             * Returns an implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            val TestSealedClass.Companion.sealedEnum: TestSealedClassSealedEnum
                get() = TestSealedClassSealedEnum
            
            /**
             * Returns the [TestSealedClass] object for the given [name].
             *
             * If the given name doesn't correspond to any [TestSealedClass], an [IllegalArgumentException] will
             * be thrown.
             */
            fun TestSealedClass.Companion.valueOf(name: String): TestSealedClass<*> =
                    TestSealedClassSealedEnum.valueOf(name)

        """.trimIndent()

        assertEquals(expected, fileSpec.build().toString())
    }

    @Test
    fun `multiple traversal orders`() {
        val fileSpec = SealedEnumFileSpec(
            sealedClass = ClassName("com.livefront", "TestSealedClass"),
            sealedClassCompanionObjectElement = mockk(),
            sealedClassNode = SealedClassNode.SealedClass(
                listOf(
                    SealedClassNode.Object(ClassName("com.livefront", "TestSealedClass", "FirstObject"))
                )
            ),
            typeParameters = listOf(STAR),
            sealedEnumOptions = mapOf(
                TreeTraversalOrder.IN_ORDER to SealedEnumOnly,
                TreeTraversalOrder.LEVEL_ORDER to SealedEnumOnly
            ),
            sealedClassCompanionObject = ClassName("com.livefront", "TestSealedClass", "Companion")
        )

        val expected = """
            package com.livefront
            
            import com.livefront.sealedenum.SealedEnum
            import kotlin.Int
            import kotlin.String
            import kotlin.collections.List
            
            /**
             * An implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            object TestSealedClassInOrderSealedEnum : SealedEnum<TestSealedClass<*>> {
                override val values: List<TestSealedClass<*>> = listOf(
                    TestSealedClass.FirstObject
                )
            
            
                override fun ordinalOf(obj: TestSealedClass<*>): Int = when (obj) {
                    TestSealedClass.FirstObject -> 0
                }
            
                override fun nameOf(obj: TestSealedClass<*>): String = when (obj) {
                    TestSealedClass.FirstObject -> "TestSealedClass_FirstObject"
                }
            
                override fun valueOf(name: String): TestSealedClass<*> = when (name) {
                    "TestSealedClass_FirstObject" -> TestSealedClass.FirstObject
                    else -> throw IllegalArgumentException(${qqq}No sealed enum constant ${d}name${qqq})
                }
            }
            
            /**
             * The index of [this] in the values list.
             */
            val TestSealedClass<*>.inOrderOrdinal: Int
                get() = TestSealedClassInOrderSealedEnum.ordinalOf(this)
            
            /**
             * The name of [this] for use with valueOf.
             */
            val TestSealedClass<*>.inOrderName: String
                get() = TestSealedClassInOrderSealedEnum.nameOf(this)
            
            /**
             * A list of all [TestSealedClass] objects.
             */
            val TestSealedClass.Companion.inOrderValues: List<TestSealedClass<*>>
                get() = TestSealedClassInOrderSealedEnum.values
            
            /**
             * Returns an implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            val TestSealedClass.Companion.inOrderSealedEnum: TestSealedClassInOrderSealedEnum
                get() = TestSealedClassInOrderSealedEnum
            
            /**
             * Returns the [TestSealedClass] object for the given [name].
             *
             * If the given name doesn't correspond to any [TestSealedClass], an [IllegalArgumentException] will
             * be thrown.
             */
            fun TestSealedClass.Companion.inOrderValueOf(name: String): TestSealedClass<*> =
                    TestSealedClassInOrderSealedEnum.valueOf(name)
            
            /**
             * An implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            object TestSealedClassLevelOrderSealedEnum : SealedEnum<TestSealedClass<*>> {
                override val values: List<TestSealedClass<*>> = listOf(
                    TestSealedClass.FirstObject
                )
            
            
                override fun ordinalOf(obj: TestSealedClass<*>): Int = when (obj) {
                    TestSealedClass.FirstObject -> 0
                }
            
                override fun nameOf(obj: TestSealedClass<*>): String = when (obj) {
                    TestSealedClass.FirstObject -> "TestSealedClass_FirstObject"
                }
            
                override fun valueOf(name: String): TestSealedClass<*> = when (name) {
                    "TestSealedClass_FirstObject" -> TestSealedClass.FirstObject
                    else -> throw IllegalArgumentException(${qqq}No sealed enum constant ${d}name${qqq})
                }
            }
            
            /**
             * The index of [this] in the values list.
             */
            val TestSealedClass<*>.levelOrderOrdinal: Int
                get() = TestSealedClassLevelOrderSealedEnum.ordinalOf(this)
            
            /**
             * The name of [this] for use with valueOf.
             */
            val TestSealedClass<*>.levelOrderName: String
                get() = TestSealedClassLevelOrderSealedEnum.nameOf(this)
            
            /**
             * A list of all [TestSealedClass] objects.
             */
            val TestSealedClass.Companion.levelOrderValues: List<TestSealedClass<*>>
                get() = TestSealedClassLevelOrderSealedEnum.values
            
            /**
             * Returns an implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            val TestSealedClass.Companion.levelOrderSealedEnum: TestSealedClassLevelOrderSealedEnum
                get() = TestSealedClassLevelOrderSealedEnum
            
            /**
             * Returns the [TestSealedClass] object for the given [name].
             *
             * If the given name doesn't correspond to any [TestSealedClass], an [IllegalArgumentException] will
             * be thrown.
             */
            fun TestSealedClass.Companion.levelOrderValueOf(name: String): TestSealedClass<*> =
                    TestSealedClassLevelOrderSealedEnum.valueOf(name)

        """.trimIndent()

        assertEquals(expected, fileSpec.build().toString())
    }

    @Test
    fun `all traversal orders`() {
        val fileSpec = SealedEnumFileSpec(
            sealedClass = ClassName("com.livefront", "TestSealedClass"),
            sealedClassCompanionObjectElement = mockk(),
            sealedClassNode = SealedClassNode.SealedClass(
                listOf(
                    SealedClassNode.Object(ClassName("com.livefront", "TestSealedClass", "FirstObject"))
                )
            ),
            typeParameters = listOf(STAR),
            sealedEnumOptions = mapOf(
                TreeTraversalOrder.IN_ORDER to SealedEnumOnly,
                TreeTraversalOrder.POST_ORDER to SealedEnumOnly,
                TreeTraversalOrder.LEVEL_ORDER to SealedEnumOnly,
                TreeTraversalOrder.PRE_ORDER to SealedEnumOnly
            ),
            sealedClassCompanionObject = ClassName("com.livefront", "TestSealedClass", "Companion")
        )

        val expected = """
            package com.livefront
            
            import com.livefront.sealedenum.SealedEnum
            import kotlin.Int
            import kotlin.String
            import kotlin.collections.List
            
            /**
             * An implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            object TestSealedClassInOrderSealedEnum : SealedEnum<TestSealedClass<*>> {
                override val values: List<TestSealedClass<*>> = listOf(
                    TestSealedClass.FirstObject
                )
            
            
                override fun ordinalOf(obj: TestSealedClass<*>): Int = when (obj) {
                    TestSealedClass.FirstObject -> 0
                }
            
                override fun nameOf(obj: TestSealedClass<*>): String = when (obj) {
                    TestSealedClass.FirstObject -> "TestSealedClass_FirstObject"
                }
            
                override fun valueOf(name: String): TestSealedClass<*> = when (name) {
                    "TestSealedClass_FirstObject" -> TestSealedClass.FirstObject
                    else -> throw IllegalArgumentException(${qqq}No sealed enum constant ${d}name${qqq})
                }
            }
            
            /**
             * The index of [this] in the values list.
             */
            val TestSealedClass<*>.inOrderOrdinal: Int
                get() = TestSealedClassInOrderSealedEnum.ordinalOf(this)
            
            /**
             * The name of [this] for use with valueOf.
             */
            val TestSealedClass<*>.inOrderName: String
                get() = TestSealedClassInOrderSealedEnum.nameOf(this)
            
            /**
             * A list of all [TestSealedClass] objects.
             */
            val TestSealedClass.Companion.inOrderValues: List<TestSealedClass<*>>
                get() = TestSealedClassInOrderSealedEnum.values
            
            /**
             * Returns an implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            val TestSealedClass.Companion.inOrderSealedEnum: TestSealedClassInOrderSealedEnum
                get() = TestSealedClassInOrderSealedEnum
            
            /**
             * Returns the [TestSealedClass] object for the given [name].
             *
             * If the given name doesn't correspond to any [TestSealedClass], an [IllegalArgumentException] will
             * be thrown.
             */
            fun TestSealedClass.Companion.inOrderValueOf(name: String): TestSealedClass<*> =
                    TestSealedClassInOrderSealedEnum.valueOf(name)
            
            /**
             * An implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            object TestSealedClassPostOrderSealedEnum : SealedEnum<TestSealedClass<*>> {
                override val values: List<TestSealedClass<*>> = listOf(
                    TestSealedClass.FirstObject
                )
            
            
                override fun ordinalOf(obj: TestSealedClass<*>): Int = when (obj) {
                    TestSealedClass.FirstObject -> 0
                }
            
                override fun nameOf(obj: TestSealedClass<*>): String = when (obj) {
                    TestSealedClass.FirstObject -> "TestSealedClass_FirstObject"
                }
            
                override fun valueOf(name: String): TestSealedClass<*> = when (name) {
                    "TestSealedClass_FirstObject" -> TestSealedClass.FirstObject
                    else -> throw IllegalArgumentException(${qqq}No sealed enum constant ${d}name${qqq})
                }
            }
            
            /**
             * The index of [this] in the values list.
             */
            val TestSealedClass<*>.postOrderOrdinal: Int
                get() = TestSealedClassPostOrderSealedEnum.ordinalOf(this)
            
            /**
             * The name of [this] for use with valueOf.
             */
            val TestSealedClass<*>.postOrderName: String
                get() = TestSealedClassPostOrderSealedEnum.nameOf(this)
            
            /**
             * A list of all [TestSealedClass] objects.
             */
            val TestSealedClass.Companion.postOrderValues: List<TestSealedClass<*>>
                get() = TestSealedClassPostOrderSealedEnum.values
            
            /**
             * Returns an implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            val TestSealedClass.Companion.postOrderSealedEnum: TestSealedClassPostOrderSealedEnum
                get() = TestSealedClassPostOrderSealedEnum
            
            /**
             * Returns the [TestSealedClass] object for the given [name].
             *
             * If the given name doesn't correspond to any [TestSealedClass], an [IllegalArgumentException] will
             * be thrown.
             */
            fun TestSealedClass.Companion.postOrderValueOf(name: String): TestSealedClass<*> =
                    TestSealedClassPostOrderSealedEnum.valueOf(name)
            
            /**
             * An implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            object TestSealedClassLevelOrderSealedEnum : SealedEnum<TestSealedClass<*>> {
                override val values: List<TestSealedClass<*>> = listOf(
                    TestSealedClass.FirstObject
                )
            
            
                override fun ordinalOf(obj: TestSealedClass<*>): Int = when (obj) {
                    TestSealedClass.FirstObject -> 0
                }
            
                override fun nameOf(obj: TestSealedClass<*>): String = when (obj) {
                    TestSealedClass.FirstObject -> "TestSealedClass_FirstObject"
                }
            
                override fun valueOf(name: String): TestSealedClass<*> = when (name) {
                    "TestSealedClass_FirstObject" -> TestSealedClass.FirstObject
                    else -> throw IllegalArgumentException(${qqq}No sealed enum constant ${d}name${qqq})
                }
            }
            
            /**
             * The index of [this] in the values list.
             */
            val TestSealedClass<*>.levelOrderOrdinal: Int
                get() = TestSealedClassLevelOrderSealedEnum.ordinalOf(this)
            
            /**
             * The name of [this] for use with valueOf.
             */
            val TestSealedClass<*>.levelOrderName: String
                get() = TestSealedClassLevelOrderSealedEnum.nameOf(this)
            
            /**
             * A list of all [TestSealedClass] objects.
             */
            val TestSealedClass.Companion.levelOrderValues: List<TestSealedClass<*>>
                get() = TestSealedClassLevelOrderSealedEnum.values
            
            /**
             * Returns an implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            val TestSealedClass.Companion.levelOrderSealedEnum: TestSealedClassLevelOrderSealedEnum
                get() = TestSealedClassLevelOrderSealedEnum
            
            /**
             * Returns the [TestSealedClass] object for the given [name].
             *
             * If the given name doesn't correspond to any [TestSealedClass], an [IllegalArgumentException] will
             * be thrown.
             */
            fun TestSealedClass.Companion.levelOrderValueOf(name: String): TestSealedClass<*> =
                    TestSealedClassLevelOrderSealedEnum.valueOf(name)
            
            /**
             * An implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            object TestSealedClassPreOrderSealedEnum : SealedEnum<TestSealedClass<*>> {
                override val values: List<TestSealedClass<*>> = listOf(
                    TestSealedClass.FirstObject
                )
            
            
                override fun ordinalOf(obj: TestSealedClass<*>): Int = when (obj) {
                    TestSealedClass.FirstObject -> 0
                }
            
                override fun nameOf(obj: TestSealedClass<*>): String = when (obj) {
                    TestSealedClass.FirstObject -> "TestSealedClass_FirstObject"
                }
            
                override fun valueOf(name: String): TestSealedClass<*> = when (name) {
                    "TestSealedClass_FirstObject" -> TestSealedClass.FirstObject
                    else -> throw IllegalArgumentException(${qqq}No sealed enum constant ${d}name${qqq})
                }
            }
            
            /**
             * The index of [this] in the values list.
             */
            val TestSealedClass<*>.preOrderOrdinal: Int
                get() = TestSealedClassPreOrderSealedEnum.ordinalOf(this)
            
            /**
             * The name of [this] for use with valueOf.
             */
            val TestSealedClass<*>.preOrderName: String
                get() = TestSealedClassPreOrderSealedEnum.nameOf(this)
            
            /**
             * A list of all [TestSealedClass] objects.
             */
            val TestSealedClass.Companion.preOrderValues: List<TestSealedClass<*>>
                get() = TestSealedClassPreOrderSealedEnum.values
            
            /**
             * Returns an implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            val TestSealedClass.Companion.preOrderSealedEnum: TestSealedClassPreOrderSealedEnum
                get() = TestSealedClassPreOrderSealedEnum
            
            /**
             * Returns the [TestSealedClass] object for the given [name].
             *
             * If the given name doesn't correspond to any [TestSealedClass], an [IllegalArgumentException] will
             * be thrown.
             */
            fun TestSealedClass.Companion.preOrderValueOf(name: String): TestSealedClass<*> =
                    TestSealedClassPreOrderSealedEnum.valueOf(name)

        """.trimIndent()

        assertEquals(expected, fileSpec.build().toString())
    }

    @Test
    fun `all traversal orders with enum and interface`() {
        val fileSpec = SealedEnumFileSpec(
            sealedClass = ClassName("com.livefront", "TestSealedClass"),
            sealedClassCompanionObjectElement = mockk(),
            sealedClassNode = SealedClassNode.SealedClass(
                listOf(
                    SealedClassNode.Object(ClassName("com.livefront", "TestSealedClass", "FirstObject"))
                )
            ),
            typeParameters = listOf(STAR),
            sealedEnumOptions = mapOf(
                TreeTraversalOrder.IN_ORDER to SealedEnumWithEnum(
                    listOf(ClassName("com.livefront", "TestA"))
                ),
                TreeTraversalOrder.POST_ORDER to SealedEnumWithEnum(
                    listOf(ClassName("com.livefront", "TestA"))
                ),
                TreeTraversalOrder.LEVEL_ORDER to SealedEnumWithEnum(
                    listOf(ClassName("com.livefront", "TestA"))
                ),
                TreeTraversalOrder.PRE_ORDER to SealedEnumWithEnum(
                    listOf(ClassName("com.livefront", "TestA"))
                )
            ),
            sealedClassCompanionObject = ClassName("com.livefront", "TestSealedClass", "Companion")
        )

        val expected = """
            package com.livefront
            
            import com.livefront.sealedenum.EnumForSealedEnumProvider
            import com.livefront.sealedenum.SealedEnum
            import com.livefront.sealedenum.SealedEnumWithEnumProvider
            import java.lang.Class
            import kotlin.Int
            import kotlin.String
            import kotlin.collections.List
            
            /**
             * An isomorphic enum for the sealed class [com.livefront.TestSealedClass]
             */
            enum class TestSealedClassInOrderEnum(
                sealedObject: TestSealedClass<*>
            ) : TestA by sealedObject {
                TestSealedClass_FirstObject(com.livefront.TestSealedClass.FirstObject)
            }
            
            /**
             * The isomorphic [TestSealedClassInOrderEnum] for [this].
             */
            val TestSealedClass<*>.inOrderEnum: TestSealedClassInOrderEnum
                get() = TestSealedClassInOrderSealedEnum.sealedObjectToEnum(this)
            
            /**
             * The isomorphic [TestSealedClass] for [this].
             */
            val TestSealedClassInOrderEnum.sealedObject: TestSealedClass<*>
                get() = TestSealedClassInOrderSealedEnum.enumToSealedObject(this)
            
            /**
             * An implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            object TestSealedClassInOrderSealedEnum : SealedEnum<TestSealedClass<*>>,
                    SealedEnumWithEnumProvider<TestSealedClass<*>, TestSealedClassInOrderEnum>,
                    EnumForSealedEnumProvider<TestSealedClass<*>, TestSealedClassInOrderEnum> {
                override val values: List<TestSealedClass<*>> = listOf(
                    TestSealedClass.FirstObject
                )
            
            
                override val enumClass: Class<TestSealedClassInOrderEnum>
                    get() = TestSealedClassInOrderEnum::class.java
            
                override fun ordinalOf(obj: TestSealedClass<*>): Int = when (obj) {
                    TestSealedClass.FirstObject -> 0
                }
            
                override fun nameOf(obj: TestSealedClass<*>): String = when (obj) {
                    TestSealedClass.FirstObject -> "TestSealedClass_FirstObject"
                }
            
                override fun valueOf(name: String): TestSealedClass<*> = when (name) {
                    "TestSealedClass_FirstObject" -> TestSealedClass.FirstObject
                    else -> throw IllegalArgumentException(${qqq}No sealed enum constant ${d}name${qqq})
                }
            
                override fun sealedObjectToEnum(obj: TestSealedClass<*>): TestSealedClassInOrderEnum = when
                        (obj) {
                    TestSealedClass.FirstObject -> TestSealedClassInOrderEnum.TestSealedClass_FirstObject
                }
            
                override fun enumToSealedObject(enum: TestSealedClassInOrderEnum): TestSealedClass<*> = when
                        (enum) {
                    TestSealedClassInOrderEnum.TestSealedClass_FirstObject -> TestSealedClass.FirstObject
                }
            }
            
            /**
             * The index of [this] in the values list.
             */
            val TestSealedClass<*>.inOrderOrdinal: Int
                get() = TestSealedClassInOrderSealedEnum.ordinalOf(this)
            
            /**
             * The name of [this] for use with valueOf.
             */
            val TestSealedClass<*>.inOrderName: String
                get() = TestSealedClassInOrderSealedEnum.nameOf(this)
            
            /**
             * A list of all [TestSealedClass] objects.
             */
            val TestSealedClass.Companion.inOrderValues: List<TestSealedClass<*>>
                get() = TestSealedClassInOrderSealedEnum.values
            
            /**
             * Returns an implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            val TestSealedClass.Companion.inOrderSealedEnum: TestSealedClassInOrderSealedEnum
                get() = TestSealedClassInOrderSealedEnum
            
            /**
             * Returns the [TestSealedClass] object for the given [name].
             *
             * If the given name doesn't correspond to any [TestSealedClass], an [IllegalArgumentException] will
             * be thrown.
             */
            fun TestSealedClass.Companion.inOrderValueOf(name: String): TestSealedClass<*> =
                    TestSealedClassInOrderSealedEnum.valueOf(name)
            
            /**
             * An isomorphic enum for the sealed class [com.livefront.TestSealedClass]
             */
            enum class TestSealedClassPostOrderEnum(
                sealedObject: TestSealedClass<*>
            ) : TestA by sealedObject {
                TestSealedClass_FirstObject(com.livefront.TestSealedClass.FirstObject)
            }
            
            /**
             * The isomorphic [TestSealedClassPostOrderEnum] for [this].
             */
            val TestSealedClass<*>.postOrderEnum: TestSealedClassPostOrderEnum
                get() = TestSealedClassPostOrderSealedEnum.sealedObjectToEnum(this)
            
            /**
             * The isomorphic [TestSealedClass] for [this].
             */
            val TestSealedClassPostOrderEnum.sealedObject: TestSealedClass<*>
                get() = TestSealedClassPostOrderSealedEnum.enumToSealedObject(this)
            
            /**
             * An implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            object TestSealedClassPostOrderSealedEnum : SealedEnum<TestSealedClass<*>>,
                    SealedEnumWithEnumProvider<TestSealedClass<*>, TestSealedClassPostOrderEnum>,
                    EnumForSealedEnumProvider<TestSealedClass<*>, TestSealedClassPostOrderEnum> {
                override val values: List<TestSealedClass<*>> = listOf(
                    TestSealedClass.FirstObject
                )
            
            
                override val enumClass: Class<TestSealedClassPostOrderEnum>
                    get() = TestSealedClassPostOrderEnum::class.java
            
                override fun ordinalOf(obj: TestSealedClass<*>): Int = when (obj) {
                    TestSealedClass.FirstObject -> 0
                }
            
                override fun nameOf(obj: TestSealedClass<*>): String = when (obj) {
                    TestSealedClass.FirstObject -> "TestSealedClass_FirstObject"
                }
            
                override fun valueOf(name: String): TestSealedClass<*> = when (name) {
                    "TestSealedClass_FirstObject" -> TestSealedClass.FirstObject
                    else -> throw IllegalArgumentException(${qqq}No sealed enum constant ${d}name${qqq})
                }
            
                override fun sealedObjectToEnum(obj: TestSealedClass<*>): TestSealedClassPostOrderEnum = when
                        (obj) {
                    TestSealedClass.FirstObject -> TestSealedClassPostOrderEnum.TestSealedClass_FirstObject
                }
            
                override fun enumToSealedObject(enum: TestSealedClassPostOrderEnum): TestSealedClass<*> = when
                        (enum) {
                    TestSealedClassPostOrderEnum.TestSealedClass_FirstObject -> TestSealedClass.FirstObject
                }
            }
            
            /**
             * The index of [this] in the values list.
             */
            val TestSealedClass<*>.postOrderOrdinal: Int
                get() = TestSealedClassPostOrderSealedEnum.ordinalOf(this)
            
            /**
             * The name of [this] for use with valueOf.
             */
            val TestSealedClass<*>.postOrderName: String
                get() = TestSealedClassPostOrderSealedEnum.nameOf(this)
            
            /**
             * A list of all [TestSealedClass] objects.
             */
            val TestSealedClass.Companion.postOrderValues: List<TestSealedClass<*>>
                get() = TestSealedClassPostOrderSealedEnum.values
            
            /**
             * Returns an implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            val TestSealedClass.Companion.postOrderSealedEnum: TestSealedClassPostOrderSealedEnum
                get() = TestSealedClassPostOrderSealedEnum
            
            /**
             * Returns the [TestSealedClass] object for the given [name].
             *
             * If the given name doesn't correspond to any [TestSealedClass], an [IllegalArgumentException] will
             * be thrown.
             */
            fun TestSealedClass.Companion.postOrderValueOf(name: String): TestSealedClass<*> =
                    TestSealedClassPostOrderSealedEnum.valueOf(name)
            
            /**
             * An isomorphic enum for the sealed class [com.livefront.TestSealedClass]
             */
            enum class TestSealedClassLevelOrderEnum(
                sealedObject: TestSealedClass<*>
            ) : TestA by sealedObject {
                TestSealedClass_FirstObject(com.livefront.TestSealedClass.FirstObject)
            }
            
            /**
             * The isomorphic [TestSealedClassLevelOrderEnum] for [this].
             */
            val TestSealedClass<*>.levelOrderEnum: TestSealedClassLevelOrderEnum
                get() = TestSealedClassLevelOrderSealedEnum.sealedObjectToEnum(this)
            
            /**
             * The isomorphic [TestSealedClass] for [this].
             */
            val TestSealedClassLevelOrderEnum.sealedObject: TestSealedClass<*>
                get() = TestSealedClassLevelOrderSealedEnum.enumToSealedObject(this)
            
            /**
             * An implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            object TestSealedClassLevelOrderSealedEnum : SealedEnum<TestSealedClass<*>>,
                    SealedEnumWithEnumProvider<TestSealedClass<*>, TestSealedClassLevelOrderEnum>,
                    EnumForSealedEnumProvider<TestSealedClass<*>, TestSealedClassLevelOrderEnum> {
                override val values: List<TestSealedClass<*>> = listOf(
                    TestSealedClass.FirstObject
                )
            
            
                override val enumClass: Class<TestSealedClassLevelOrderEnum>
                    get() = TestSealedClassLevelOrderEnum::class.java
            
                override fun ordinalOf(obj: TestSealedClass<*>): Int = when (obj) {
                    TestSealedClass.FirstObject -> 0
                }
            
                override fun nameOf(obj: TestSealedClass<*>): String = when (obj) {
                    TestSealedClass.FirstObject -> "TestSealedClass_FirstObject"
                }
            
                override fun valueOf(name: String): TestSealedClass<*> = when (name) {
                    "TestSealedClass_FirstObject" -> TestSealedClass.FirstObject
                    else -> throw IllegalArgumentException(${qqq}No sealed enum constant ${d}name${qqq})
                }
            
                override fun sealedObjectToEnum(obj: TestSealedClass<*>): TestSealedClassLevelOrderEnum = when
                        (obj) {
                    TestSealedClass.FirstObject -> TestSealedClassLevelOrderEnum.TestSealedClass_FirstObject
                }
            
                override fun enumToSealedObject(enum: TestSealedClassLevelOrderEnum): TestSealedClass<*> = when
                        (enum) {
                    TestSealedClassLevelOrderEnum.TestSealedClass_FirstObject -> TestSealedClass.FirstObject
                }
            }
            
            /**
             * The index of [this] in the values list.
             */
            val TestSealedClass<*>.levelOrderOrdinal: Int
                get() = TestSealedClassLevelOrderSealedEnum.ordinalOf(this)
            
            /**
             * The name of [this] for use with valueOf.
             */
            val TestSealedClass<*>.levelOrderName: String
                get() = TestSealedClassLevelOrderSealedEnum.nameOf(this)
            
            /**
             * A list of all [TestSealedClass] objects.
             */
            val TestSealedClass.Companion.levelOrderValues: List<TestSealedClass<*>>
                get() = TestSealedClassLevelOrderSealedEnum.values
            
            /**
             * Returns an implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            val TestSealedClass.Companion.levelOrderSealedEnum: TestSealedClassLevelOrderSealedEnum
                get() = TestSealedClassLevelOrderSealedEnum
            
            /**
             * Returns the [TestSealedClass] object for the given [name].
             *
             * If the given name doesn't correspond to any [TestSealedClass], an [IllegalArgumentException] will
             * be thrown.
             */
            fun TestSealedClass.Companion.levelOrderValueOf(name: String): TestSealedClass<*> =
                    TestSealedClassLevelOrderSealedEnum.valueOf(name)
            
            /**
             * An isomorphic enum for the sealed class [com.livefront.TestSealedClass]
             */
            enum class TestSealedClassPreOrderEnum(
                sealedObject: TestSealedClass<*>
            ) : TestA by sealedObject {
                TestSealedClass_FirstObject(com.livefront.TestSealedClass.FirstObject)
            }
            
            /**
             * The isomorphic [TestSealedClassPreOrderEnum] for [this].
             */
            val TestSealedClass<*>.preOrderEnum: TestSealedClassPreOrderEnum
                get() = TestSealedClassPreOrderSealedEnum.sealedObjectToEnum(this)
            
            /**
             * The isomorphic [TestSealedClass] for [this].
             */
            val TestSealedClassPreOrderEnum.sealedObject: TestSealedClass<*>
                get() = TestSealedClassPreOrderSealedEnum.enumToSealedObject(this)
            
            /**
             * An implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            object TestSealedClassPreOrderSealedEnum : SealedEnum<TestSealedClass<*>>,
                    SealedEnumWithEnumProvider<TestSealedClass<*>, TestSealedClassPreOrderEnum>,
                    EnumForSealedEnumProvider<TestSealedClass<*>, TestSealedClassPreOrderEnum> {
                override val values: List<TestSealedClass<*>> = listOf(
                    TestSealedClass.FirstObject
                )
            
            
                override val enumClass: Class<TestSealedClassPreOrderEnum>
                    get() = TestSealedClassPreOrderEnum::class.java
            
                override fun ordinalOf(obj: TestSealedClass<*>): Int = when (obj) {
                    TestSealedClass.FirstObject -> 0
                }
            
                override fun nameOf(obj: TestSealedClass<*>): String = when (obj) {
                    TestSealedClass.FirstObject -> "TestSealedClass_FirstObject"
                }
            
                override fun valueOf(name: String): TestSealedClass<*> = when (name) {
                    "TestSealedClass_FirstObject" -> TestSealedClass.FirstObject
                    else -> throw IllegalArgumentException(${qqq}No sealed enum constant ${d}name${qqq})
                }
            
                override fun sealedObjectToEnum(obj: TestSealedClass<*>): TestSealedClassPreOrderEnum = when
                        (obj) {
                    TestSealedClass.FirstObject -> TestSealedClassPreOrderEnum.TestSealedClass_FirstObject
                }
            
                override fun enumToSealedObject(enum: TestSealedClassPreOrderEnum): TestSealedClass<*> = when
                        (enum) {
                    TestSealedClassPreOrderEnum.TestSealedClass_FirstObject -> TestSealedClass.FirstObject
                }
            }
            
            /**
             * The index of [this] in the values list.
             */
            val TestSealedClass<*>.preOrderOrdinal: Int
                get() = TestSealedClassPreOrderSealedEnum.ordinalOf(this)
            
            /**
             * The name of [this] for use with valueOf.
             */
            val TestSealedClass<*>.preOrderName: String
                get() = TestSealedClassPreOrderSealedEnum.nameOf(this)
            
            /**
             * A list of all [TestSealedClass] objects.
             */
            val TestSealedClass.Companion.preOrderValues: List<TestSealedClass<*>>
                get() = TestSealedClassPreOrderSealedEnum.values
            
            /**
             * Returns an implementation of [SealedEnum] for the sealed class [TestSealedClass]
             */
            val TestSealedClass.Companion.preOrderSealedEnum: TestSealedClassPreOrderSealedEnum
                get() = TestSealedClassPreOrderSealedEnum
            
            /**
             * Returns the [TestSealedClass] object for the given [name].
             *
             * If the given name doesn't correspond to any [TestSealedClass], an [IllegalArgumentException] will
             * be thrown.
             */
            fun TestSealedClass.Companion.preOrderValueOf(name: String): TestSealedClass<*> =
                    TestSealedClassPreOrderSealedEnum.valueOf(name)

        """.trimIndent()

        assertEquals(expected, fileSpec.build().toString())
    }
}
