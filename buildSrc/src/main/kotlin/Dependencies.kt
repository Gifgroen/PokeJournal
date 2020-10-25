object Dependencies {
    object BuildPlugins {
        const val androidGradlePlugin = "com.android.tools.build:gradle:4.1.0"

        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
    }

    object Kotlin {
        const val version = "1.4.10"

        const val stdlib = "stdlib"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.3.2"

        const val appCompat = "androidx.appcompat:appcompat:1.2.0"

        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.2"
    }

    object Material {
        const val material = "com.google.android.material:material:1.2.1"
    }

    object Testing {
        const val AndroidXJunit = "androidx.test.ext:junit:1.1.2"

        const val junit = "junit:junit:4.13.1"

        const val AndroidXEspressoCore = "androidx.test.espresso:espresso-core:3.3.0"
    }
}