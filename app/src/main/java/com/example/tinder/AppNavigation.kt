package com.example.tinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tinder.ui.theme.TinderTheme

class AppNavigation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TinderTheme {
                AppNavHost()
            }
        }
    }
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "TelaLogin") {

        composable("TelaLogin") {
            TelaLogin(onClickPrincipal = { navController.navigate("TelaPrincipal") })
        }

        composable("TelaPrincipal") {
            TelaPrincipal(onClickMatches = { navController.navigate("TelaMatches") })
        }

        composable("TelaMatches") {
            TelaMatches(onClickVoltar = { navController.popBackStack() })
        }

    }
}