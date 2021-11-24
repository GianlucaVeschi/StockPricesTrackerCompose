package com.gianlucaveschi.domain.interactors

import com.gianlucaveschi.domain.model.TickerUiModel
import com.gianlucaveschi.domain.repo.MainRepository


class UnsubscribeFromTickerUseCase(
    private val mainRepository: MainRepository
) {
    operator fun invoke(tickerUiModel: TickerUiModel) {
        mainRepository.unsubscribeFromTicker(tickerUiModel)
    }
}