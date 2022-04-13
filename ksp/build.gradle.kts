plugins {
    id("com.livefront.sealedenum.kotlin")
    id("com.livefront.sealedenum.detekt")
    id("com.livefront.sealedenum.publish")
    kotlin("kapt")
}

dependencies {
    implementation(projects.runtime)
    implementation(projects.processingCommon)
    implementation(libs.squareUp.kotlinPoet)
    implementation(libs.squareUp.kotlinPoetKsp)
    compileOnly(libs.ksp.api)
    implementation(libs.autoService.runtime)
    kapt(libs.autoService.processor)
}

kapt {
    includeCompileClasspath = false
}
