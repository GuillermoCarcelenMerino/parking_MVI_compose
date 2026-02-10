package com.neo.core.utils.DataStore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "preferences")


@Singleton
class DataStoreImp @Inject constructor(
    @ApplicationContext private val context: Context
) : DataStore {

    private val dataStore = context.dataStore


    suspend fun clear() = dataStore.edit { it.clear() }
    override suspend fun saveString(key: String, data: String) {
        val key = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[key] = data
        }
    }

    override suspend fun saveBoolean(key: String, data: Boolean) {
        val key = booleanPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[key] = data
        }
    }

    override suspend fun getBoolean(key: String): Boolean {
        val key = booleanPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[key] ?: false

    }

    override suspend fun getString(key: String): String? {
        val key = stringPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[key]
    }
}