// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    kotlin("jvm") version Versions.kotlin
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
    apply {
        plugin<org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper>()
        plugin<JacocoPlugin>()
        plugin<io.gitlab.arturbosch.detekt.DetektPlugin>()
        plugin<org.jetbrains.dokka.gradle.DokkaPlugin>()
    }

    dependencies {
        detektPlugins(Dependencies.detektFormatting)
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlin {
        explicitApi()
    }

    tasks {
        compileKotlin {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
                allWarningsAsErrors = true
            }
        }

        compileTestKotlin {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
                allWarningsAsErrors = true
            }
        }

        test {
            useJUnitPlatform()
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

        dokkaHtml {
            outputDirectory.set(javadoc.get().destinationDir)
        }

        plugins.withType(MavenPublishPlugin::class) {
            val sourcesJar by creating(Jar::class) {
                archiveClassifier.set("sources")
                from(sourceSets.main.get().allSource)
            }

            val javadocJar by creating(Jar::class) {
                archiveClassifier.set("javadoc")
                from(dokkaHtml)
            }

            publishing {
                publications {
                    create<MavenPublication>("default") {
                        from(this@subprojects.components["java"])
                        artifact(sourcesJar)
                        artifact(javadocJar)

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

apiValidation {
    ignoredPackages.add("com.livefront.sealedenum.internal")
}

tasks {
    val jacocoMergeTest by registering(JacocoMerge::class) {
        dependsOn(subprojects.map { it.tasks.jacocoTestReport })

        destinationFile = file("$buildDir/jacoco/test.exec")
        executionData = fileTree(rootDir) {
            include("**/build/jacoco/test.exec")
        }
    }

    jacocoTestReport {
        dependsOn(jacocoMergeTest)

        sourceSets(*subprojects.map { it.sourceSets.main.get() }.toTypedArray())

        reports {
            html.isEnabled = true
            xml.isEnabled = true
        }
    }
}
