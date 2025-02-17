package by.slizh.carracingapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import by.slizh.carracingapp.R

@Composable
fun PoliceCar(lanePosition: Int, offsetY: Float, modifier: Modifier = Modifier) {
    val density = LocalDensity.current
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val roadWidthRatio = 0.9f
    val laneCount = 3

    val laneOffsetDp = with(density) {
        val roadWidth = (screenWidth * roadWidthRatio).toPx()
        val laneWidth = roadWidth / laneCount
        val roadStartX = (screenWidth.toPx() - roadWidth) / 2

        (roadStartX + laneWidth * lanePosition + laneWidth / 2 - 55.dp.toPx()).toDp()
    }

    Image(
        painter = painterResource(id = R.drawable.police_car),
        contentDescription = stringResource(id = R.string.police_car),
        modifier = modifier
            .width(90.dp)
            .height(90.dp)
            .offset(x = laneOffsetDp, y = offsetY.dp)
    )
}
