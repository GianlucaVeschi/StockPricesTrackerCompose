package com.gianlucaveschi.stockpricestrackercompose.ui.components.network

import com.gianlucaveschi.stockpricestrackercompose.ui.model.TickerApiModel
import com.gianlucaveschi.stockpricestrackercompose.ui.model.TickerSubscription
import com.gianlucaveschi.stockpricestrackercompose.ui.model.TickerUnsubscription
import kotlinx.coroutines.flow.Flow

interface StockMarketWebSocket {
    fun observeTickerUpdates(): Flow<TickerApiModel>
    fun subscribeToTicker(tickerSubscription: TickerSubscription)
    fun unsubscribeFromTicker(tickerUnsubscription: TickerUnsubscription)
}