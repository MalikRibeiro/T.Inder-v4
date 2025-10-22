package com.example.tinder.ui.principal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tinder.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class PerfilPrincipal(
    val nome: String,
    val fotoResId: Int
)

data class PrincipalUiState(
    val listaPerfis: List<PerfilPrincipal> = emptyList(),
    val indiceAtual: Int = 0,
    val mensagemFeedback: String? = null
) {
    val perfilAtual: PerfilPrincipal?
        get() = listaPerfis.getOrNull(indiceAtual)
}

class PrincipalViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PrincipalUiState())
    val uiState: StateFlow<PrincipalUiState> = _uiState.asStateFlow()

    init {
        carregarPerfisIniciais()
    }

    private fun carregarPerfisIniciais() {
        val perfis = listOf(
            PerfilPrincipal("Silvo, 60", R.drawable.h),
            PerfilPrincipal("Ludmila, 23", R.drawable.e),
            PerfilPrincipal("Neymar, 18", R.drawable.f),
            PerfilPrincipal("Careca, 32", R.drawable.g),
            PerfilPrincipal("Ana, 24", R.drawable.a),
            PerfilPrincipal("Rosana, 29", R.drawable.b),
            PerfilPrincipal("Beatriz, 22", R.drawable.c),
            PerfilPrincipal("Roberta, 31", R.drawable.d)
        )
        _uiState.update { it.copy(listaPerfis = perfis, indiceAtual = 0) }
    }

    fun onProximoPerfil(match: Boolean) {
        val state = _uiState.value
        if (state.listaPerfis.isEmpty()) return

        val nomePerfilAtual = state.perfilAtual?.nome ?: "Alguém"
        val feedback = if (match) "Match com $nomePerfilAtual!" else "Match cancelado com $nomePerfilAtual!"

        val proximoIndice = (state.indiceAtual + 1) % state.listaPerfis.size
        _uiState.update {
            it.copy(
                indiceAtual = proximoIndice,
                mensagemFeedback = feedback
            )
        }
    }

    fun onVoltarPerfil() {
        val state = _uiState.value
        if (state.listaPerfis.isEmpty()) return

        val nomePerfilAtual = state.perfilAtual?.nome ?: "Alguém"
        val feedback = "Match cancelado com $nomePerfilAtual!"

        val indiceAnterior = (state.indiceAtual - 1 + state.listaPerfis.size) % state.listaPerfis.size
        _uiState.update {
            it.copy(
                indiceAtual = indiceAnterior,
                mensagemFeedback = feedback
            )
        }
    }


    fun onMensagemMostrada() {
        _uiState.update { it.copy(mensagemFeedback = null) }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                PrincipalViewModel()
            }
        }
    }
}