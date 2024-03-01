package ru.kraz.collectionapi.presentation.common

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ru.kraz.collectionapi.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(navController: NavController) {
    val apis = remember {
        mutableStateListOf(
            APIUi("MarsAPI", R.drawable.ic_mars, "MarsAPI"),
            APIUi("JSONPlaceholder", R.drawable.ic_json, "JsonAPI"),
            APIUi("dummyJSON", R.drawable.ic_json, "dummyJSON")
        )
    }
    var currentAPI by remember { mutableStateOf("") }
    val pagerState = rememberPagerState { apis.size }
    val coroutineScope = rememberCoroutineScope()

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect {
            currentAPI = apis[it].name
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxHeight(0.2f)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(
                    id = R.string.choise_api
                ) + currentAPI,
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(15.dp))
            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                state = pagerState
            ) {
                var showIcNext by remember { mutableStateOf(true) }
                if (it == apis.size - 1) showIcNext = false
                Box {
                    ElementAPI(apis[it]) {
                        navController.navigate(apis[it].route)
                    }
                    if (showIcNext)
                        Image(
                            modifier = Modifier
                                .size(25.dp)
                                .clip(CircleShape)
                                .background(color)
                                .align(Alignment.CenterEnd)
                                .clickable {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(it + 1)
                                    }
                                },
                            painter = painterResource(id = R.drawable.ic_next),
                            contentDescription = null
                        )
                }
            }
        }
    }
}