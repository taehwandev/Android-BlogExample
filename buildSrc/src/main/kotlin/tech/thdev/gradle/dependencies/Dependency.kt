package tech.thdev.gradle.dependencies

object Dependency {

    object Base {
        const val buildToolsVersion = "31.0.0"
        const val compileVersion = 31
        const val targetSdk = 31
        const val minSdk = 26
    }

    object Kotlin {
        // https://github.com/JetBrains/kotlin
        private const val version: String = "1.7.10"

        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
    }

    object AndroidX {
        // https://developer.android.com/jetpack/androidx/releases/core
        const val coreKtx = "androidx.core:core-ktx:1.9.0-alpha03"

        // https://developer.android.com/jetpack/androidx/releases/appcompat
        const val appCompat = "androidx.appcompat:appcompat:1.6.0-alpha03"

        // https://developer.android.com/jetpack/androidx/releases/activity
        const val activity = "androidx.activity:activity-ktx:1.6.0-alpha03"

        // https://developer.android.com/jetpack/androidx/releases/constraintlayout
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.3"

        // https://developer.android.com/jetpack/androidx/releases/vectordrawable
        const val vectorDrawable = "androidx.vectordrawable:vectordrawable:1.2.0-beta01"

        // https://developer.android.com/jetpack/androidx/releases/navigation
        private const val navigationVersion = "2.5.0-beta01"
        const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
        const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:$navigationVersion"

        // https://developer.android.com/jetpack/androidx/releases/lifecycle
        private const val lifecycleVersion = "2.5.0-beta01"
        const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"

        // https://developer.android.com/jetpack/androidx/releases/annotation
        const val annotation = "androidx.annotation:annotation:1.4.0-alpha02"
    }

    object Google {
        // https://github.com/material-components/material-components-android/releases
        const val material = "com.google.android.material:material:1.6.0"
    }

    object Image {
        // https://github.com/bumptech/glide
        private const val version: String = "4.13.2"

        const val glide = "com.github.bumptech.glide:glide:$version"
        const val glideCompiler = "com.github.bumptech.glide:compiler:$version"
    }

    object Network {
        // https://square.github.io/okhttp/
        private const val okhttpVersion = "4.9.3"
        const val okhttp = "com.squareup.okhttp3:okhttp:$okhttpVersion"
        const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:$okhttpVersion"

        // https://github.com/square/retrofit
        private const val retrofitVersion = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    }

    object AndroidTest {
        // https://github.com/mockito/mockito-kotlin
        const val mockito = "org.mockito:mockito-core:4.0.0"
        const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:4.0.0"

        // https://github.com/mannodermaus/android-junit5
        private const val junit5Version = "5.8.2"
        const val engine = "org.junit.jupiter:junit-jupiter-engine:$junit5Version"
        const val vintage = "org.junit.vintage:junit-vintage-engine:$junit5Version"
        const val junit5 = "org.junit.jupiter:junit-jupiter-api:$junit5Version"
    }
}