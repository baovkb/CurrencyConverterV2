package com.vkbao.landing.navigation.composer

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vkbao.landing.LandingViewModel
import com.vkbao.landing.home.composer.HomeScreen
import com.vkbao.landing.navigation.deeplink.getSupportedDeepLinks
import com.vkbao.landingapi.currencyPicker.CurrencyPickerDeepLinkProvider
import kotlinx.serialization.Serializable

@Serializable
internal sealed class Screen(val route: String) {
    object Home : Screen("home")
}

@Composable
fun LandingNavigator(
    landingViewModel: LandingViewModel,
    currencyPickerDeepLinkProvider: CurrencyPickerDeepLinkProvider,
    context: Context
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route,
            deepLinks = getSupportedDeepLinks()
        ) {
            HomeScreen(
                onFromPress = {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        currencyPickerDeepLinkProvider.getDeepLink()
                    )
                    context.startActivity(intent)
                },
                onToPress = {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        currencyPickerDeepLinkProvider.getDeepLink()
                    )
                    context.startActivity(intent)
                },
                viewModel = landingViewModel
            )
        }
    }
}