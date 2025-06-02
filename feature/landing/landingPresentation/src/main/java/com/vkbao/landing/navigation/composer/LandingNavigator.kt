package com.vkbao.landing.navigation.composer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vkbao.landing.LandingViewModel
import com.vkbao.landing.currency_picker.composer.CurrencyPickerScreen
import com.vkbao.landing.home.composer.HomeScreen
import com.vkbao.landingbusiness.data.getExchangeRates.request.ExchangeRate
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.serialization.Serializable

@Serializable
internal sealed class Screen(val route: String) {
    object Home : Screen("home")
    object CurrencyPicker : Screen("currency_picker")
}

@Composable
fun LandingNavigator(
    landingViewModel: LandingViewModel
) {
    val navController = rememberNavController()
    var fromCurrency by rememberSaveable { mutableStateOf<String?>(null) }
    var toCurrency by rememberSaveable { mutableStateOf<String?>(null) }
    val currenciesState by landingViewModel.currenciesState.collectAsStateWithLifecycle()

    val pickerResult = rememberSaveable { mutableStateOf<Pair<CurrencySelectionType, String>?>(null) }

    LaunchedEffect(pickerResult.value) {
        pickerResult.value?.let { (type, selected) ->
            when (type) {
                CurrencySelectionType.FROM -> fromCurrency = selected
                CurrencySelectionType.TO -> toCurrency = selected
            }
            pickerResult.value = null
        }
    }

    LaunchedEffect(Unit) {
        snapshotFlow { fromCurrency to toCurrency }
            .filter { it.first != null && it.second != null }
            .debounce(200) // delay for stability
            .distinctUntilChanged()
            .collectLatest { (from, to) ->
                landingViewModel.getExchangeRates(
                    exchangeRate = ExchangeRate(from!!, to!!)
                )
            }
    }

    LaunchedEffect(Unit) {
        landingViewModel.getCurrencies()
    }

    LaunchedEffect(currenciesState) {
        when(currenciesState) {
            is CurrencyState.Success -> {
                val currencies = (currenciesState as CurrencyState.Success).currencies
                if (currencies.size > 1) {
                    if (fromCurrency.isNullOrEmpty()) {
                        fromCurrency = currencies[0].code
                    }
                    if (toCurrency.isNullOrEmpty()) {
                        toCurrency = currencies[1].code
                    }
                }
            }
            is CurrencyState.Error -> {
                println("error: ${(currenciesState as CurrencyState.Error).errorEntity}")
            }
            else -> {
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(
                onFromPress = {
                    navController.navigate("${Screen.CurrencyPicker.route}?type=${CurrencySelectionType.FROM.name}")
                },
                fromCurrency = fromCurrency,
                toCurrency = toCurrency,
                onReverse = {
                    val tmp = fromCurrency
                    fromCurrency = toCurrency
                    toCurrency = tmp
                },
                onToPress = {
                    navController.navigate("${Screen.CurrencyPicker.route}?type=${CurrencySelectionType.TO.name}")
                },
                viewModel = landingViewModel
            )
        }

        composable(
            route = "${Screen.CurrencyPicker.route}?type={type}",
            arguments = listOf(
                navArgument("type") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val typeString = backStackEntry.arguments?.getString("type")
            val type = CurrencySelectionType.valueOf(typeString ?: CurrencySelectionType.FROM.name)
            val selectedCurrency = if (type == CurrencySelectionType.FROM) fromCurrency else toCurrency

            CurrencyPickerScreen(
                onSelectedChange = {
                    pickerResult.value = type to it
                    navController.popBackStack()
                },
                viewModel = landingViewModel,
                selectedCurrency = selectedCurrency ?: "",
                onGetBack = {
                    navController.popBackStack()
                },
            )
        }
        //other composable functions
    }
}

enum class CurrencySelectionType {
    FROM, TO
}