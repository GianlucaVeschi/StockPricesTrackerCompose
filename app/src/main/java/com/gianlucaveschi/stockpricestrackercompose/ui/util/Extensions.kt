package com.gianlucaveschi.stockpricestrackercompose.ui.util


fun Double?.getFormattedPrice() : String = this?.shorten(2) ?: "0.00"

fun Double.shorten(decimals: Int) : String {
    return "€" + String.format("%.$decimals" + "f", this).replace(',', '.')
}