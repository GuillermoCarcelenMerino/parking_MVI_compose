package com.neo.core.data.network

sealed class NetworkFailure {

    data class LoginError(val message : String, val errorCode : Int) : NetworkFailure()
    object Error : NetworkFailure()
    //todo añadir errores posibles
}