package com.surivalcoding.memoapp.data.local

import androidx.room.*
import com.surivalcoding.memoapp.data.model.Memo

@Dao
interface MemoDao {
    @Insert
    suspend fun insert(memo: Memo)

    @Update
    suspend fun update(memo: Memo)

    @Delete
    suspend fun delete(memo: Memo)

    @Query("SELECT * FROM Memo")
    suspend fun findAll(): List<Memo>

    @Query("SELECT * FROM Memo WHERE id = :id")
    suspend fun findById(id: Int): Memo?
}