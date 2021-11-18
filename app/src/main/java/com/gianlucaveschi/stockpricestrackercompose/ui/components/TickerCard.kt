package com.gianlucaveschi.stockpricestrackercompose.ui.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gianlucaveschi.stockpricestrackercompose.ui.model.TickerUiModel
import com.gianlucaveschi.stockpricestrackercompose.ui.model.TickerUiModelFactory.getHardcodedTickerUiModel
import com.gianlucaveschi.stockpricestrackercompose.ui.util.getFormattedPrice
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import java.security.SecureRandom

@Composable
fun TickerCardSlim(
    ticker: TickerUiModel,
    onClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp,
            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 8.dp,
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
            ) {
                Text(
                    text = ticker.name,
                    modifier = Modifier
                        .wrapContentWidth(Alignment.Start)
                        .align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.h5
                )

                Spacer(modifier = Modifier.width(30.dp))
                Text(
                    text = ticker.price.getFormattedPrice(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                        .align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}

@ExperimentalCoroutinesApi
@Preview
@Composable
fun PreviewTickerCardSlim() {
    TickerCardSlim(
        ticker = getHardcodedTickerUiModel(),
        onClick = {
            Timber.d("ToDo: ")
        })
}

