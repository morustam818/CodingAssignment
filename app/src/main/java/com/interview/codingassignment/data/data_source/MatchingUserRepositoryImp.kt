package com.interview.codingassignment.data.data_source

import androidx.room.withTransaction
import com.interview.codingassignment.data.data_source.local.ApplicationDatabase
import com.interview.codingassignment.data.data_source.remote.MatchingUserApiService
import com.interview.codingassignment.data.data_source.remote.dto.toMatchingUserRequest
import com.interview.codingassignment.domain.repository.MatchingUserRepository
import com.interview.codingassignment.domain.utils.RequestStatus
import javax.inject.Inject

class MatchingUserRepositoryImp @Inject constructor(
    private val matchingUserApiService: MatchingUserApiService,
    private val db : ApplicationDatabase
) : MatchingUserRepository {

    override fun getUsers() = networkBoundResource(
        query = {
            db.applicationDao.getUsers()
        },
        fetch = {
           matchingUserApiService.fetchUsers()
        },
        saveFetchResult = {
            db.withTransaction {
                with(db.applicationDao){
                    deleteUsers()
                    it.body()?.let { result ->
                        insertUsers(result.users.map { it.toMatchingUserRequest() })
                    }
                }
            }
        }
    )

    override suspend fun updateRequestStatus(status: RequestStatus, email: String) {
        db.applicationDao.updateRequestStatus(status,email)
    }
}