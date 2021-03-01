package com.livefront.sealedenum.internal.processor

import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.STAR
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec

/**
 * A mapping of [TypeSpec.typeVariables] to [TypeName]s.
 *
 * Rather than returning variable names, they are converted into a [TypeName] that can be directly specified in an
 * interface.
 *
 * In particular, single invariant types can be replaced with the type directly. In all other cases (multiple bounds,
 * other types of variance) the only thing we can do is wildcard the type.
 */
internal val TypeSpec.wildcardedTypeVariables: List<TypeName>
    get() = typeVariables
        .map {
            if (it.bounds.size == 1) {
                when (it.variance) {
                    KModifier.IN -> STAR
                    KModifier.OUT -> STAR
                    null -> it.bounds[0]
                    else -> throw AssertionError("Invalid variance!")
                }
            } else {
                STAR
            }
        }
