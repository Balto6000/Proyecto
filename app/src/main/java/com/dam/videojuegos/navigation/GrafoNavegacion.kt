package com.dam.videojuegos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dam.videojuegos.screens.LoginScreen
import com.dam.videojuegos.screens.MainScreen

@Composable
fun GrafoNavegacion(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen { username, password ->
                if (loginValido(username, password)) {
                    navController.navigate("main")
                }
            }
        }
        composable("main") {
            MainScreen()
        }
    }
}

private fun loginValido(username: String, password: String): Boolean {
    return username.isNotBlank() && password.isNotBlank()
}
