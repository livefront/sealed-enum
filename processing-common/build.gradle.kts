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

    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(projects.runtime)
                implementation(libs.squareUp.kotlinPoet)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(libs.junit.jupiter)
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

dependencies {
    detektPlugins(Dependencies.detektFormatting)

    implementation(projects.runtime)
    implementation(libs.squareUp.kotlinPoet)

    testImplementation(libs.junit.jupiter)
}
