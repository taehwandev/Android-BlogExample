import tech.thdev.gradle.configureJvmJacoco
import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinLibraryVerifyJacocoConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("jacoco")

            configureJvmJacoco()
        }
    }
}
