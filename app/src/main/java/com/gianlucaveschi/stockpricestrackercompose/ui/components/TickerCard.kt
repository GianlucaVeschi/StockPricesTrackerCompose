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
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
                        //.fillMaxWidth(0.85f)
                        .wrapContentWidth(Alignment.Start)
                        .align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.h5
                )

                Spacer(modifier = Modifier.width(30.dp))
                Text(
                    text = "${ticker.price}€",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                        .align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.h6
                )

//                val iconFilled = mutableStateOf(false)
//                IconToggleButton(
//                    checked = iconFilled.value,
//                    onCheckedChange = { iconFilled.value = true }, // TODO: Add to fav list
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .wrapContentWidth(Alignment.End)
//                        .align(Alignment.CenterVertically),
//                    content = {
//                        Icon(
//                            imageVector = Icons.Default.FavoriteBorder,
//                            contentDescription = "Add to Favorites"
//                        )
//                    }
//                )
            }
        }
    }
}

@ExperimentalCoroutinesApi
@Preview
@Composable
fun PreviewTickerCardSlim() {
    TickerCardSlim(
        ticker = TickerUiModel(
            name = "Apple Inc.",
            isin = "12345678",
            price = SecureRandom().nextInt(100).toDouble()
        ),
        onClick = {
            Log.d("TAG", "PreviewTickerCardSlim: ")
        })
}