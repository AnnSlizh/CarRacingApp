package by.slizh.carracingapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import by.slizh.carracingapp.R

enum class Lane(val position: Int, val offset: Dp) {
    LEFT(0, (-100).dp),
    CENTER(1, 0.dp),
    RIGHT(2, 100.dp);

    companion object {
        fun fromPosition(position: Int): Lane {
            return entries.find { it.position == position } ?: CENTER
        }
    }
}

@Composable
fun PlayerCar(lanePosition: Int, modifier: Modifier = Modifier) {
    val laneOffset = Lane.fromPosition(lanePosition).offset

    Image(
        painter = painterResource(id = R.drawable.player_car),
        contentDescription = stringResource(id = R.string.player_car),
        modifier = modifier
            .width(50.dp)
            .height(90.dp)
            .offset(x = laneOffset, y = (-30).dp)
    )
}
