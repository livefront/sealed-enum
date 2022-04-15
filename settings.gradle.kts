enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
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
include(":jacoco")
rootProject.name = "sealed-enum"
