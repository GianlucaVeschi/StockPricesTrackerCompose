package com.gianlucaveschi.stockpricestrackercompose.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gianlucaveschi.data.model.TickerUiModel
import com.gianlucaveschi.data.api.StockMarketWebSocket
import com.gianlucaveschi.data.repo.MainRepository
import com.gianlucaveschi.stockpricestrackercompose.mappers.mapToTicketSubscription
import com.gianlucaveschi.stockpricestrackercompose.mappers.mapToUiModel
import com.gianlucaveschi.stockpricestrackercompose.util.updateTicker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository : MainRepository
) : ViewModel() {

    val tickersList: MutableState<MutableList<TickerUiModel>> =
        mutableStateOf(com.gianlucaveschi.data.model.getListOfHardcodedTickerUiModel())
    val tickerState: MutableState<TickerUiModel> = mutableStateOf(com.gianlucaveschi.data.model.getHardcodedTickerUiModel())

    init {
        subscribeToAllTickersThroughRepo()
    }

    private fun subscribeToAllTickersThroughRepo() {
        mainRepository.observeTicker().onEach { ticker ->
            tickersList.value.updateTicker(ticker)
            tickerState.value = ticker
        }.catch { error ->
            Timber.d("Collecting failed with ${error.message}")
        }.launchIn(viewModelScope)

        tickersList.value.forEach {
            Timber.d("Subscribe to $it")
            mainRepository.subscribeToTicker(it)
        }
    }
}