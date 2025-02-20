package by.slizh.carracingapp.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import by.slizh.carracingapp.GameState
import kotlinx.coroutines.delay

@Composable
fun PoliceCarManager(
    gameState: MutableState<GameState>,
    screenHeight: Float,
    carHeight: Float,
) {
    val roadSpeed = 3.5f

    LaunchedEffect(Unit) {
        while (!gameState.value.gameOver && gameState.value.timeLeft > 0) {
            delay(1000L)

            val occupiedLanes = gameState.value.obstacles
                .filter { it.second < 150f }
                .map { it.first }

            val availableLanes = Lane.entries.filterNot { it in occupiedLanes }

            if (availableLanes.isNotEmpty()) {
                val newCarLane = availableLanes.random()
                gameState.value = gameState.value.copy(
                    obstacles = gameState.value.obstacles + (newCarLane to -carHeight)
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        while (!gameState.value.gameOver && gameState.value.timeLeft > 0) {
            delay(16L)

            val updatedObstacles = gameState.value.obstacles.map { (x, y) -> x to y + roadSpeed }
                .filter { (_, y) -> y < screenHeight }

            val collision = updatedObstacles.any { (lane, y) ->
                lane == gameState.value.playerLane &&
                        y < gameState.value.playerY + 80f &&
                        y + 80f > gameState.value.playerY
            }

            val newScore = gameState.value.score + gameState.value.obstacles.count { (_, y) ->
                y < gameState.value.playerY && y + roadSpeed >= gameState.value.playerY
            }

            gameState.value = gameState.value.copy(
                obstacles = updatedObstacles,
                score = newScore,
                gameOver = collision
            )
        }
    }
}



