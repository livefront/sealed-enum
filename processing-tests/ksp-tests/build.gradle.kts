plugins {
    kotlin("multiplatform")
    `java-library`
    jacoco
    id("io.gitlab.arturbosch.detekt")
    id("org.jetbrains.dokka")
    id("com.google.devtools.ksp")
}

/**
 * Swap to `true` to allow debugging `processor-tests`
 */
val debugProcessor = false

dependencies {
    ksp(projects.ksp)
}

kotlin {
    explicitApi()

    jvm {
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
        withJava()
    }
    // TODO: Adding this results in "Collection has more than one element."
    //       This is the same issue as https://github.com/glureau/ksp-kmp-issues/tree/kotlinjsir_single_crashing
    // js(BOTH) {
    //     browser()
    //     nodejs()
    // }

    macosX64()
    iosArm32(); iosArm64(); iosX64()
    watchosArm32(); watchosArm64(); watchosX86(); watchosX64()

    linuxArm64(); linuxX64()
    mingwX86(); mingwX64()

    sourceSets {
        val commonTest by getting {
            dependencies {
                implementation(kotlin("reflect"))
                implementation(projects.runtime)
            }
            if (!debugProcessor) {
                kotlin.srcDir("$rootDir/processing-tests/common/src/commonTest")
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
            if (!debugProcessor) {
                kotlin.srcDir("$rootDir/processing-tests/common/src/jvmTest")
            }
        }
    }
}

jacoco {
    toolVersion = Versions.jacoco
}

tasks {
    jacocoTestReport {
        dependsOn("jvmTest")

        executionData.setFrom("$buildDir/jacoco/jvmTest.exec")
        classDirectories.setFrom(
            File("$buildDir/classes/kotlin/jvm/main").walkBottomUp().toList()
        )
        sourceDirectories.setFrom(
            files(
                "src/commonMain/kotlin",
                "src/jvmMain/kotlin"
            )
        )

        reports {
            html.isEnabled = true
            xml.isEnabled = true
        }
    }

    withType<io.gitlab.arturbosch.detekt.Detekt> {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
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
    input = files(
        "src/jvmMain/kotlin",
        "src/jvmTest/kotlin",
        "$rootDir/processing-tests/common/src/commonTest/kotlin",
        "$rootDir/processing-tests/common/src/jvmTest/java",
        "$rootDir/processing-tests/common/src/jvmTest/kotlin"
    )
}

dependencies {
    detektPlugins(Dependencies.detektFormatting)
}
