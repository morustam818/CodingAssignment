package com.interview.codingassignment.common

sealed class Resource<T>(
    val data : T? = null,
    val message : String? = null
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Failed<T>(data: T?,message: String?) : Resource<T>(data,message)
    class Loading<T>(data: T?) : Resource<T>()
}