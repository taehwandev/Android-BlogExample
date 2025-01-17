package tech.thdev.gradle

import com.android.build.api.artifact.ScopedArtifact
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.ScopedArtifacts
import tech.thdev.gradle.extensions.findVersion
import org.gradle.api.Project
import org.gradle.api.file.Directory
import org.gradle.api.file.RegularFile
import org.gradle.api.provider.ListProperty
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension
import org.gradle.testing.jacoco.tasks.JacocoReport
import java.io.File
import java.util.Locale

internal val coverageExclusions = listOf(
    // Android
    "**/R.class",
    "**/R\$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    "**/*_Hilt*.class",
    "**/Hilt_*.class",
    "**/BR.class",
    "**/*\$ViewInjector*.*",
    "**/*\$ViewBinder*.*",
    // ignore Dagger 2 generated code
    "**/*_MembersInjector.class",
    "**/Dagger*Component*.class",
    "**/Dagger*Subcomponent*.class",
    "**/*Subcomponent\$Builder.class",
    "**/*Module_*Factory.class",
    // Dagger declaration classes
    "**/**Module.class",
    "**/**Component.class",
    "**/*Constants.class",
    //ignore all model classes
    "**/models/**",
    "**/model/**",
    //ignore all exceptions classes
    "**/exceptions/**",
    "**/**Exception.class",
    "**/**Exception**.class",
    //ignore all event classes
    "**/Mock**.class",
    // ignore all generated classes
    "**/generated/**",
    "**/**assert*/**",
)

private fun String.capitalize() = replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
}

/**
 * Creates a new task that generates a combined coverage report with data from local and
 * instrumented tests.
 *
 * `create{variant}CombinedCoverageReport`
 *
 * Note that coverage data must exist before running the task. This allows us to run device
 * tests on CI using a different Github Action or an external device farm.
 */
internal fun Project.configureJacoco(
    androidComponentsExtension: AndroidComponentsExtension<*, *, *>,
) {
    configure<JacocoPluginExtension> {
        toolVersion = findVersion("jacoco")
    }

    androidComponentsExtension.onVariants { variant ->
        val myObjFactory = project.objects
        val buildDir = layout.buildDirectory.get().asFile
        val allJars: ListProperty<RegularFile> = myObjFactory.listProperty(RegularFile::class.java)
        val allDirectories: ListProperty<Directory> =
            myObjFactory.listProperty(Directory::class.java)
        val reportTask =
            tasks.register(
                "create${variant.name.capitalize()}CombinedCoverageReport",
                JacocoReport::class,
            ) {

                classDirectories.setFrom(
                    allJars,
                    allDirectories.map { dirs ->
                        dirs.map { dir ->
                            myObjFactory.fileTree().setDir(dir).exclude(coverageExclusions)
                        }
                    },
                )
                reports {
                    xml.required = true
                    html.required = true
                }

                sourceDirectories.setFrom(
                    files(
                        "$projectDir/src/main/java",
                        "$projectDir/src/main/kotlin",
                    ),
                )

                executionData.setFrom(
                    project.fileTree("$buildDir/outputs/unit_test_code_coverage/${variant.name}UnitTest")
                        .matching { include("**/*.exec") },

                    project.fileTree("$buildDir/outputs/code_coverage/${variant.name}AndroidTest")
                        .matching { include("**/*.ec") },
                )
            }


        variant.artifacts.forScope(ScopedArtifacts.Scope.PROJECT)
            .use(reportTask)
            .toGet(
                ScopedArtifact.CLASSES,
                { _ -> allJars },
                { _ -> allDirectories },
            )
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()

        configure<JacocoTaskExtension> {
            // Required for JaCoCo + Robolectric
            // https://github.com/robolectric/robolectric/issues/2230
            isIncludeNoLocationClasses = true

            // Required for JDK 11 with the above
            // https://github.com/gradle/gradle/issues/5184#issuecomment-391982009
            excludes = listOf("jdk.internal.*")
        }
    }
}

/**
 * Jvm codeQuality
 */
internal fun Project.configureJvmJacoco() {
    configure<JacocoPluginExtension> {
        toolVersion = findVersion("jacoco")
    }

    // 코드 커버리지 report
    tasks.register<JacocoReport>("jacocoReport") {
        group = "Reporting"
        description = "Generate Jacoco coverage reports."

        reports {
            xml.required.set(true)
            xml.outputLocation.set(file("$rootDir/build/reports/jacoco/${project.name}/jacocoReport.xml"))
            html.required.set(true)
            html.outputLocation.set(file("$rootDir/build/reports/jacoco/${project.name}/"))
        }

        val kotlinPath = "tmp/kotlin-classes/debug/"
        val classPath = "intermediates/classes/debug/"
        val javacPath = "intermediates/javac/debug/compileDebugJavaWithJavac/classes/"
        val javaPath = "classes/"

        sourceDirectories.setFrom(fileTree(File(projectDir, "build")) {
            include("src/main/java")
        })
        classDirectories.setFrom(fileTree(File(projectDir, "build")) {
            include(classPath, javacPath, javaPath, kotlinPath)
            exclude(coverageExclusions)
        })

        executionData.setFrom(fileTree(File(projectDir, "build")) {
            include("jacoco/test*.exec")
        })
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()

        configure<JacocoTaskExtension> {
            // Required for JaCoCo + Robolectric
            // https://github.com/robolectric/robolectric/issues/2230
            isIncludeNoLocationClasses = true

            // Required for JDK 11 with the above
            // https://github.com/gradle/gradle/issues/5184#issuecomment-391982009
            excludes = listOf("jdk.internal.*")
        }
    }
}
