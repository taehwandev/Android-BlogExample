import tech.thdev.gradle.dependencies.Dependency

plugins {
    id("com.android.application")

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

    buildFeatures {
        dataBinding = true
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(Dependency.Kotlin.stdLib)

    implementation(Dependency.Google.material)

    Dependency.AndroidX.run {
        implementation(coreKtx)
        implementation(appCompat)
        implementation(activity)
        implementation(constraintLayout)
        implementation(vectorDrawable)
        implementation(navigationFragmentKtx)
        implementation(navigationUiKtx)
        implementation(liveDataKtx)
        implementation(lifecycleCommonJava8)
    }

    implementation(Dependency.Image.glide)
    kapt(Dependency.Image.glideCompiler)

    Dependency.Network.run {
        implementation(retrofit)
        implementation(okhttp)
        implementation(okhttpLogging)
    }

    Dependency.AndroidTest.run {
        testImplementation(androidxCore)
        testImplementation(androidxRunner)
        testImplementation(mockito)
        testImplementation(mockitoKotlin)
        testImplementation(coreTesting)
    }

    Dependency.Coroutines.run {
        testImplementation(test)
        testImplementation(turbine)
    }
    implementation(project(":library"))
    testImplementation(project(":library-test"))
}