package tech.thdev.gradle

import implementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import tech.thdev.gradle.extensions.findLibrary

/**
 * Dagger Android 적용 시
 */
internal fun Project.configureDaggerHilt() {
    configureDaggerKotlin()

    dependencies {
        implementation(findLibrary("dagger-hilt-android"))
        "ksp"(findLibrary("dagger-hilt-android-compiler"))
    }
}

internal fun Project.configureDaggerKotlin() {
    dependencies {
        implementation(findLibrary("dagger-javaxInject"))
        implementation(findLibrary("dagger-baseApi"))
        "ksp"(findLibrary("dagger-baseCompiler"))
    }
}
