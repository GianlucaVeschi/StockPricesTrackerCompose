package com.gianlucaveschi.stockpricestrackercompose.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gianlucaveschi.stockpricestrackercompose.ui.network.StockMarketWebSocketImpl
import com.gianlucaveschi.stockpricestrackercompose.ui.mappers.mapToTicketSubscription
import com.gianlucaveschi.stockpricestrackercompose.ui.mappers.mapToUiModel
import com.gianlucaveschi.stockpricestrackercompose.ui.model.TickerUiModel
import com.gianlucaveschi.stockpricestrackercompose.ui.model.TickerUiModelFactory.getHardcodedTickerUiModel
import com.gianlucaveschi.stockpricestrackercompose.ui.model.TickerUiModelFactory.getListOfHardcodedTickerUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.serialization.ExperimentalSerializationApi
import timber.log.Timber

class MainViewModel() : ViewModel() {

    //ToDo Inject
    private val stockMarketWebSocketImpl = StockMarketWebSocketImpl()

    val tickersList: MutableState<MutableList<TickerUiModel>> = mutableStateOf(getListOfHardcodedTickerUiModel())
    val tickerState : MutableState<TickerUiModel> = mutableStateOf(getHardcodedTickerUiModel())

    init {
        subscribeToAllTickers()
    }

    @ExperimentalCoroutinesApi
    @ExperimentalSerializationApi
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