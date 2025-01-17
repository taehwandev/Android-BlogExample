package tech.thdev.gradle

import getVersionInfo
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.provideDelegate
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinTopLevelExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import tech.thdev.gradle.extensions.androidExtension
import tech.thdev.gradle.extensions.findVersion
import java.io.File

fun Project.configureBuildConfig() {
    val (majorVersion, minorVersion, patchVersion, versionCode) = getVersionInfo()

    androidExtension.apply {
        buildFeatures {
            buildConfig = true
        }

        buildTypes {
            getByName("debug") {
                // Version 코드 사용을 위한 등록
                buildConfigField(
                    "String",
                    "APP_VERSION_NAME",
                    "\"$majorVersion.$minorVersion.$patchVersion\""
                )
                buildConfigField("int", "APP_VERSION_CODE", "$versionCode")
            }

            getByName("release") {
                // Version 코드 사용을 위한 등록
                buildConfigField(
                    "String",
                    "APP_VERSION_NAME",
                    "\"$majorVersion.$minorVersion.$patchVersion\""
                )
                buildConfigField("int", "APP_VERSION_CODE", "$versionCode")
            }
        }
    }
}

/**
 * https://github.com/android/nowinandroid/blob/main/build-logic/convention/src/main/kotlin/com/google/samples/apps/nowinandroid/KotlinAndroid.kt
 */
internal fun Project.configureKotlinAndroid() {
    // Plugins
    pluginManager.apply("org.jetbrains.kotlin.android")

    // Android settings
    androidExtension.apply {
        compileSdk = findVersion("compileSdk").toInt()
        buildToolsVersion = findVersion("buildToolsVersion")

        defaultConfig {
            minSdk = findVersion("minSdk").toInt()
            vectorDrawables.useSupportLibrary = true
        }

        buildFeatures {
            buildConfig = false
        }

        buildTypes {
            getByName("debug") {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }

            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        @Suppress("UnstableApiUsage")
        testOptions {
            unitTests.all {
                it.reports.html.outputLocation.set(File("${project.rootDir}/build/reports/test/${project.name}"))
                it.reports.html.required.set(true)
                it.reports.junitXml.outputLocation.set(File("${project.rootDir}/build/reports/test/${project.name}"))
                it.reports.junitXml.required.set(true)
            }
        }

        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }

    configureKotlin<KotlinAndroidProjectExtension>()
}

/**
 * Configure base Kotlin options for JVM (non-Android)
 */
internal fun Project.configureKotlinJvm() {
    extensions.configure<JavaPluginExtension> {
        // Up to Java 11 APIs are available through desugaring
        // https://developer.android.com/studio/write/java11-minimal-support-table
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    configureKotlin<KotlinJvmProjectExtension>()
}

/**
 * Configure base Kotlin options
 */
private inline fun <reified T : KotlinTopLevelExtension> Project.configureKotlin() = configure<T> {
    // Treat all Kotlin warnings as errors (disabled by default)
    // Override by setting warningsAsErrors=true in your ~/.gradle/gradle.properties
    val warningsAsErrors: String? by project
    when (this) {
        is KotlinAndroidProjectExtension -> compilerOptions
        is KotlinJvmProjectExtension -> compilerOptions
        else -> TODO("Unsupported project extension $this ${T::class}")
    }.apply {
        jvmTarget = JvmTarget.JVM_17
        languageVersion.set(KotlinVersion.KOTLIN_1_9)
        apiVersion.set(KotlinVersion.KOTLIN_1_9)

        allWarningsAsErrors = warningsAsErrors.toBoolean()
        freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
        freeCompilerArgs.add("-opt-in=kotlin.Experimental")
        freeCompilerArgs.add(
            // Enable experimental coroutines APIs, including Flow
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        )
        freeCompilerArgs.add("-opt-in=androidx.compose.material3.ExperimentalMaterial3Api")
        freeCompilerArgs.add("-opt-in=androidx.compose.animation.ExperimentalAnimationApi")
    }
}
