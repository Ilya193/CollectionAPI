package ru.kraz.collectionapi.presentation.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.kraz.collectionapi.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(navController: NavController) {
    val apis = remember {
        mutableStateListOf(
            APIUi("MarsAPI", R.drawable.ic_mars, "MarsAPI"),
            APIUi("JSONPlaceholder", R.drawable.ic_json, "JsonAPI")
        )
    }
    var currentAPI by remember { mutableStateOf("") }
    val pagerState = rememberPagerState { apis.size }

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
                ElementAPI(apis[it]) {
                    navController.navigate(apis[it].route)
                }
            }
        }
    }
}