package com.livefront.sealedenum.compilation.location

import com.livefront.sealedenum.GenSealedEnum

sealed class AlphaOutsideSealedClass {
    @GenSealedEnum(generateEnum = true)
    companion object
}

object AlphaFirstObject : AlphaOutsideSealedClass()

sealed class BetaOutsideSealedClass {
    @GenSealedEnum(generateEnum = true)
    companion object
}

object BetaFirstObject : BetaOutsideSealedClass()

object BetaSecondObject : BetaOutsideSealedClass()

object BetaThirdObject : BetaOutsideSealedClass()

object BetaFourthObject : BetaOutsideSealedClass()

object GammaFirstObject : GammaOutsideSealedClass()

sealed class GammaOutsideSealedClass {

    object GammaSecondObject : GammaOutsideSealedClass()

    @GenSealedEnum(generateEnum = true)
    companion object
}

object GammaThirdObject : GammaOutsideSealedClass()

object GammaFourthObject : GammaOutsideSealedClass()

sealed class DeltaOutsideSealedClass {
    object DeltaObject : DeltaOutsideSealedClass()

    @GenSealedEnum(generateEnum = true)
    companion object
}

object DeltaObject : DeltaOutsideSealedClass()
