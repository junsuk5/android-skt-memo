package com.surivalcoding.memoapp.data.repository

import com.surivalcoding.memoapp.data.model.Memo

class MemoryMemoRepositoryImpl : MemoRepository {
    private var memos = listOf(
        Memo(0, "test"),
        Memo(1, "test1"),
        Memo(2, "test2"),
    )

    override suspend fun insert(memo: Memo) {
        memos = memos.toMutableList().apply {
            add(memo)
        }
    }

    override suspend fun update(memo: Memo) {
        memos = memos.map {
            if (it.id == memo.id) {
                memo
            } else {
                it
            }
        }
    }

    override suspend fun delete(memo: Memo) {
        memos = memos.filter { it.id != memo.id }
    }

    override suspend fun findAll(): List<Memo> {
        return memos
    }

}