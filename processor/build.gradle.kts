plugins {
    kotlin("multiplatform")
    `java-library`
    kotlin("kapt")
    id("io.gitlab.arturbosch.detekt")
    id("org.jetbrains.dokka")
    `maven-publish`
}

kotlin {
    explicitApi()

    jvm()

    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(projects.runtime)
                implementation(projects.processingCommon)
                implementation(libs.squareUp.kotlinPoet)
                implementation(libs.squareUp.kotlinPoetMetadata)
                implementation(libs.autoService.runtime)
                configurations["kapt"].dependencies.add(project.dependencies.create(libs.autoService.processor.get()))
                implementation(libs.incap.runtime)
                configurations["kapt"].dependencies.add(project.dependencies.create(libs.incap.processor.get()))
            }
        }
    }
}

tasks {
    withType<io.gitlab.arturbosch.detekt.Detekt> {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    detektPlugins(Dependencies.detektFormatting)
}

kapt {
    includeCompileClasspath = false
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
    kotlinOptions.freeCompilerArgs += "-Xopt-in=com.squareup.kotlinpoet.metadata.KotlinPoetMetadataPreview"
}
