package by.slizh.carracingapp

import by.slizh.carracingapp.components.Lane

data class GameState(
    val playerLane: Lane = Lane.CENTER,
    val playerY: Float,
    val obstacles: List<Pair<Lane, Float>>,
    val score: Int,
    val bestScore: Int,
    val gameOver: Boolean,
    val timeLeft: Int
)
