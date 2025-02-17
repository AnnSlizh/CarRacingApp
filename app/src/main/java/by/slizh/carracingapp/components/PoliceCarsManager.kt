package by.slizh.carracingapp.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import by.slizh.carracingapp.PoliceCarState
import kotlinx.coroutines.delay
import kotlin.random.Random


@Composable
fun PoliceCarsManager(modifier: Modifier = Modifier) {
    val policeCars = remember { mutableStateListOf<PoliceCarState>() }
    val density = LocalDensity.current
    val screenHeight = with(density) { LocalConfiguration.current.screenHeightDp.dp.toPx() }
    val roadSpeed = remember { mutableFloatStateOf(2.5f) }
    val carHeightPx = with(density) { 100.dp.toPx() }
    val minGap = with(density) { (120..160).random().dp.toPx() }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)

            val totalLanes = listOf(0, 1, 2).shuffled()
            val chosenLanes = totalLanes.take(Random.nextInt(1, 3))
            val occupiedLanes = policeCars.map { it.lane to it.offsetY }

            val availableLanes = chosenLanes.filter { lane ->
                occupiedLanes.none { (existingLane, y) ->
                    existingLane == lane && y < minGap
                }
            }

            if (availableLanes.isNotEmpty()) {
                availableLanes.forEach { lane ->
                    policeCars.add(PoliceCarState(lane, offsetY = -carHeightPx))
                }
            }
            policeCars.removeAll { it.offsetY > screenHeight }
        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(16L)
            policeCars.forEachIndexed { index, enemy ->
                policeCars[index] = enemy.copy(offsetY = enemy.offsetY + roadSpeed.floatValue)
            }
        }
    }

    policeCars.forEach { enemy ->
        PoliceCar(
            lanePosition = enemy.lane,
            offsetY = enemy.offsetY,
            modifier = modifier
        )
    }
}


