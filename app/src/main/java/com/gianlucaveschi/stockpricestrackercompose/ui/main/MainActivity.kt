package com.gianlucaveschi.stockpricestrackercompose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.gianlucaveschi.data.model.TickerUiModel
import com.gianlucaveschi.stockpricestrackercompose.ui.screens.TickersListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //Values are not used but cannot be removed -> FixMe
            val tickersList : MutableList<TickerUiModel> = mainViewModel.tickersList.value
            val ticker = mainViewModel.tickerState.value
            TickersListScreen(tickers = tickersList)
        }
    }
}
