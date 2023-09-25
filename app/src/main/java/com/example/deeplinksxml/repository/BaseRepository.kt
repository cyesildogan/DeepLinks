package com.example.deeplinksxml.repository

import com.example.deeplinksxml.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import retrofit2.Response

open class BaseRepository {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Flow<Resource<T>> {
        return flow<Resource<T>> {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) emit(Resource.Success(body))
            } else {
                emit(Resource.Error(response.errorBody().toString()))
            }
        }.catch {
            emit(Resource.Error(it.message ?: it.toString()))
        }.onStart {
            emit(Resource.Loading())
        }.flowOn(Dispatchers.IO)
    }
}