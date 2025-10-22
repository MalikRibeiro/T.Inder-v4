package com.example.tinder.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tinder.data.repository.UsuarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class LoginUiState(
    val usuarioInput: String = "",
    val senhaInput: String = "",
    val carregando: Boolean = false,
    val error: String? = null,
    val loginSucesso: Boolean = false,
    val nomeUsuarioLogado: String? = null
)

class LoginViewModel(
    private val repository: UsuarioRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onUsuarioInputChange(novoUsuario: String) {
        _uiState.update { it.copy(usuarioInput = novoUsuario, error = null) }
    }

    fun onSenhaInputChange(novaSenha: String) {
        _uiState.update { it.copy(senhaInput = novaSenha, error = null) }
    }

    fun onLoginClick() {
        if (_uiState.value.carregando) return

        val state = _uiState.value
        if (state.usuarioInput.isBlank() || state.senhaInput.isBlank()) {
            _uiState.update { it.copy(error = "Usuário e senha não podem estar vazios.") }
            return
        }

        _uiState.update { it.copy(carregando = true, error = null) }

        viewModelScope.launch {
            try {
                val usuarioEncontrado = withContext(Dispatchers.IO) {
                    repository.buscarUsuarioPorNomeESenha(state.usuarioInput, state.senhaInput)
                }

                if (usuarioEncontrado != null) {
                    _uiState.update {
                        it.copy(
                            carregando = false, // <-- 3. DESATIVA O LOADING
                            loginSucesso = true,
                            nomeUsuarioLogado = usuarioEncontrado.nome
                        )
                    }
                } else {
                    _uiState.update { it.copy(carregando = false, error = "Usuário ou senha inválidos!") } // <-- 3. DESATIVA O LOADING
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(carregando = false, error = "Erro ao tentar fazer login: ${e.message}") } // <-- 3. DESATIVA O LOADING
            }
        }
    }

    fun onLoginNavegado() {
        _uiState.update { it.copy(loginSucesso = false, nomeUsuarioLogado = null) }
    }

    fun onErrorMostrado() {
        _uiState.update { it.copy(error = null) }
    }

    companion object {
        fun Factory(repository: UsuarioRepository): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                LoginViewModel(
                    repository = repository
                )
            }
        }
    }
}