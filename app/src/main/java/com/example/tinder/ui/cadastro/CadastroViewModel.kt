package com.example.tinder.ui.cadastro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tinder.data.local.Usuario
import com.example.tinder.data.repository.UsuarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CadastroUiState(
    val nomeInput: String = "",
    val senhaInput: String = "",
    val descInput: String = "Fale um pouco sobre você.",
    val isLoading: Boolean = false,
    val error: String? = null,
    val cadastroSucesso: Boolean = false
)

class CadastroViewModel(
    private val repository: UsuarioRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CadastroUiState())
    val uiState: StateFlow<CadastroUiState> = _uiState.asStateFlow()

    fun onNomeChange(novoNome: String) {
        _uiState.update { it.copy(nomeInput = novoNome, error = null) }
    }

    fun onSenhaChange(novaSenha: String) {
        _uiState.update { it.copy(senhaInput = novaSenha, error = null) }
    }

    fun onDescChange(novaDesc: String) {
        _uiState.update { it.copy(descInput = novaDesc, error = null) }
    }

    fun onCadastrarClick() {
        val state = _uiState.value
        if (state.nomeInput.isBlank() || state.senhaInput.isBlank() || state.descInput.isBlank()) {
            _uiState.update { it.copy(error = "Por favor, preencha todos os campos.") }
            return
        }

        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val usuarioExistente = repository.buscarUsuarioPorNome(state.nomeInput)
                if (usuarioExistente != null) {
                    _uiState.update { it.copy(isLoading = false, error = "Nome de usuário já existe.") }
                    return@launch // Sai da coroutine
                }

                val novoUsuario = Usuario(
                    nome = state.nomeInput,
                    senha = state.senhaInput,
                    desc = state.descInput
                )
                repository.inserirUsuario(novoUsuario)

                _uiState.update { it.copy(isLoading = false, cadastroSucesso = true) }

            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = "Erro ao cadastrar: ${e.message}") }
            }
        }
    }

    fun onCadastroNavegado() {
        _uiState.update { it.copy(cadastroSucesso = false) }
    }

    companion object {
        fun Factory(repository: UsuarioRepository): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                CadastroViewModel(
                    repository = repository
                )
            }
        }
    }
}