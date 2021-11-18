package com.gianlucaveschi.stockpricestrackercompose.util


fun Double?.getFormattedPrice() : String = this?.shorten(2) ?: "0.00"

fun Double.shorten(decimals: Int) : String {
    return "€" + String.format("%.$decimals" + "f", this).replace(',', '.')
}