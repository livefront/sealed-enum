plugins {
    kotlin("kapt")
    `maven-publish`
}

dependencies {
    implementation(projects.runtime)
    implementation(projects.processingCommon)
    implementation(libs.squareUp.kotlinPoet)
    implementation(libs.squareUp.kotlinPoetMetadata)
    implementation(libs.autoService.runtime)
    kapt(libs.autoService.processor)
    implementation(libs.incap.runtime)
    kapt(libs.incap.processor)
}

kapt {
    includeCompileClasspath = false
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
    kotlinOptions.freeCompilerArgs += "-Xopt-in=com.squareup.kotlinpoet.metadata.KotlinPoetMetadataPreview"
}
