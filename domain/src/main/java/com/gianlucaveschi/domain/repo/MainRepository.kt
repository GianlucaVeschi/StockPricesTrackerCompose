package com.gianlucaveschi.domain.repo

import com.gianlucaveschi.domain.model.TickerUiModel
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun observeTicker() : Flow<TickerUiModel>
    fun subscribeToTicker(tickerUiModel: TickerUiModel)
    fun unsubscribeFromTicker(tickerUiModel: TickerUiModel)
}