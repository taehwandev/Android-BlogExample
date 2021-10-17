import tech.thdev.gradle.dependencies.Dependency

plugins {
    id("com.android.library")

    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Dependency.Base.compileVersion
    buildToolsVersion = Dependency.Base.buildToolsVersion

    defaultConfig {
        minSdk = Dependency.Base.minSdk
        targetSdk = Dependency.Base.targetSdk
        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    // buildconfig 생성하지 않기
    libraryVariants.all {
        generateBuildConfigProvider.configure {
            enabled = false
        }
    }
}

dependencies {
    implementation(Dependency.Kotlin.stdLib)
    implementation(Dependency.Coroutines.core)

    implementation(Dependency.AndroidX.appCompat)
    compileOnly(Dependency.AndroidX.annotation)

    implementation(Dependency.Databinding.runtime)
}