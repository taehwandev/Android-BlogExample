// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.plugin.agp)
        classpath(libs.plugin.kotlin)
        classpath(libs.plugin.ksp)
    }
}