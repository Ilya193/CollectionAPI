package ru.kraz.collectionapi.presentation.mars

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun ImageItem(item: ImageUi) {
    var isLoadingImage by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }
    Box {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(16.dp),
            model = item.img,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            onLoading = {
                isLoadingImage = true
                isError = false
            },
            onSuccess = {
                isLoadingImage = false
                isError = false
            },
            onError = {
                isLoadingImage = false
                isError = true
            },
        )
        if (isLoadingImage) CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
        if (isError) Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Произошла ошибка при загрузке изображения",
            style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold)
        )
    }
}