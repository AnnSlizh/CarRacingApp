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
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun Road() {
    val density = LocalDensity.current
    val screenWidth = with(density) { LocalConfiguration.current.screenWidthDp.dp.toPx() }
    val screenHeight = with(density) { LocalConfiguration.current.screenHeightDp.dp.toPx() }
    val roadWidth = screenWidth * 0.9f
    val roadStartX = (screenWidth - roadWidth) / 2
    val laneWidth = roadWidth / 3

    val lineWidth = 10f
    val lineHeight = 80f
    val lineSpacing = 30f
    val totalLineOffset = lineHeight + lineSpacing

    val offsetY = rememberInfiniteTransition(label = "").animateFloat(
        initialValue = 0f,
        targetValue = totalLineOffset,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawRect(
            color = Color.DarkGray,
            topLeft = Offset(roadStartX, 0f),
            size = Size(roadWidth, screenHeight)
        )

        for (i in 1..2) {
            val lineX = roadStartX + laneWidth * i - lineWidth / 2
            var startY = offsetY.value - totalLineOffset

            while (startY < screenHeight) {
                drawRect(
                    color = Color.White,
                    topLeft = Offset(lineX, startY),
                    size = Size(lineWidth, lineHeight)
                )
                startY += totalLineOffset
            }
        }
    }
}


