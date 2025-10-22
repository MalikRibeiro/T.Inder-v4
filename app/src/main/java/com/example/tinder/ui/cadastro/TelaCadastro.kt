package com.example.tinder.ui.cadastro

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tinder.data.local.AppDatabase
import com.example.tinder.ui.login.Laranja
import com.example.tinder.ui.theme.TinderTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect
import com.example.tinder.data.repository.UsuarioRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaCadastro(navController: NavController) {

    val context = LocalContext.current
    val repository = remember {
        UsuarioRepository(AppDatabase.getDatabase(context).usuarioDAO())
    }
    val viewModel: CadastroViewModel = viewModel(
        factory = CadastroViewModel.Factory(repository)
    )

    val uiState by viewModel.uiState.collectAsState()

    if (uiState.cadastroSucesso) {
        LaunchedEffect(Unit) {
            Toast.makeText(context, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
            viewModel.onCadastroNavegado()
            navController.popBackStack()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Crie sua Conta",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        OutlinedTextField(
            value = uiState.nomeInput,
            label = { Text(text = "Nome de Usuário") },
            onValueChange = { viewModel.onNomeChange(it) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiState.senhaInput,
            label = { Text(text = "Senha") },
            onValueChange = { viewModel.onSenhaChange(it) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiState.descInput,
            label = { Text(text = "Sua bio inicial") },
            onValueChange = { viewModel.onDescChange(it) },
            modifier = Modifier.fillMaxWidth().height(100.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { viewModel.onCadastrarClick() },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Laranja)
        ) {
            Text(text = "CADASTRAR", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = { navController.popBackStack() }) {
            Text(text = "Já tem uma conta? Faça Login")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaCadastro() {
    val navController = rememberNavController()

    TinderTheme {
        TelaCadastro(navController = navController)
    }
}