package com.example.tinder

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class PerfilcomMatches(
    val id: Int,
    val nome: String,
    val descricao: String,
    val imageRes: Int,
)

private val mesmoPerfil = listOf(
    PerfilcomMatches(
        id = 1,
        nome = "Ana, 24",
        descricao = "Desenvolvedora de apps por profissão, aventureira por vocação. Em busca de alguém que tope maratonar séries e criar o próximo grande app juntos. 🤓✨",
        imageRes = R.drawable.a
    ),
    PerfilcomMatches(
        id = 2,
        nome = "Rosana, 29",
        descricao = "Engenheira de Machine Learning com uma paixão secreta por cafés coados e trilhas na natureza. Topa decifrar o algoritmo do amor comigo?",
        imageRes = R.drawable.b
    ),
    PerfilcomMatches(
        id = 3,
        nome = "Beatriz, 22",
        descricao = "Designer UX com a missão de tornar a vida mais fácil, uma interface de cada vez. Adoro museus, shows e encontrar beleza nas pequenas coisas.",
        imageRes = R.drawable.c
    ),
    PerfilcomMatches(
        id = 4,
        nome = "Roberta, 31",
        descricao = "Capturando a alma do mundo com minha câmera. Entre uma viagem e outra, estou sempre procurando um bom papo, uma risada e uma história para contar.",
        imageRes = R.drawable.d
    ),
    PerfilcomMatches(
        id = 5,
        nome = "Ludmila, 26",
        descricao = "Produtora musical nas horas vagas, busco uma sintonia perfeita. Se a vida fosse uma música, qual seria sua melodia favorita?",
        imageRes = R.drawable.e
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaMatches(navController: NavController, usuario: String) {
    var Pesquisa by remember { mutableStateOf("") }

    val ListaFiltro= mesmoPerfil.filter {
        it.nome.contains(Pesquisa, ignoreCase = true) ||
                it.descricao.contains(Pesquisa, ignoreCase = true)
    }

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
                value = Pesquisa,
                onValueChange = { Pesquisa = it },
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
                items(ListaFiltro) { perfil ->
                    CardPerfil(perfil = perfil)
                }
            }
        }
    }
}

@Composable
fun CardPerfil(perfil: PerfilcomMatches) {
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