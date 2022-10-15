package com.interview.codingassignment.data.data_source.remote

import com.interview.codingassignment.common.Constants
import com.interview.codingassignment.data.data_source.remote.dto.MatchingRequestResult
import retrofit2.Response
import retrofit2.http.GET

interface MatchingUserApiService {
    @GET(Constants.END_POINT)
    suspend fun fetchUsers() : Response<MatchingRequestResult>
}