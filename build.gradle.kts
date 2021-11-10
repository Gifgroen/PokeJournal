// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.gradlePlugin.android)
        classpath(libs.gradlePlugin.androidJunit5)
        classpath(libs.gradlePlugin.detekt)
        classpath(libs.gradlePlugin.hiltAndroid)
        classpath(libs.gradlePlugin.kotlin)
        classpath(libs.gradlePlugin.kotlinSerialization)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}