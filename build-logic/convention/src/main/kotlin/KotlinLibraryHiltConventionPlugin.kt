import org.gradle.api.Plugin
import org.gradle.api.Project
import tech.thdev.gradle.configureDaggerKotlin

class KotlinLibraryHiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("tech.thdev.kotlin.library.ksp")
            }

            configureDaggerKotlin()
        }
    }
}
