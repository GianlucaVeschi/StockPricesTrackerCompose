package com.gianlucaveschi.stockpricestrackercompose.ui.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TickerSubscription(
  @SerialName("subscribe") val isin: String
)