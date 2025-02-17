package by.slizh.carracingapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import by.slizh.carracingapp.R

@Composable
fun PlayerCar(lanePosition: Int, modifier: Modifier = Modifier) {
    val laneOffset = when (lanePosition) {
        0 -> (-100).dp
        1 -> 0.dp
        2 -> 100.dp
        else -> 0.dp
    }

    Image(
        painter = painterResource(id = R.drawable.player_car),
        contentDescription = stringResource(id = R.string.player_car),
        modifier = modifier
            .width(50.dp)
            .height(90.dp)
            .offset(x = laneOffset, y = (-30).dp)
    )
}