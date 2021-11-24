package com.gianlucaveschi.stockpricestrackercompose.mappers

import com.gianlucaveschi.data.model.TickerApiModel
import com.gianlucaveschi.data.model.TickerSubscription
import com.gianlucaveschi.data.model.TickerUiModel
import com.gianlucaveschi.data.model.TickerUnsubscription

fun TickerApiModel.mapToUiModel() = com.gianlucaveschi.data.model.TickerUiModel(
    name = com.gianlucaveschi.data.model.getListOfHardcodedTickerUiModel()
        .first { it.isin == this.isin }.name,
    isin = isin,
    price = price
)

fun TickerUiModel.mapToTicketSubscription() = TickerSubscription(this.isin)

fun TickerUiModel.mapToTicketUnsubscription() = TickerUnsubscription(this.isin)
