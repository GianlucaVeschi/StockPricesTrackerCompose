package com.gianlucaveschi.stockpricestrackercompose.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gianlucaveschi.stockpricestrackercompose.ui.components.network.StockMarketWebSocketImpl
import com.gianlucaveschi.stockpricestrackercompose.ui.mappers.mapToTicketSubscription
import com.gianlucaveschi.stockpricestrackercompose.ui.mappers.mapToUiModel
import com.gianlucaveschi.stockpricestrackercompose.ui.model.TickerUiModel
import com.gianlucaveschi.stockpricestrackercompose.ui.model.TickerUiModelFactory.getHardcodedTickerUiModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.serialization.ExperimentalSerializationApi
import timber.log.Timber

class MainViewModel : ViewModel() {

    var tickersList: MutableList<TickerUiModel> = getHardcodedTickerUiModel()

    private val _tickerStateFlow = MutableStateFlow(TickerUiModel("Apple Inc.", "US0378331005"))
    val tickerStateFlow: StateFlow<TickerUiModel> = _tickerStateFlow

    @ExperimentalSerializationApi
    private val tradeRepublicWebSocketImpl = StockMarketWebSocketImpl()

    fun init() {
        Timber.d("init")
        initWebSocket()
        subscribeToAllTickers()
    }

    @ExperimentalCoroutinesApi
    @ExperimentalSerializationApi
    private fun initWebSocket() {
        tradeRepublicWebSocketImpl.observeTickerUpdates().onEach { ticker ->
            _tickerStateFlow.value = ticker.mapToUiModel()
            Timber.d("Collecting $ticker")
        }.catch { error ->
            Timber.d("Collecting failed with ${error.message}")
        }.launchIn(viewModelScope)

        subscribeToAllTickers()
    }

    @ExperimentalCoroutinesApi
    @ExperimentalSerializationApi
    private fun subscribeToAllTickers() {
        tickersList.forEach {
            Timber.d("Subscribe to $it")
            tradeRepublicWebSocketImpl.subscribeToTicker(it.mapToTicketSubscription())
        }
    }
}

private fun MutableList<TickerUiModel>.updateTicker(ticker: TickerUiModel) : Int {
    val indexOfTicket = this.getTickerIndex(ticker)
    this[indexOfTicket] = ticker
    return indexOfTicket
}

private fun MutableList<TickerUiModel>.getTickerIndex(ticker: TickerUiModel): Int {
    return this.indexOfFirst { it.isin == ticker.isin }
}