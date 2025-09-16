package com.example.tinder

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class Perfil(val nome: String, val fotoResId: Int)

private val listaDePerfis = listOf(
    Perfil("Silvo, 60", R.drawable.h),
    Perfil("Ludmila, 23", R. drawable.e),
    Perfil("Neymar, 18", R.drawable.f),
    Perfil("Careca, 32", R.drawable.g)
)

@Composable
fun TelaPrincipal(navController: NavController) {
    var indiceAtual by remember { mutableStateOf(0) }
    val perfilAtual = listaDePerfis[indiceAtual]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(40.dp)
            )

            Button(
                colors = ButtonDefaults.buttonColors(Color.Magenta),
                onClick = { navController.navigate("tela_matches") })
            {
                Text(text = "Matches")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = perfilAtual.nome,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Image(
            painter = painterResource(id = perfilAtual.fotoResId),
            contentDescription = "Foto de ${perfilAtual.nome}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(Color.Magenta),
                onClick = {
                    indiceAtual = (indiceAtual + 1) % listaDePerfis.size
                }
            ) {
                Text("◀️")
            }
            Button(
                colors = ButtonDefaults.buttonColors(Color.Magenta),
                onClick = {
                    indiceAtual = (indiceAtual + 1) % listaDePerfis.size
                }
            ) {
                Text("❤️")
            }
        }
    }
}