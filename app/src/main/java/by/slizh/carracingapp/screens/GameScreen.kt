package by.slizh.carracingapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import by.slizh.carracingapp.components.CarControlButton
import by.slizh.carracingapp.components.PlayerCar
import by.slizh.carracingapp.components.Road
import by.slizh.carracingapp.ui.theme.LightGray

@Composable
fun GameScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(LightGray)
    ) {
        Road()
    }
}
