plugins {
    id("com.livefront.sealedenum.kotlin")
    id("com.livefront.sealedenum.detekt")
    kotlin("kapt")
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
            }
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

        val jvmMain by getting {
            configurations["kapt"].dependencies.add(projects.processor)
            if (!disableForSharedCode) {
                kotlin.srcDir("$rootDir/processing-tests/common/src/jvmMain")
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(libs.junit.jupiter)
                implementation(libs.kotlinCompileTesting.base)
                implementation(projects.processor)
            }
            if (!disableForSharedCode) {
                kotlin.srcDir("$rootDir/processing-tests/common/src/jvmTest")
            }
        }
    }
}

kapt {
    includeCompileClasspath = false
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

// See https://github.com/tschuchortdev/kotlin-compile-testing#java-16-compatibility
if (JavaVersion.current() >= JavaVersion.VERSION_16) {
    tasks.withType<Test>().all {
        jvmArgs(
            "--add-opens=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED",
            "--add-opens=jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED",
            "--add-opens=jdk.compiler/com.sun.tools.javac.comp=ALL-UNNAMED",
            "--add-opens=jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED",
            "--add-opens=jdk.compiler/com.sun.tools.javac.jvm=ALL-UNNAMED",
            "--add-opens=jdk.compiler/com.sun.tools.javac.main=ALL-UNNAMED",
            "--add-opens=jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED",
            "--add-opens=jdk.compiler/com.sun.tools.javac.processing=ALL-UNNAMED",
            "--add-opens=jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED",
            "--add-opens=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED",
        )
    }
}
