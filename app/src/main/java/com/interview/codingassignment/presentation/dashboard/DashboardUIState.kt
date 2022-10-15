package com.interview.codingassignment.presentation.dashboard

import com.interview.codingassignment.domain.model.User

data class DashboardUIState(
    val users : List<User>? = emptyList(),
    val isLoading : Boolean = false,
    val errorMessage : String? = null
)
