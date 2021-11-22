package com.gianlucaveschi.stockpricestrackercompose.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gianlucaveschi.stockpricestrackercompose.network.StockMarketWebSocketImpl
import com.gianlucaveschi.stockpricestrackercompose.mappers.mapToTicketSubscription
import com.gianlucaveschi.stockpricestrackercompose.mappers.mapToUiModel
import com.gianlucaveschi.stockpricestrackercompose.model.TickerUiModel
import com.gianlucaveschi.stockpricestrackercompose.model.TickerUiModelFactory.getHardcodedTickerUiModel
import com.gianlucaveschi.stockpricestrackercompose.model.TickerUiModelFactory.getListOfHardcodedTickerUiModel
import com.gianlucaveschi.stockpricestrackercompose.network.StockMarketWebSocket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.serialization.ExperimentalSerializationApi
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val stockMarketWebSocketImpl: StockMarketWebSocket
) : ViewModel() {

    val tickersList: MutableState<MutableList<TickerUiModel>> =
        mutableStateOf(getListOfHardcodedTickerUiModel())
    val tickerState: MutableState<TickerUiModel> = mutableStateOf(getHardcodedTickerUiModel())

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