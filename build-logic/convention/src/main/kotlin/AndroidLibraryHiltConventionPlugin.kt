import org.gradle.api.Plugin
import org.gradle.api.Project
import tech.thdev.gradle.configureDaggerHilt

class AndroidLibraryHiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("tech.thdev.kotlin.library.ksp")
                apply("com.google.dagger.hilt.android")
            }

            configureDaggerHilt()
        }
    }
}
