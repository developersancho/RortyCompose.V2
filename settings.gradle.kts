pluginManagement {
//    resolutionStrategy {
//        eachPlugin {
//            if (requested.id.id == "com.android.library") {
//                useModule("com.android.tools.build:gradle:${requested.version}")
//            }
//            if (requested.id.id == "com.android.application") {
//                useModule("com.android.tools.build:gradle:${requested.version}")
//            }
//        }
//    }
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://plugins.gradle.org/m2/")
        maven("https://developer.huawei.com/repo/")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://plugins.gradle.org/m2/")
        maven("https://developer.huawei.com/repo/")
    }
}
rootProject.name = "RortyCompose.V2"
include(":app")
include(":libraries:framework")
