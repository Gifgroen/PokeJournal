plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(kotlin(Dependencies.Kotlin.stdlib, Dependencies.Kotlin.version))

    implementation(Dependencies.Coroutines.core)

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
