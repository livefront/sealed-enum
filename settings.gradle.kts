enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":runtime")
include(":processing-common")
include(":processor")
include(":ksp")
include(":processing-tests:processor-tests")
include(":processing-tests:ksp-tests")
rootProject.name = "sealed-enum"
