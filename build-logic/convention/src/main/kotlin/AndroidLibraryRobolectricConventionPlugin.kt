import tech.thdev.gradle.extensions.androidExtension
import tech.thdev.gradle.extensions.findLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Robolectric 사용시 juni4의 @Test 어노테이션을 사용해야 정상 동작합니다.
 * 이외 임포트는 junit5껄 해도 동작하도록 호환 모듈을 포함합니다. junit5Vintage
 */
class AndroidLibraryRobolectricConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            androidExtension.apply {
                @Suppress("UnstableApiUsage")
                testOptions {
                    unitTests {
                        // For Robolectric
                        isIncludeAndroidResources = true
                    }
                }
            }

            dependencies {
                testImplementation(findLibrary("test-androidx-core"))
                testImplementation(findLibrary("test-androidx-runner"))
                testImplementation(findLibrary("test-androidx-junit"))
                testImplementation(findLibrary("test-robolectric"))
                "testRuntimeOnly"(findLibrary("test-junit5-vintage"))
            }
        }
    }
}
