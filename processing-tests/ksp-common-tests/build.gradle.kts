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

    js(BOTH) {
        browser()
        nodejs()
    }

    macosX64()
    iosArm32(); iosArm64(); iosX64()
    watchosArm32(); watchosArm64(); watchosX86(); watchosX64()

    linuxArm64(); linuxX64()
    mingwX86(); mingwX64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.runtime)
                project.dependencies.add("kspCommonMainMetadata", projects.ksp)
            }
            if (!disableForSharedCode) {
                kotlin.srcDir("$rootDir/processing-tests/common/src/commonMain")
            }
            kotlin.srcDir("$buildDir/generated/ksp/metadata/commonMain/kotlin")
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("reflect"))
                implementation(kotlin("test"))
            }
            if (!disableForSharedCode) {
                kotlin.srcDir("$rootDir/processing-tests/common/src/commonTest")
            }
        }
    }
}

tasks {
    withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().all {
        if (name != "kspCommonMainKotlinMetadata") {
            dependsOn("kspCommonMainKotlinMetadata")
        }
    }
}

detekt {
    source = files(
        "src/commonMain/kotlin",
        "src/commonTest/kotlin",
        "$rootDir/processing-tests/common/src/commonMain/kotlin",
        "$rootDir/processing-tests/common/src/commonTest/kotlin",
    )
}
