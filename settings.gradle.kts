enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }

    plugins {
        id("com.google.devtools.ksp") version "1.4.30-1.0.0-alpha05"
    }
}

include(":runtime")
include(":processing-common")
include(":processor")
include(":ksp")
include(":processing-tests:processor-tests")
include(":processing-tests:ksp-tests")
rootProject.name = "sealed-enum"
