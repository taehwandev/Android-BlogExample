@file:Suppress("PackageDirectoryMismatch")

import org.gradle.api.Project

@Suppress("UnusedReceiverParameter")
fun Project.getVersionInfo(): VersionInfo {
    return VersionInfo(
        majorVersion = "25",
        minorVersion = "1",
        patchVersion = "0",
        versionCode = 1,
    )

//    val versionPropsFile = file("${rootDir.absolutePath}/version.properties")

//    val majorVersion: String
//    val minorVersion: String
//    val patchVersion: String
//    val versionCode: Int

//    if (versionPropsFile.exists()) {
//        val versionProps = Properties()
//        versionProps.load(versionPropsFile.reader())
//
//        versionCode = versionProps["versionCode"].toString().toInt()
//        majorVersion = versionProps["majorVersion"].toString()
//        minorVersion = versionProps["minorVersion"].toString()
//        patchVersion = versionProps["patchVersion"].toString()
//        return VersionInfo(
//            majorVersion = majorVersion,
//            minorVersion = minorVersion,
//            patchVersion = patchVersion,
//            versionCode = versionCode,
//        )
//    } else {
//        throw GradleException("version.properties 파일을 찾을 수 없습니다.")
//    }
}

data class VersionInfo(
    val majorVersion: String,
    val minorVersion: String,
    val patchVersion: String,
    val versionCode: Int,
)
