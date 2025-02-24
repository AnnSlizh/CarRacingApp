package by.slizh.carracingapp.navigation

sealed class Screen(val route: String) {
    object StartScreen : Screen(route = "start_screen")
    object GameScreen : Screen(route = "game_screen")
    object GameOverScreen : Screen(route = "game_over_screen")
}