package com.gianlucaveschi.data.repo

import com.gianlucaveschi.data.model.TickerUiModel
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun observeTicker() : Flow<TickerUiModel>
    fun subscribeToTicker(tickerUiModel: TickerUiModel)
    fun unsubscribeFromTicker(tickerUiModel: TickerUiModel)
}