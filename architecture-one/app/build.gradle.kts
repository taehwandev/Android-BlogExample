plugins {
    alias(libs.plugins.tech.thdev.android.application)
    alias(libs.plugins.tech.thdev.android.library.hilt)
}

setNamespace("architecture.app")

android {
    val (majorVersion, minorVersion, patchVersion, code) = getVersionInfo()

    defaultConfig {
        applicationId = "tech.thdev.architecture.app"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        vectorDrawables.useSupportLibrary = true
        versionCode = code
        versionName = "$majorVersion.$minorVersion.$patchVersion"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }
}

ksp {
    arg("moduleName", project.name)
    arg("rootDir", rootDir.absolutePath)
}

dependencies {
    implementation(libs.compose.navigation)
    implementation(libs.google.material)

    implementation(projects.architectureOne.coreData.repository.alertRepository)
    implementation(projects.architectureOne.coreData.repository.alertRepositoryApi)
    implementation(projects.architectureOne.coreData.repository.snackbarRepository)
    implementation(projects.architectureOne.coreData.repository.snackbarRepositoryApi)
}
