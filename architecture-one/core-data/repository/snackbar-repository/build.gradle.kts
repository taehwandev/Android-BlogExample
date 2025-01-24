plugins {
    alias(libs.plugins.tech.thdev.android.library)
    alias(libs.plugins.tech.thdev.kotlin.library.hilt)
}

setNamespace("architecture.app.snackbar.repository")

dependencies {
    implementation(projects.architectureOne.coreData.repository.snackbarRepositoryApi)
}
