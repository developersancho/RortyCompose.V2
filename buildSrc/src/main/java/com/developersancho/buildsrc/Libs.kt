package com.developersancho.buildsrc

object Versions {
    const val Compose = "1.1.1"
}

object ComposeLib {

}

object TestingLib {
    const val Junit = "junit:junit:4.13.2"
}

object AndroidTestingLib {
    const val JunitExt = "androidx.test.ext:junit:1.1.3"
    const val ComposeTestJunit = "androidx.compose.ui:ui-test-junit4:${Versions.Compose}"
    const val EspressoCore = "androidx.test.espresso:espresso-core:3.4.0"
}