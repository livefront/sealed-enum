package com.livefront.sealedenum.internal

import com.livefront.sealedenum.TreeTraversalOrder
import com.squareup.kotlinpoet.ClassName
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SealedEnumFileSpecTests {

    @Test
    fun `empty sealed objects`() {
        val fileSpec = SealedEnumFileSpec(
            sealedClass = ClassName("com.livefront", "TestSealedClass"),
            sealedClassElement = mockk(),
            traversalOrders = listOf(TreeTraversalOrder.IN_ORDER),
            sealedClassNode = SealedClassNode.SealedClass(emptyList()),
            numberOfTypeParameters = 0
        )

        val expected = """
            package com.livefront
            
            import com.livefront.sealedenum.SealedEnum
            import kotlin.Int
            import kotlin.collections.List
            
            /**
             * An implementation of [com.livefront.sealedenum.SealedEnum] for the sealed class
             * [com.livefront.TestSealedClass]
             */
            object TestSealedClassSealedEnum : SealedEnum<TestSealedClass> {
                override val values: List<TestSealedClass> = emptyList()
            
            
                override fun ordinalOf(obj: TestSealedClass): Int = throw
                        AssertionError("Constructing a TestSealedClass is impossible, since it has no sealed subclasses")
            }

        """.trimIndent()

        assertEquals(expected, fileSpec.create().toString())
    }

    @Test
    fun `one object sealed class`() {
        val fileSpec = SealedEnumFileSpec(
            sealedClass = ClassName("com.livefront", "TestSealedClass"),
            sealedClassElement = mockk(),
            traversalOrders = listOf(TreeTraversalOrder.IN_ORDER),
            sealedClassNode = SealedClassNode.SealedClass(
                listOf(
                    SealedClassNode.Object(ClassName("com.livefront", "TestSealedClass", "FirstObject"))
                )
            ),
            numberOfTypeParameters = 0
        )

        val expected = """
            package com.livefront
            
            import com.livefront.sealedenum.SealedEnum
            import kotlin.Int
            import kotlin.collections.List
            
            /**
             * An implementation of [com.livefront.sealedenum.SealedEnum] for the sealed class
             * [com.livefront.TestSealedClass]
             */
            object TestSealedClassSealedEnum : SealedEnum<TestSealedClass> {
                override val values: List<TestSealedClass> = listOf(
                    TestSealedClass.FirstObject
                )
            
            
                override fun ordinalOf(obj: TestSealedClass): Int = when (obj) {
                    TestSealedClass.FirstObject -> 0
                }
            }

        """.trimIndent()

        assertEquals(expected, fileSpec.create().toString())
    }

    @Test
    fun `two object sealed class`() {
        val fileSpec = SealedEnumFileSpec(
            sealedClass = ClassName("com.livefront", "TestSealedClass"),
            sealedClassElement = mockk(),
            traversalOrders = listOf(TreeTraversalOrder.IN_ORDER),
            sealedClassNode = SealedClassNode.SealedClass(
                listOf(
                    SealedClassNode.Object(ClassName("com.livefront", "TestSealedClass", "FirstObject")),
                    SealedClassNode.Object(ClassName("com.livefront", "TestSealedClass", "SecondObject"))
                )
            ),
            numberOfTypeParameters = 0
        )

        val expected = """
            package com.livefront
            
            import com.livefront.sealedenum.SealedEnum
            import kotlin.Int
            import kotlin.collections.List
            
            /**
             * An implementation of [com.livefront.sealedenum.SealedEnum] for the sealed class
             * [com.livefront.TestSealedClass]
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
            }

        """.trimIndent()

        assertEquals(expected, fileSpec.create().toString())
    }

    @Test
    fun `inner sealed class`() {
        val fileSpec = SealedEnumFileSpec(
            sealedClass = ClassName("com.livefront", "OuterClass", "TestSealedClass"),
            sealedClassElement = mockk(),
            traversalOrders = listOf(TreeTraversalOrder.IN_ORDER),
            sealedClassNode = SealedClassNode.SealedClass(
                listOf(
                    SealedClassNode.Object(ClassName("com.livefront", "OuterClass", "TestSealedClass", "FirstObject"))
                )
            ),
            numberOfTypeParameters = 0
        )

        val expected = """
            package com.livefront
            
            import com.livefront.sealedenum.SealedEnum
            import kotlin.Int
            import kotlin.collections.List
            
            /**
             * An implementation of [com.livefront.sealedenum.SealedEnum] for the sealed class
             * [com.livefront.OuterClass.TestSealedClass]
             */
            object OuterClass_TestSealedClassSealedEnum : SealedEnum<OuterClass.TestSealedClass> {
                override val values: List<OuterClass.TestSealedClass> = listOf(
                    OuterClass.TestSealedClass.FirstObject
                )
            
            
                override fun ordinalOf(obj: OuterClass.TestSealedClass): Int = when (obj) {
                    OuterClass.TestSealedClass.FirstObject -> 0
                }
            }

        """.trimIndent()

        assertEquals(expected, fileSpec.create().toString())
    }

    @Test
    fun `generic sealed class`() {
        val fileSpec = SealedEnumFileSpec(
            sealedClass = ClassName("com.livefront", "TestSealedClass"),
            sealedClassElement = mockk(),
            traversalOrders = listOf(TreeTraversalOrder.IN_ORDER),
            sealedClassNode = SealedClassNode.SealedClass(
                listOf(
                    SealedClassNode.Object(ClassName("com.livefront", "TestSealedClass", "FirstObject"))
                )
            ),
            numberOfTypeParameters = 1
        )

        val expected = """
            package com.livefront
            
            import com.livefront.sealedenum.SealedEnum
            import kotlin.Int
            import kotlin.collections.List
            
            /**
             * An implementation of [com.livefront.sealedenum.SealedEnum] for the sealed class
             * [com.livefront.TestSealedClass]
             */
            object TestSealedClassSealedEnum : SealedEnum<TestSealedClass<*>> {
                override val values: List<TestSealedClass<*>> = listOf(
                    TestSealedClass.FirstObject
                )
            
            
                override fun ordinalOf(obj: TestSealedClass<*>): Int = when (obj) {
                    TestSealedClass.FirstObject -> 0
                }
            }

        """.trimIndent()

        assertEquals(expected, fileSpec.create().toString())
    }

    @Test
    fun `multiple traversal orders`() {
        val fileSpec = SealedEnumFileSpec(
            sealedClass = ClassName("com.livefront", "TestSealedClass"),
            sealedClassElement = mockk(),
            traversalOrders = listOf(
                TreeTraversalOrder.IN_ORDER,
                TreeTraversalOrder.LEVEL_ORDER
            ),
            sealedClassNode = SealedClassNode.SealedClass(
                listOf(
                    SealedClassNode.Object(ClassName("com.livefront", "TestSealedClass", "FirstObject"))
                )
            ),
            numberOfTypeParameters = 1
        )

        val expected = """
            package com.livefront
            
            import com.livefront.sealedenum.SealedEnum
            import kotlin.Int
            import kotlin.collections.List
            
            /**
             * An implementation of [com.livefront.sealedenum.SealedEnum] for the sealed class
             * [com.livefront.TestSealedClass]
             */
            object TestSealedClassInOrderSealedEnum : SealedEnum<TestSealedClass<*>> {
                override val values: List<TestSealedClass<*>> = listOf(
                    TestSealedClass.FirstObject
                )
            
            
                override fun ordinalOf(obj: TestSealedClass<*>): Int = when (obj) {
                    TestSealedClass.FirstObject -> 0
                }
            }
            
            /**
             * An implementation of [com.livefront.sealedenum.SealedEnum] for the sealed class
             * [com.livefront.TestSealedClass]
             */
            object TestSealedClassLevelOrderSealedEnum : SealedEnum<TestSealedClass<*>> {
                override val values: List<TestSealedClass<*>> = listOf(
                    TestSealedClass.FirstObject
                )
            
            
                override fun ordinalOf(obj: TestSealedClass<*>): Int = when (obj) {
                    TestSealedClass.FirstObject -> 0
                }
            }

        """.trimIndent()

        assertEquals(expected, fileSpec.create().toString())
    }

    @Test
    fun `all traversal orders`() {
        val fileSpec = SealedEnumFileSpec(
            sealedClass = ClassName("com.livefront", "TestSealedClass"),
            sealedClassElement = mockk(),
            traversalOrders = listOf(
                TreeTraversalOrder.IN_ORDER,
                TreeTraversalOrder.POST_ORDER,
                TreeTraversalOrder.LEVEL_ORDER,
                TreeTraversalOrder.PRE_ORDER
            ),
            sealedClassNode = SealedClassNode.SealedClass(
                listOf(
                    SealedClassNode.Object(ClassName("com.livefront", "TestSealedClass", "FirstObject"))
                )
            ),
            numberOfTypeParameters = 1
        )

        val expected = """
            package com.livefront
            
            import com.livefront.sealedenum.SealedEnum
            import kotlin.Int
            import kotlin.collections.List
            
            /**
             * An implementation of [com.livefront.sealedenum.SealedEnum] for the sealed class
             * [com.livefront.TestSealedClass]
             */
            object TestSealedClassInOrderSealedEnum : SealedEnum<TestSealedClass<*>> {
                override val values: List<TestSealedClass<*>> = listOf(
                    TestSealedClass.FirstObject
                )
            
            
                override fun ordinalOf(obj: TestSealedClass<*>): Int = when (obj) {
                    TestSealedClass.FirstObject -> 0
                }
            }
            
            /**
             * An implementation of [com.livefront.sealedenum.SealedEnum] for the sealed class
             * [com.livefront.TestSealedClass]
             */
            object TestSealedClassPostOrderSealedEnum : SealedEnum<TestSealedClass<*>> {
                override val values: List<TestSealedClass<*>> = listOf(
                    TestSealedClass.FirstObject
                )
            
            
                override fun ordinalOf(obj: TestSealedClass<*>): Int = when (obj) {
                    TestSealedClass.FirstObject -> 0
                }
            }
            
            /**
             * An implementation of [com.livefront.sealedenum.SealedEnum] for the sealed class
             * [com.livefront.TestSealedClass]
             */
            object TestSealedClassLevelOrderSealedEnum : SealedEnum<TestSealedClass<*>> {
                override val values: List<TestSealedClass<*>> = listOf(
                    TestSealedClass.FirstObject
                )
            
            
                override fun ordinalOf(obj: TestSealedClass<*>): Int = when (obj) {
                    TestSealedClass.FirstObject -> 0
                }
            }
            
            /**
             * An implementation of [com.livefront.sealedenum.SealedEnum] for the sealed class
             * [com.livefront.TestSealedClass]
             */
            object TestSealedClassPreOrderSealedEnum : SealedEnum<TestSealedClass<*>> {
                override val values: List<TestSealedClass<*>> = listOf(
                    TestSealedClass.FirstObject
                )
            
            
                override fun ordinalOf(obj: TestSealedClass<*>): Int = when (obj) {
                    TestSealedClass.FirstObject -> 0
                }
            }

        """.trimIndent()

        assertEquals(expected, fileSpec.create().toString())
    }
}
