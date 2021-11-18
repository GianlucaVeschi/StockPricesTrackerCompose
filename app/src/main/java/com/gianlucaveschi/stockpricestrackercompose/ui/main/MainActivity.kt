package com.gianlucaveschi.stockpricestrackercompose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.gianlucaveschi.stockpricestrackercompose.ui.components.BottomButtons
import com.gianlucaveschi.stockpricestrackercompose.ui.components.TickerList
import com.gianlucaveschi.stockpricestrackercompose.ui.theme.StockPricesTrackerComposeTheme
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val tickersList = mainViewModel.tickersList.value
            val ticker = mainViewModel.tickerState.value

            StockPricesTrackerComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        TickerList(tickers = tickersList)
                        BottomButtons()
                    }
                }
            }
        }
    }
}