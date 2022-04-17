pluginManagement {
//    resolutionStrategy {
//        eachPlugin {
//            if (requested.id.id == "com.android.application") {
//                useModule("com.android.tools.build:gradle:7.1.3")
//            }
//            if (requested.id.id == "com.android.library") {
//                useModule("com.android.tools.build:gradle:7.1.3")
//            }
//            if (requested.id.namespace == "com.huawei.agconnect") {
//                println(">> ${requested.id.id}")
//                if (requested.id.id == "com.huawei.agconnect.agcp") {
//                    useModule("com.huawei.agconnect:agcp:1.6.3.300")
//                }
//                println(">> $target")
//            } else {
//                println(">  $target")
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
include(":libraries:jetframework")
include(":libraries:testutils")
