import tech.thdev.gradle.configureKotlinAndroid
import tech.thdev.gradle.extensions.findLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")

                apply("tech.thdev.android.library.unit.test")
                apply("tech.thdev.kotlin.library.ksp")

                apply("tech.thdev.android.library.verify.jacoco")
                apply("tech.thdev.kotlin.library.verify.detekt")
                apply("tech.thdev.kotlin.library.verify.test")
            }

            configureKotlinAndroid()

            dependencies {
                implementation(findLibrary("coroutines-core"))
                implementation(findLibrary("androidx-annotation"))
            }
        }
    }
}
