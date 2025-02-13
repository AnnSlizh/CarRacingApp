package by.slizh.carracingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import by.slizh.carracingapp.ui.theme.CarRacingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarRacingAppTheme {
            //    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   StartScreen()
          //      }
            }
        }
    }
}

