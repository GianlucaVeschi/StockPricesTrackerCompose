package com.gianlucaveschi.stockpricestrackercompose.ui.model

data class TickerUiModel(
    val name: String,
    val isin: String,
    val price: Double? = null
)