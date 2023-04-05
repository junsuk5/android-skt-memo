package com.surivalcoding.memoapp.data.repository

import com.surivalcoding.memoapp.data.model.Memo

interface MemoRepository {
    suspend fun insert(memo: Memo)
    suspend fun update(memo: Memo)
    suspend fun delete(memo: Memo)
    suspend fun findAll(): List<Memo>
}