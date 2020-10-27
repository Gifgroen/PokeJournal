plugins {
    id("com.android.application")
    kotlin("android")
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
    implementation(kotlin(Dependencies.Kotlin.stdlib, Dependencies.Kotlin.version))
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.Material.material)
    implementation(Dependencies.AndroidX.constraintLayout)

    testImplementation(Dependencies.Testing.junitJupiterEngine)
    androidTestImplementation(Dependencies.Testing.AndroidXJunit)
    androidTestImplementation(Dependencies.Testing.AndroidXEspressoCore)
}