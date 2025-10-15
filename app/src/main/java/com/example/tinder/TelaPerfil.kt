// malikribeiro/t.inder-v3/T.Inder-v3-b3fe78c748e6529a5171d07b29595c81105f0915/app/src/main/java/com/example/tinder/TelaPerfil.kt

package com.example.tinder

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tinder.ui.theme.TinderTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaPerfil(navController: NavController, usuario: String) {

    var bio by remember { mutableStateOf("") }
    var usuarioCompleto by remember { mutableStateOf<Usuario?>(null) }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val usuarioDAO = AppDatabase.getDatabase(context).usuarioDAO()

    LaunchedEffect(key1 = usuario) {
        scope.launch(Dispatchers.IO) {
            val usuarioEncontrado = usuarioDAO.buscarPorNome(usuario)
            if (usuarioEncontrado != null) {
                withContext(Dispatchers.Main) {
                    usuarioCompleto = usuarioEncontrado
                    bio = usuarioEncontrado.desc
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Meu Perfil") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Ícone de Perfil",
                modifier = Modifier.size(150.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = usuario,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = bio,
                onValueChange = { bio = it },
                label = { Text("Escreva sua bio") },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp),
                singleLine = false
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (usuarioCompleto != null) {
                        scope.launch(Dispatchers.IO) {
                            val usuarioAtualizado = usuarioCompleto!!.copy(desc = bio)
                            usuarioDAO.atualizar(usuarioAtualizado)
                            withContext(Dispatchers.Main) {
                                Toast.makeText(context, "Bio salva com sucesso!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            ) {
                Text(text = "Salvar Bio")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                colors = ButtonDefaults.buttonColors(Laranja),
                onClick = {
                    if (usuarioCompleto != null) {
                        scope.launch(Dispatchers.IO) {
                            val usuarioAtualizado = usuarioCompleto!!.copy(desc = bio)
                            usuarioDAO.atualizar(usuarioAtualizado)
                        }
                    }
                    navController.navigate("tela_principal/$usuario")
                })
            {
                Text(text = "Tela Principal")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaPerfilPreview() {

    TinderTheme {

        val navController = rememberNavController()

        TelaPerfil(navController = navController, usuario = "Joao")

    }
}