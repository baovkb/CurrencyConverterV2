package com.vkbao.landing.navigation.composer

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vkbao.landing.LandingViewModel
import com.vkbao.landing.currency_picker.composer.CurrencyPickerScreen
import com.vkbao.landing.home.composer.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
internal sealed class Screen(val route: String) {
    object Home : Screen("home")
    object CurrencyPicker : Screen("currency_picker")
}

@Composable
fun LandingNavigator(
    landingViewModel: LandingViewModel,
    onLandingBackPress: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500)) },
        exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500)) },
        popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500)) },
        popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500)) }
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(
                onBackPress = onLandingBackPress,
                onSettingPress = {}
            )
        }

        composable(
            route = Screen.CurrencyPicker.route
        ) {
            CurrencyPickerScreen()
        }
        //other composable functions
    }
}