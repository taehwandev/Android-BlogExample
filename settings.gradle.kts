@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS

    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "thdev-example"

include(":app")

include(
    ":architecture-one:app",
    ":architecture-one:core-data:repository:alert-repository",
    ":architecture-one:core-data:repository:alert-repository-api",
    ":architecture-one:core-data:repository:snackbar-repository",
    ":architecture-one:core-data:repository:snackbar-repository-api",
)

include(
    ":sample:compose:web-sample-01",
    ":sample:compose:compose-example-01",
    ":sample:view:recycler-view-ex",
)
