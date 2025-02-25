package by.slizh.carracingapp.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import by.slizh.carracingapp.domain.repository.BestScoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BestScoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : BestScoreRepository {

    private companion object {
       private val BEST_SCORE_KEY = intPreferencesKey("best_score")
    }

    override fun getBestScore(): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[BEST_SCORE_KEY] ?: 0
        }
    }

    override suspend fun saveBestScore(score: Int) {
        dataStore.edit { preferences ->
            preferences[BEST_SCORE_KEY] = score
        }
    }
}