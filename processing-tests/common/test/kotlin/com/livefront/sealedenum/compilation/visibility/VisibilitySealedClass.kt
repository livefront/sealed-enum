package com.livefront.sealedenum.compilation.visibility

import com.livefront.sealedenum.GenSealedEnum

sealed class InternalObjectsSealedClass {
    internal object FirstObject : InternalObjectsSealedClass()

    internal object SecondObject : InternalObjectsSealedClass()

    internal sealed class InnerSealedClass : InternalObjectsSealedClass() {
        internal object ThirdObject : InnerSealedClass()
    }

    @GenSealedEnum(generateEnum = true)
    companion object
}

internal sealed class InternalSealedClass {
    object FirstObject : InternalSealedClass()

    internal object SecondObject : InternalSealedClass()

    @GenSealedEnum(generateEnum = true)
    companion object
}

sealed class InternalCompanionSealedClass {
    object FirstObject : InternalCompanionSealedClass()

    internal object SecondObject : InternalCompanionSealedClass()

    @GenSealedEnum(generateEnum = true)
    internal companion object
}

internal sealed class InternalSealedAndCompanionSealedClass {
    object FirstObject : InternalSealedAndCompanionSealedClass()

    internal object SecondObject : InternalSealedAndCompanionSealedClass()

    @GenSealedEnum(generateEnum = true)
    internal companion object
}
