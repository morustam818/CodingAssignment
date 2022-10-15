package com.interview.codingassignment.domain.use_case

import com.interview.codingassignment.domain.repository.UserRepository
import javax.inject.Inject

class GetUsers @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke() = userRepository.getUsers()
}