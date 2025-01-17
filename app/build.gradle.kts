plugins {
    alias(libs.plugins.tech.thdev.android.application)
}

android {
    val (majorVersion, minorVersion, patchVersion, code) = getVersionInfo()

    defaultConfig {
        applicationId = "tech.thdev.app"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        vectorDrawables.useSupportLibrary = true
        versionCode = code
        versionName = "$majorVersion.$minorVersion.$patchVersion"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

setNamespace("app")

ksp {
    arg("moduleName", project.name)
    arg("rootDir", rootDir.absolutePath)
}

dependencies {
    implementation(libs.kotlin.stdlib)

    implementation(libs.google.material)

    implementation(libs.androidx.core)
    implementation(libs.androidx.appCompat)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintLayout)
    implementation(libs.androidx.vectorDrawable)
    implementation(libs.androidx.navigationFragment)
    implementation(libs.androidx.navigationUi)
    implementation(libs.androidx.liveData)

    implementation(libs.image.coil)

    implementation(libs.network.retrofit)
    implementation(libs.network.okhttp)
    implementation(libs.network.okhttp.logging)

    implementation(libs.compose.navigation)

    testImplementation(libs.test.coroutines)
}