plugins {
    id("io.gitlab.arturbosch.detekt")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

detekt {
    buildUponDefaultConfig = true
    allRules = true
    autoCorrect = System.getenv("CI") != "true"
    config.setFrom("$rootDir/config/detekt/detekt.yml")
}

dependencies {
    detektPlugins(libs.findLibrary("detekt.formatting").get())
}

tasks {
    withType<io.gitlab.arturbosch.detekt.Detekt> {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    val detektAll by registering {
        dependsOn(tasks.withType<io.gitlab.arturbosch.detekt.Detekt>())
    }

    getByName("check") {
        dependsOn(detektAll)
    }
}
