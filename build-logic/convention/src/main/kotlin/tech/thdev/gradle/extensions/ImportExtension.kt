@file:Suppress("PackageDirectoryMismatch")

import org.gradle.kotlin.dsl.DependencyHandlerScope

internal fun DependencyHandlerScope.api(dependency: Any) {
    add("api", dependency)
}

internal fun DependencyHandlerScope.compileOnly(dependency: Any) {
    add("compileOnly", dependency)
}

internal fun DependencyHandlerScope.implementation(dependency: Any) {
    add("implementation", dependency)
}

internal fun DependencyHandlerScope.testImplementation(dependency: Any) {
    add("testImplementation", dependency)
}
