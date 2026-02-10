package com.neo.core.utils.cache

interface LocalCache {

    fun saveString(key: String, data: String)
    fun getString(key: String): String?
}