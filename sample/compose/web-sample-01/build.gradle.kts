plugins {
    alias(libs.plugins.tech.thdev.android.application)
}

setNamespace("compose.web.sample")

android {
    val (majorVersion, minorVersion, patchVersion, code) = getVersionInfo()

    defaultConfig {
        applicationId = "tech.thdev.compose.web.sample"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        vectorDrawables.useSupportLibrary = true
        versionCode = code
        versionName = "$majorVersion.$minorVersion.$patchVersion"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }
}

dependencies {
    implementation(libs.compose.navigation)

    implementation(libs.image.coil.compose)
}