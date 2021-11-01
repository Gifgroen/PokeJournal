plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = appConfig.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.gifgroen.pokejournal"
        minSdk = appConfig.versions.minSdk.get().toInt()
        targetSdk = appConfig.versions.targetSdk.get().toInt()

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        compose = true
    }
    buildTypes {
        named("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.extension.get()
    }
}

dependencies {
    implementation(project(":data:android"))
    implementation(project(":domain"))

    implementation(libs.kotlin.stdlib)

    // Compose
    implementation(libs.androidx.activityCompose)

    implementation(libs.androidx.compose.foundation)
    implementation(libs.bundles.androidx.compose.material)
    implementation(libs.bundles.androidx.compose.ui)
    implementation(libs.androidx.coreKtx)
    implementation(libs.androidx.lifecycleRuntimeKtx)
    // Compose VM Lifecycle
    implementation(libs.androidx.compose.lifecycleViewModel)
    // Networking
    implementation(libs.bundles.retrofitRxMoshi)
    // DI
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    // Google Material
    implementation(libs.googleAndroidMaterial.material)
    // Kotlin
    implementation(libs.orgJetbrainsKotlinx.kotlinxCoroutinesCore)

    testImplementation(libs.orgJetbrainsKotlinx.kotlinxCoroutinesTest)
    testImplementation(libs.bundles.testing.jupiterMockK)

    androidTestImplementation(libs.bundles.androidtesting.junitEspresso)
    // UI Tests
    androidTestImplementation(libs.androidx.compose.uiTest)
}