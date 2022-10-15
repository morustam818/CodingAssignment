package com.interview.codingassignment.data.data_source

import com.interview.codingassignment.common.Resource
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()
    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))
        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        } catch (e : HttpException) {
            query().map { Resource.Failed(
                it,e.localizedMessage ?: "An unexpected error occurred")
            }
        } catch (e : IOException){
            query().map { Resource.Failed(
                it,"Failed to get response, please check your internet connection")
            }
        }
    } else {
        query().map { Resource.Success(it) }
    }
    emitAll(flow)
}