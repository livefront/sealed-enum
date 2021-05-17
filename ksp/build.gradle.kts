plugins {
    kotlin("kapt")
    `maven-publish`
}

repositories {
    google()
}

dependencies {
    implementation(projects.runtime)
    implementation(projects.processingCommon)
    implementation(libs.squareUp.kotlinPoet)
    compileOnly(libs.ksp.api)
    implementation(libs.autoService.runtime)
    kapt(libs.autoService.processor)
}

kapt {
    includeCompileClasspath = false
}
