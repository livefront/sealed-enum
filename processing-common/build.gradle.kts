plugins {
    id("com.livefront.sealedenum.kotlin")
    id("com.livefront.sealedenum.detekt")
    id("com.livefront.sealedenum.publish")
    `maven-publish`
}

kotlin {
    jvm()
}

dependencies {
    implementation(projects.runtime)
    implementation(libs.squareUp.kotlinPoet)

    testImplementation(libs.junit.jupiter)
}
