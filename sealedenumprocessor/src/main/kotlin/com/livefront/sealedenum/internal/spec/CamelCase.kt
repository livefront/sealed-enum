package com.livefront.sealedenum.internal.spec

/**
 * Converts a PascalCase string to a camelCase string. That is, this function lowercases the first letter.
 *
 * This function assumes that [pascalCase] is non-empty.
 */
fun pascalCaseToCamelCase(pascalCase: String): String =
    pascalCase.take(1).toLowerCase() + pascalCase.drop(1)
