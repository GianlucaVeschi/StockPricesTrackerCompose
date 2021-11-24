package com.gianlucaveschi.stockpricestrackercompose.model

import java.security.SecureRandom

fun getListOfHardcodedTickerUiModel(): MutableList<TickerUiModel> = mutableListOf(
    TickerUiModel("Apple Inc.", "US0378331005", getRandomDouble()),
    TickerUiModel("Microsoft Corp.", "US5949181045", getRandomDouble()),
    TickerUiModel("Invesco Ltd.", "BMG491BT1088", getRandomDouble()),
    TickerUiModel("Tesla Motors Inc.", "US88160R1014", getRandomDouble()),
    TickerUiModel("Tiffany & Co.", "US8865471085", getRandomDouble()),
    TickerUiModel("Amazon.com Inc.", "US0231351067", getRandomDouble()),
    TickerUiModel("Nike, Inc.", "US6541061031", getRandomDouble()),
    TickerUiModel("Kellogg Co.", "US4878361082", getRandomDouble()),
    TickerUiModel("JPMorgan & Co.", "US46625H1005", getRandomDouble()),
    TickerUiModel("Google", "US02079K3059", getRandomDouble()),
    TickerUiModel("Xiaomi Corp.", "KYG9830T1067", getRandomDouble()),
    TickerUiModel("Samsung Electronic & Co.", "KR7005930003", getRandomDouble()),
    TickerUiModel("Eni S.P.A.", "IT0003132476", getRandomDouble())
)

private fun getRandomDouble() = SecureRandom().nextInt(100).toDouble()

fun getHardcodedTickerUiModel() = TickerUiModel("Apple Inc.", "US0378331005", getRandomDouble())
