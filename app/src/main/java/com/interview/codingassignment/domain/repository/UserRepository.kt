package com.interview.codingassignment.domain.repository

import com.interview.codingassignment.common.Resource
import com.interview.codingassignment.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
//    suspend fun insertUsers(user : List<User>)
//    suspend fun fetchUsers() : List<User>
    fun getUsers() : Flow<Resource<List<User>>>
//    suspend fun deleteUsers()
}