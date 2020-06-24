// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    kotlin("jvm") version "1.3.72"
    jacoco
    id("io.gitlab.arturbosch.detekt") version "1.6.0"
    id("org.jetbrains.dokka") version "0.10.1"
    `maven-publish`
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
        plugin<MavenPublishPlugin>()
        plugin<org.jetbrains.dokka.gradle.DokkaPlugin>()
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

        val dokka by getting(org.jetbrains.dokka.gradle.DokkaTask::class) {
            outputFormat = "html"
            outputDirectory = javadoc.get().destinationDir!!.absolutePath

            configuration {
                jdkVersion = 8
            }
        }

        val sourcesJar by creating(Jar::class) {
            archiveClassifier.set("sources")
            from(sourceSets.main.get().allSource)
        }

        val javadocJar by creating(Jar::class) {
            archiveClassifier.set("javadoc")
            from(dokka)
        }

        publishing {
            publications {
                create<MavenPublication>("default") {
                    from(this@subprojects.components["java"])
                    artifact(sourcesJar)
                    artifact(javadocJar)
                }
            }
        }
    }
}
