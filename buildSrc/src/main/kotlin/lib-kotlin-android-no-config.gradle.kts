plugins {
    id("lib-kotlin-android")
}

android {
    libraryVariants.all {
        generateBuildConfigProvider.configure {
            enabled = false
        }
    }
}