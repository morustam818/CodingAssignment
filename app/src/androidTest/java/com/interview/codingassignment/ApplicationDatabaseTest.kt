package com.interview.codingassignment

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.interview.codingassignment.data.data_source.local.ApplicationDao
import com.interview.codingassignment.data.data_source.local.ApplicationDatabase
import com.interview.codingassignment.domain.model.MatchingUserRequest
import com.interview.codingassignment.domain.utils.RequestStatus
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ApplicationDatabaseTest {

    private lateinit var db : ApplicationDatabase
    private lateinit var dao: ApplicationDao

    @Before
    fun setup(){
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ApplicationDatabase::class.java
        ).build()
        dao = db.applicationDao
    }

    @Test
    fun writeAndReadUserMatchingRequest(): Unit = runBlocking{
        val matchingUserRequest = MatchingUserRequest(
            "testing@gmail.com",
            "Testing",
            "testing.com",
            12,
            "Nearby"
        )
        dao.insertUsers(listOf(matchingUserRequest))
        val getMatchingUserRequest = dao.getUsers()
        assertThat(getMatchingUserRequest.first().contains(matchingUserRequest))
    }

    @Test
    fun writeAndReadUserMatchingRequestStatus(): Unit = runBlocking{
        val matchingUserRequest = MatchingUserRequest(
            "testing@gmail.com",
            "Testing",
            "testing.com",
            12,
            "Nearby"
        )
        dao.insertUsers(listOf(matchingUserRequest))
        dao.updateRequestStatus(RequestStatus.ACCEPTED,matchingUserRequest.email)
        val getMatchingUserRequest = dao.getUsers()
        assertThat(getMatchingUserRequest.first()[0].status).isEqualTo(RequestStatus.ACCEPTED)
    }

    @Test
    fun writeAndDeleteUserMatchingRequest(): Unit = runBlocking{
        val matchingUserRequest = MatchingUserRequest(
            "testing@gmail.com",
            "Testing",
            "testing.com",
            12,
            "Nearby"
        )
        dao.insertUsers(listOf(matchingUserRequest))
        dao.deleteUsers()
        val getMatchingUserRequest = dao.getUsers()
        assertThat(getMatchingUserRequest.first().size).isEqualTo(0)
    }


    @After
    fun close(){
        db.close()
    }
}