package com.gianlucaveschi.data.mappers

import com.gianlucaveschi.data.model.TickerApiModel
import com.gianlucaveschi.data.model.TickerSubscription
import com.gianlucaveschi.domain.model.TickerUiModel
import com.gianlucaveschi.data.model.TickerUnsubscription
import com.gianlucaveschi.domain.model.getListOfHardcodedTickerUiModel

fun TickerApiModel.mapToUiModel() = TickerUiModel(
    name = getListOfHardcodedTickerUiModel()
        .first { it.isin == this.isin }.name,
    isin = isin,
    price = price
)

fun TickerUiModel.mapToTicketSubscription() = TickerSubscription(this.isin)

fun TickerUiModel.mapToTicketUnsubscription() = TickerUnsubscription(this.isin)
