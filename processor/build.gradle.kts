plugins {
    kotlin("kapt")
    `maven-publish`
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":runtime"))
    implementation(project(":processing-common"))
    implementation(libs.squareUp.kotlinPoet)
    implementation(libs.squareUp.kotlinPoetClassInspectorElements)
    implementation(libs.squareUp.kotlinPoetMetadata)
    implementation(libs.squareUp.kotlinPoetMetadataSpecs)
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
