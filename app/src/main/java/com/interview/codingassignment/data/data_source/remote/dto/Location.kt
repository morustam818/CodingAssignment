package com.interview.codingassignment.data.data_source.remote.dto

data class Location(
    val city: String,
    val coordinates: Coordinates,
    val country: String,
    val postcode: String,
    val state: String,
    val street: Street,
    val timezone: Timezone
){
    fun getAddress() : String {
        return "$city, $state, $country"
    }
}