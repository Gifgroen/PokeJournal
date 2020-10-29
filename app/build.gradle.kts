plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(AndroidConfig.sdkVersion)
    buildToolsVersion(AndroidConfig.buildToolsVersion)

    defaultConfig {
        applicationId(AndroidConfig.appId)
        minSdkVersion(AndroidConfig.minSdkVersion)
        targetSdkVersion(AndroidConfig.sdkVersion)

        versionCode(AndroidConfig.versionCode)
        versionName(AndroidConfig.versionName)

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        named("debug") {
            isTestCoverageEnabled = true
            applicationIdSuffix = ".debug"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data:android"))

    implementation(Dependencies.Di.dagger)
    kapt(Dependencies.Di.daggerCompiler)

    implementation(Dependencies.AndroidX.lifecycleRuntimeKtx)

    implementation(kotlin(Dependencies.Kotlin.stdlib, Dependencies.Kotlin.version))
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.Material.material)
    implementation(Dependencies.AndroidX.constraintLayout)
    // Rx
    implementation(Dependencies.Rx.rxKotlin)
    // Networking
    implementation(Dependencies.Data.retrofit)
    implementation(Dependencies.Data.retrofitConverterMoshi)
    implementation(Dependencies.Data.retrofitAdapterRxJava)

    testImplementation(Dependencies.Testing.junitJupiterEngine)
    testImplementation(Dependencies.Testing.mockK)
    androidTestImplementation(Dependencies.Testing.AndroidXJunit)
    androidTestImplementation(Dependencies.Testing.AndroidXEspressoCore)
}