package com.gianlucaveschi.stockpricestrackercompose.network

import com.gianlucaveschi.stockpricestrackercompose.model.TickerApiModel
import com.gianlucaveschi.stockpricestrackercompose.model.TickerSubscription
import com.gianlucaveschi.stockpricestrackercompose.model.TickerUnsubscription
import kotlinx.coroutines.flow.Flow

interface StockMarketWebSocket {
    fun observeTickerUpdates(): Flow<TickerApiModel>
    fun subscribeToTicker(tickerSubscription: TickerSubscription)
    fun unsubscribeFromTicker(tickerUnsubscription: TickerUnsubscription)
}