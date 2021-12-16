rootProject.name = "MyApplication"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(
    ":library",
    ":library-impl",
    ":library-test",
    ":base",
    ":second-view",
)