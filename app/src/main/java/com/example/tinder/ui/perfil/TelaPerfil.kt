package com.example.tinder.ui.perfil

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tinder.ui.login.Laranja
import com.example.tinder.data.local.AppDatabase
import com.example.tinder.ui.theme.TinderTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect
import com.example.tinder.data.repository.UsuarioRepository
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Delete
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.filled.Search

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaPerfil(navController: NavController, usuario: String) {

    val context = LocalContext.current
    val repository = remember {
        UsuarioRepository(AppDatabase.getDatabase(context).usuarioDAO())
    }
    val viewModel: PerfilViewModel = viewModel(
        factory = PerfilViewModel.Factory(repository)
    )

    val uiState by viewModel.uiState.collectAsState()

    if (uiState.perfilExcluido) {
        LaunchedEffect(Unit) {
            Toast.makeText(context, "Perfil excluído.", Toast.LENGTH_SHORT).show()
            viewModel.onPerfilExcluidoNavegado()
            navController.navigate("tela_login") {
                popUpTo(0)
            }
        }
    }

    LaunchedEffect(key1 = uiState.bioSalvaComSucesso) {
        if (uiState.bioSalvaComSucesso) {
            Toast.makeText(context, "Bio salva com sucesso!", Toast.LENGTH_SHORT).show()
            viewModel.onMensagemBioSalvaMostrada()
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
                },
                actions = {
                    IconButton(onClick = {
                        uiState.usuario?.let { user ->
                            navController.navigate("tela_principal/${user.nome}") {
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Tela Principal"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        if (uiState.carregando && uiState.usuario == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            }
        } else if (uiState.usuario != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            )
            {
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
                    value = uiState.bio,
                    onValueChange = { viewModel.onBioChange(it) },
                    label = { Text("Escreva sua bio") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 100.dp),
                    singleLine = false
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { viewModel.onSalvarBio() },
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Salvar Bio"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Salvar Bio")
                    }
                }

                Spacer(modifier = Modifier.height(26.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    onClick = { viewModel.onExcluirPerfil() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Excluir Perfil"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Excluir Perfil")
                    }
                }
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