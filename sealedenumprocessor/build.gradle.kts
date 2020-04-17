plugins {
    kotlin("kapt")
}

dependencies {
    val kotlinPoetVersion by rootProject.extra("1.5.0")
    val autoServiceVersion by rootProject.extra("1.0-rc6")
    val incapVersion by rootProject.extra("0.2")
    val junitVersion by rootProject.extra("5.6.0")
    val mockkVersion by rootProject.extra("1.9.3")

    implementation(kotlin("stdlib"))
    implementation(project(":sealedenum"))
    implementation("com.squareup:kotlinpoet:$kotlinPoetVersion")
    implementation("com.squareup:kotlinpoet-metadata:$kotlinPoetVersion")
    implementation("com.squareup:kotlinpoet-metadata-specs:$kotlinPoetVersion")
    implementation("com.google.auto.service:auto-service-annotations:$autoServiceVersion")
    kapt("com.google.auto.service:auto-service:$autoServiceVersion")
    implementation("net.ltgt.gradle.incap:incap:$incapVersion")
    kapt("net.ltgt.gradle.incap:incap-processor:$incapVersion")

    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
    kaptTest(project(":sealedenumprocessor"))
}

kapt {
    arguments {
        arg("sealedenum.autoGenerateSealedEnums", "true")
    }
}
