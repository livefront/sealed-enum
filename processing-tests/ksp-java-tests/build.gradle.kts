plugins {
    id("com.livefront.sealedenum.kotlin")
    id("com.livefront.sealedenum.detekt")
    alias(libs.plugins.ksp)
}

/**
 * Swap to `true` to allow debugging other tests that share code
 */
val disableForSharedCode = false

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.runtime)
            }
            if (!disableForSharedCode) {
                kotlin.srcDir("$rootDir/processing-tests/common/src/commonMain")
                kotlin.srcDir("$rootDir/processing-tests/ksp-common-tests/src/commonMain")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("reflect"))
                implementation(kotlin("test"))
            }
            if (!disableForSharedCode) {
                kotlin.srcDir("$rootDir/processing-tests/common/src/commonTest")
                kotlin.srcDir("$rootDir/processing-tests/ksp-common-tests/src/commonTest")
            }
        }

        val jvmMain by getting {
            dependencies {
                project.dependencies.add("kspJvm", projects.ksp)
            }
            if (!disableForSharedCode) {
                kotlin.srcDir("$rootDir/processing-tests/common/src/jvmMain")
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(libs.junit.jupiter)
                implementation(libs.kotlinCompileTesting.base)
                implementation(libs.kotlinCompileTesting.ksp)
                implementation(libs.ksp.runtime)
                implementation(libs.ksp.api)
                implementation(projects.ksp)
            }
            if (!disableForSharedCode) {
                kotlin.srcDir("$rootDir/processing-tests/common/src/jvmTest")
            }
        }
    }
}

if (!disableForSharedCode) {
    sourceSets {
        main {
            java {
                srcDir("$rootDir/processing-tests/common/src/jvmMain/java")
            }
        }
        test {
            java {
                srcDir("$rootDir/processing-tests/common/src/jvmTest/java")
            }
        }
    }
}

detekt {
    source = files(
        "src/commonMain/kotlin",
        "src/commonTest/kotlin",
        "src/jvmMain/kotlin",
        "src/jvmTest/kotlin",
        "$rootDir/processing-tests/common/src/commonMain/kotlin",
        "$rootDir/processing-tests/common/src/commonTest/kotlin",
        "$rootDir/processing-tests/common/src/jvmMain/java",
        "$rootDir/processing-tests/common/src/jvmMain/kotlin",
        "$rootDir/processing-tests/common/src/jvmTest/java",
        "$rootDir/processing-tests/common/src/jvmTest/kotlin"
    )
}
