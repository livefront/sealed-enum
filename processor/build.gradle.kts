plugins {
    kotlin("kapt")
    `maven-publish`
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":runtime"))
    implementation(project(":processing-common"))
    implementation(Dependencies.kotlinPoet)
    implementation(Dependencies.kotlinPoetClassInspectorElements)
    implementation(Dependencies.kotlinPoetMetadata)
    implementation(Dependencies.kotlinPoetMetadataSpecs)
    autoService()
    incap()
}

kapt {
    includeCompileClasspath = false
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
    kotlinOptions.freeCompilerArgs += "-Xopt-in=com.squareup.kotlinpoet.metadata.KotlinPoetMetadataPreview"
}
