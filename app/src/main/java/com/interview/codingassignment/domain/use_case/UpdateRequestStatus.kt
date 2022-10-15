package com.interview.codingassignment.domain.use_case

import com.interview.codingassignment.domain.repository.MatchingUserRepository
import com.interview.codingassignment.domain.utils.RequestStatus
import javax.inject.Inject

class UpdateRequestStatus @Inject constructor(
    private val matchingUserRepository : MatchingUserRepository
) {
    suspend operator fun invoke(status : RequestStatus,email : String){
        matchingUserRepository.updateRequestStatus(status,email)
    }
}