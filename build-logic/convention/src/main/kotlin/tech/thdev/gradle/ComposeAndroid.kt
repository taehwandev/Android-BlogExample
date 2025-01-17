package tech.thdev.gradle

import implementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import tech.thdev.gradle.extensions.androidExtension
import tech.thdev.gradle.extensions.findLibrary

fun Project.configureComposeAndroid() {
    androidExtension.apply {
        dependencies {
            implementation(findLibrary("compose-keyboardState"))

            implementation(findLibrary("kotlin-collectionsImmutable"))

            implementation(findLibrary("compose-ui"))
            implementation(findLibrary("compose-foundation"))
            implementation(findLibrary("compose-material3"))
            implementation(findLibrary("compose-runtime"))
            implementation(findLibrary("compose-uiToolingPreview"))
            implementation(findLibrary("compose-constraintLayout"))
            implementation(findLibrary("compose-animation"))

            implementation(findLibrary("androidx-lifecycleRuntimeCompose"))

            implementation(findLibrary("image-coil"))
            implementation(findLibrary("image-coil-network"))

            "debugRuntimeOnly"(findLibrary("compose-uiTooling"))
        }
    }
}

/**
 * Compose Library
 */
fun Project.configureComposeFeature() {
    androidExtension.apply {
        with(plugins) {
            apply("org.jetbrains.kotlin.plugin.compose")
        }

        buildFeatures {
            compose = true
        }
    }
}
