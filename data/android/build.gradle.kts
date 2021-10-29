plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = appConfig.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = appConfig.versions.minSdk.get().toInt()
        targetSdk = appConfig.versions.targetSdk.get().toInt()

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

    implementation(libs.kotlin.stdlib)
    implementation(libs.orgJetbrainsKotlin.kotlinxCoroutinesCore)

    // Networking
    implementation(libs.bundles.retrofitRxMoshi)

    // Testing
    testImplementation(Dependencies.Testing.Coroutines.test)
    testImplementation(Dependencies.Testing.junitJupiterEngine)
    testImplementation(Dependencies.Testing.mockK)

    androidTestImplementation(Dependencies.Testing.AndroidXEspressoCore)
    androidTestImplementation(Dependencies.Testing.AndroidXJunit)
}
