package com.gianlucaveschi.domain.model

import java.security.SecureRandom

fun getListOfHardcodedTickerUiModel(): MutableList<TickerUiModel> = mutableListOf(
    TickerUiModel("Apple Inc.", "US0378331005"),
    TickerUiModel("Microsoft Corp.", "US5949181045"),
    TickerUiModel("Invesco Ltd.", "BMG491BT1088"),
    TickerUiModel("Tesla Motors Inc.", "US88160R1014"),
    TickerUiModel("Tiffany & Co.", "US8865471085"),
    TickerUiModel("Amazon.com Inc.", "US0231351067"),
    TickerUiModel("Nike, Inc.", "US6541061031"),
    TickerUiModel("Kellogg Co.", "US4878361082"),
    TickerUiModel("JPMorgan & Co.", "US46625H1005"),
    TickerUiModel("Google", "US02079K3059"),
    TickerUiModel("Xiaomi Corp.", "KYG9830T1067"),
    TickerUiModel("Samsung Electronic & Co.", "KR7005930003"),
    TickerUiModel("Eni S.P.A.", "IT0003132476"),
    TickerUiModel("N26", "NET000N26000"),
    TickerUiModel("Razer", "KYG7397A1067"),
    TickerUiModel("Harley Davidson", "US4128221086"),
    TickerUiModel("Dell", "US24703L2025")
)

private fun getRandomDouble() = SecureRandom().nextInt(100).toDouble()

fun getHardcodedTickerUiModel() = TickerUiModel(
    "Apple Inc.",
    "US0378331005",
    getRandomDouble()
)
