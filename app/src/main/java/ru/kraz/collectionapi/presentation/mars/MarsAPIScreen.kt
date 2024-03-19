package ru.kraz.collectionapi.presentation.mars

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.kraz.collectionapi.presentation.common.ErrorMessage
import ru.kraz.collectionapi.presentation.common.Loading

@Composable
fun MarsAPIScreen(viewModel: MarsViewModel = koinViewModel()) {
    val data by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchImages()
    }

    data.error?.let { ErrorMessage(stringResource(id = it)) }

    if (data.isLoading) Loading()

    if (data.images.isNotEmpty())
        LazyVerticalGrid(columns = GridCells.Adaptive(150.dp)) {
            items(items = data.images, key = { item -> item.id }) { item ->
                ImageItem(item)
            }
        }
}