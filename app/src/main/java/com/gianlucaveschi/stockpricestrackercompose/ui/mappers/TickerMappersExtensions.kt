package com.gianlucaveschi.stockpricestrackercompose.ui.mappers

import com.gianlucaveschi.stockpricestrackercompose.ui.model.TickerApiModel
import com.gianlucaveschi.stockpricestrackercompose.ui.model.TickerSubscription
import com.gianlucaveschi.stockpricestrackercompose.ui.model.TickerUiModel
import com.gianlucaveschi.stockpricestrackercompose.ui.model.TickerUiModelFactory.getHardcodedTickerUiModel
import com.gianlucaveschi.stockpricestrackercompose.ui.model.TickerUnsubscription

fun TickerApiModel.mapToUiModel(): TickerUiModel = this.run {
    TickerUiModel(
        name = getHardcodedTickerUiModel().first { it.isin == this.isin }.name,
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