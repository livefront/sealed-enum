plugins {
    kotlin("jvm")
    jacoco
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

kotlin {
    explicitApi()
}

jacoco {
    toolVersion = libs.findVersion("jacoco").get().toString()
}

tasks {
    test {
        useJUnitPlatform()
    }

    jacocoTestReport {
        dependsOn(test)

        reports {
            html.required.set(true)
            xml.required.set(true)
        }
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            allWarningsAsErrors = true
            freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
        }
    }
}
