package ru.kraz.collectionapi.presentation.json

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import ru.kraz.collectionapi.R

@Composable
fun PostItem(modifier: Modifier, item: PostUi, click: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(4.dp)
            .clickable { click() },
        contentAlignment = Alignment.TopStart
    ) {
        Card(
            modifier = Modifier.fillMaxSize(),
            colors = CardDefaults.cardColors(
                containerColor = if (isSystemInDarkTheme()) colorResource(id = R.color.containerDark) else colorResource(
                    id = R.color.containerWhite
                ),
                contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 3.dp
            ),
        ) {
            Text(
                modifier = Modifier.padding(4.dp),
                text = item.title
            )
        }
    }
}