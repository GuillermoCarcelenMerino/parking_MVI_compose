package com.neo.core.utils.cache

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalCacheImp @Inject constructor() : LocalCache {
    private val map = mutableMapOf<String, Any>()

    override fun saveString(key: String, data: String) {
        map.put(key, data)
    }

    override fun getString(key: String): String? {
        return map[key] as? String
    }

}