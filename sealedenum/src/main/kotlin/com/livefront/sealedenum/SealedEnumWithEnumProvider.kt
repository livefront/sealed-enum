package com.livefront.sealedenum

/**
 * A [SealedEnum] along with a [EnumForSealedEnumProvider].
 */
interface SealedEnumWithEnumProvider<T, E : Enum<E>> : SealedEnum<T>, EnumForSealedEnumProvider<T, E>
