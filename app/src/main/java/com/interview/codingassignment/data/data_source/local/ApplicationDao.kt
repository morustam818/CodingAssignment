package com.interview.codingassignment.data.data_source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.interview.codingassignment.domain.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface ApplicationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users : List<User>)

    @Query("SELECT * FROM user")
    fun getUsers() : Flow<List<User>>

    @Query("DELETE FROM user")
    suspend fun deleteUsers()
}