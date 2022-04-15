plugins{
    kotlin("jvm")
    `maven-publish`
    id("org.jetbrains.dokka")
    id("org.jetbrains.kotlinx.binary-compatibility-validator")
}

apiValidation {
    ignoredPackages.add("com.livefront.sealedenum.internal")
}

tasks {
    dokkaHtml {
        outputDirectory.set(javadoc.get().destinationDir)
    }
}

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

val javadocJar by tasks.creating(Jar::class) {
    archiveClassifier.set("javadoc")
    from(tasks.dokkaHtml)
}

publishing {
    publications {
        create<MavenPublication>("default") {
            from(components["java"])
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
