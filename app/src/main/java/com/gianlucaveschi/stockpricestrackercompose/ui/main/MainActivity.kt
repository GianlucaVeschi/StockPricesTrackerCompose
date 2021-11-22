package com.gianlucaveschi.stockpricestrackercompose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomAppBar
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.gianlucaveschi.stockpricestrackercompose.ui.theme.StockPricesTrackerComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //Values are not used but cannot be removed -> FixMe
            val tickersList = mainViewModel.tickersList.value
            val ticker = mainViewModel.tickerState.value
            MainScreen()
        }
    }
}
