package by.slizh.carracingapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface BestScoreRepository {
    fun getBestScore(): Flow<Int>
    suspend fun saveBestScore(score: Int)
}