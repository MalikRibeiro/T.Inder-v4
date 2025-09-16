package com.example.tinder

import android.widget.Toast
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class MatchProfile(
    val id: Int,
    val name: String,
    val description: String,
    val imageRes: Int,
)

private val sampleProfiles = listOf(
    MatchProfile(
        id = 1,
        name = "Ana, 24",
        description = "Desenvolvedora de apps por profissão, aventureira por vocação. Em busca de alguém que tope maratonar séries e criar o próximo grande app juntos. 🤓✨",
        imageRes = R.drawable.a
    ),
    MatchProfile(
        id = 2,
        name = "Rosana, 29",
        description = "Engenheira de Machine Learning com uma paixão secreta por cafés coados e trilhas na natureza. Topa decifrar o algoritmo do amor comigo?",
        imageRes = R.drawable.b
    ),
    MatchProfile(
        id = 3,
        name = "Beatriz, 22",
        description = "Designer UX com a missão de tornar a vida mais fácil, uma interface de cada vez. Adoro museus, shows e encontrar beleza nas pequenas coisas.",
        imageRes = R.drawable.c
    ),
    MatchProfile(
        id = 4,
        name = "Daniel, 31",
        description = "Capturando a alma do mundo com minha câmera. Entre uma viagem e outra, estou sempre procurando um bom papo, uma risada e uma história para contar.",
        imageRes = R.drawable.d
    ),
    MatchProfile(
        id = 5,
        name = "Julia, 26",
        description = "Produtora musical nas horas vagas, busco uma sintonia perfeita. Se a vida fosse uma música, qual seria sua melodia favorita?",
        imageRes = R.drawable.e
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, usuario: String) {
    var searchQuery by remember { mutableStateOf("") }

    val filteredList = sampleProfiles.filter {
        it.name.contains(searchQuery, ignoreCase = true) ||
                it.description.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Descobrir") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Buscar perfil...") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                colors = ButtonDefaults.buttonColors(Color.Magenta),
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
                items(filteredList) { profile ->
                    ProfileCard(profile = profile)
                }
            }
        }
    }
}

@Composable
fun ProfileCard(profile: MatchProfile) {
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
                painter = painterResource(id = profile.imageRes),
                contentDescription = profile.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = profile.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = profile.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
                )
                Button(
                    onClick = {
                        Toast.makeText(context, "Match com ${profile.name}!", Toast.LENGTH_SHORT)
                            .show()
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("❤️")
                }
            }
        }
    }
}