package com.interview.codingassignment.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.interview.codingassignment.domain.model.MatchingUserRequest
import com.interview.codingassignment.domain.utils.RequestStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface ApplicationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users : List<MatchingUserRequest>)

    @Query("SELECT * FROM matching_user_request")
    fun getUsers() : Flow<List<MatchingUserRequest>>

    @Query("DELETE FROM matching_user_request")
    suspend fun deleteUsers()

    @Query("UPDATE matching_user_request SET status=:status WHERE email =:email")
    suspend fun updateRequestStatus(status: RequestStatus,email : String)
}