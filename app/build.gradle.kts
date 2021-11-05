plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("de.mannodermaus.android-junit5")
    id("dagger.hilt.android.plugin")
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
        // 2) Connect JUnit 5 to the runner
        testInstrumentationRunnerArguments["runnerBuilder"] = "de.mannodermaus.junit5.AndroidJUnit5Builder"
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

// Allow references to generated code
kapt {
    correctErrorTypes = true
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

    implementation(libs.dagger.hilt)
    implementation (libs.androidx.hilt.lifecycleViewmodel)
    kapt(libs.dagger.hilt.compiler)

    implementation(libs.googleAndroidMaterial.material)
    implementation(libs.orgJetbrainsKotlinx.kotlinxCoroutinesCore)

    testImplementation(libs.orgJetbrainsKotlinx.kotlinxCoroutinesTest)
    testRuntimeOnly(libs.junit.jupiterEngine)
    testImplementation(libs.junit.jupiterApi)
    testImplementation(libs.mockk)

    androidTestImplementation(libs.bundles.androidtesting.junitEspresso)
    // UI Tests
    androidTestImplementation(libs.androidx.compose.uiTest)

    // 4) Jupiter API & Test Runner, if you don't have it already
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.junit.jupiterApi)

    // 5) The instrumentation test companion libraries
    androidTestImplementation("de.mannodermaus.junit5:android-test-core:1.3.0")
    androidTestRuntimeOnly("de.mannodermaus.junit5:android-test-runner:1.3.0")
}