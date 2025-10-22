package com.example.tinder

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tinder.ui.cadastro.TelaCadastro
import com.example.tinder.ui.login.TelaLogin
import com.example.tinder.ui.matches.TelaMatches
import com.example.tinder.ui.perfil.TelaPerfil
import com.example.tinder.ui.principal.TelaPrincipal

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

        composable("tela_cadastro") {
            TelaCadastro(navController = navController)
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