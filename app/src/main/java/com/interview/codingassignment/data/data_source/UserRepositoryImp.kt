package com.interview.codingassignment.data.data_source

import androidx.room.withTransaction
import com.interview.codingassignment.data.data_source.local.ApplicationDao
import com.interview.codingassignment.data.data_source.local.ApplicationDatabase
import com.interview.codingassignment.data.data_source.networkBoundResource
import com.interview.codingassignment.data.data_source.remote.UserApiService
import com.interview.codingassignment.domain.model.User
import com.interview.codingassignment.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val userApiService: UserApiService,
    private val db : ApplicationDatabase
) : UserRepository {

    override fun getUsers() = networkBoundResource(
        query = {
            db.applicationDao.getUsers()
        },
        fetch = {
           userApiService.fetchUsers()
        },
        saveFetchResult = {
            db.withTransaction {
                with(db.applicationDao){
                    deleteUsers()
                    insertUsers(it)
                }
            }
        }
    )
}