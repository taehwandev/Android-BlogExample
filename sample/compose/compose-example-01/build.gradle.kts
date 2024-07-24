@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("kotlin-android")
    kotlin("kapt")
}

android {
    namespace = "tech.thdev.compose.web.sample"

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
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compilerVersion.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.kotlin.stdlib)

    implementation(libs.google.material)

    implementation(libs.androidx.coreKtx)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.vectorDrawable)

    implementation(libs.compose.activity)
    implementation(libs.compose.ui)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material3)
    implementation(libs.compose.runtime)
    implementation(libs.compose.uiToolingPreview)
    implementation(libs.compose.animation)
    debugRuntimeOnly(libs.compose.uiTooling)

    implementation(libs.compose.navigation)

    implementation(libs.image.coil.compose)
}