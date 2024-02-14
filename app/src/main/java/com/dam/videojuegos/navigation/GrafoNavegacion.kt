package com.dam.videojuegos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dam.videojuegos.screens.AddGame
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
        composable("main/{idUsuario}?admin={admin}",
            arguments = listOf(
                navArgument("idUsuario") { type = NavType.StringType },
                navArgument("admin") { type = NavType.BoolType }
            )
        ) { backStackEntry ->
            val idUsuario = backStackEntry.arguments?.getString("userId")
            val esAdmin = backStackEntry.arguments?.getBoolean("admin") ?: false
            MainScreen(
                navController = navController,
                idUsuario = idUsuario ?: "",
                esAdmin = esAdmin
            )
        }
        composable(
            route = "detalleJuego/{juegoId}?admin={admin}",
            arguments = listOf(
                navArgument("juegoId") { type = NavType.StringType },
                navArgument("admin") { type = NavType.BoolType }
            )
        ) { backStackEntry ->
            val juegoId = backStackEntry.arguments?.getString("juegoId")
            val esAdmin = backStackEntry.arguments?.getBoolean("admin") ?: false
            val juego = viewModelFirebase.obtenerJuegoPorId(juegoId)
            if (juego != null) {
                GameScreen(juego = juego, esAdmin = esAdmin, navController = navController)
            } else {
                viewModelFirebase.crearListener()
            }
        }


        composable("add") {
            AddGame(navController = navController)
        }

    }
}