package com.example.tinder

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Update

@Dao
interface UsuarioDAO {

    @Insert
    suspend fun inserir(usuario: Usuario)

    @Query("SELECT * FROM usuario")
    suspend fun buscarTodos(): List<Usuario>

    @Delete
    suspend fun deletar(usuario: Usuario)

    @Update
    suspend fun atualizar(usuario: Usuario)

    @Query("SELECT * FROM usuario WHERE nome = :nome AND senha = :senha LIMIT 1")
    suspend fun buscarPorNomeESenha(nome: String, senha: String): Usuario?

    @Query("SELECT * FROM usuario WHERE nome = :nome LIMIT 1")
    suspend fun buscarPorNome(nome: String): Usuario?
}