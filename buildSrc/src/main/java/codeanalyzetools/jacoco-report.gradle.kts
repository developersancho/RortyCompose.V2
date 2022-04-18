package codeanalyzetools

import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import org.gradle.testing.jacoco.plugins.JacocoPlugin
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension
import org.gradle.testing.jacoco.tasks.JacocoCoverageVerification
import org.gradle.testing.jacoco.tasks.JacocoReport
import org.gradle.testing.jacoco.tasks.JacocoReportsContainer

plugins.apply(JacocoPlugin::class)

configure<JacocoPluginExtension> {
    toolVersion = "0.8.7"
}

tasks.withType<Test> {
    configure<JacocoTaskExtension> {
        isIncludeNoLocationClasses = false
        excludes = listOf("jdk.internal.*")
    }
}

val fileFilter = listOf(
    "**/R.class",
    "**/R\$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    "**/*Test*.*",
    "android/**/*.*",
    "**/*\$Lambda$*.*", // Jacoco can not handle several "$" in class name.
    "**/*\$inlined$*.*", // Kotlin specific, Jacoco can not handle several "$" in class name.
    // adapters generated by moshi
    "**/*JsonAdapter.*",
    "**/*Companion*.*",
    "**/*Module*.*",
    "**/*Dagger*.*",
    "**/*Hilt*.*",
    "**/*MembersInjector*.*",
    "**/*_MembersInjector.class",
    "**/*_Factory*.*",
    "**/*_Provide*Factory*.*",
    "**/*Extensions*.*",
    // sealed and data classes
    "**/*$Result.*",
    "**/*$Result\$*.*",
    "**/*App.*",
    "**/*Application.*",
    "**/*Activity.*",
    "**/*Fragment.*",
)

private val classDirectoriesTree = fileTree(project.buildDir) {
    include(
        "**/classes/**/main/**",
        "**/intermediates/classes/debug/**",
        "**/intermediates/javac/debug/*/classes/**", // Android Gradle Plugin 3.2.x support.
        "**/tmp/kotlin-classes/debug/**"
    )

    exclude(fileFilter)
}

private val sourceDirectoriesTree = fileTree("${project.buildDir}") {
    include(
        "src/main/java/**",
        "src/main/kotlin/**",
        "src/debug/java/**",
        "src/debug/kotlin/**"
    )
}

private val executionDataTree = fileTree(project.buildDir) {
    include(
        "outputs/unit_test_code_coverage/debugUnitTest/testDebugUnitTest.exec",
        "outputs/code_coverage/**/*.ec",
        "jacoco/jacocoTestReportDebug.exec",
        "jacoco/testDebugUnitTest.exec",
        "jacoco/test.exec"
    )
}

fun JacocoReportsContainer.reports() {
    xml.required.set(true)
    html.required.set(true)
    xml.outputLocation.set(file("${buildDir}/reports/jacoco/jacocoTestReport/jacocoTestReport.xml"))
    html.outputLocation.set(file("${buildDir}/reports/jacoco/jacocoTestReport/html"))
}

fun JacocoReport.setDirectories() {
    sourceDirectories.setFrom(sourceDirectoriesTree)
    classDirectories.setFrom(classDirectoriesTree)
    executionData.setFrom(executionDataTree)
}

fun JacocoCoverageVerification.setDirectories() {
    sourceDirectories.setFrom(sourceDirectoriesTree)
    classDirectories.setFrom(classDirectoriesTree)
    executionData.setFrom(executionDataTree)
}

afterEvaluate {
    tasks.register<JacocoReport>("jacocoAndroidTestReport") {
        group = "JacocoReport"
        description = "Code coverage report for both Android and Unit tests."
        dependsOn("testDebugUnitTest")

        reports {
            reports()
        }
        setDirectories()

        finalizedBy("jacocoAndroidTestCoverage")

        doLast {
            exec {
                commandLine("open", "${buildDir}/reports/jacoco/jacocoTestReport/html/index.html")
            }
        }
    }

    tasks.register<JacocoCoverageVerification>("jacocoAndroidTestCoverage") {
        group = "JacocoReport"
        description =
            "Generates coverage ratio(./gradlew jacocoAndroidTestCoverage -PminCoverage=0.8)"
        setDirectories()
        violationRules {
            isFailOnViolation = true
            rule {
                limit {
                    minimum = "0.5".toBigDecimal()
                }
            }
            rule {
                element = "CLASS"
                includes = listOf("org.gradle")
                limit {
                    counter = "LINE"
                    value = "TOTALCOUNT"
                    minimum = "0.5".toBigDecimal()
                }
            }
        }
    }
}