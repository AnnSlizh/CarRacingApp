package by.slizh.carracingapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.slizh.carracingapp.domain.repository.BestScoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val bestScoreRepository: BestScoreRepository
) : ViewModel() {

    private val _bestScore = MutableStateFlow(0)
    val bestScore: StateFlow<Int> = _bestScore.asStateFlow()

    init {
        getBestScore()
    }

    private fun getBestScore() {
        viewModelScope.launch(Dispatchers.IO) {
            bestScoreRepository.getBestScore().collect { score ->
                _bestScore.value = score
            }
        }
    }
}

