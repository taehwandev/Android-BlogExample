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
        const val version: String = "1.5.21"

        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
    }

    object Coroutines {
        // https://github.com/Kotlin/kotlinx.coroutines
        const val version: String = "1.5.2"

        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"

        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        const val turbine = "app.cash.turbine:turbine:0.6.1"
    }

    object AndroidX {
        // https://developer.android.com/jetpack/androidx/releases/core
        const val coreKtx = "androidx.core:core-ktx:1.6.0"

        // https://developer.android.com/jetpack/androidx/releases/appcompat
        const val appCompat = "androidx.appcompat:appcompat:1.3.1"

        // https://developer.android.com/jetpack/androidx/releases/activity
        const val activity = "androidx.activity:activity-ktx:1.3.1"

        // https://developer.android.com/jetpack/androidx/releases/constraintlayout
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.1"

        // https://developer.android.com/jetpack/androidx/releases/coordinatorlayout
        const val coordinatorlayout = "androidx.coordinatorlayout:coordinatorlayout:1.1.0"

        // https://developer.android.com/jetpack/androidx/releases/vectordrawable
        const val vectorDrawable = "androidx.vectordrawable:vectordrawable:1.1.0"

        // https://developer.android.com/jetpack/androidx/releases/navigation
        private const val navigationVersion = "2.3.5"
        const val navigationFragment = "androidx.navigation:navigation-fragment:$navigationVersion"
        const val navigationRuntime = "androidx.navigation:navigation-runtime:$navigationVersion"
        const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
        const val navigationUi = "androidx.navigation:navigation-ui:$navigationVersion"
        const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:$navigationVersion"

        // https://developer.android.com/jetpack/androidx/releases/lifecycle
        private const val lifecycleVersion = "2.3.1"
        const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
        const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"

        // https://developer.android.com/jetpack/androidx/releases/annotation
        const val annotation = "androidx.annotation:annotation:1.2.0"

        // https://developer.android.com/jetpack/androidx/releases/recyclerview
        const val recyclerView = "androidx.recyclerview:recyclerview:1.2.1"

        // https://developer.android.com/jetpack/androidx/releases/swiperefreshlayout
        const val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"

        // https://developer.android.com/jetpack/androidx/releases/browser
        const val browser = "androidx.browser:browser:1.3.0"

        // https://developer.android.com/jetpack/androidx/releases/viewpager2
        const val viewPager2 = "androidx.viewpager2:viewpager2:1.0.0"

        // https://developer.android.com/jetpack/androidx/releases/webkit
        const val webkit = "androidx.webkit:webkit:1.4.0"
    }

    object Databinding {
        const val version = "7.0.3"

        const val runtime = "androidx.databinding:databinding-runtime:$version"
        const val common = "androidx.databinding:databinding-common:$version"
        const val adapters = "androidx.databinding:databinding-adapters:$version"
    }

    object Google {
        // https://github.com/material-components/material-components-android/releases
        const val material = "com.google.android.material:material:1.4.0"
    }

    object Image {
        // https://github.com/bumptech/glide
        private const val version: String = "4.12.0"

        const val glide = "com.github.bumptech.glide:glide:$version"
        const val glideCompiler = "com.github.bumptech.glide:compiler:$version"
    }

    object Network {
        // https://square.github.io/okhttp/
        const val okhttpVersion = "4.9.0"
        const val okhttp = "com.squareup.okhttp3:okhttp:$okhttpVersion"
        const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:$okhttpVersion"

        // https://github.com/square/retrofit
        const val retrofitVersion = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
    }

    object AndroidTest {
        // https://developer.android.com/jetpack/androidx/releases/test
        private const val androidXTestVersion = "1.4.0"
        const val androidxCore = "androidx.test:core:$androidXTestVersion"
        const val androidxRunner = "androidx.test:runner:$androidXTestVersion"

        const val coreTesting = "androidx.arch.core:core-testing:2.1.0"

        const val androidxJunit = "androidx.test.ext:junit:1.1.3"

        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"

        const val mockito = "org.mockito:mockito-core:4.0.0"

        // https://github.com/mockito/mockito-kotlin
        const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:4.0.0"

        // https://github.com/mannodermaus/android-junit5
        private const val junit5Version = "5.8.0"
        const val engine = "org.junit.jupiter:junit-jupiter-engine:$junit5Version"
        const val vintage = "org.junit.vintage:junit-vintage-engine:$junit5Version"
        const val junit5 = "org.junit.jupiter:junit-jupiter-api:$junit5Version"
        const val params = "org.junit.jupiter:junit-jupiter-params:$junit5Version"
    }
}