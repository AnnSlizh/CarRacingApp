package by.slizh.carracingapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import by.slizh.carracingapp.viewModels.GameViewModel
import by.slizh.carracingapp.components.CarControlButton
import by.slizh.carracingapp.components.PlayerCar
import by.slizh.carracingapp.components.PoliceCar
import by.slizh.carracingapp.components.Road
import by.slizh.carracingapp.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun GameScreen(
    navController: NavController,
    viewModel: GameViewModel = hiltViewModel()
) {
    val gameState by viewModel.gameState.collectAsState()

    LaunchedEffect(gameState.gameOver, gameState.timeLeft) {
        if (gameState.gameOver) {
            navController.navigate(Screen.GameOverScreen.route) {
                popUpTo(Screen.GameScreen.route) { inclusive = true }
            }
        } else if (gameState.timeLeft <= 0) {
            navController.navigate(Screen.StartScreen.route) {
                popUpTo(Screen.GameScreen.route) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier.background(Color.Yellow),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Score: ${gameState.score}",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(text = "Time Left: ${gameState.timeLeft}s")

        Box {
            Road(Modifier.fillMaxSize())

            PlayerCar(
                playerLane = gameState.playerLane,
                playerCarY = gameState.playerY
            )

            gameState.obstacles.forEach { (x, y) ->
                PoliceCar(
                    lane = x,
                    y = y
                )
            }
            CarControlButton(
                modifier = Modifier.align(Alignment.BottomCenter),
                onMoveLeft = { viewModel.movePlayerLeft() },
                onMoveRight = { viewModel.movePlayerRight() }
            )
        }
    }
}


