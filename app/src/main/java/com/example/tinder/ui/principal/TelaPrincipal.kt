package com.example.tinder.ui.principal

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.tinder.R
import com.example.tinder.ui.login.Laranja
import com.example.tinder.ui.theme.TinderTheme

data class Perfil(val nome: String, val fotoResId: Int)

private val listaDePerfis = listOf(
    Perfil("Silvo, 60", R.drawable.h),
    Perfil("Ludmila, 23", R.drawable.e),
    Perfil("Neymar, 18", R.drawable.f),
    Perfil("Careca, 32", R.drawable.g)
)

@Composable
fun TelaPrincipal(navController: NavController, usuario: String) {
    var indiceAtual by remember { mutableStateOf(0) }
    val perfilAtual = listaDePerfis[indiceAtual]
    val context = LocalContext.current

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
                colors = ButtonDefaults.buttonColors(Laranja),
                onClick = { navController.navigate("tela_matches/$usuario") }
            ) {
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
                colors = ButtonDefaults.buttonColors(Laranja),
                onClick = {
                    Toast.makeText(context, "Match cancelado com ${perfilAtual.nome}!", Toast.LENGTH_SHORT).show()
                    indiceAtual = (indiceAtual + listaDePerfis.size - 1) % listaDePerfis.size
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Voltar",
                    modifier = Modifier.size(24.dp),
                    tint = Color.White
                )
            }
            Button(
                colors = ButtonDefaults.buttonColors(Laranja),
                onClick = {
                    Toast.makeText(context, "Match com ${perfilAtual.nome}!", Toast.LENGTH_SHORT).show()
                    indiceAtual = (indiceAtual + 1) % listaDePerfis.size
                }
            ) {
                Text("\uD83E\uDD0D")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaPrincipalPreview() {
    TinderTheme {
        val navController = rememberNavController()
        TelaPrincipal(navController = navController, usuario = "Joao")
    }
}