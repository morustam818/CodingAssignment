package com.interview.codingassignment.data.data_source.remote.dto

import com.google.gson.annotations.SerializedName

data class MatchingRequestResult(
    val info: Info,
    @SerializedName("results")
    val users: List<UserDto>
)