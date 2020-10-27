plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdkVersion(AndroidConfig.sdkVersion)
    buildToolsVersion(AndroidConfig.buildToolsVersion)

    defaultConfig {
        minSdkVersion(AndroidConfig.minSdkVersion)
        targetSdkVersion(AndroidConfig.sdkVersion)
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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

    implementation(kotlin(Dependencies.Kotlin.stdlib, Dependencies.Kotlin.version))
    implementation(Dependencies.Rx.rxKotlin)
    // Networking
    implementation(Dependencies.Data.retrofit)
    implementation(Dependencies.Data.retrofitConverterMoshi)
    implementation(Dependencies.Data.retrofitAdapterRxJava)
    // Testing
    testImplementation(Dependencies.Testing.junitJupiterEngine)
    androidTestImplementation(Dependencies.Testing.AndroidXJunit)
    androidTestImplementation(Dependencies.Testing.AndroidXEspressoCore)
}