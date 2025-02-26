package by.slizh.carracingapp.presentation.viewModels

import by.slizh.carracingapp.presentation.components.Lane

data class GameState(
    val playerLane: Lane = Lane.CENTER,
    val playerY: Float,
    val obstacles: List<Pair<Lane, Float>>,
    val score: Int,
    val bestScore: Int,
    val gameOver: Boolean,
    val timeLeft: Int
)
