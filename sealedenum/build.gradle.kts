dependencies {
    val junitVersion by rootProject.extra("5.6.0")
    val mockkVersion by rootProject.extra("1.9.3")

    compileOnly(kotlin("stdlib"))

    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
}
