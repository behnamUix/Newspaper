package com.behnamuix.newspaper.database.dataStore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore by preferencesDataStore(name = "setting")

object SettingsKeys {
    val DARK_MODE = booleanPreferencesKey("dark_mode")
    val LAST_OPENED_NEWS = stringPreferencesKey("last_opened_news")
}

object DataStoreManager : DataStore {

    override fun saveDarkMode(context: Context, isDark: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            context.dataStore.edit { prefs ->
                prefs[SettingsKeys.DARK_MODE] = isDark
            }
        }
    }

    override fun getDarkMode(context: Context): Flow<Boolean> {
        return context.dataStore.data
            .map { prefs ->
                prefs[SettingsKeys.DARK_MODE] ?: false
            }
    }

    override fun saveLastOpenedNews(context: Context, title: String) {
        CoroutineScope(Dispatchers.IO).launch {
            context.dataStore.edit { prefs ->
                prefs[SettingsKeys.LAST_OPENED_NEWS] = title

            }
        }
    }


    override fun getLastOpenedNews(context: Context): Flow<String> {
        return context.dataStore.data.map { prefs ->
            prefs[SettingsKeys.LAST_OPENED_NEWS] ?: "none"
        }

    }

}
