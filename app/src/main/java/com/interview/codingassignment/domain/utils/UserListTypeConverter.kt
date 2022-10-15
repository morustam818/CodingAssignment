package com.interview.codingassignment.domain.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.interview.codingassignment.domain.model.User
import java.lang.reflect.Type

class UserListTypeConverter {
    @TypeConverter
    fun fromUserListToString(applications: List<User>) : String {
        return Gson().toJson(applications)
    }

    @TypeConverter
    fun fromStringToUserList(value : String) : List<User>{
        val listType : Type? = object : TypeToken<List<User>>(){}.type
        return Gson().fromJson(value,listType)
    }
}