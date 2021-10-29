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
        kotlinCompilerExtensionVersion = appConfig.versions.compose.extensionVersion.get()
    }
}

dependencies {
    implementation(project(":data:android"))
    implementation(project(":domain"))

    implementation(libs.kotlin.stdlib)

    // Compose
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.compose.foundation:foundation:${appConfig.versions.compose.extensionVersion.get()}")
    // Compose Material
    implementation("androidx.compose.material:material-icons-core:${appConfig.versions.compose.extensionVersion.get()}")
    implementation("androidx.compose.material:material-icons-extended:${appConfig.versions.compose.extensionVersion.get()}")
    implementation("androidx.compose.material:material:${appConfig.versions.compose.extensionVersion.get()}")
    // Compose RxJava runtime
    implementation("androidx.compose.runtime:runtime-rxjava3:${appConfig.versions.compose.extensionVersion.get()}")
    // Compose UI
    implementation("androidx.compose.ui:ui-tooling:${appConfig.versions.compose.extensionVersion.get()}")
    implementation("androidx.compose.ui:ui:${appConfig.versions.compose.extensionVersion.get()}")
    // Compose VM Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0")

    // AndroidX
    implementation(libs.androidx.coreKtx)
    implementation(libs.androidx.lifecycleRuntimeKtx)
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
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${appConfig.versions.compose.extensionVersion.get()}")
}