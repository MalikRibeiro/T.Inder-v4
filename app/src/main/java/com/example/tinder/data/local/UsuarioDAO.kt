package com.example.tinder.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDAO {

    @Insert
    suspend fun inserir(usuario: Usuario)

    @Query("SELECT * FROM usuario ORDER BY nome ASC")
    fun buscarTodos(): Flow<List<Usuario>>

    @Query("DELETE FROM usuario WHERE id = :id")
    suspend fun deletar(id: Int)

    @Update
    suspend fun atualizar(usuario: Usuario)

    @Query("SELECT * FROM usuario WHERE nome = :nome AND senha = :senha LIMIT 1")
    suspend fun buscarPorNomeESenha(nome: String, senha: String): Usuario?

   @Query("SELECT * FROM usuario WHERE nome = :nome LIMIT 1")
    fun buscarPorNomeFlow(nome: String): Flow<Usuario?>
    @Query("SELECT * FROM usuario WHERE nome = :nome LIMIT 1")
    suspend fun buscarPorNome(nome: String): Usuario?
}