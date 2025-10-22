package com.example.tinder.data.repository

import com.example.tinder.data.local.Usuario
import com.example.tinder.data.local.UsuarioDAO
import kotlinx.coroutines.flow.Flow

class UsuarioRepository(private val usuarioDAO: UsuarioDAO) {

    fun buscarTodosUsuarios(): Flow<List<Usuario>> = usuarioDAO.buscarTodos()

    fun buscarUsuarioPorNomeFlow(nome: String): Flow<Usuario?> = usuarioDAO.buscarPorNomeFlow(nome)

    suspend fun buscarUsuarioPorNome(nome: String): Usuario? = usuarioDAO.buscarPorNome(nome)

    suspend fun buscarUsuarioPorNomeESenha(nome: String, senha: String): Usuario? {
        return usuarioDAO.buscarPorNomeESenha(nome, senha)
    }

    suspend fun inserirUsuario(usuario: Usuario) {
        usuarioDAO.inserir(usuario)
    }

    suspend fun atualizarUsuario(usuario: Usuario) {
        usuarioDAO.atualizar(usuario)
    }

    suspend fun deletarUsuario(id: Int) {
        usuarioDAO.deletar(id)
    }
}