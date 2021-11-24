package com.gianlucaveschi.stockpricestrackercompose.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gianlucaveschi.domain.interactors.ObserveTickerUpdatesUseCase
import com.gianlucaveschi.domain.interactors.SubscribeToTickerUseCase
import com.gianlucaveschi.domain.interactors.UnsubscribeFromTickerUseCase
import com.gianlucaveschi.domain.model.TickerUiModel
import com.gianlucaveschi.domain.model.getHardcodedTickerUiModel
import com.gianlucaveschi.domain.model.getListOfHardcodedTickerUiModel
import com.gianlucaveschi.domain.repo.MainRepository
import com.gianlucaveschi.stockpricestrackercompose.util.updateTicker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val observeTickerUpdatesUseCase: ObserveTickerUpdatesUseCase,
    private val subscribeToTickerUseCase: SubscribeToTickerUseCase,
    private val unsubscribeFromTickerUseCase: UnsubscribeFromTickerUseCase
) : ViewModel() {

    val tickersList: MutableState<MutableList<TickerUiModel>> =
        mutableStateOf(getListOfHardcodedTickerUiModel())
    val tickerState: MutableState<TickerUiModel> = mutableStateOf(
        getHardcodedTickerUiModel()
    )

    init {
        observeTickersUpdates()
        subscribeToAllTickers()
    }

    fun observeTickersUpdates() {
        observeTickerUpdatesUseCase().onEach { ticker ->
            tickersList.value.updateTicker(ticker)
            tickerState.value = ticker
        }.catch { error ->
            Timber.d("Collecting failed with ${error.message}")
        }.launchIn(viewModelScope)
    }

    fun subscribeToAllTickers() {
        tickersList.value.forEach {
            subscribeToTickerUseCase(it)
        }
    }

    fun unsubscribeFromAllTickers() {
        tickersList.value.forEach {
            unsubscribeFromTickerUseCase(it)
        }
    }
}