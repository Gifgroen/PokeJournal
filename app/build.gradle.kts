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
        kotlinCompilerExtensionVersion = Dependencies.Compose.extensionVersion
    }
}

dependencies {
    implementation(project(":data:android"))
    implementation(project(":domain"))

    implementation(libs.kotlin.stdlib)

    // Compose
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.compose.foundation:foundation:${Dependencies.Compose.extensionVersion}")
    // Compose Material
    implementation("androidx.compose.material:material-icons-core:${Dependencies.Compose.extensionVersion}")
    implementation("androidx.compose.material:material-icons-extended:${Dependencies.Compose.extensionVersion}")
    implementation("androidx.compose.material:material:${Dependencies.Compose.extensionVersion}")
    // Compose RxJava runtime
    implementation("androidx.compose.runtime:runtime-rxjava3:${Dependencies.Compose.extensionVersion}")
    // Compose UI
    implementation("androidx.compose.ui:ui-tooling:${Dependencies.Compose.extensionVersion}")
    implementation("androidx.compose.ui:ui:${Dependencies.Compose.extensionVersion}")
    // Compose VM Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0")

    // AndroidX
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.lifecycleRuntimeKtx)
    // Networking
    implementation(libs.bundles.retrofitRxMoshi)
    // DI
    implementation(libs.dagger.dagger)
    kapt(libs.dagger.daggerCompiler)
    // Google Material
    implementation(libs.googleAndroidMaterial.material)
    // Kotlin
    implementation(libs.orgJetbrainsKotlin.kotlinxCoroutinesCore)

    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Dependencies.Compose.extensionVersion}")

    testImplementation(Dependencies.Testing.Coroutines.test)
    testImplementation(Dependencies.Testing.junitJupiterEngine)
    testImplementation(Dependencies.Testing.mockK)

    androidTestImplementation(Dependencies.Testing.AndroidXEspressoCore)
    androidTestImplementation(Dependencies.Testing.AndroidXJunit)
}