plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("de.mannodermaus.android-junit5")
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

    implementation(libs.androidx.activityCompose)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.lifecycleViewModel)
    implementation(libs.androidx.coreKtx)
    implementation(libs.androidx.lifecycleRuntimeKtx)
    implementation(libs.bundles.androidx.compose.material)
    implementation(libs.bundles.androidx.compose.ui)
    implementation(libs.bundles.retrofitRxMoshi)
    implementation(libs.dagger)
    implementation(libs.googleAndroidMaterial.material)
    implementation(libs.kotlin.stdlib)
    implementation(libs.orgJetbrainsKotlinx.kotlinxCoroutinesCore)
    kapt(libs.dagger.compiler)

    testImplementation(libs.orgJetbrainsKotlinx.kotlinxCoroutinesTest)
    testRuntimeOnly(libs.junit.jupiterEngine)
    testImplementation(libs.junit.jupiterApi)
    testImplementation(libs.mockk)

    androidTestImplementation(libs.bundles.androidtesting.junitEspresso)
    // UI Tests
    androidTestImplementation(libs.androidx.compose.uiTest)
}