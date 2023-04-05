package com.surivalcoding.memoapp.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.surivalcoding.memoapp.data.local.AppDatabase
import com.surivalcoding.memoapp.data.local.MemoDao
import com.surivalcoding.memoapp.data.repository.DbMemoRepositoryImpl
import com.surivalcoding.memoapp.data.repository.MemoRepository
import com.surivalcoding.memoapp.data.repository.MemoryMemoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "memo"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMemoDao(db: AppDatabase): MemoDao {
        return db.memoDao()
    }

    @Provides
    @Singleton
    fun provideMemoRepository(dao: MemoDao) : MemoRepository {
//        return MemoryMemoRepositoryImpl()
        return DbMemoRepositoryImpl(dao)
    }
}