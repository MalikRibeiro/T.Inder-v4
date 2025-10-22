package com.example.tinder.ui.perfil

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tinder.data.local.Usuario
import com.example.tinder.data.repository.UsuarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class PerfilUiState(
    val usuario: Usuario? = null,
    val bio: String = "",
    val carregando: Boolean = true,
    val error: String? = null,
    val perfilExcluido: Boolean = false,
    val nomeUsuarioArg: String = ""
)

class PerfilViewModel(
    private val repository: UsuarioRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(PerfilUiState())
    val uiState: StateFlow<PerfilUiState> = _uiState.asStateFlow()

    private val nomeUsuario: String = checkNotNull(savedStateHandle[USUARIO_SAVED_STATE_KEY])

    init {
        _uiState.update { it.copy(nomeUsuarioArg = nomeUsuario) }
        carregarDadosUsuario()
    }

    private fun carregarDadosUsuario() {
        viewModelScope.launch {
            repository.buscarUsuarioPorNomeFlow(nomeUsuario)
                .onStart { _uiState.update { it.copy(carregando = true, error = null) } }
                .catch { e ->
                    _uiState.update { it.copy(carregando = false, error = "Erro ao carregar perfil: ${e.message}") }
                }
                .collect { usuarioEncontrado ->
                    if (usuarioEncontrado != null) {
                        _uiState.update { currentState ->
                            currentState.copy(
                                usuario = usuarioEncontrado,
                                bio = if (currentState.bio.isEmpty() || currentState.bio == currentState.usuario?.desc) {
                                    usuarioEncontrado.desc
                                } else {
                                    currentState.bio
                                },
                                carregando = false,
                                error = null,
                                perfilExcluido = false
                            )
                        }
                    } else {
                        _uiState.update {
                            it.copy(
                                carregando = false,
                                usuario = null, // Limpa o usuário
                                error = it.error ?: "Usuário '$nomeUsuario' não encontrado.",
                            )
                        }
                    }
                }
        }
    }
    fun onBioChange(novaBio: String) {
        _uiState.update { it.copy(bio = novaBio, error = null) }
    }

    fun onSalvarBio() {
        val state = _uiState.value
        if (state.usuario != null && state.bio != state.usuario.desc) {
            _uiState.update { it.copy(carregando = true) }
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val usuarioAtualizado = state.usuario.copy(desc = state.bio)
                    repository.atualizarUsuario(usuarioAtualizado)
                    _uiState.update { it.copy(carregando = false) }

                } catch (e: Exception) {
                    _uiState.update { it.copy(carregando = false, error = "Erro ao salvar bio: ${e.message}") }
                }
            }
        }
    }

    fun onExcluirPerfil() {
        val state = _uiState.value
        if (state.usuario != null) {
            _uiState.update { it.copy(carregando = true) }
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    repository.deletarUsuario(state.usuario.id)
                    _uiState.update { it.copy(carregando = false, perfilExcluido = true, usuario = null) }
                } catch (e: Exception) {
                    _uiState.update { it.copy(carregando = false, error = "Erro ao excluir perfil: ${e.message}") }
                }
            }
        }
    }

    fun onPerfilExcluidoNavegado() {
        _uiState.update { it.copy(perfilExcluido = false) }
    }


    companion object {
        const val USUARIO_SAVED_STATE_KEY = "usuario"

        fun Factory(repository: UsuarioRepository): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                PerfilViewModel(
                    repository = repository,
                    savedStateHandle = savedStateHandle
                )
            }
        }
    }
}