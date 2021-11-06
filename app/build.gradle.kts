plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("de.mannodermaus.android-junit5")
    id("io.gitlab.arturbosch.detekt")
}

android {
    compileSdk = appConfig.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = appConfig.versions.minSdk.get().toInt()
        targetSdk = appConfig.versions.targetSdk.get().toInt()

        applicationId = "com.gifgroen.pokejournal"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    packagingOptions {
        resources.pickFirsts.run {
            add("META-INF/AL2.0")
            add("META-INF/LGPL2.1")
        }
    }
}

detekt {
    source = files("src/main/java", "src/main/kotlin")
    config = rootProject.files("build-config/detekt.yml")
    buildUponDefaultConfig = true
    reports {
        sarif {
            enabled = true
        }
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
    implementation(libs.androidx.hilt.lifecycleViewmodel)
    kapt(libs.dagger.hilt.compiler)
    implementation(libs.googleAndroidMaterial.material)
    implementation(libs.orgJetbrainsKotlinx.kotlinxCoroutinesCore)

    testImplementation(libs.junit.jupiterApi)
    testImplementation(libs.mockk)
    testImplementation(libs.orgJetbrainsKotlinx.kotlinxCoroutinesTest)
    testRuntimeOnly(libs.junit.jupiterEngine)

    androidTestImplementation(libs.androidx.compose.uiTest)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.bundles.androidtesting.junitEspresso)
    androidTestImplementation(libs.junit.jupiterApi)
    androidTestImplementation(libs.mannodermaus.junit5.androidTestCore)
    androidTestRuntimeOnly(libs.mannodermaus.junit5.androidTestRunner)
}