// Crie este novo arquivo: TelaCadastro.kt

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
import com.example.tinder.data.local.Usuario
import com.example.tinder.ui.login.Laranja
import com.example.tinder.ui.theme.TinderTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaCadastro(navController: NavController) {

    var nome by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("Fale um pouco sobre você.") }

    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val usuarioDAO = db.usuarioDAO()

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
            value = nome,
            label = { Text(text = "Nome de Usuário") },
            onValueChange = { nome = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = senha,
            label = { Text(text = "Senha") },
            onValueChange = { senha = it },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = desc,
            label = { Text(text = "Sua bio inicial") },
            onValueChange = { desc = it },
            modifier = Modifier.fillMaxWidth().height(100.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (nome.isNotBlank() && senha.isNotBlank() && desc.isNotBlank()) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val novoUsuario = Usuario(nome = nome, senha = senha, desc = desc)
                        usuarioDAO.inserir(novoUsuario)
                    }
                    Toast.makeText(context, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                } else {
                    Toast.makeText(context, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
                }
            },
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