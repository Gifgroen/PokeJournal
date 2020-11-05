object Dependencies {

    object BuildPlugins {

        const val androidGradlePlugin = "com.android.tools.build:gradle:4.1.0"

        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
    }

    object Kotlin {

        const val version = "1.4.10"

        const val stdlib = "stdlib"
    }

    object Rx {

        const val rxKotlin = "io.reactivex.rxjava3:rxkotlin:3.0.1"
    }

    object Di {

        private const val version = "2.29.1"

        const val dagger = "com.google.dagger:dagger:$version"

        const val daggerCompiler = "com.google.dagger:dagger-compiler:$version"
    }

    object Data {

        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"

        const val retrofitAdapterRxJava = "com.squareup.retrofit2:adapter-rxjava3:2.9.0"

        const val retrofitConverterMoshi = "com.squareup.retrofit2:converter-moshi:2.9.0"
    }

    object AndroidX {

        const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.2.0"

        const val coreKtx = "androidx.core:core-ktx:1.3.2"

        const val appCompat = "androidx.appcompat:appcompat:1.2.0"

        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.2"
    }

    object Material {

        const val material = "com.google.android.material:material:1.2.1"
    }

    object Testing {

        const val AndroidXJunit = "androidx.test.ext:junit:1.1.2"

        const val junitJupiterEngine = "org.junit.jupiter:junit-jupiter-engine:5.7.0"

        const val mockK = "io.mockk:mockk:1.10.2"

        const val AndroidXEspressoCore = "androidx.test.espresso:espresso-core:3.3.0"


    }
}