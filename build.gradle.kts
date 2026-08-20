plugins {
    id("base")
}

val buildForge1122 = tasks.register("buildForge1122", org.gradle.api.tasks.Exec::class) {
    group = "build"
    description = "Builds Forge 1.12.2 with isolated wrapper"
    workingDir = file("forge-1122")

    val j8Path = providers.gradleProperty("java8Home").orNull
        ?: System.getenv("JAVA8_HOME")
        ?: throw GradleException("Missing 'java8Home' in gradle.properties!")

    environment("JAVA_HOME", j8Path)

    val isWindows = org.gradle.internal.os.OperatingSystem.current().isWindows
    commandLine(if (isWindows) listOf("cmd", "/c", "gradlew.bat", "build") else listOf("./gradlew", "build"))
}

val collectJars = tasks.register("collectJars", org.gradle.api.tasks.Copy::class) {
    group = "build"
    description = "Copies all mod JARs to root build/libs"

    into(layout.buildDirectory.dir("libs"))

    subprojects.forEach { subproject ->
        from(subproject.layout.buildDirectory.dir("libs")) {
            include("*.jar")
            exclude("*-dev.jar", "*-sources.jar")
        }
    }

    from(file("forge-1122/build/libs")) {
        include("*.jar")
        exclude("*-dev.jar", "*-sources.jar")
    }

    dependsOn(subprojects.map { it.tasks.matching { t -> t.name == "build" } })
    dependsOn(buildForge1122)
}

tasks.named("build") {
    finalizedBy(collectJars)
}