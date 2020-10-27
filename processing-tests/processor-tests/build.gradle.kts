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
    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.kotlinCompileTesting)
    testImplementation(kotlin("reflect"))
    testImplementation(project(":runtime"))
    testImplementation(project(":processor"))
    kaptTest(project(":processor"))
}
