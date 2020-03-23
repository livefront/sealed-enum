package com.livefront.sealedenum.internal

import com.livefront.sealedenum.TreeTraversalOrder
import com.livefront.sealedenum.internal.SealedClassNode.Object
import com.livefront.sealedenum.internal.SealedClassNode.SealedClass
import com.squareup.kotlinpoet.ClassName
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class TreeUtilsTests {

    @Nested
    inner class GetSealedEnumFromNode {

        @ParameterizedTest
        @EnumSource(TreeTraversalOrder::class)
        fun `empty sealed class node`(treeTraversalOrder: TreeTraversalOrder) {
            val root = SealedClass(emptyList())

            assertEquals(
                emptyList<ClassName>(),
                getSealedObjectsFromNode(treeTraversalOrder, root)
            )
        }

        @ParameterizedTest
        @EnumSource(TreeTraversalOrder::class)
        fun `sealed class node with one leaf`(treeTraversalOrder: TreeTraversalOrder) {
            val className1 = mockk<ClassName>()

            val root = SealedClass(
                listOf(
                    Object(className1)
                )
            )

            assertEquals(
                listOf(className1),
                getSealedObjectsFromNode(treeTraversalOrder, root)
            )
        }

        @ParameterizedTest
        @EnumSource(TreeTraversalOrder::class)
        fun `sealed class node with two leaves`(treeTraversalOrder: TreeTraversalOrder) {
            val className1 = mockk<ClassName>()
            val className2 = mockk<ClassName>()

            val root = SealedClass(
                listOf(
                    Object(className1),
                    Object(className2)
                )
            )

            assertEquals(
                listOf(className1, className2),
                getSealedObjectsFromNode(treeTraversalOrder, root)
            )
        }

        @ParameterizedTest
        @EnumSource(TreeTraversalOrder::class)
        fun `empty nested sealed class node`(treeTraversalOrder: TreeTraversalOrder) {
            val root = SealedClass(
                listOf(
                    SealedClass(emptyList())
                )
            )

            assertEquals(
                emptyList<ClassName>(),
                getSealedObjectsFromNode(treeTraversalOrder, root)
            )
        }

        @ParameterizedTest
        @EnumSource(TreeTraversalOrder::class)
        fun `two empty nested sealed class nodes`(treeTraversalOrder: TreeTraversalOrder) {
            val root = SealedClass(
                listOf(
                    SealedClass(emptyList()),
                    SealedClass(emptyList())
                )
            )

            assertEquals(
                emptyList<ClassName>(),
                getSealedObjectsFromNode(treeTraversalOrder, root)
            )
        }

        @ParameterizedTest
        @EnumSource(TreeTraversalOrder::class)
        fun `nested sealed class node with one leaf`(treeTraversalOrder: TreeTraversalOrder) {
            val className1 = mockk<ClassName>()

            val root = SealedClass(
                listOf(
                    SealedClass(
                        listOf(
                            Object(className1)
                        )
                    )
                )
            )

            assertEquals(
                listOf(className1),
                getSealedObjectsFromNode(treeTraversalOrder, root)
            )
        }

        @Nested
        inner class PerfectTree {

            @Nested
            inner class HeightOne {

                private val className1 = mockk<ClassName>()
                private val className2 = mockk<ClassName>()
                private val className3 = mockk<ClassName>()

                private val root = SealedClass(
                    listOf(
                        SealedClass(
                            listOf(
                                Object(className1)
                            )
                        ),
                        Object(className2),
                        SealedClass(
                            listOf(
                                Object(className3)
                            )
                        )
                    )
                )

                @Test
                fun `pre order`() {
                    assertEquals(
                        listOf(className2, className1, className3),
                        getSealedObjectsFromNode(TreeTraversalOrder.PRE_ORDER, root)
                    )
                }

                @Test
                fun `in order`() {
                    assertEquals(
                        listOf(className1, className2, className3),
                        getSealedObjectsFromNode(TreeTraversalOrder.IN_ORDER, root)
                    )
                }

                @Test
                fun `post order`() {
                    assertEquals(
                        listOf(className1, className3, className2),
                        getSealedObjectsFromNode(TreeTraversalOrder.POST_ORDER, root)
                    )
                }

                @Test
                fun `level order`() {
                    assertEquals(
                        listOf(className2, className1, className3),
                        getSealedObjectsFromNode(TreeTraversalOrder.LEVEL_ORDER, root)
                    )
                }
            }

            @Nested
            inner class HeightTwo {
                private val className1 = mockk<ClassName>()
                private val className2 = mockk<ClassName>()
                private val className3 = mockk<ClassName>()
                private val className4 = mockk<ClassName>()
                private val className5 = mockk<ClassName>()
                private val className6 = mockk<ClassName>()
                private val className7 = mockk<ClassName>()

                private val root = SealedClass(
                    listOf(
                        SealedClass(
                            listOf(
                                SealedClass(
                                    listOf(
                                        Object(className1)
                                    )
                                ),
                                Object(className2),
                                SealedClass(
                                    listOf(
                                        Object(className3)
                                    )
                                )
                            )
                        ),
                        Object(className4),
                        SealedClass(
                            listOf(
                                SealedClass(
                                    listOf(
                                        Object(className5)
                                    )
                                ),
                                Object(className6),
                                SealedClass(
                                    listOf(
                                        Object(className7)
                                    )
                                )
                            )
                        )
                    )
                )

                @Test
                fun `pre order`() {
                    assertEquals(
                        listOf(
                            className4,
                            className2,
                            className1,
                            className3,
                            className6,
                            className5,
                            className7
                        ),
                        getSealedObjectsFromNode(TreeTraversalOrder.PRE_ORDER, root)
                    )
                }

                @Test
                fun `in order`() {
                    assertEquals(
                        listOf(
                            className1,
                            className2,
                            className3,
                            className4,
                            className5,
                            className6,
                            className7
                        ),
                        getSealedObjectsFromNode(TreeTraversalOrder.IN_ORDER, root)
                    )
                }

                @Test
                fun `post order`() {
                    assertEquals(
                        listOf(
                            className1,
                            className3,
                            className2,
                            className5,
                            className7,
                            className6,
                            className4
                        ),
                        getSealedObjectsFromNode(TreeTraversalOrder.POST_ORDER, root)
                    )
                }

                @Test
                fun `level order`() {
                    assertEquals(
                        listOf(
                            className4,
                            className2,
                            className6,
                            className1,
                            className3,
                            className5,
                            className7
                        ),
                        getSealedObjectsFromNode(TreeTraversalOrder.LEVEL_ORDER, root)
                    )
                }
            }
        }
    }
}
