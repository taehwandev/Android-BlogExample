import org.gradle.api.Plugin
import org.gradle.api.Project
import tech.thdev.gradle.configureKotlinJvm

class KotlinLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")

                apply("tech.thdev.android.library.unit.test")

                apply("tech.thdev.kotlin.library.verify.detekt")
                apply("tech.thdev.kotlin.library.verify.test")
            }

            configureKotlinJvm()
        }
    }
}
