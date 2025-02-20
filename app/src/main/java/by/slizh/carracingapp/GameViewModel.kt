package by.slizh.carracingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.slizh.carracingapp.components.Lane
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor() : ViewModel() {
    private val _gameState = MutableStateFlow(
        GameState(
            playerLane = Lane.CENTER,
            playerY = 600f - 90f - 20,
            obstacles = listOf(),
            score = 0,
            bestScore = 0,
            gameOver = false,
            timeLeft = 180
        )
    )
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    private val roadSpeed = 3.5f

    init {
        startGame()
        startTimer()
    }

    private fun startGame() {
        viewModelScope.launch {
            while (!_gameState.value.gameOver && _gameState.value.timeLeft > 0) {
                delay(16L)

                val updatedObstacles =
                    _gameState.value.obstacles.map { (x, y) -> x to y + roadSpeed }
                        .filter { (_, y) -> y < 600f }

                val collision = updatedObstacles.any { (lane, y) ->
                    lane == _gameState.value.playerLane &&
                            y < _gameState.value.playerY + 80f &&
                            y + 80f > _gameState.value.playerY
                }

                val newScore = _gameState.value.score + _gameState.value.obstacles.count { (_, y) ->
                    y < _gameState.value.playerY && y + roadSpeed >= _gameState.value.playerY
                }

                _gameState.value = _gameState.value.copy(
                    obstacles = updatedObstacles,
                    score = newScore,
                    gameOver = collision
                )
            }
        }

        viewModelScope.launch {
            while (!_gameState.value.gameOver && _gameState.value.timeLeft > 0) {
                delay(1000L)
                addPoliceCar()
            }
        }
    }

    private fun startTimer() {
        viewModelScope.launch {
            while (!_gameState.value.gameOver && _gameState.value.timeLeft > 0) {
                delay(1000)
                _gameState.value = _gameState.value.copy(timeLeft = _gameState.value.timeLeft - 1)
            }
        }
    }

    fun movePlayerLeft() {
        _gameState.value = _gameState.value.copy(
            playerLane = when (_gameState.value.playerLane) {
                Lane.RIGHT -> Lane.CENTER
                Lane.CENTER -> Lane.LEFT
                Lane.LEFT -> Lane.LEFT
            }
        )
    }

    fun movePlayerRight() {
        _gameState.value = _gameState.value.copy(
            playerLane = when (_gameState.value.playerLane) {
                Lane.LEFT -> Lane.CENTER
                Lane.CENTER -> Lane.RIGHT
                Lane.RIGHT -> Lane.RIGHT
            }
        )
    }

    private fun addPoliceCar() {
        val occupiedLanes = _gameState.value.obstacles
            .filter { it.second < 150f }
            .map { it.first }

        val availableLanes = Lane.entries.filterNot { it in occupiedLanes }

        if (availableLanes.isNotEmpty()) {
            val newCarLane = availableLanes.random()
            _gameState.value = _gameState.value.copy(
                obstacles = _gameState.value.obstacles + (newCarLane to -90f)
            )
        }
    }
}
