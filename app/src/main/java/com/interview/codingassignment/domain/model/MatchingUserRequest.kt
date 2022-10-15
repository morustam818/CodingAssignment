package com.interview.codingassignment.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.interview.codingassignment.domain.utils.RequestStatus

@Entity(tableName = "matching_user_request")
data class MatchingUserRequest(
    @PrimaryKey(autoGenerate = false)
    val email : String,
    val name : String,
    val imageUrl : String,
    val age : Int,
    val address : String,
    val status : RequestStatus? = null
)
