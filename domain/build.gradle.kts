plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.orgJetbrainsKotlin.kotlinxCoroutinesCore)

    testImplementation(Dependencies.Testing.Coroutines.test)

    testImplementation(Dependencies.Testing.junitJupiterEngine)
    testImplementation(Dependencies.Testing.mockK)
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
