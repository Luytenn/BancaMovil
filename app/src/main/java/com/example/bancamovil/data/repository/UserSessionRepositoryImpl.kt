package com.example.bancamovil.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.bancamovil.domain.repository.UserSessionRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "BANCA_MOVIL")

class UserSessionRepositoryImpl @Inject constructor(
    context: Context): UserSessionRepository {

    private val dataStore = context.dataStore

    override suspend fun saveUserId(key: String, value: Int){
        val preferencesKey = intPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    override suspend fun getUserId(key: String): Int? {
        val preferenceKey = intPreferencesKey(key)
        val preferences = dataStore.data.first()
        return preferences[preferenceKey]
    }

    override suspend fun clearAll() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }


}