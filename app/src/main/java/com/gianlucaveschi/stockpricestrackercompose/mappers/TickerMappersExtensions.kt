package com.gianlucaveschi.stockpricestrackercompose.mappers

import com.gianlucaveschi.stockpricestrackercompose.model.TickerApiModel
import com.gianlucaveschi.stockpricestrackercompose.model.TickerSubscription
import com.gianlucaveschi.stockpricestrackercompose.model.TickerUiModel
import com.gianlucaveschi.stockpricestrackercompose.model.TickerUiModelFactory.getListOfHardcodedTickerUiModel
import com.gianlucaveschi.stockpricestrackercompose.model.TickerUnsubscription

fun TickerApiModel.mapToUiModel(): TickerUiModel = this.run {
    TickerUiModel(
        name = getListOfHardcodedTickerUiModel().first { it.isin == this.isin }.name,
        isin = isin,
        price = price
    )
}

fun TickerUiModel.mapToTicketSubscription(): TickerSubscription = this.run {
    TickerSubscription(this.isin)
}

fun TickerUiModel.mapToTicketUnsubscription(): TickerUnsubscription = this.run {
    TickerUnsubscription(this.isin)
}