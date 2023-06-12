package com.livefront.sealedenum.compilation.visibility

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.reflect.KVisibility

class VisibilitySealedClassVisibilityTests {
    @Test
    fun `internal objects sealed enum class has correct visibility`() {
        assertEquals(
            KVisibility.PUBLIC,
            InternalObjectsSealedClassSealedEnum::class.visibility
        )
    }

    @Test
    fun `internal sealed class sealed enum class has correct visibility`() {
        kotlin.test.assertEquals(
            KVisibility.INTERNAL,
            InternalSealedClassSealedEnum::class.visibility
        )
    }

    @Test
    fun `internal companion object sealed enum class has correct visibility`() {
        kotlin.test.assertEquals(
            KVisibility.PUBLIC,
            InternalCompanionSealedClassSealedEnum::class.visibility
        )
    }

    @Test
    fun `internal sealed class and companion object sealed enum class has correct visibility`() {
        kotlin.test.assertEquals(
            KVisibility.INTERNAL,
            InternalSealedAndCompanionSealedClassSealedEnum::class.visibility
        )
    }
}
