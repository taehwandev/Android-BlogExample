plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

val kotlinVersion = "1.7.10"

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions {
    languageVersion = kotlinVersion
}