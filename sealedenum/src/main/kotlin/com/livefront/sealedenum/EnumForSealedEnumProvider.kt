package com.livefront.sealedenum

/**
 * Defines a provider for interoperability between a [SealedEnum] for type [T] and a normal enum class [E].
 */
interface EnumForSealedEnumProvider<T, E : Enum<E>> {
    /**
     * A mapping of the sealed object of type [T] to the enum constant of type [E]. Together with the inverse function
     * [enumToSealedObject], this is an isomorphism.
     */
    fun sealedObjectToEnum(obj: T): E

    /**
     * A mapping of the enum constant of type [E] to the enum constant of type [ET. Together with the inverse function
     * [sealedObjectToEnum], this is an isomorphism.
     */
    fun enumToSealedObject(enum: E): T

    /**
     * The class object for the enum.
     */
    val enumClass: Class<E>
}
