package com.interview.codingassignment.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.interview.codingassignment.domain.model.MatchingUserRequest
import com.interview.codingassignment.domain.utils.UserListTypeConverter

@Database(
    entities = [MatchingUserRequest::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(UserListTypeConverter::class)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract val applicationDao : ApplicationDao
}