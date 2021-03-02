plugins {
    `maven-publish`
}

dependencies {
    compileOnly(kotlin("stdlib"))

    testImplementation(kotlin("stdlib"))
    testImplementation(Dependencies.junit)
}
