package com.example.tinder

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun TelaPrincipal(navController: NavController) {

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
            Text("T.inder", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)

            Button(
                colors = ButtonDefaults.buttonColors(Color.Magenta),
                onClick = { navController.navigate("tela_matches") })
            {
                Text(text = "Matches")
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
            Button(
                colors = ButtonDefaults.buttonColors(Color.Magenta),
                onClick = { /* Ação "Não" */ }) {
                Text("X")
            }
            Button(
                colors = ButtonDefaults.buttonColors(Color.Magenta),
                onClick = { /* Ação "Sim" */ }) {
                Text("❤️")
            }
        }
    }
}