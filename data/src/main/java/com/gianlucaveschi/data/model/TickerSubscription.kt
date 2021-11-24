package com.gianlucaveschi.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TickerSubscription(
  @SerialName("subscribe") val isin: String
)