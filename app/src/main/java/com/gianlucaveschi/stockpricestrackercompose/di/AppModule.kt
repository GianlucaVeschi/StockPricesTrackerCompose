package com.gianlucaveschi.stockpricestrackercompose.di

import android.content.Context
import com.gianlucaveschi.stockpricestrackercompose.BuildConfig
import com.gianlucaveschi.stockpricestrackercompose.StockPricesTrackerComposeApp
import com.gianlucaveschi.data.api.StockMarketWebSocket
import com.gianlucaveschi.data.api.StockMarketWebSocketImpl
import com.gianlucaveschi.data.repo.MainRepositoryImpl
import com.gianlucaveschi.domain.interactors.ObserveTickerUpdatesUseCase
import com.gianlucaveschi.domain.interactors.SubscribeToTickerUseCase
import com.gianlucaveschi.domain.interactors.UnsubscribeFromTickerUseCase
import com.gianlucaveschi.domain.repo.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesApplication(
        @ApplicationContext context: Context
    ): StockPricesTrackerComposeApp {
        return context as StockPricesTrackerComposeApp
    }

    @Provides
    fun provideHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC
            else HttpLoggingInterceptor.Level.NONE
        )
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
class WebSocketListenerModule {

    @Provides
    fun provideOpenConnectionRequest(): Request {
        return Request
            .Builder()
            .url(BuildConfig.HIDDEN_WEB_SOCKET_URL)
            .build()
    }

    @Provides
    fun provideJsonEncoder() : Json {
        return Json { ignoreUnknownKeys = true }
    }

    @ExperimentalCoroutinesApi
    @ExperimentalSerializationApi
    @Provides
    fun provideWebSocketListener(
        okHttpClient: OkHttpClient,
        openConnectionRequest: Request,
        jsonEncoder : Json
    ) : StockMarketWebSocket = StockMarketWebSocketImpl(
        okHttpClient,
        openConnectionRequest,
        jsonEncoder
    )
}

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @ExperimentalSerializationApi
    @Singleton
    @Provides
    fun provideMainRepository(
        stockMarketWebSocket: StockMarketWebSocket
    ) : MainRepository = MainRepositoryImpl(
        stockMarketWebSocket
    )
}

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @ViewModelScoped
    @Provides
    fun provideInitStockMarketObservationUseCase(
        mainRepository: MainRepository
    ) = ObserveTickerUpdatesUseCase(
        mainRepository
    )

    @ViewModelScoped
    @Provides
    fun provideStartSubscriptionToStockMarketUseCase(
        mainRepository: MainRepository
    ) = SubscribeToTickerUseCase(
        mainRepository
    )

    @ViewModelScoped
    @Provides
    fun provideStopSubscriptionToStockMarketUseCase(
        mainRepository: MainRepository
    ) = UnsubscribeFromTickerUseCase(
        mainRepository
    )

}