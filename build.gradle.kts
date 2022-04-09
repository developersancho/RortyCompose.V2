plugins {
    id("com.google.devtools.ksp") version "1.6.10-1.0.4" apply false
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val testJvm by tasks.registering {
    dependsOn("test")
}

val buildTests by tasks.registering {
    dependsOn("testClasses")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}