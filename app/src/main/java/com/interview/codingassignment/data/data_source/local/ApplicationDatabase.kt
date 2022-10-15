package com.interview.codingassignment.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.interview.codingassignment.domain.model.User
import com.interview.codingassignment.domain.utils.UserListTypeConverter

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(UserListTypeConverter::class)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract val applicationDao : ApplicationDao
}