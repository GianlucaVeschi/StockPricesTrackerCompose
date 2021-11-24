package com.gianlucaveschi.stockpricestrackercompose.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gianlucaveschi.data.model.TickerUiModel
import com.gianlucaveschi.data.api.StockMarketWebSocket
import com.gianlucaveschi.stockpricestrackercompose.mappers.mapToTicketSubscription
import com.gianlucaveschi.stockpricestrackercompose.mappers.mapToUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val stockMarketWebSocketImpl: StockMarketWebSocket
) : ViewModel() {

    val tickersList: MutableState<MutableList<TickerUiModel>> =
        mutableStateOf(com.gianlucaveschi.data.model.getListOfHardcodedTickerUiModel())
    val tickerState: MutableState<TickerUiModel> = mutableStateOf(com.gianlucaveschi.data.model.getHardcodedTickerUiModel())

    init {
        subscribeToAllTickers()
    }

    private fun subscribeToAllTickers() {
        stockMarketWebSocketImpl.observeTickerUpdates().onEach { ticker ->
            tickersList.value.updateTicker(ticker.mapToUiModel())
            tickerState.value = ticker.mapToUiModel()
        }.catch { error ->
            Timber.d("Collecting failed with ${error.message}")
        }.launchIn(viewModelScope)

        tickersList.value.forEach {
            Timber.d("Subscribe to $it")
            stockMarketWebSocketImpl.subscribeToTicker(it.mapToTicketSubscription())
        }
    }
}

private fun MutableList<TickerUiModel>.updateTicker(ticker: TickerUiModel): Int {
    val indexOfTicket = this.getTickerIndex(ticker)
    this[indexOfTicket] = ticker
    return indexOfTicket
}

private fun MutableList<TickerUiModel>.getTickerIndex(ticker: TickerUiModel): Int {
    return this.indexOfFirst { it.isin == ticker.isin }
}