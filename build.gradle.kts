/*buildscript {
    val compose_version by extra("1.1.1")
    dependencies {
        classpath("com.google.gms:google-services:4.3.10")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.8.1")
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath("com.huawei.agconnect:agcp:1.6.2.300")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.41")
    }
}*/

plugins {
//    id("com.android.application") apply false
//    id("com.android.library") apply false
//    id("com.huawei.agconnect.agcp") version "1.6.3.300" apply false
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