plugins {
    kotlin("multiplatform")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

kotlin {
    explicitApi()
    jvm {
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            allWarningsAsErrors = true
            freeCompilerArgs = freeCompilerArgs + "-opt-in=kotlin.RequiresOptIn"
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }
}
