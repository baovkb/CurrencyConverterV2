package com.vkbao.landing.navigation.deeplink

import androidx.navigation.NavDeepLink
import androidx.navigation.navDeepLink

internal fun getSupportedDeepLinks(): List<NavDeepLink> {
    return listOf(navDeepLink {
        uriPattern = "currencyconverter://landing"
    })
}