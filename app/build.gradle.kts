plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = AndroidConfig.sdkVersion

    defaultConfig {
        applicationId = AndroidConfig.appId
        minSdk = AndroidConfig.minSdkVersion
        targetSdk = AndroidConfig.sdkVersion

        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName

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
        kotlinCompilerExtensionVersion = Dependencies.Compose.extensionVersion
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
    // Coroutines
    implementation(Dependencies.Coroutines.core)
    // Networking
    implementation(Dependencies.Data.retrofit)
    implementation(Dependencies.Data.retrofitConverterMoshi)
    implementation(Dependencies.Data.retrofitAdapterRxJava)

    implementation("androidx.activity:activity-compose:1.3.1")

    implementation("androidx.compose.ui:ui:${Dependencies.Compose.extensionVersion}")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:${Dependencies.Compose.extensionVersion}")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0-rc01")

    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:${Dependencies.Compose.extensionVersion}")
    // Material Design
    implementation("androidx.compose.material:material:${Dependencies.Compose.extensionVersion}")
    // Material design icons
    implementation("androidx.compose.material:material-icons-core:${Dependencies.Compose.extensionVersion}")
    implementation("androidx.compose.material:material-icons-extended:${Dependencies.Compose.extensionVersion}")
    // Integration with observables
    implementation("androidx.compose.runtime:runtime-rxjava3:${Dependencies.Compose.extensionVersion}")

    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Dependencies.Compose.extensionVersion}")

    testImplementation(Dependencies.Testing.junitJupiterEngine)
    testImplementation(Dependencies.Testing.mockK)

    androidTestImplementation(Dependencies.Testing.AndroidXJunit)
    androidTestImplementation(Dependencies.Testing.AndroidXEspressoCore)
}