package com.interview.codingassignment.domain.use_case

import com.interview.codingassignment.domain.repository.UserRepository
import com.interview.codingassignment.domain.utils.RequestStatus
import javax.inject.Inject

class UpdateRequestStatus @Inject constructor(
    private val userRepository : UserRepository
) {
    suspend operator fun invoke(status : RequestStatus,email : String){
        userRepository.updateRequestStatus(status,email)
    }
}