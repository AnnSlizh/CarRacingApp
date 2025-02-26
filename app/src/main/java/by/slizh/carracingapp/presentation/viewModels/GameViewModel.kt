package by.slizh.carracingapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.slizh.carracingapp.domain.repository.BestScoreRepository
import by.slizh.carracingapp.presentation.components.Lane
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val bestScoreRepository: BestScoreRepository
) : ViewModel() {

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
        getBestScore()
        startGame()
        startTimer()
    }

    private fun getBestScore() {
        viewModelScope.launch(Dispatchers.IO) {
            bestScoreRepository.getBestScore().collect { score ->
                _gameState.update { it.copy(bestScore = score) }
            }
        }
    }

    private fun startGame() {
        viewModelScope.launch {
            while (!_gameState.value.gameOver && _gameState.value.timeLeft > 0) {
                delay(16L)

                _gameState.update { currentState ->
                    val updatedObstacles =
                        currentState.obstacles.map { (x, y) -> x to y + roadSpeed }
                            .filter { (_, y) -> y < 600f }

                    val collision = updatedObstacles.any { (lane, y) ->
                        lane == currentState.playerLane &&
                                y < currentState.playerY + 80f &&
                                y + 80f > currentState.playerY
                    }

                    val newScore = currentState.score + currentState.obstacles.count { (_, y) ->
                        y < currentState.playerY && y + roadSpeed >= currentState.playerY
                    }

                    currentState.copy(
                        obstacles = updatedObstacles,
                        score = newScore,
                        gameOver = collision
                    )
                }

                if (_gameState.value.gameOver) {
                    saveBestScore(_gameState.value.score)
                }
            }
        }

        viewModelScope.launch {
            while (!_gameState.value.gameOver && _gameState.value.timeLeft > 0) {
                delay(1000L)
                addPoliceCar()
            }
        }
    }

    private fun saveBestScore(newScore: Int) {
        if (newScore > _gameState.value.bestScore) {
            viewModelScope.launch(Dispatchers.IO) {
                bestScoreRepository.saveBestScore(newScore)
                _gameState.update { it.copy(bestScore = newScore) }

            }
        }
    }

    private fun startTimer() {
        viewModelScope.launch {
            while (!_gameState.value.gameOver && _gameState.value.timeLeft > 0) {
                delay(1000)
                _gameState.update { it.copy(timeLeft = it.timeLeft - 1) }
            }
        }
    }

    fun movePlayerCarLeft() {
        _gameState.update { currentState ->
            currentState.copy(
                playerLane = when (currentState.playerLane) {
                    Lane.RIGHT -> Lane.CENTER
                    Lane.CENTER -> Lane.LEFT
                    Lane.LEFT -> Lane.LEFT
                }
            )
        }
    }

    fun movePlayerCarRight() {
        _gameState.update { currentState ->
            currentState.copy(
                playerLane = when (currentState.playerLane) {
                    Lane.LEFT -> Lane.CENTER
                    Lane.CENTER -> Lane.RIGHT
                    Lane.RIGHT -> Lane.RIGHT
                }
            )
        }
    }

    private fun addPoliceCar() {
        _gameState.update { currentState ->
            val occupiedLanes = currentState.obstacles
                .filter { it.second < 150f }
                .map { it.first }

            val availableLanes = Lane.entries.filterNot { it in occupiedLanes }

            if (availableLanes.isNotEmpty()) {
                val newCarLane = availableLanes.random()
                currentState.copy(
                    obstacles = currentState.obstacles + (newCarLane to 0f)
                )
            } else {
                currentState
            }
        }
    }
}
