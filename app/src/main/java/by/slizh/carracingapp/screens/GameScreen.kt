package by.slizh.carracingapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import by.slizh.carracingapp.GameViewModel
import by.slizh.carracingapp.components.CarControlButton
import by.slizh.carracingapp.components.PlayerCar
import by.slizh.carracingapp.components.PoliceCar
import by.slizh.carracingapp.components.Road

@Composable
fun GameScreen(modifier: Modifier, viewModel: GameViewModel = hiltViewModel()) {
    val gameState by viewModel.gameState.collectAsState()

    Box(
        modifier = modifier
            .background(Color.Gray)
    ) {
        Road()

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

    }

    CarControlButton(
        onMoveLeft = { viewModel.movePlayerLeft() },
        onMoveRight = { viewModel.movePlayerRight() }
    )
}


