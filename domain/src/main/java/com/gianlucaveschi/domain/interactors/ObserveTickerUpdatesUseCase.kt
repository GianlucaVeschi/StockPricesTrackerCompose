package com.gianlucaveschi.domain.interactors

import com.gianlucaveschi.domain.model.TickerUiModel
import com.gianlucaveschi.domain.repo.MainRepository
import kotlinx.coroutines.flow.Flow


class ObserveTickerUpdatesUseCase(
    private val mainRepository: MainRepository
) {
    operator fun invoke() : Flow<TickerUiModel> = mainRepository.observeTicker()
}