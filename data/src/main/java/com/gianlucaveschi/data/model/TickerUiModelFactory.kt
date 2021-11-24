package com.gianlucaveschi.data.model

import java.security.SecureRandom

fun getListOfHardcodedTickerUiModel(): MutableList<TickerUiModel> = mutableListOf(
    com.gianlucaveschi.data.model.TickerUiModel(
        "Apple Inc.",
        "US0378331005",
        com.gianlucaveschi.data.model.getRandomDouble()
    ),
    com.gianlucaveschi.data.model.TickerUiModel(
        "Microsoft Corp.",
        "US5949181045",
        com.gianlucaveschi.data.model.getRandomDouble()
    ),
    com.gianlucaveschi.data.model.TickerUiModel(
        "Invesco Ltd.",
        "BMG491BT1088",
        com.gianlucaveschi.data.model.getRandomDouble()
    ),
    com.gianlucaveschi.data.model.TickerUiModel(
        "Tesla Motors Inc.",
        "US88160R1014",
        com.gianlucaveschi.data.model.getRandomDouble()
    ),
    com.gianlucaveschi.data.model.TickerUiModel(
        "Tiffany & Co.",
        "US8865471085",
        com.gianlucaveschi.data.model.getRandomDouble()
    ),
    com.gianlucaveschi.data.model.TickerUiModel(
        "Amazon.com Inc.",
        "US0231351067",
        com.gianlucaveschi.data.model.getRandomDouble()
    ),
    com.gianlucaveschi.data.model.TickerUiModel(
        "Nike, Inc.",
        "US6541061031",
        com.gianlucaveschi.data.model.getRandomDouble()
    ),
    com.gianlucaveschi.data.model.TickerUiModel(
        "Kellogg Co.",
        "US4878361082",
        com.gianlucaveschi.data.model.getRandomDouble()
    ),
    com.gianlucaveschi.data.model.TickerUiModel(
        "JPMorgan & Co.",
        "US46625H1005",
        com.gianlucaveschi.data.model.getRandomDouble()
    ),
    com.gianlucaveschi.data.model.TickerUiModel(
        "Google",
        "US02079K3059",
        com.gianlucaveschi.data.model.getRandomDouble()
    ),
    com.gianlucaveschi.data.model.TickerUiModel(
        "Xiaomi Corp.",
        "KYG9830T1067",
        com.gianlucaveschi.data.model.getRandomDouble()
    ),
    com.gianlucaveschi.data.model.TickerUiModel(
        "Samsung Electronic & Co.",
        "KR7005930003",
        com.gianlucaveschi.data.model.getRandomDouble()
    ),
    com.gianlucaveschi.data.model.TickerUiModel(
        "Eni S.P.A.",
        "IT0003132476",
        com.gianlucaveschi.data.model.getRandomDouble()
    )
)

private fun getRandomDouble() = SecureRandom().nextInt(100).toDouble()

fun getHardcodedTickerUiModel() = com.gianlucaveschi.data.model.TickerUiModel(
    "Apple Inc.",
    "US0378331005",
    com.gianlucaveschi.data.model.getRandomDouble()
)
