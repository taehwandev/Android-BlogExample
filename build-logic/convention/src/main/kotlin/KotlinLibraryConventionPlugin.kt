import tech.thdev.gradle.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")

                apply("tech.thdev.android.library.unit.test")

                apply("tech.thdev.kotlin.library.verify.detekt")
                apply("tech.thdev.kotlin.library.verify.jacoco")
                apply("tech.thdev.kotlin.library.verify.test")
            }

            configureKotlinJvm()
        }
    }
}
