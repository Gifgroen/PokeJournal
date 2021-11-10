enableFeaturePreview("VERSION_CATALOGS")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("appConfig") {
            from(files("gradle/appconfig.versions.toml"))
        }
    }
}

rootProject.name = "PokeJournal"
rootProject.buildFileName = "build.gradle.kts"

include(":domain")
include(":app")
include(":data:network")
