package by.slizh.carracingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import by.slizh.carracingapp.navigation.Screen
import by.slizh.carracingapp.presentation.screens.GameOverScreen
import by.slizh.carracingapp.presentation.screens.GameScreen
import by.slizh.carracingapp.presentation.screens.StartScreen
import by.slizh.carracingapp.ui.theme.CarRacingAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarRacingAppTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.StartScreen.route,
                        Modifier.padding(innerPadding)
                    ) {
                        composable(route = Screen.StartScreen.route) {
                            StartScreen(navController = navController)
                        }
                        composable(route = Screen.GameScreen.route) {
                            GameScreen(navController = navController)
                        }
                        composable(route = Screen.GameOverScreen.route) {
                            GameOverScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}