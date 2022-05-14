import tech.thdev.gradle.dependencies.Dependency

plugins {
    id("com.android.library")
    id("com.google.devtools.ksp")

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    namespace = "tech.thdev.other"

    buildTypes {
        sourceSets.getByName("debug") {
            kotlin.srcDir("build/generated/ksp/debug/kotlin")
        }
        sourceSets.getByName("release") {
            kotlin.srcDir("build/generated/ksp/release/kotlin")
        }
    }
}

dependencies {
    implementation(Dependency.Kotlin.stdLib)

    implementation(Dependency.Google.material)

    implementation(Dependency.AndroidX.appCompat)
    implementation(Dependency.AndroidX.activity)

    ksp(project(":ksp-sample"))
    implementation(projects.kspSampleAnnotation)
}