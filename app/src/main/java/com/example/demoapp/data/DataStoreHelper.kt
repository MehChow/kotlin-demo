package com.example.demoapp.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "settings")

object DataStoreHelper {
    private val FIRST_LAUNCH_KEY = booleanPreferencesKey("is_first_launch")

    fun isFirstLaunch(context: Context): Flow<Boolean> =
        context.dataStore.data.map { prefs ->
            prefs[FIRST_LAUNCH_KEY] ?: true
        }

    suspend fun setFirstLaunch(context: Context, isFirst: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[FIRST_LAUNCH_KEY] = isFirst
        }
    }
}