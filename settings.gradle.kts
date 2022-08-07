@file:Suppress("UnstableApiUsage")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    versionCatalogs {
        create("libs") {
            from(files("./buildSrc/libs.versions.toml"))
        }
    }

    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "MyApplication"

include(":app")

/**
 * multi module
 */
val modules = hashMapOf<String, String>()

rootProject.projectDir.listFiles()
    ?.forEach {
        findSubProjects(it)
    }

fun findSubProjects(file: File) {
    if (file.name.startsWith(".")) {
        return
    }

    if (file.name == "build.gradle.kts" || file.name == "build.gradle") {
        modules[file.parentFile.name] = file.parentFile.path
        return
    }

    if (file.isDirectory) {
        file.listFiles()
            ?.forEach {
                findSubProjects(it)
            }
    }
}

for (project in rootProject.children) {

    if (modules.containsKey(project.name)) {
        val directory = modules[project.name] ?: continue
        project.projectDir = File(directory)
    }
}