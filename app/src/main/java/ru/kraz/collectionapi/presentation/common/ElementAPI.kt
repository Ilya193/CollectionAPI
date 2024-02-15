package ru.kraz.collectionapi.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun ElementAPI(item: APIUi, open: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(modifier = Modifier.fillMaxWidth().clickable(
        interactionSource = interactionSource,
        indication = null
    ) { open() }) {
        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = item.img),
            contentDescription = null
        )
    }
}