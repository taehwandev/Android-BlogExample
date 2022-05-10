plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

val kotlinVersion = "1.6.21"

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions {
    languageVersion = kotlinVersion
}