enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include(":runtime")
include(":processing-common")
include(":processor")
include(":ksp")
include(":processing-tests:processor-tests")
include(":processing-tests:ksp-tests")
rootProject.name = "sealed-enum"
