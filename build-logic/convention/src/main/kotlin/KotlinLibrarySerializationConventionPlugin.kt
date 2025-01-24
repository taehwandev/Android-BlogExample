import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import tech.thdev.gradle.extensions.findLibrary

class KotlinLibrarySerializationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            dependencies {
                implementation(findLibrary("kotlin-serializationJson"))
            }
        }
    }
}
