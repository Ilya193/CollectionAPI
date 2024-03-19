package ru.kraz.collectionapi.presentation.dummy

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.kraz.collectionapi.R
import ru.kraz.collectionapi.presentation.common.ElementAPI
import ru.kraz.collectionapi.presentation.common.ErrorMessage
import ru.kraz.collectionapi.presentation.common.Loading

@Composable
fun DummyJSONScreen(viewModel: ProductsViewModel = koinViewModel()) {
    val data by viewModel.uiState.collectAsState()
    val openAlertDialog = remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<Int?>(null) }

    LaunchedEffect(Unit) {
        viewModel.fetchProducts()
    }

    data.error?.let { ErrorMessage(stringResource(id = it)) }

    if (data.isLoading) Loading()

    if (data.products.isNotEmpty())
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(items = data.products, key = { index, item -> item.id }) { index, item ->
                ProductItem(item) {
                    openAlertDialog.value = true
                    selectedItem = index
                }
            }
        }

    if (openAlertDialog.value)
        selectedItem?.let {
            MinimalDialog(data.products[it].images) {
                openAlertDialog.value = false
                selectedItem = null
            }
        }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MinimalDialog(
    images: List<String>,
    onDismissRequest: () -> Unit,
) {
    val pagerState = rememberPagerState { images.size }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        HorizontalPager(
            modifier = Modifier.wrapContentSize(),
            state = pagerState
        ) {
            Card(
                modifier = Modifier
                    .size(350.dp),
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(images[it])
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}