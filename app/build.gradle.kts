import com.developersancho.buildsrc.*
import extensions.setSigningConfigs

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
    id("codeanalyzetools.quality")
    id("codeanalyzetools.jacoco-report")
}

if (file("google-services.json").exists()) {
    apply(plugin = "com.google.gms.google-services")
    apply(plugin = "com.google.firebase.crashlytics")
}

if (file("agconnect-services.json").exists()) {
    apply(plugin = "com.huawei.agconnect")
}

android {
    compileSdk = Configs.CompileSdk

    defaultConfig {
        applicationId = Configs.Id
        minSdk = Configs.MinSdk
        targetSdk = Configs.TargetSdk
        versionCode = Configs.VersionCode
        versionName = Configs.VersionName
        multiDexEnabled = true
        testInstrumentationRunner = Configs.AndroidJunitRunner
        vectorDrawables.useSupportLibrary = true
    }

    setSigningConfigs(project)

//    signingConfigs {
//        create("signingConfigRelease") {
//            storeFile = rootProject.file("signing/rortycompose-release.jks")
//            keyAlias = "rortycompose"
//            storePassword = "123456"
//            keyPassword = "123456"
//        }
//
//        getByName("debug") {
//            storeFile = rootProject.file("signing/debug.keystore")
//            keyAlias = "androiddebugkey"
//            keyPassword = "android"
//            storePassword = "android"
//        }
//    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("signingConfigRelease")
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"${Configs.Release.BaseUrl}\"")
            buildConfigField("String", "DB_NAME", "\"${Configs.Release.DbName}\"")
        }

        debug {
            signingConfig = signingConfigs.getByName("debug")
//            To get coverage reports for instrumented test set isTestCoverageEnabled flag to true
            isTestCoverageEnabled = false
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"${Configs.Debug.BaseUrl}\"")
            buildConfigField("String", "DB_NAME", "\"${Configs.Debug.DbName}\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
        freeCompilerArgs = Configs.FreeCompilerArgs
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Compose
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1,gradle-plugins}"
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

android.applicationVariants.all {
    val variantName = name
    kotlin.sourceSets {
        getByName("main") {
            kotlin.srcDir(File("build/generated/ksp/$variantName/kotlin"))
        }
    }
}

dependencies {
    implementation(project(mapOf("path" to ":libraries:framework")))
    implementation(project(mapOf("path" to ":libraries:jetframework")))

    implementation(SupportLib.CoreKtx)
    implementation(SupportLib.Appcompat)
    implementation(SupportLib.Material)
    implementation(SupportLib.CoroutineCore)
    implementation(SupportLib.CoroutineAndroid)
    implementation(SupportLib.LifecycleRuntime)
    // Compose
    implementation(ComposeLib.Ui)
    implementation(ComposeLib.Material)
    implementation(ComposeLib.Preview)
    implementation(ComposeLib.Runtime)
    implementation(ComposeLib.Foundation)
    implementation(ComposeLib.MaterialIconCore)
    implementation(ComposeLib.MaterialIconExtended)
    androidTestImplementation(AndroidTestingLib.ComposeTestJunit)
    debugImplementation(ComposeLib.Tooling)
    debugImplementation(ComposeLib.Manifest)
    //
    implementation(ComposeLib.Activity)
    implementation(ComposeLib.ViewModel)
    implementation(ComposeLib.ConstraintLayout)
    implementation(ComposeLib.Lottie)
    implementation(ComposeLib.Paging)
    implementation(ComposeLib.Coil)

    // Navigation
    implementation(NavigationLib.Navigation)
    implementation(NavigationLib.DestinationCore)
    ksp(NavigationLib.DestinationKsp)
    implementation(NavigationLib.DestinationAnimation)

    // Accompanist
    implementation(AccompanistLib.Swiperefresh)
    implementation(AccompanistLib.Systemuicontroller)
    implementation(AccompanistLib.Insets)
    implementation(AccompanistLib.PlaceholderMaterial)
    implementation(AccompanistLib.NavigationMaterial)
    implementation(AccompanistLib.Permissions)
    implementation(AccompanistLib.Pager)
    implementation(AccompanistLib.Indicators)
    implementation(AccompanistLib.Webview)

    // Network
    implementation(NetworkLib.Moshi)
    ksp(NetworkLib.MoshiCodegen)
    implementation(NetworkLib.MoshiLazyAdapter)
    implementation(NetworkLib.Retrofit)
    implementation(NetworkLib.RetrofitMoshi)
    implementation(NetworkLib.Okhttp)
    implementation(NetworkLib.LoggingInterceptor)
    // testImplementation(TestingLib.Okhttp)
    debugImplementation(NetworkLib.ChuckerDebug)
    releaseImplementation(NetworkLib.ChuckerRelease)

    // Storage
    implementation(StorageLib.RoomKtx)
    ksp(StorageLib.RoomCompiler)
    implementation(StorageLib.DatastorePref)
    implementation(StorageLib.Datastore)
    implementation(StorageLib.SecurityPref)

    // Firebase
    implementation(FirebaseLib.Base)
    implementation(FirebaseLib.Analytic)
    implementation(FirebaseLib.Crashlytics)
    implementation(FirebaseLib.Push)
    implementation(FirebaseLib.Config)

    // Huawei
    implementation(HuaweiLib.Base)
    implementation(HuaweiLib.Core)
    // xxx Analytic RoboElectric'i patlatıyor xxx
    // implementation(HuaweiLib.Analytic)
//    implementation(HuaweiLib.Crashlytics)
    implementation(HuaweiLib.Push)
//    implementation(HuaweiLib.Config)

    // Core
    implementation(SupportLib.Splashscreen)
    implementation(SupportLib.Timber)
    implementation(SupportLib.Paging)

    // Dagger Hilt
    implementation(DaggerHiltLib.Android)
    kapt(DaggerHiltLib.Compiler)
    implementation(DaggerHiltLib.Compose)

    // Test
    testImplementation(project(mapOf("path" to ":libraries:testutils")))
//    testImplementation(TestingLib.Junit)
//    androidTestImplementation(AndroidTestingLib.JunitExt)
//    androidTestImplementation(AndroidTestingLib.EspressoCore)
//    testImplementation(TestingLib.Coroutine)
//    testImplementation(TestingLib.Truth)
//    testImplementation(TestingLib.Robolectric)
//    testImplementation(TestingLib.Turbine)
//    testImplementation(TestingLib.Mockk)
}