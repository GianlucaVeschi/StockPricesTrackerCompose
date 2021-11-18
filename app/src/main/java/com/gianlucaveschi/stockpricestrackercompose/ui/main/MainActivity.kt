package com.gianlucaveschi.stockpricestrackercompose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gianlucaveschi.stockpricestrackercompose.ui.components.TickerCardSlim
import com.gianlucaveschi.stockpricestrackercompose.ui.model.TickerUiModelFactory.getListOfHardcodedTickerUiModel
import com.gianlucaveschi.stockpricestrackercompose.ui.model.TickerUiModel
import com.gianlucaveschi.stockpricestrackercompose.ui.theme.StockPricesTrackerComposeTheme

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val tickersList = mainViewModel.tickersList.value
            val ticker = mainViewModel.tickerState.value

            StockPricesTrackerComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        TickerList(tickers = tickersList)
                        BottomButtons()
                    }
                }
            }
        }
    }
}

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
            TickerList(tickers = getListOfHardcodedTickerUiModel())
            BottomButtons()
        }
    }
}