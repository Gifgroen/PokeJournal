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
    implementation(libs.orgJetbrainsKotlinx.kotlinxCoroutinesCore)

    testImplementation(libs.orgJetbrainsKotlinx.kotlinxCoroutinesTest)
    testRuntimeOnly(libs.junit.jupiterEngine)
    testImplementation(libs.junit.jupiterApi)
    testImplementation(libs.mockk)
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
