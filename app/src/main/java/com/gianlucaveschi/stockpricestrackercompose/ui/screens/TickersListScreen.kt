package com.gianlucaveschi.stockpricestrackercompose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gianlucaveschi.data.model.TickerUiModel
import com.gianlucaveschi.stockpricestrackercompose.ui.components.TickerCardSlim
import com.gianlucaveschi.stockpricestrackercompose.ui.theme.StockPricesTrackerComposeTheme

@Composable
fun TickersListScreen(tickers: List<TickerUiModel>) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(Modifier.weight(1f)) {
            items(
                count = tickers.size
            ) { i ->
                TickerCardSlim(ticker = tickers[i]) {
                    /*Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show() */
                }
            }
        }
        //TickerSubscriptionButtons() //So ugly please fixme
    }
}

@Composable
fun TickerSubscriptionButtons() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(106.dp),
        modifier = Modifier.wrapContentHeight()
    ) {
        Button(
            onClick = { /*TODO*/ },
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Subscribe All",
                style = TextStyle(fontSize = 15.sp)
            )
        }
        Button(
            onClick = { /*TODO*/ },
        ) {
            Text(
                modifier = Modifier.padding(9.dp),
                text = "Unsubscribe all",
                style = TextStyle(fontSize = 15.sp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StockPricesTrackerComposeTheme {
        TickersListScreen(com.gianlucaveschi.data.model.getListOfHardcodedTickerUiModel())
    }
}

