package com.example.tinder

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinder.ui.theme.TinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TinderTheme {

                var telaAtual by remember { mutableStateOf(1) }
                when (telaAtual) {
                    1 -> TelaLogin(onLoginClick = { telaAtual = 2 })

                    2 -> TelaPrincipal(
                        onMatchesClick = { telaAtual = 3 }
                    )
                    3 -> TelaMatches(
                        onBackClick = { telaAtual = 2 }
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TelaLogin(onLoginClick: () -> Unit = {}) {
    var usuario by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "T.Inder",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        TextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = { Text("Usuário") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        Button(
            onClick = onLoginClick,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text("Entrar")
        }
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo do app",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(top = 32.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun TelaPrincipal(onMatchesClick: () -> Unit) {

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
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo", modifier = Modifier.size(40.dp))
            Text("T.inder", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Button(onClick = onMatchesClick) {
                Text("Matches")
            }
        }

        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "Foto Perfil",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(16.dp))
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { /* Ação "Não" */ }) {
                Text("X")
            }
            Button(onClick = { /* Ação "Sim" */ }) {
                Text("❤️")
            }
        }
    }
}

@Composable
fun TelaMatches(onBackClick: () -> Unit) {
    val matches = listOf("Paula Fernandes", "Ana Castela", "Giulia Be", "Marilia Mendonça", "Simone Mendes")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Matches", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Button(onClick = onBackClick) {
                Text("Voltar")
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            matches.forEach { nome ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Foto de perfil",
                        modifier = Modifier.size(50.dp),
                        tint = Color.Gray
                    )

                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = nome,
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}