package by.slizh.carracingapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import by.slizh.carracingapp.components.CarControlButton
import by.slizh.carracingapp.components.PlayerCar
import by.slizh.carracingapp.components.Road
import by.slizh.carracingapp.ui.theme.LightGray

@Composable
fun GameScreen(modifier: Modifier = Modifier) {
    var playerPosition by remember { mutableIntStateOf(1) }

    Box(
        modifier = modifier
            .background(LightGray)
    ) {
        Road()

        PlayerCar(
            lanePosition = playerPosition,
            modifier = Modifier.align(Alignment.BottomCenter)
        )

        CarControlButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            onMoveLeft = { if (playerPosition > 0) playerPosition-- },
            onMoveRight = { if (playerPosition < 2) playerPosition++ }
        )
    }
}
