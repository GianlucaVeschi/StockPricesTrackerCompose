package com.gianlucaveschi.stockpricestrackercompose.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gianlucaveschi.stockpricestrackercompose.model.TickerUiModel
import com.gianlucaveschi.stockpricestrackercompose.model.TickerUiModelFactory
import com.gianlucaveschi.stockpricestrackercompose.ui.theme.StockPricesTrackerComposeTheme

@Composable
fun TickerList(tickers: List<TickerUiModel>) {
    LazyColumn {
        itemsIndexed(
            items = tickers
        ) { _, ticker ->
            TickerCardSlim(ticker = ticker) {
                /*Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show() */
            }
        }
    }
}

@Composable
fun BottomButtons() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(170.dp),
        modifier = Modifier.fillMaxWidth()
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
                modifier = Modifier.padding(8.dp),
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
        Column {
            TickerList(tickers = TickerUiModelFactory.getListOfHardcodedTickerUiModel())
            BottomButtons()
        }
    }
}
