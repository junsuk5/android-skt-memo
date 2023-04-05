package com.surivalcoding.memoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Memo(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val content: String,
)