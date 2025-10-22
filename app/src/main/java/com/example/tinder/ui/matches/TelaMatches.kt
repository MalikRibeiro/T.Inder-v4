package com.example.tinder.ui.matches

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tinder.ui.login.Laranja
import com.example.tinder.R
import com.example.tinder.ui.theme.TinderTheme
import com.example.tinder.ui.matches.MatchesViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaMatches(navController: NavController, usuario: String) {
    val viewModel: MatchesViewModel = viewModel(factory = MatchesViewModel.Factory)

    val uiState by viewModel.uiState.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Meus Matches - $usuario") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = uiState.termoBusca,
                onValueChange = { viewModel.onBuscaChange(it) },
                label = { Text("Buscar perfil...") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                colors = ButtonDefaults.buttonColors(Laranja),
                onClick = { navController.navigate("tela_perfil/$usuario") },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            )
            {
                Text(text = "Tela de Perfil")
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(uiState.matchesFiltrados) { perfil ->
                    CardPerfil(perfil = perfil)
                }
            }
        }
    }
}

@Composable
fun CardPerfil(perfil: PerfilMatch) {
    val context = LocalContext.current

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = perfil.imageRes),
                contentDescription = perfil.nome,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = perfil.nome,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = perfil.descricao,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaMatchesPreview() {
    TinderTheme {
        val navController = rememberNavController()
        TelaMatches(navController = navController, usuario = "Joao")
    }
}