package com.example.tinder.ui.login

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tinder.R
import com.example.tinder.data.local.AppDatabase
import com.example.tinder.ui.theme.TinderTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect
import com.example.tinder.data.repository.UsuarioRepository

val Laranja = Color(0xFFFF5722)
val Preto = Color(0xFF000000)

class TelaLogin : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TinderTheme {
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaLogin(navController: NavController) {
    val context = LocalContext.current
    val repository = remember {
        UsuarioRepository(AppDatabase.getDatabase(context).usuarioDAO())
    }

    val viewModel: LoginViewModel = viewModel(
        factory = LoginViewModel.Factory(repository)
    )

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = uiState.loginSucesso) {
        if (uiState.loginSucesso) {
            val nomeUsuario = uiState.nomeUsuarioLogado ?: ""

            if (nomeUsuario.isNotBlank()) {
                navController.navigate("tela_perfil/$nomeUsuario")
                viewModel.onLoginNavegado()
            }
        }
    }

    LaunchedEffect(key1 = uiState.error) {
        if (uiState.error != null) {
            Toast.makeText(context, uiState.error, Toast.LENGTH_SHORT).show()
            viewModel.onErrorMostrado()
        }
    }

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
        OutlinedTextField(
            value = uiState.usuarioInput,
            onValueChange = { viewModel.onUsuarioInputChange(it) },
            label = { Text("Usuário") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Laranja,
                unfocusedBorderColor = Preto,
                cursorColor = Laranja,
                focusedLabelColor = Laranja,
                unfocusedLabelColor = Preto,
                textColor = Preto
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiState.senhaInput,
            onValueChange = { viewModel.onSenhaInputChange(it) },
            label = { Text("Senha") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Laranja,
                unfocusedBorderColor = Preto,
                cursorColor = Laranja,
                focusedLabelColor = Laranja,
                unfocusedLabelColor = Preto,
                textColor = Preto
            )
        )

        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { viewModel.onLoginClick() },
            enabled = !uiState.carregando,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Laranja,
                contentColor = Color.White
            )
        )
        {
            Text(text = "ENTRAR", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }


        //Spacer(modifier = Modifier.height(100.dp))

        TextButton(onClick = { navController.navigate("tela_cadastro") }) {
            Text(text = "Cadastre-se")
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


@Preview(showBackground = true)
@Composable
fun TelaLoginPreview() {
    val navController = rememberNavController()

    TinderTheme {
        TelaLogin(navController = navController)
    }
}