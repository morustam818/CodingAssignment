package com.interview.codingassignment.di

import android.content.Context
import androidx.room.Room
import com.interview.codingassignment.common.Constants
import com.interview.codingassignment.data.data_source.local.ApplicationDatabase
import com.interview.codingassignment.data.data_source.remote.MatchingUserApiService
import com.interview.codingassignment.data.data_source.UserRepositoryImp
import com.interview.codingassignment.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context) : ApplicationDatabase {
        return Room.databaseBuilder(
            context,
            ApplicationDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        ).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideUserApiService(retrofit: Retrofit) : MatchingUserApiService {
        return retrofit.create(MatchingUserApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserRepository(
        matchingUserApiService: MatchingUserApiService,
        db : ApplicationDatabase
    ) : UserRepository {
        return UserRepositoryImp(matchingUserApiService,db)
    }
}