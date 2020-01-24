package com.livefront.sealedenum

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

enum class AlphaEnum { BETA, GAMMA, DELTA }

class CreateSealedEnumFromEnumTests {

    private val alphaEnumSealedEnum = createSealedEnumFromEnum<AlphaEnum>()

    @Test
    fun `verify values are correct`() {
        assertEquals(listOf(AlphaEnum.BETA, AlphaEnum.GAMMA, AlphaEnum.DELTA), alphaEnumSealedEnum.values)
    }

    @Test
    fun `verify ordinal of BETA is correct`() {
        assertEquals(0, alphaEnumSealedEnum.ordinalOf(AlphaEnum.BETA))
    }

    @Test
    fun `verify ordinal of GAMMA is correct`() {
        assertEquals(1, alphaEnumSealedEnum.ordinalOf(AlphaEnum.GAMMA))
    }

    @Test
    fun `verify ordinal of DELTA is correct`() {
        assertEquals(2, alphaEnumSealedEnum.ordinalOf(AlphaEnum.DELTA))
    }

    data class ComparatorConfig(
        val first: AlphaEnum,
        val second: AlphaEnum,
        val compareValue: Int
    ) {
        class Provider : ArgumentsProvider {
            override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> =
                listOf(
                    ComparatorConfig(AlphaEnum.BETA, AlphaEnum.BETA, 0),
                    ComparatorConfig(AlphaEnum.BETA, AlphaEnum.GAMMA, -1),
                    ComparatorConfig(AlphaEnum.BETA, AlphaEnum.DELTA, -2),
                    ComparatorConfig(AlphaEnum.GAMMA, AlphaEnum.BETA, 1),
                    ComparatorConfig(AlphaEnum.GAMMA, AlphaEnum.GAMMA, 0),
                    ComparatorConfig(AlphaEnum.GAMMA, AlphaEnum.DELTA, -1),
                    ComparatorConfig(AlphaEnum.DELTA, AlphaEnum.BETA, 2),
                    ComparatorConfig(AlphaEnum.DELTA, AlphaEnum.GAMMA, 1),
                    ComparatorConfig(AlphaEnum.DELTA, AlphaEnum.DELTA, 0)
                ).map { Arguments.of(it) }.stream()

        }
    }

    @ParameterizedTest
    @ArgumentsSource(ComparatorConfig.Provider::class)
    fun `verify compareTo is correct`(config: ComparatorConfig) {
        assertEquals(config.compareValue, alphaEnumSealedEnum.compare(config.first, config.second))
    }
}
