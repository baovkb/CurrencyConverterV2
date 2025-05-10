pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CurrencyConverterV2"
include(":app")
include(":feature:landingPresentation")
include(":feature:landingBusiness")
include(":core:network:networkbusinessapi")
include(":core:network:networkbusiness")
include(":core:network:networkpresentation")
include(":core:corelibs")

