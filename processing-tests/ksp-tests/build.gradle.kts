plugins {
    id("com.google.devtools.ksp")
    idea
}

repositories {
    google()
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

dependencies {
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.kotlinCompileTesting.base)
    testImplementation(libs.kotlinCompileTesting.ksp)
    testImplementation(kotlin("reflect"))
    testImplementation(projects.runtime)
    testImplementation(libs.ksp.runtime)
    testImplementation(libs.ksp.api)
    testImplementation(projects.ksp)
    kspTest(projects.ksp)
}
