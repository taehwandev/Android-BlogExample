import tech.thdev.gradle.configureCoroutineAndroid
import tech.thdev.gradle.extensions.findLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryComposeFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("tech.thdev.android.library")
                apply("tech.thdev.android.library.compose")
                apply("tech.thdev.android.library.unit.test")
            }

            configureCoroutineAndroid()

            dependencies {
                implementation(findLibrary("androidx-activity"))
            }
        }
    }
}
