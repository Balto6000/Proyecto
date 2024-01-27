package com.dam.videojuegos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dam.videojuegos.screens.LoginScreen
import com.dam.videojuegos.screens.MainScreen
import com.dam.videojuegos.screens.RegisterScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun GrafoNavegacion(navController: NavHostController) {

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
            MainScreen()
        }
    }
}