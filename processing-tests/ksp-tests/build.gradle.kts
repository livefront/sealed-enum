plugins {
    id("com.livefront.sealedenum.kotlin")
    id("com.livefront.sealedenum.detekt")
    alias(libs.plugins.ksp)
}

/**
 * Swap to `true` to allow debugging `processor-tests`
 */
val debugProcessor = false

kotlin {
    jvm()

    sourceSets {
        val jvmTest by getting {
            dependencies {
                implementation(libs.junit.jupiter)
                implementation(libs.kotlinCompileTesting.base)
                implementation(libs.kotlinCompileTesting.ksp)
                implementation(kotlin("reflect"))
                implementation(projects.runtime)
                implementation(libs.ksp.runtime)
                implementation(libs.ksp.api)
                implementation(projects.ksp)
                configurations["kspJvmTest"].dependencies.add(projects.ksp)
            }
            if (!debugProcessor) {
                kotlin.srcDir("$rootDir/processing-tests/common/src/jvmTest")
            }
        }
    }
}

if (!debugProcessor) {
    sourceSets {
        test {
            java {
                srcDir("$rootDir/processing-tests/common/src/jvmTest/java")
            }
        }
    }
}

detekt {
    source = files(
        "src/jvmMain/kotlin",
        "src/jvmTest/kotlin",
        "$rootDir/processing-tests/common/src/jvmTest/java",
        "$rootDir/processing-tests/common/src/jvmTest/kotlin"
    )
}
