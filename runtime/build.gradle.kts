plugins {
    kotlin("multiplatform")
    `java-library`
    jacoco
    id("io.gitlab.arturbosch.detekt")
    id("org.jetbrains.dokka")
    `maven-publish`
}

kotlin {
    explicitApi()

    jvm {
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
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
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

jacoco {
    toolVersion = Versions.jacoco
}

tasks {
    withType<io.gitlab.arturbosch.detekt.Detekt> {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    jacocoTestReport {
        dependsOn("jvmTest")

        executionData.setFrom("$buildDir/jacoco/jvmTest.exec")
        classDirectories.setFrom(
            File("$buildDir/classes/kotlin/jvm/main").walkBottomUp().toList()
        )
        sourceDirectories.setFrom(
            files(
                "src/commonMain",
                "src/jvmMain"
            )
        )

        reports {
            html.isEnabled = true
            xml.isEnabled = true
        }
    }
}

dependencies {
    detektPlugins(Dependencies.detektFormatting)
}
