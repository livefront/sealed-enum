package com.livefront.sealedenum

/**
 * Indicates that an implementation of [SealedEnum] should be generated for the annotated sealed class.
 *
 * For example,
 * ```
 * @GenSealedEnum
 * sealed class Alpha {
 *     object Beta : Alpha()
 *     object Gamma : Alpha()
 * }
 * ```
 * will generate the following object:
 * ```
 * object AlphaSealedEnum : SealedEnum<Alpha> {
 *     override val values: List<Alpha> = listOf(
 *         Alpha.Beta,
 *         Alpha.Gamma
 *     )
 *
 *     override fun ordinalOf(obj: Alpha): Int = when (obj) {
 *         Alpha.Beta -> 0
 *         Alpha.Gamma -> 1
 *     }
 * }
 * ```
 *
 * If there is a nested hierarchy of sealed classes, then `values` will contain all object subclasses, with the
 * tree ordering given by [traversalOrder]. By default, an [TreeTraversalOrder.IN_ORDER] will be used.
 *
 * If multiple ordering strategies are desired simultaneously, then the sealed class can be annotated with multiple
 * [GenSealedEnum] annotations, with different [TreeTraversalOrder]s. This will cause multiple objects to be generated,
 * with different names to indicate the traversal order used.
 *
 * For example,
 * ```
 * @GenSealedEnum(traversalOrder = TreeTraversalOrder.IN_ORDER)
 * @GenSealedEnum(traversalOrder = TreeTraversalOrder.LEVEL_ORDER)
 * sealed class Alpha {
 *     sealed class Beta : Alpha() {
 *         object Gamma : Beta()
 *     }
 *     object Delta : Alpha()
 *     sealed class Epsilon : Alpha() {
 *         object Zeta : Epsilon()
 *     }
 * }
 * ```
 * will generate two objects:
 * ```
 * object AlphaLevelOrderSealedEnum : SealedEnum<Alpha> {
 *     override val values: List<Alpha> = listOf(
 *         Alpha.Delta,
 *         Alpha.Beta.Gamma,
 *         Alpha.Epsilon.Zeta
 *     )
 *
 *     override fun ordinalOf(obj: Alpha): Int = when (obj) {
 *         Alpha.Delta -> 0
 *         Alpha.Beta.Gamma -> 1
 *         Alpha.Epsilon.Zeta -> 2
 *     }
 * }
 *
 * object AlphaInOrderSealedEnum : SealedEnum<Alpha> {
 *     override val values: List<Alpha> = listOf(
 *         Alpha.Beta.Gamma,
 *         Alpha.Delta,
 *         Alpha.Epsilon.Zeta
 *     )
 *
 *     override fun ordinalOf(obj: Alpha): Int = when (obj) {
 *         Alpha.Beta.Gamma -> 0
 *         Alpha.Delta -> 1
 *         Alpha.Epsilon.Zeta -> 2
 *     }
 * }
 * ```
 *
 * If the sealed class (or any child sealed class) has non-object and non-sealed subclasses, they will cause
 * annotation processing to fail.
 *
 * If the sealed class has generic type arguments, then the generated object will star-project the sealed class.
 *
 * For example,
 * ```
 * @GenSealedEnum
 * sealed class Alpha<A, B> {
 *     object Beta : Alpha<Double, Double>()
 *     object Gamma : Alpha<Int, Int>()
 * }
 * ```
 * will generate the following object:
 * ```
 * object AlphaSealedEnum : SealedEnum<Alpha<*, *>> {
 *     override val values: List<Alpha<*, *>> = listOf(
 *         Alpha.Beta,
 *         Alpha.Gamma
 *     )
 *
 *     override fun ordinalOf(obj: Alpha<*, *>): Int = when (obj) {
 *         Alpha.Beta -> 0
 *         Alpha.Gamma -> 1
 *     }
 * }
 * ```
 */
@Suppress("DEPRECATED_JAVA_ANNOTATION")
@java.lang.annotation.Repeatable(GenSealedEnums::class)
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
@Repeatable
annotation class GenSealedEnum(val traversalOrder: TreeTraversalOrder = TreeTraversalOrder.IN_ORDER)

/**
 * This is the container annotation for multiple [GenSealedEnum] annotations.
 */
annotation class GenSealedEnums(vararg val value: GenSealedEnum)
