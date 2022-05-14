import tech.thdev.gradle.dependencies.Dependency

plugins {
    kotlin("jvm")
}

dependencies {
    implementation(Dependency.Kotlin.stdLib)
    implementation("com.google.devtools.ksp:symbol-processing-api:1.6.21-1.0.5")
    implementation("com.squareup:kotlinpoet:1.11.0")
    api(projects.kspSampleAnnotation)
}