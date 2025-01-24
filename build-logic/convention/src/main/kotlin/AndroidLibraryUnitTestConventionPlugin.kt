import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import tech.thdev.gradle.configureUnitTest
import tech.thdev.gradle.extensions.findLibrary

class AndroidLibraryUnitTestConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            configureUnitTest()

            dependencies {
                implementation(findLibrary("kotlin-stdlib"))
                implementation(findLibrary("coroutines-core"))

                testImplementation(findLibrary("test-mockito"))
                testImplementation(findLibrary("test-mockito-kotlin"))
                testImplementation(findLibrary("test-junit5"))
                "testRuntimeOnly"(findLibrary("test-junit5-engine"))
            }
        }
    }
}
