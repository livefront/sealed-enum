plugins {
    kotlin("kapt")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":sealedenum"))
    implementation(Dependencies.kotlinPoet)
    implementation(Dependencies.kotlinPoetClassInspectorElements)
    implementation(Dependencies.kotlinPoetMetadata)
    implementation(Dependencies.kotlinPoetMetadataSpecs)
    autoService()
    incap()

    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.mockk)
    testImplementation(kotlin("reflect"))
    kaptTest(project(":sealedenumprocessor"))
}

kapt {
    includeCompileClasspath = false
}
