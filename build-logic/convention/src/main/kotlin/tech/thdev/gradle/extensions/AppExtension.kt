@file:Suppress("PackageDirectoryMismatch")

import org.gradle.api.Project
import org.gradle.api.internal.catalog.DelegatingProjectDependency
import tech.thdev.gradle.extensions.androidExtension

fun Project.setNamespace(name: String) {
    androidExtension.apply {
        namespace = "tech.thdev.$name"
    }
}

val String.kspSourceSet: String
    get() = "build/generated/ksp/$this/kotlin"

fun DelegatingProjectDependency.filterImplementation(
    body: (target: Project) -> Unit,
) {
    dependencyProject.allprojects.forEach {
        if (it.buildFile.isFile) {
            body(it)
        }
    }
}
