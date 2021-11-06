plugins {
    id("java-library")
    id("kotlin")
    id("io.gitlab.arturbosch.detekt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(libs.orgJetbrainsKotlinx.kotlinxCoroutinesCore)

    testImplementation(libs.orgJetbrainsKotlinx.kotlinxCoroutinesTest)
    testRuntimeOnly(libs.junit.jupiterEngine)
    testImplementation(libs.junit.jupiterApi)
    testImplementation(libs.mockk)
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

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
