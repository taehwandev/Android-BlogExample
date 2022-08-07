tasks.withType<Test>() {
    useJUnitPlatform()
    jvmArgs = listOf("--add-opens", "java.base/java.lang.invoke=ALL-UNNAMED")
}