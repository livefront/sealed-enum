plugins {
    id("com.livefront.sealedenum.kotlin")
    id("com.livefront.sealedenum.detekt")
    alias(libs.plugins.ksp)
}

/**
 * Swap to `true` to allow debugging `processor-tests`
 */
val debugProcessor = false
if (!debugProcessor) {
    sourceSets {
        test {
            java {
                srcDir("$rootDir/processing-tests/common/test/java")
                srcDir("$rootDir/processing-tests/common/test/kotlin")
            }
        }
    }
}

detekt {
    source = files(
        "src/main/java",
        "src/test/java",
        "src/main/kotlin",
        "src/test/kotlin",
        "$rootDir/processing-tests/common/test/java",
        "$rootDir/processing-tests/common/test/kotlin"
    )
}

dependencies {
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.kotlinCompileTesting.base)
    testImplementation(libs.kotlinCompileTesting.ksp)
    testImplementation(libs.okeyDoke)
    testImplementation(kotlin("reflect"))
    testImplementation(projects.runtime)
    testImplementation(libs.ksp.runtime)
    testImplementation(libs.ksp.api)
    testImplementation(projects.ksp)
    kspTest(projects.ksp)
}
