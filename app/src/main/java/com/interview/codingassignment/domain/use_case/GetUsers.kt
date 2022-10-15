package com.interview.codingassignment.domain.use_case

import com.interview.codingassignment.domain.repository.MatchingUserRepository
import javax.inject.Inject

class GetUsers @Inject constructor(
    private val matchingUserRepository: MatchingUserRepository
) {
    operator fun invoke() = matchingUserRepository.getUsers()
}