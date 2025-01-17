import tech.thdev.gradle.configureVerifyDetekt
import tech.thdev.gradle.extensions.findLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinLibraryVerifyDetektConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("io.gitlab.arturbosch.detekt")
            }

            configureVerifyDetekt()

            dependencies {
                "detektPlugins"(findLibrary("verify-detektFormatting"))
            }
        }
    }
}
