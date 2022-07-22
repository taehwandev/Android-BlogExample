import tech.thdev.gradle.dependencies.Dependency

plugins {
    kotlin("jvm")
}

dependencies {
    implementation(Dependency.Kotlin.stdLib)
    implementation("com.google.devtools.ksp:symbol-processing-api:1.7.10-1.0.6")
    implementation("com.squareup:kotlinpoet:1.12.0")
    api(projects.kspSampleAnnotation)
}