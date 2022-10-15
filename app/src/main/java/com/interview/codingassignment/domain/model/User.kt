package com.interview.codingassignment.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = false)
    val name : String,
    val imageUrl : String
)
