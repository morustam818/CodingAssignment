package com.interview.codingassignment.data.data_source.remote.dto

import com.interview.codingassignment.domain.model.MatchingUserRequest

data class UserDto(
    val cell: String,
    val dob: Dob,
    val email: String,
    val gender: String,
    val id: Id,
    val location: Location,
    val login: Login,
    val name: Name,
    val nat: String,
    val phone: String,
    val picture: Picture,
    val registered: Registered
)
fun UserDto.toMatchingUserRequest() : MatchingUserRequest {
    return MatchingUserRequest(
        email = email,
        name = name.getName(),
        imageUrl = picture.large,
        age = dob.age,
        address = location.getAddress()
    )
}