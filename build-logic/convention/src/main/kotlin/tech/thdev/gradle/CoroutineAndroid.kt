package tech.thdev.gradle

import implementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import tech.thdev.gradle.extensions.findLibrary

internal fun Project.configureCoroutineAndroid() {
    dependencies {
        implementation(findLibrary("coroutines-android"))
    }
}
