package com.neo.core.utils

import com.neo.core.data.network.NetworkFailure
import com.neo.core.data.network.Result
import com.neo.core.domain.mappers.ResultMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

fun Builder.buildWithFactories() = this.addConverterFactory(GsonConverterFactory.create()).build()

suspend fun <T, R> requestHandler(
    coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
    request: suspend () -> Response<T>,
    mapper: ResultMapper<T, R>
): Result<R, NetworkFailure> {
    return withContext(coroutineDispatcher) {
        runCatching {
            val response = request()
            if (response.isSuccessful) {
                Result.Success(mapper.map(response.body()!!))
            } else {
                Result.Failure(
                    NetworkFailure.LoginError(response.errorBody().toString(), response.code())
                )
            }
        }
    }.getOrElse {
        it.toError()
    }
}

suspend fun <T, R> requestHandlerList(
    coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
    request: suspend () -> Response<List<T>>,
    mapper: ResultMapper<T, R>
): Result<List<R>, NetworkFailure> {
    return withContext(coroutineDispatcher) {
        runCatching {
            val response = request()
            if (response.isSuccessful) {
                Result.Success(response.body()!!.map { mapper.map(it) })
            } else {
                Result.Failure(
                    NetworkFailure.LoginError(response.errorBody().toString(), response.code())
                )
            }
        }
    }.getOrElse {
        it.toError()
    }
}


fun Throwable.toError(): Result<Nothing, NetworkFailure> {
    //todo control de tipos de error genericos
    return Result.Failure(NetworkFailure.Error)
}

fun String.containsNumber() = this.contains(Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).+$"))
fun String.containsCapLetterAndNonCap() = this.contains(Regex("^(?=.*[A-Z])(?=.*[a-z]).+$"))