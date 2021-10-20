plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = AndroidConfig.sdkVersion

    defaultConfig {
        minSdk = AndroidConfig.minSdkVersion
        targetSdk = AndroidConfig.sdkVersion

//        versionCode = AndroidConfig.versionCode
//        versionName = AndroidConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
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
    testImplementation(Dependencies.Testing.mockK)
    androidTestImplementation(Dependencies.Testing.AndroidXJunit)
    androidTestImplementation(Dependencies.Testing.AndroidXEspressoCore)
}