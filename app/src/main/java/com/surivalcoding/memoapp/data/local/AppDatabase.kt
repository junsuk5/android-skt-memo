package com.surivalcoding.memoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.surivalcoding.memoapp.data.model.Memo

@Database(entities = [Memo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memoDao(): MemoDao
}