package com.gianlucaveschi.stockpricestrackercompose.ui.model

data class TickerUiModel(
    val name: String,
    val isin: String,
    val price: Double? = null
) {
    fun getFormattedPrice() : String = price?.shorten(2) ?: "0.00"
}

fun Double.shorten(decimals: Int) : String {
    return "€" + String.format("%.$decimals" + "f", this).replace(',', '.')
}