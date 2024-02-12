@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("kotlin-android")
    kotlin("kapt")
}

android {
    namespace = "tech.thdev.app"

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(libs.kotlin.stdlib)

    implementation(libs.google.material)

    implementation(libs.androidx.coreKtx)
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
    implementation(libs.network.okhttpLogging)
}