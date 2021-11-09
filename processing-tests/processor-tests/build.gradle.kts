plugins {
    kotlin("kapt")
    idea
}

/**
 * Swap to `true` to allow debugging `ksp-tests`
 */
val debugKsp = false
if (!debugKsp) {
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
    testImplementation(kotlin("reflect"))
    testImplementation(projects.runtime)
    testImplementation(projects.processor)
    kaptTest(projects.processor)
}
