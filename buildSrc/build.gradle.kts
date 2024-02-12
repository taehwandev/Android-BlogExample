@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

subprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "17"
            languageVersion = "1.8"
        }
    }
    tasks.withType<JavaCompile>().configureEach {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
}

dependencies {
    implementation(libs.plugin.kotlin)
    implementation(libs.plugin.agp)
}