package com.dam.videojuegos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dam.videojuegos.screens.GameScreen
import com.dam.videojuegos.screens.LoginScreen
import com.dam.videojuegos.screens.MainScreen
import com.dam.videojuegos.screens.RegisterScreen
import com.dam.videojuegos.shared.ViewModelFirebase
import com.google.firebase.auth.FirebaseAuth

@Composable
fun GrafoNavegacion(navController: NavHostController, viewModelFirebase: ViewModelFirebase) {

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                navController = navController,
                auth = FirebaseAuth.getInstance()
            )
        }
        composable("register") {
            RegisterScreen(
                navController = navController,
                auth = FirebaseAuth.getInstance()
            )
        }
        composable("main") {
            MainScreen(navController = navController)
        }
        composable(
            route = "detalleJuego/{juegoId}",
            arguments = listOf(navArgument("juegoId") { type = NavType.StringType })
        ) { backStackEntry ->
            val juegoId = backStackEntry.arguments?.getString("juegoId")
            val juego = viewModelFirebase.obtenerJuegoPorId(juegoId)
            if (juego != null) {
                GameScreen(juego = juego)
            } else {
                viewModelFirebase.crearListener()
            }
        }

    }
}