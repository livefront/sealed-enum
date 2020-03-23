package com.livefront.sealedenum

/**
 * An interface providing `Enum`-like methods in a generic manner, especially for sealed classes that only have
 * objects as subclasses.
 *
 * Object implementations for [SealedEnum] will automatically be generated for all sealed classes if automatic
 * generation is enabled, or by annotating the sealed class with [GenSealedEnum]. Additionally, an implementation of
 * [SealedEnum] can be created for a normal `Enum` with [createSealedEnumFromEnum].
 */
interface SealedEnum<T> : Comparator<T> {

    /**
     * A list of all [T] objects.
     */
    val values: List<T>

    /**
     * Returns the index of [obj] in the [values] list.
     */
    fun ordinalOf(obj: T): Int

    override fun compare(first: T, second: T) = ordinalOf(first) - ordinalOf(second)
}

/**
 * Creates a [SealedEnum] for the enum [E].
 */
inline fun <reified E : Enum<E>> createSealedEnumFromEnum(): SealedEnum<E> =
    createSealedEnumFromEnumArray(enumValues())

/**
 * Creates a [SealedEnum] for the enum [E] with the given array of enum values.
 */
fun <E : Enum<E>> createSealedEnumFromEnumArray(values: Array<E>): SealedEnum<E> = object : SealedEnum<E> {
    override val values: List<E> = values.toList()

    override fun ordinalOf(obj: E): Int = obj.ordinal
}
