plugins {
    kotlin("multiplatform")
    `java-library`
    kotlin("kapt")
    id("io.gitlab.arturbosch.detekt")
    id("org.jetbrains.dokka")
    `maven-publish`
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
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
                implementation(libs.squareUp.kotlinPoetKsp)
                compileOnly(libs.ksp.api)
                implementation(libs.autoService.runtime)
                configurations["kapt"].dependencies.add(project.dependencies.create(libs.autoService.processor.get()))
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
