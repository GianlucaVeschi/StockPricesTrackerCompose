package com.gianlucaveschi.data.api

import com.gianlucaveschi.data.model.TickerApiModel
import com.gianlucaveschi.data.model.TickerSubscription
import com.gianlucaveschi.data.model.TickerUnsubscription
import kotlinx.coroutines.flow.Flow

interface StockMarketWebSocket {
    fun observeTickerUpdates(): Flow<TickerApiModel>
    fun subscribeToTicker(tickerSubscription: TickerSubscription)
    fun unsubscribeFromTicker(tickerUnsubscription: TickerUnsubscription)
}