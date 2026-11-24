package com.example.myapplication.composable7

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Un único archivo DataStore llamado "settings" para todas estas preferencias.
val Context.datastore by preferencesDataStore(name = "settings")

private object PreferencesKey {
    val NOTIFICATIONS = booleanPreferencesKey("notifications")
    val VIBRATION = booleanPreferencesKey("vibration")
    val SOUNDS = booleanPreferencesKey("sounds")
    val USERNAME = stringPreferencesKey("username")
    val TIPS = booleanPreferencesKey("tips") // Nuevo switch.
}

class SettingsDataStore(private val context: Context) {

    // Cada Flow emite el valor guardado y los cambios posteriores.
    // El valor tras ?: se usa cuando aún no hay nada guardado.
    val notificationsEnabled: Flow<Boolean> = context.datastore.data.map { preferences ->
        preferences[PreferencesKey.NOTIFICATIONS] ?: true
    }

    val vibrationEnabled: Flow<Boolean> = context.datastore.data.map { preferences ->
        preferences[PreferencesKey.VIBRATION] ?: true
    }

    val soundsEnabled: Flow<Boolean> = context.datastore.data.map { preferences ->
        preferences[PreferencesKey.SOUNDS] ?: true
    }

    val username: Flow<String> = context.datastore.data.map { preferences ->
        preferences[PreferencesKey.USERNAME] ?: ""
    }

    val tipsEnabled: Flow<Boolean> = context.datastore.data.map { preferences ->
        preferences[PreferencesKey.TIPS] ?: false
    }

    // edit guarda una preferencia sin borrar las demás.
    suspend fun setNotificationsEnabled(enabled: Boolean) {
        context.datastore.edit { preferences ->
            preferences[PreferencesKey.NOTIFICATIONS] = enabled
        }
    }

    suspend fun setVibrationEnabled(enabled: Boolean) {
        context.datastore.edit { preferences ->
            preferences[PreferencesKey.VIBRATION] = enabled
        }
    }

    suspend fun setSoundsEnabled(enabled: Boolean) {
        context.datastore.edit { preferences ->
            preferences[PreferencesKey.SOUNDS] = enabled
        }
    }

    suspend fun setUsername(username: String) {
        context.datastore.edit { preferences ->
            preferences[PreferencesKey.USERNAME] = username
        }
    }

    suspend fun setTipsEnabled(enabled: Boolean) {
        context.datastore.edit { preferences ->
            preferences[PreferencesKey.TIPS] = enabled
        }
    }

    // Al borrar todas las claves, cada Flow vuelve a mostrar su valor inicial.
    suspend fun resetSettings() {
        context.datastore.edit { preferences ->
            preferences.clear()
        }
    }
}
