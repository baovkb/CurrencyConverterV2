package navigation.composer

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vkbao.currencypickerpresentation.CurrencyPickerViewModel
import com.vkbao.currencypickerpresentation.currencypicker.composer.CurrencyPickerScreen
import navigation.deeplink.getSupportedDeepLinks

internal sealed class Screen(val route: String) {
    object CurrencyPicker: Screen("currency_picker")
}

@Composable
fun CurrencyPickerNavigator(
    viewModel: CurrencyPickerViewModel,
    onGetBack: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.CurrencyPicker.route
    ) {
        composable(
            route = Screen.CurrencyPicker.route,
            deepLinks = getSupportedDeepLinks()
        ) {
            CurrencyPickerScreen(
                viewModel = viewModel,
                selectedCurrency = "",
                onGetBack = onGetBack
            )
        }
    }
}