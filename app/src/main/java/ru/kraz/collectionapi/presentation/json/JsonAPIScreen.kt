package ru.kraz.collectionapi.presentation.json

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.koin.androidx.compose.koinViewModel
import ru.kraz.collectionapi.presentation.common.ErrorMessage
import ru.kraz.collectionapi.presentation.common.Loading

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun JsonAPIScreen(viewModel: PostsViewModel = koinViewModel()) {
    val data by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchPosts()
    }

    data.error?.let { ErrorMessage(stringResource(id = it)) }

    if (data.isLoading) Loading()

    if (data.posts.isNotEmpty())
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(items = data.posts, key = { index, item -> item.id }) { index, item ->
                PostItem(
                    modifier = Modifier.animateItemPlacement(tween(durationMillis = 250)),
                    item = item,
                    onClick = {
                        viewModel.expand(index)
                    },
                    onLongClick = {
                        viewModel.delete(index)
                    })
            }
        }
}