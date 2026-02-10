package com.neo.core.utils.DataStore

interface DataStore {
    suspend fun saveString(key: String, data: String)
    suspend fun saveBoolean(key: String, data: Boolean)
    suspend fun getBoolean(key: String) : Boolean
    suspend fun getString(key: String): String?
}