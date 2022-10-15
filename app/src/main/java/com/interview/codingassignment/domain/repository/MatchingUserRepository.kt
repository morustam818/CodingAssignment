package com.interview.codingassignment.domain.repository

import com.interview.codingassignment.common.Resource
import com.interview.codingassignment.domain.model.MatchingUserRequest
import com.interview.codingassignment.domain.utils.RequestStatus
import kotlinx.coroutines.flow.Flow

interface MatchingUserRepository {
//    suspend fun insertUsers(user : List<MatchingUserRequest>)
//    suspend fun fetchUsers() : List<MatchingUserRequest>
    fun getUsers() : Flow<Resource<List<MatchingUserRequest>>>
    suspend fun updateRequestStatus(status: RequestStatus,email : String)
}