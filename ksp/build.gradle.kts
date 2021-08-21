plugins {
    id("com.livefront.sealedenum.kotlin")
    id("com.livefront.sealedenum.detekt")
    id("com.livefront.sealedenum.publish")
    kotlin("kapt")
}

kotlin {
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

kapt {
    includeCompileClasspath = false
}
