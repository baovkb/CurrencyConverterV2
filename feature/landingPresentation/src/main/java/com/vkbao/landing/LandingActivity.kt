package com.vkbao.landing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.vkbao.landing.navigation.composer.LandingNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingActivity : ComponentActivity() {
    private val viewModel: LandingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LandingNavigator(
                landingViewModel = viewModel
            )
        }
    }
}