plugins {
    `maven-publish`
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":runtime"))
    implementation(libs.squareUp.kotlinPoet)

    testImplementation(libs.junit.jupiter)
}
