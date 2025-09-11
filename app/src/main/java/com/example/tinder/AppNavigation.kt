package com.example.tinder

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "tela_login"
    ) {
        composable("tela_login") {
            TelaLogin(navController = navController)
        }

        composable("tela_principal") {
            TelaPrincipal(navController = navController)
        }

        composable("tela_matches") {
            HomeScreen(navController = navController)
        }

        composable("tela_perfil") {
            TelaPerfil(navController = navController)
        }
    }
}