package com.surivalcoding.memoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Memo(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    val content: String,
)