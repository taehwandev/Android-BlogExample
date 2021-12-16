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
    implementation(project(":library")) // ViewController 사용
    testImplementation(project(":library-test")) // ViewController test 사용
    implementation(project(":base")) // Base 코드 접근

    implementation(Dependency.Kotlin.stdLib)
    implementation(Dependency.Coroutines.core)
    implementation(Dependency.AndroidX.lifecycleCommonJava8)
    implementation(Dependency.AndroidX.liveDataKtx)
    implementation(Dependency.AndroidX.coreKtx)
    implementation(Dependency.AndroidX.appCompat)
    implementation(Dependency.AndroidX.constraintLayout)
    implementation(Dependency.AndroidX.navigationFragmentKtx)

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
}