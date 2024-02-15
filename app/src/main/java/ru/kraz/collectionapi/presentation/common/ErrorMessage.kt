package ru.kraz.collectionapi.presentation.common

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel
import ru.kraz.collectionapi.R
import ru.kraz.collectionapi.presentation.mars.MarsViewModel

@Composable
fun ErrorMessage(msg: String, viewModel: MarsViewModel = koinViewModel()) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = msg,
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )
            OutlinedButton(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally),
                onClick = {
                    viewModel.fetchImages()
                }) {
                Text(text = stringResource(R.string.retry))
            }
        }
    }
}