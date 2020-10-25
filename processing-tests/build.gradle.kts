plugins {
    kotlin("kapt")
}

dependencies {
    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.kotlinCompileTesting)
    testImplementation(kotlin("reflect"))
    testImplementation(project(":runtime"))
    testImplementation(project(":processor"))
    kaptTest(project(":processor"))
}

kapt {
    includeCompileClasspath = false
}
