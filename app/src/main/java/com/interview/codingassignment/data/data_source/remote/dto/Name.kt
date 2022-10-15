package com.interview.codingassignment.data.data_source.remote.dto

data class Name(
    val first: String,
    val last: String,
    val title: String
){
    fun getName() : String {
        return "$title $first $last"
    }
}