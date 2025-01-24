import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import tech.thdev.gradle.configureKspSourceSets

class KotlinLibraryKspConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
            }

            configureKspSourceSets()

            extensions.configure<KspExtension> {
                arg("moduleName", project.name)
                arg("rootDir", rootDir.absolutePath)
            }
        }
    }
}
