@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("lib-tasks")
    id("code-quality")
    kotlin("android")
}

val versionCatalog = project.extensions.getByType<VersionCatalogsExtension>()
val libs = versionCatalog.named("libs")


android {
    compileSdk = libs.findVersion("compileSdk").get().requiredVersion.toInt()
    buildToolsVersion = libs.findVersion("buildToolsVersion").get().requiredVersion

    defaultConfig {
        minSdk = libs.findVersion("minSdk").get().requiredVersion.toInt()
        targetSdk = libs.findVersion("targetSdk").get().requiredVersion.toInt()

    }

    kotlinOptions {
        jvmTarget = "11"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    testImplementation(libs.findLibrary("test.mockito").get())
    testImplementation(libs.findLibrary("test.mockitoKotlin").get())

    testImplementation(libs.findLibrary("test.junit5").get())
    testRuntimeOnly(libs.findLibrary("test.junit5.engine").get())
    testRuntimeOnly(libs.findLibrary("test.junit5.vintage").get())
}