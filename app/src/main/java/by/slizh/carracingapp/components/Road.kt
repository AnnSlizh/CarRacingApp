package by.slizh.carracingapp.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun Road() {
    val lineWidth = 10.dp
    val density = LocalDensity.current
    val lineWidthPx = with(density) { lineWidth.toPx() }
    val offsetY = rememberRoadSpeed()

    Canvas(modifier = Modifier.fillMaxSize()) {
        val roadWidth = size.width * 0.9f
        val roadStartX = (size.width - roadWidth) / 2
        val laneWidth = roadWidth / 3

        drawRect(
            color = Color.DarkGray,
            topLeft = Offset(roadStartX, 0f),
            size = Size(roadWidth, size.height)
        )

        for (i in 1..2) {
            val lineX = roadStartX + laneWidth * i - lineWidthPx / 2
            var startY = offsetY - with(density) { (80.dp + 30.dp).toPx() }

            while (startY < size.height) {
                drawRect(
                    color = Color.White,
                    topLeft = Offset(lineX, startY),
                    size = Size(lineWidthPx, with(density) { 80.dp.toPx() })
                )
                startY += with(density) { (80.dp + 30.dp).toPx() }
            }
        }
    }
}

@Composable
fun rememberRoadSpeed(): Float {
    val lineHeight = 80.dp
    val lineSpacing = 30.dp
    val density = LocalDensity.current
    val totalLineOffset = with(density) { (lineHeight + lineSpacing).toPx() }

    val transition = rememberInfiniteTransition(label = "")
    val offsetY by transition.animateFloat(
        initialValue = 0f,
        targetValue = totalLineOffset,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    return offsetY
}
