package by.slizh.carracingapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import by.slizh.carracingapp.GameState
import by.slizh.carracingapp.components.CarControlButton
import by.slizh.carracingapp.components.Lane
import by.slizh.carracingapp.components.PlayerCar
import by.slizh.carracingapp.components.PoliceCar
import by.slizh.carracingapp.components.PoliceCarManager
import by.slizh.carracingapp.components.Road
import kotlinx.coroutines.delay

@SuppressLint("UnrememberedMutableState")
@Composable
fun GameScreen(modifier: Modifier) {
    val screenHeight = 600f
    val carHeight = 90f

    val gameState = remember {
        mutableStateOf(
            GameState(
                playerLane = Lane.CENTER,
                playerY = screenHeight - carHeight - 20,
                obstacles = listOf(),
                score = 0,
                bestScore = 0,
                gameOver = false,
                timeLeft = 180
            )
        )
    }

    val timeLeft by produceState(initialValue = 180) {
        while (!gameState.value.gameOver && value > 0) {
            delay(1000)
            value -= 1
            gameState.value = gameState.value.copy(timeLeft = value)
        }
    }

    Box(
        modifier = modifier.background(Color.Gray)
    ) {
        Road()

        PlayerCar(
            playerLane = gameState.value.playerLane,
            playerCarY = gameState.value.playerY
        )

        gameState.value.obstacles.forEach { (x, y) ->
            PoliceCar(
                lane = x,
                y = y
            )
        }
    }

    CarControlButton(
        onMoveLeft = {
            gameState.value = gameState.value.copy(
                playerLane = when (gameState.value.playerLane) {
                    Lane.RIGHT -> Lane.CENTER
                    Lane.CENTER -> Lane.LEFT
                    Lane.LEFT -> Lane.LEFT
                }
            )
        },
        onMoveRight = {
            gameState.value = gameState.value.copy(
                playerLane = when (gameState.value.playerLane) {
                    Lane.LEFT -> Lane.CENTER
                    Lane.CENTER -> Lane.RIGHT
                    Lane.RIGHT -> Lane.RIGHT
                }
            )
        }
    )

    PoliceCarManager(
        gameState = gameState,
        screenHeight = screenHeight,
        carHeight = carHeight,
    )
}

