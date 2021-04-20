plugins {
    `maven-publish`
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(projects.runtime)
    implementation(libs.squareUp.kotlinPoet)

    testImplementation(libs.junit.jupiter)
}
