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

    /**
     * Returns the name of [obj] for use with [valueOf].
     */
    fun nameOf(obj: T): String

    /**
     * Returns the [T] object for the given [name].
     *
     * If the given name doesn't correspond to any [T], an [IllegalArgumentException] will be thrown.
     */
    fun valueOf(name: String): T

    override fun compare(first: T, second: T) = ordinalOf(first) - ordinalOf(second)
}

/**
 * Creates a [SealedEnumWithEnumProvider] for the enum [E].
 */
inline fun <reified E : Enum<E>> createSealedEnumFromEnum(): SealedEnumWithEnumProvider<E, E> =
    createSealedEnumFromEnumArray(enumValues(), E::class.java)

/**
 * Creates a [SealedEnumWithEnumProvider] for the enum [E] with the given array of enum values.
 */
fun <E : Enum<E>> createSealedEnumFromEnumArray(
    values: Array<E>,
    enumClass: Class<E>
): SealedEnumWithEnumProvider<E, E> = object : SealedEnumWithEnumProvider<E, E> {
    val nameToValueMap: Map<String, E> by lazy(LazyThreadSafetyMode.PUBLICATION) {
        values.associateBy { it.name }
    }

    override val enumClass: Class<E> = enumClass

    override val values: List<E> = values.toList()

    override fun ordinalOf(obj: E): Int = obj.ordinal

    override fun nameOf(obj: E): String = obj.name

    override fun valueOf(name: String): E =
        nameToValueMap[name] ?: throw IllegalArgumentException("No sealed enum constant $name")

    /**
     * Isomorphism is given by the identity function
     */
    override fun sealedObjectToEnum(obj: E): E = obj

    /**
     * Isomorphism is given by the identity function
     */
    override fun enumToSealedObject(enum: E): E = enum
}
