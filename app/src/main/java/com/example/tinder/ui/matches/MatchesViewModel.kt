package com.example.tinder.ui.matches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tinder.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class PerfilMatch(
    val id: Int,
    val nome: String,
    val descricao: String,
    val imageRes: Int,
)

data class MatchesUiState(
    val termoBusca: String = "",
    val todosMatches: List<PerfilMatch> = emptyList(),
    val matchesFiltrados: List<PerfilMatch> = emptyList()
)

class MatchesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MatchesUiState())
    val uiState: StateFlow<MatchesUiState> = _uiState.asStateFlow()

    init {
        carregarMatchesIniciais()
        filtrarMatches(_uiState.value.termoBusca)
    }

    private fun carregarMatchesIniciais() {
        val matches = listOf(
            PerfilMatch(
                id = 1,
                nome = "Ana, 24",
                descricao = "Desenvolvedora de apps por profissão...",
                imageRes = R.drawable.a
            ),
            PerfilMatch(
                id = 2,
                nome = "Rosana, 29",
                descricao = "Engenheira de Machine Learning...",
                imageRes = R.drawable.b
            ),
            PerfilMatch(
                id = 3,
                nome = "Beatriz, 22",
                descricao = "Designer UX com a missão...",
                imageRes = R.drawable.c
            ),
            PerfilMatch(
                id = 4,
                nome = "Roberta, 31",
                descricao = "Capturando a alma do mundo...",
                imageRes = R.drawable.d
            ),
            PerfilMatch(
                id = 5,
                nome = "Ludmila, 26",
                descricao = "Produtora musical nas horas vagas...",
                imageRes = R.drawable.e
            )
        )
        _uiState.update { it.copy(todosMatches = matches) }
    }

    fun onBuscaChange(novoTermo: String) {
        _uiState.update { it.copy(termoBusca = novoTermo) }
        filtrarMatches(novoTermo)
    }

    private fun filtrarMatches(termo: String) {
        val state = _uiState.value
        val filtrados = if (termo.isBlank()) {
            state.todosMatches
        } else {
            state.todosMatches.filter { perfil ->
                perfil.nome.contains(termo, ignoreCase = true) ||
                        perfil.descricao.contains(termo, ignoreCase = true)
            }
        }
        _uiState.update { it.copy(matchesFiltrados = filtrados) }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MatchesViewModel()
            }
        }
    }
}