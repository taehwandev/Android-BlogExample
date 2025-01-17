package tech.thdev.gradle

import tech.thdev.gradle.extensions.findLibrary
import implementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import testImplementation

/**
 * UnitTest dependencies
 */
fun Project.configureUnitTest() {
    dependencies {
        implementation(findLibrary("kotlin.stdlib"))
        implementation(findLibrary("coroutines-core"))

        testImplementation(findLibrary("test-mockito"))
        testImplementation(findLibrary("test-mockito-kotlin"))
        testImplementation(findLibrary("test-junit5"))
        "testRuntimeOnly"(findLibrary("test-junit5-engine"))
    }
}
