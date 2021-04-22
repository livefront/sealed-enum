enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":runtime")
include(":processing-common")
include(":processor")
include(":processing-tests:processor-tests")
rootProject.name = "sealed-enum"
