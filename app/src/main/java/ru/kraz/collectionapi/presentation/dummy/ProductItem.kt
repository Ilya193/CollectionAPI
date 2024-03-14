package ru.kraz.collectionapi.presentation.dummy

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.kraz.collectionapi.R
import ru.kraz.collectionapi.ui.theme.BlueDark
import ru.kraz.collectionapi.ui.theme.Gray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItem(item: ProductUi, click: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(4.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Card(
            modifier = Modifier.fillMaxSize(),
            colors = CardDefaults.cardColors(
                containerColor = if (isSystemInDarkTheme()) BlueDark else Gray
            )
        ) {
            Column {
                InformationText(item.title, 20.sp)
                InformationText(item.description, 16.sp)
                Box(modifier = Modifier.wrapContentSize().clickable {
                    click()
                }) {
                    AsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        model = item.thumbnail,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Row(
                        modifier = Modifier.align(Alignment.BottomStart)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_star),
                            contentDescription = null,
                        )
                        Text(
                            modifier = Modifier.align(Alignment.CenterVertically),
                            text = item.rating.toString(), style = TextStyle(
                                color = Color.Yellow,
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun InformationText(info: String, size: TextUnit) {
    Text(
        modifier = Modifier.padding(4.dp), text = info, style = TextStyle(
            color = if (isSystemInDarkTheme()) Color.White else Color.Black,
            fontSize = size,
            fontWeight = FontWeight.Bold
        )
    )
}