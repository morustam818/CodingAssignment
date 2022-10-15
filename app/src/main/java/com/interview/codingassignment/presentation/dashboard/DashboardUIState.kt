package com.interview.codingassignment.presentation.dashboard

import com.interview.codingassignment.domain.model.MatchingUserRequest

data class DashboardUIState(
    val users : List<MatchingUserRequest>? = emptyList(),
    val isLoading : Boolean = false,
    val errorMessage : String? = null
)
