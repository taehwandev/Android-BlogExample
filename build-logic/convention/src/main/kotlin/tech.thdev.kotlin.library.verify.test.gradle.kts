afterEvaluate {
    val tests = mutableListOf<String>()
    val lints = mutableListOf<String>()
    if (plugins.hasPlugin("java-library") || plugins.hasPlugin("java") || plugins.hasPlugin("kotlin")) {
        // test
        tests.add("test")
    } else {
        // test and lint
        if (project.name == "app") {
            // 앱 모듈만 flavor 옵션이 있어 devDebug 빌드타입으로 unit test 실행
            tests.add("testDevDebugUnitTest")
        } else {
            tests.add("testDebugUnitTest")
        }
        lints.add("lintDebug")
    }

    tasks.register("testAll") {
        dependsOn(*tests.toTypedArray())
    }
    tasks.register("lintAll") {
        if (lints.isNotEmpty()) {
            dependsOn(*lints.toTypedArray())
        } else {
            logger.log(LogLevel.DEBUG, "${project.name} has no lint")
        }
    }
}
