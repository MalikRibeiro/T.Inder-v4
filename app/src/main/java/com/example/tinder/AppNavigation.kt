package com.example.tinder

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

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

        composable (
            route = "tela_principal/{usuario}",
            arguments = listOf(navArgument("usuario") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val usuario = backStackEntry.arguments?.getString("usuario") ?: ""
            TelaPrincipal(navController = navController, usuario = usuario)
        }

        composable(
            route = "tela_matches/{usuario}",
            arguments = listOf(navArgument("usuario") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val usuario = backStackEntry.arguments?.getString("usuario") ?: ""
            TelaMatches(navController = navController, usuario = usuario)
        }

        composable(
            route = "tela_perfil/{usuario}",
            arguments = listOf(navArgument("usuario") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val usuario = backStackEntry.arguments?.getString("usuario")
            if (usuario != null) {
                TelaPerfil(navController = navController, usuario = usuario)
            }
        }
    }
}