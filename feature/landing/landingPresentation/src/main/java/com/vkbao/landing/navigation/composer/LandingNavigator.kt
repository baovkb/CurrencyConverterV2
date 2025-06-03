package com.vkbao.landing.navigation.composer

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vkbao.landing.LandingViewModel
import com.vkbao.landing.home.composer.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
internal sealed class Screen(val route: String) {
    object Home : Screen("home")
}

@Composable
fun LandingNavigator(
    landingViewModel: LandingViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(
                onFromPress = {
                    navController.navigate
                },
                onToPress = {

                },
                viewModel = landingViewModel
            )
        }
    }
}