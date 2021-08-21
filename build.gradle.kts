// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    kotlin("multiplatform") version Versions.kotlin apply false
    jacoco
    id("io.gitlab.arturbosch.detekt") version Versions.detekt
    id("org.jetbrains.dokka") version Versions.dokka
    id("org.jetbrains.kotlinx.binary-compatibility-validator") version Versions.kotlinBinaryCompatibilityValidator
    `maven-publish`
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    tasks {
        withType<org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile>().configureEach {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }
        withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
            kotlinOptions {
                freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
                allWarningsAsErrors = true
            }
        }

        plugins.withType(MavenPublishPlugin::class) {
            publishing {
                publications {
                    create<MavenPublication>("default") {
                        pom {
                            name.set(project.name)
                            description.set("Obsoleting enums with sealed classes of objects")
                            url.set("https://github.com/livefront/sealed-enum")

                            licenses {
                                license {
                                    name.set("The Apache Software License, Version 2.0")
                                    url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                                    distribution.set("repo")
                                }
                            }

                            developers {
                                developer {
                                    id.set("alexvanyo")
                                    name.set("Alex Vanyo")
                                    organization.set("Livefront")
                                    organizationUrl.set("https://www.livefront.com")
                                }
                            }

                            scm {
                                url.set("https://github.com/livefront/sealed-enum")
                                connection.set("scm:git:git://github.com/livefront/sealed-enum.git")
                                developerConnection.set("scm:git:git://github.com/livefront/sealed-enum.git")
                            }
                        }
                    }
                }
            }
        }
    }
}

jacoco {
    toolVersion = Versions.jacoco
}

apiValidation {
    ignoredPackages.add("com.livefront.sealedenum.internal")
}

tasks {
    val jacocoMergeTest by registering(JacocoMerge::class) {
        dependsOn(subprojects.mapNotNull { it.tasks.findByName("jacocoTestReport") })

        destinationFile = file("$buildDir/jacoco/jvmTest.exec")

        executionData(
            subprojects.filter { it.tasks.findByName("jacocoTestReport") != null }.map {
                "${it.buildDir}/jacoco/jvmTest.exec"
            }
        )
    }

    val jacocoTestReport by registering(JacocoReport::class) {
        dependsOn(jacocoMergeTest)

        executionData.setFrom("$buildDir/jacoco/jvmTest.exec")
        classDirectories.setFrom(
            files(
                subprojects
                    .map {
                        it.buildDir.resolve("classes/kotlin/jvm/main")
                    }
                    .flatMap { it.walkBottomUp().toList() }
            )
        )
        sourceDirectories.setFrom(
            files(
                subprojects.flatMap {
                    listOf(
                        it.projectDir.resolve("src/commonMain/kotlin"),
                        it.projectDir.resolve("src/jvmMain/kotlin")
                    ).filter(File::exists)
                }
            )
        )

        reports {
            html.isEnabled = true
            xml.isEnabled = true
        }
    }

    val detektAll by registering {
        allprojects {
            this@registering.dependsOn(tasks.withType<io.gitlab.arturbosch.detekt.Detekt>())
        }

        getByName("check").dependsOn(this)
    }
}
