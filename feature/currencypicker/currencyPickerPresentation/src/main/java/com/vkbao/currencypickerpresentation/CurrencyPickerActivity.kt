package com.vkbao.currencypickerpresentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vkbao.currencypickerpresentation.ui.theme.CurrencyConverterV2Theme
import dagger.hilt.android.AndroidEntryPoint
import navigation.composer.CurrencyPickerNavigator
import javax.inject.Inject

@AndroidEntryPoint
class CurrencyPickerActivity : ComponentActivity() {

    val viewModel: CurrencyPickerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CurrencyPickerNavigator(
                viewModel = viewModel,
                onGetBack = {
                    this.finish()
                }
            )
        }
    }
}