package ru.kraz.collectionapi.presentation.common

import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.kraz.collectionapi.presentation.dummy.DummyJSONScreen
import ru.kraz.collectionapi.presentation.json.JsonAPIScreen
import ru.kraz.collectionapi.presentation.mars.MarsAPIScreen
import ru.kraz.collectionapi.ui.theme.CollectionAPITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            CollectionAPITheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Content()
                }
            }
        }
    }
}

@Composable
fun Content(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.Main.route, enterTransition = {
        EnterTransition.None
    }, exitTransition = {
        ExitTransition.None
    }) {
        composable(Screens.Main.route) {
            MainScreen(navController = navController)
        }
        composable(Screens.MarsAPI.route) {
            MarsAPIScreen()
        }
        composable(Screens.JsonAPI.route) {
            JsonAPIScreen()
        }
        composable(Screens.DummyJSON.route) {
            DummyJSONScreen()
        }
    }
}