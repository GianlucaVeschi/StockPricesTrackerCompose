package com.gianlucaveschi.stockpricestrackercompose.di

import com.gianlucaveschi.stockpricestrackercompose.network.StockMarketWebSocket
import com.gianlucaveschi.stockpricestrackercompose.network.StockMarketWebSocketImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.ExperimentalSerializationApi


@Module
@InstallIn(ViewModelComponent::class)
class AppModule {

    @ExperimentalCoroutinesApi
    @ExperimentalSerializationApi
    @ViewModelScoped
    @Provides
    fun provideStockMarketWebSocket() : StockMarketWebSocket = StockMarketWebSocketImpl()
}