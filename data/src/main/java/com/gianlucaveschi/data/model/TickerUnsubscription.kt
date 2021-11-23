package com.gianlucaveschi.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TickerUnsubscription(
  @SerialName("unsubscribe") val isin: String
)