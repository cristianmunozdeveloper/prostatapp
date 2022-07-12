package com.cristiansofthouse.testhistory.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TestHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val test: String,
    val score: String,
    val date: Long
)
