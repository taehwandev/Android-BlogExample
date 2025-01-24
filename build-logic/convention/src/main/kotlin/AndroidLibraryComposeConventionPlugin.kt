import org.gradle.api.Plugin
import org.gradle.api.Project
import tech.thdev.gradle.configureComposeAndroid
import tech.thdev.gradle.configureComposeFeature

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            configureComposeFeature()
            configureComposeAndroid()
        }
    }
}
