package com.surivalcoding.memoapp.data.repository

import com.surivalcoding.memoapp.data.local.MemoDao
import com.surivalcoding.memoapp.data.model.Memo

class DbMemoRepositoryImpl(
    private val dao: MemoDao
): MemoRepository {

    override suspend fun insert(memo: Memo) {
        dao.insert(memo)
    }

    override suspend fun update(memo: Memo) {
        dao.update(memo)
    }

    override suspend fun delete(memo: Memo) {
        dao.delete(memo)
    }

    override suspend fun findAll(): List<Memo> {
        return dao.findAll()
    }
}