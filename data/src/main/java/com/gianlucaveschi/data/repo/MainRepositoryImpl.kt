package com.gianlucaveschi.data.repo

import com.gianlucaveschi.data.api.StockMarketWebSocket
import com.gianlucaveschi.data.model.TickerUiModel
import com.gianlucaveschi.stockpricestrackercompose.mappers.mapToTicketSubscription
import com.gianlucaveschi.stockpricestrackercompose.mappers.mapToTicketUnsubscription
import com.gianlucaveschi.stockpricestrackercompose.mappers.mapToUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainRepositoryImpl(
    private val stockMarketWebSocket: StockMarketWebSocket
) : MainRepository {

    /** Observe Last updated ticker and convert it to Ui Model */
    override fun observeTicker(): Flow<TickerUiModel> {
        return stockMarketWebSocket.observeTickerUpdates().map { it.mapToUiModel() }
    }

    override fun subscribeToTicker(tickerUiModel: TickerUiModel) {
        stockMarketWebSocket.subscribeToTicker(tickerUiModel.mapToTicketSubscription())
    }

    override fun unsubscribeFromTicker(tickerUiModel: TickerUiModel) {
        stockMarketWebSocket.unsubscribeFromTicker(tickerUiModel.mapToTicketUnsubscription())
    }

}