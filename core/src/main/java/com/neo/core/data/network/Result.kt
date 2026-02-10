package com.neo.core.data.network

sealed class  Result<out T, out R> {
    data class  Success<out T> (val value : T): Result<T, Nothing>()
    data class  Failure<out R> (val value : R): Result<Nothing, R>()
}

inline fun <L,R,T> Result<L,R>.launchWithResponse (
    success : (L) -> T,
    failure : (R) -> T
) = when(this){
    is Result.Success -> success(value)
    is Result.Failure -> failure(value)
}