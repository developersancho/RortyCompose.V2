buildscript {
    val compose_version by extra("1.1.1")
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath("com.huawei.agconnect:agcp:1.6.4.300")
    }
}

plugins {
    id("com.google.devtools.ksp") version "1.6.10-1.0.4" apply false
}

apply<codequality.DependencyUpdatePlugin>()

apply(from = "git-hooks/githooks.gradle")

apply(plugin = "codeanalyzetools.jacoco-multi-report")

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

afterEvaluate {
    // We install the hook at the first occasion
    tasks["clean"].dependsOn("installGitHooks")
}