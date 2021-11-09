package com.gianlucaveschi.stockpricestrackercompose.ui.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TickerUnsubscription(
  @SerialName("unsubscribe") val isin: String
)