// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    kotlin("jvm") version "1.3.70"
    jacoco
    id("io.gitlab.arturbosch.detekt") version "1.6.0"
}

allprojects {
    repositories {
        jcenter()
    }
}

subprojects {
    apply {
        plugin<org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper>()
        plugin<JacocoPlugin>()
        plugin<io.gitlab.arturbosch.detekt.DetektPlugin>()
        plugin<MavenPlugin>()
    }

    dependencies {
        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.6.0")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks {
        compileKotlin {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }

        compileTestKotlin {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }

        test {
            useJUnitPlatform()
            configure<JacocoTaskExtension> {
                isIncludeNoLocationClasses = true
            }
        }

        jacocoTestReport {
            dependsOn(test)

            reports {
                html.isEnabled = true
                xml.isEnabled = true
            }
        }

        withType<io.gitlab.arturbosch.detekt.Detekt> {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }
}
