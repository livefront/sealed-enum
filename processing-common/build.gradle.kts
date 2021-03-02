plugins {
    `maven-publish`
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":runtime"))
    implementation(Dependencies.kotlinPoet)

    testImplementation(Dependencies.junit)
}
