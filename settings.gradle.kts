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
include(":feature:landing:landingPresentation")
include(":feature:landing:landingBusiness")
include(":feature:landing:landingBusinessApi")
include(":feature:landing:landingApi")
include(":core:network:networkbusinessapi")
include(":core:network:networkbusiness")
include(":core:network:networkpresentation")
include(":core:corelibs")

include(":feature:currencypicker:currencyPickerBusiness")
include(":feature:currencypicker:currencyPickerPresentation")
include(":feature:currencypicker:currencyPickerBusinessApi")
include(":core:utilities")
