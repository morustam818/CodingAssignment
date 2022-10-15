package com.interview.codingassignment.presentation.dashboard

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.codingassignment.common.Resource
import com.interview.codingassignment.domain.use_case.GetUsers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getUsers: GetUsers
) : ViewModel() {
    var uiState = mutableStateOf(DashboardUIState())
        private set

    fun getUsers() = viewModelScope.launch(Dispatchers.IO){
        getUsers.invoke().collect{
            when(it){
                is Resource.Loading -> {
                    it.data?.let { users ->
                        if (users.isEmpty()){
                            uiState.value = DashboardUIState(
                                users = null,
                                isLoading = true,
                                errorMessage = null
                            )
                        }
                    }
                }
                is Resource.Failed -> {
                    uiState.value = DashboardUIState(
                        users = null,
                        isLoading = false,
                        errorMessage = it.message
                    )
                }
                is Resource.Success ->{
                    uiState.value = DashboardUIState(
                        users = it.data,
                        isLoading = false,
                        errorMessage = null
                    )
                }
            }
        }
    }
}