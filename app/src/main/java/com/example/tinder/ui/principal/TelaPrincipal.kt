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
import com.example.tinder.ui.principal.PrincipalViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect

@Composable
fun TelaPrincipal(navController: NavController, usuario: String) {
    val viewModel: PrincipalViewModel = viewModel(factory = PrincipalViewModel.Factory)
    val uiState by viewModel.uiState.collectAsState()
    val perfilAtual = uiState.perfilAtual
    val context = LocalContext.current
    LaunchedEffect(uiState.mensagemFeedback) {
        if (uiState.mensagemFeedback != null) {
            Toast.makeText(context, uiState.mensagemFeedback, Toast.LENGTH_SHORT).show()
            viewModel.onMensagemMostrada() // Limpa o evento
        }
    }

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

        if (perfilAtual != null) {
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
                        viewModel.onVoltarPerfil()
                        Toast.makeText(
                            context,
                            "Match cancelado com ${perfilAtual.nome}!",
                            Toast.LENGTH_SHORT
                        ).show()
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
                        viewModel.onProximoPerfil(match = true)
                        Toast.makeText(
                            context,
                            "Match com ${perfilAtual.nome}!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                ) {
                    Text("\uD83E\uDD0D")
                }
            }
        }
        else {
                Text("Nenhum perfil disponível no momento.")
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