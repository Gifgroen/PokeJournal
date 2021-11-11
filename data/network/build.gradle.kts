import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    kotlin("native.cocoapods")
    id("com.android.library")
}

version = "1.0"

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }

    iosTarget("ios") {}

    cocoapods {
        summary = "Multiplatform API provision of PokeApi using Ktor"
        homepage = "https://gifgroen.com"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "PokeapiNetwork"
        }
        podfile = project.file("../../iosApp/PokeJournaliOS/Podfile")
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":domain"))

                implementation(libs.ktor.client.serialization)
                implementation(libs.ktor.cio)
                implementation(libs.ktor.core)
                implementation(libs.orgJetbrainsKotlinx.kotlinxCoroutinesCore)
                implementation(libs.orgJetbrainsKotlinx.kotlinxSerializationJson)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.android)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(libs.ktor.ios)
            }
        }
        val iosTest by getting
    }
}

android {
    compileSdk = appConfig.versions.compileSdk.get().toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = appConfig.versions.minSdk.get().toInt()
        targetSdk = appConfig.versions.targetSdk.get().toInt()
    }
}