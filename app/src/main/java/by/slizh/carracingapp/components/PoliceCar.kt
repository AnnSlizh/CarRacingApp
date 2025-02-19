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

@Composable
fun PoliceCar(lane: Lane, y: Float) {
    Image(
        painter = painterResource(id = R.drawable.police_car),
        contentDescription = stringResource(id = R.string.police_car),
        modifier = Modifier
            .offset(x = lane.positionX.dp, y = y.dp)
            .size(90.dp, 90.dp)
    )
}