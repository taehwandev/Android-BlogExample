@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "11"
    languageVersion = libs.versions.kotlin.get()
}

dependencies {
    implementation(libs.plugin.kotlin)
    implementation(libs.plugin.agp)
}