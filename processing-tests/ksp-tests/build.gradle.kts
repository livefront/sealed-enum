plugins {
    id("com.google.devtools.ksp")
    idea
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
    input = files(
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
    testImplementation(kotlin("reflect"))
    testImplementation(projects.runtime)
    testCompileOnly(libs.ksp.runtime)
    testCompileOnly(libs.ksp.api)
    testImplementation(projects.ksp)
    kspTest(projects.ksp)
}
