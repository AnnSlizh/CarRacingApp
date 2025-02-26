package by.slizh.carracingapp.presentation.screens

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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import by.slizh.carracingapp.presentation.viewModels.GameViewModel
import by.slizh.carracingapp.presentation.components.CarControlButton
import by.slizh.carracingapp.presentation.components.PlayerCar
import by.slizh.carracingapp.presentation.components.PoliceCar
import by.slizh.carracingapp.presentation.components.Road
import by.slizh.carracingapp.navigation.Screen

@Composable
fun GameScreen(
    navController: NavController,
    gameViewModel: GameViewModel = hiltViewModel()
) {
    val gameState by gameViewModel.gameState.collectAsState()

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
            fontSize = 16.sp
        )
        Text(
            text = "Best Score: ${gameState.bestScore}",
            fontSize = 12.sp
        )
        Text(
            text = "Time Left: ${gameState.timeLeft}s",
            fontSize = 12.sp
        )

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
                onMoveLeft = { gameViewModel.movePlayerCarLeft() },
                onMoveRight = { gameViewModel.movePlayerCarRight() }
            )
        }
    }
}


