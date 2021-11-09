package com.gianlucaveschi.stockpricestrackercompose.ui.components.network

import com.gianlucaveschi.stockpricestrackercompose.BuildConfig
import com.gianlucaveschi.stockpricestrackercompose.ui.model.TickerApiModel
import com.gianlucaveschi.stockpricestrackercompose.ui.model.TickerSubscription
import com.gianlucaveschi.stockpricestrackercompose.ui.model.TickerUnsubscription
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

@ExperimentalSerializationApi
@ExperimentalCoroutinesApi
class StockMarketWebSocketImpl(

) : StockMarketWebSocket {

    private val logger = HttpLoggingInterceptor().setLevel(
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC
        else HttpLoggingInterceptor.Level.NONE
    )
    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()

    private val openConnectionRequest = Request.Builder()
        .url(WEB_SOCKET_URL)
        .build()

    private lateinit var webSocket: WebSocket
    private val jsonEncoder: Json = Json

    /**
     * callbackFlow converts a multi-shot callback into a flow
     *
     * https://elizarov.medium.com/callbacks-and-kotlin-flows-2b53aa2525cf
     * */
    private val tickerUpdatesFlow: Flow<TickerApiModel> = callbackFlow {
        val webSocketListener = object : WebSocketListener() {

            override fun onOpen(webSocket: WebSocket, response: Response) {
                Timber.d("onOpen")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                Timber.d("onMessage $text")
                trySend(jsonEncoder.decodeFromString(text))
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                Timber.d("onClosed")
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                Timber.d("onClosing")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                t.printStackTrace()
            }
        }

        webSocket = client.newWebSocket(openConnectionRequest, webSocketListener)

        awaitClose { webSocket.close(CLOSE_CONNECTION_TIMEOUT, null) }
    }

    override fun observeTickerUpdates(): Flow<TickerApiModel> = tickerUpdatesFlow

    override fun subscribeToTicker(tickerSubscription: TickerSubscription) {
        webSocket.send(jsonEncoder.encodeToString(tickerSubscription))
    }

    override fun unsubscribeFromTicker(tickerUnsubscription: TickerUnsubscription) {
        webSocket.send(jsonEncoder.encodeToString(tickerUnsubscription))
    }

    companion object {
        const val CLOSE_CONNECTION_TIMEOUT = 1000
        const val WEB_SOCKET_URL = "ws://159.89.15.214:8080/"
    }
}