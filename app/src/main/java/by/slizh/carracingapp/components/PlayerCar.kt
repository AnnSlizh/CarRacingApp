package by.slizh.carracingapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import by.slizh.carracingapp.R

enum class Lane(val positionX: Float) {
    LEFT(50f),
    CENTER(150f),
    RIGHT(250f);
}

@Composable
fun PlayerCar(playerLane: Lane, playerCarY: Float) {
    Image(
        painter = painterResource(id = R.drawable.player_car),
        contentDescription = stringResource(id = R.string.player_car),
        modifier = Modifier
            .offset(x = playerLane.positionX.dp, y = playerCarY.dp)
            .size(90.dp, 90.dp)
    )
}