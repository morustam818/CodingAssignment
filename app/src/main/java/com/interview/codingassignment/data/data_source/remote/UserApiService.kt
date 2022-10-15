package com.interview.codingassignment.data.data_source.remote

import com.interview.codingassignment.common.Constants
import com.interview.codingassignment.domain.model.User
import retrofit2.http.GET

interface UserApiService {
    @GET(Constants.END_POINT)
    suspend fun fetchUsers() : List<User>
}