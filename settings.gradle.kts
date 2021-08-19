enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        id("com.google.devtools.ksp") version "1.5.21-1.0.0-beta07"
    }
}

include(":runtime")
include(":processing-common")
include(":ksp")
include(":processing-tests:ksp-tests")
rootProject.name = "sealed-enum"
