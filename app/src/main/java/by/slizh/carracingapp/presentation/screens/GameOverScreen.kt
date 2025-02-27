package by.slizh.carracingapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import by.slizh.carracingapp.R
import by.slizh.carracingapp.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun GameOverScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(3000L)
        navController.navigate(Screen.StartScreen.route) {
            popUpTo(Screen.GameOverScreen.route) { inclusive = true }
        }
    }
    Box(
        modifier = Modifier
            .background(color = Color.Black)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.game_over),
            contentDescription = stringResource(id = R.string.game_over),
        )
    }
}