plugins {
    kotlin("kapt")
    idea
}

repositories {
    google()
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

dependencies {
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.kotlinCompileTesting)
    testImplementation(kotlin("reflect"))
    testImplementation(projects.runtime)
    testImplementation(projects.processor)
    kaptTest(projects.processor)
}
