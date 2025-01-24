import org.gradle.api.Plugin
import org.gradle.api.Project
import tech.thdev.gradle.configureComposeAndroid
import tech.thdev.gradle.configureComposeFeature
import tech.thdev.gradle.configureKotlinAndroid

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")

                apply("tech.thdev.android.library.unit.test")

                apply("org.jetbrains.kotlin.plugin.serialization")

                apply("tech.thdev.kotlin.library.verify.detekt")
                apply("tech.thdev.kotlin.library.verify.test")
            }

            configureKotlinAndroid()
            configureComposeFeature()
            configureComposeAndroid()
        }
    }
}
