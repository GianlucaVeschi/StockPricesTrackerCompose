package com.gianlucaveschi.stockpricestrackercompose.util

import com.gianlucaveschi.data.model.TickerUiModel


fun Double?.getFormattedPrice() : String = this?.shorten(2) ?: "--"

fun Double.shorten(decimals: Int) : String {
    return "€" + String.format("%.$decimals" + "f", this)
}

fun MutableList<TickerUiModel>.updateTicker(ticker: TickerUiModel): Int {
    val indexOfTicket = this.getTickerIndex(ticker)
    this[indexOfTicket] = ticker
    return indexOfTicket
}

fun MutableList<TickerUiModel>.getTickerIndex(ticker: TickerUiModel): Int {
    return this.indexOfFirst { it.isin == ticker.isin }
}