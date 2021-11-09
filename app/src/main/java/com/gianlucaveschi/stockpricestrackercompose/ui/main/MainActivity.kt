package com.gianlucaveschi.stockpricestrackercompose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.gianlucaveschi.stockpricestrackercompose.ui.components.TickerCardSlim
import com.gianlucaveschi.stockpricestrackercompose.ui.model.TickerUiModelFactory.getHardcodedTickerUiModel
import com.gianlucaveschi.stockpricestrackercompose.ui.model.TickerUiModel
import com.gianlucaveschi.stockpricestrackercompose.ui.theme.StockPricesTrackerComposeTheme
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockPricesTrackerComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        TickerList(tickers = mainViewModel.tickersList)
                        BottomButtons()
                    }
                }
            }
        }
        mainViewModel.init()
        collectTickerUpdates()
    }

    private fun collectTickerUpdates(){
        lifecycleScope.launch {
            mainViewModel.tickerStateFlow
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED) //Avoid collecting flows when UI is in the background
                .onEach {
                    Timber.d("Collecting $it")
                    //Update List

                }.launchIn(lifecycleScope)
        }
    }

}

@Composable
fun TickerList(tickers: List<TickerUiModel>) {
    LazyColumn {
        itemsIndexed(
            items = tickers
        ) { index, ticker ->
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
            TickerList(tickers = getHardcodedTickerUiModel())
            BottomButtons()
        }
    }
}