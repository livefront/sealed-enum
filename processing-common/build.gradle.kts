plugins {
    `maven-publish`
}

dependencies {
    implementation(projects.runtime)
    implementation(libs.squareUp.kotlinPoet)

    testImplementation(libs.junit.jupiter)
}
