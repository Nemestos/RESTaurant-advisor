package com.tah.fourmetal.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.tah.fourmetal.data.models.AuthUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


class SessionManager(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "sessionPrefs")
        private val ID = intPreferencesKey("user_id")
        private val AUTH_TOKEN = stringPreferencesKey("auth_token")
    }


    suspend fun login(authUser: AuthUser) {
        context.dataStore.edit { preferences ->
            preferences[ID] = authUser.id!!
            preferences[AUTH_TOKEN] = authUser.token!!
        }
    }

    suspend fun logout() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    val currentUserFlow: Flow<AuthUser?>
        get() = context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    Log.e(exception.toString(), "Error reading auth")

                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val id = preferences[ID]
                val authToken = preferences[AUTH_TOKEN]
                Log.d("id::", id.toString())
                Log.d("authToken::", authToken.toString())
                if (id == null || authToken == null) {
                    null
                } else {
                    AuthUser(
                        token = authToken,
                        id = id
                    )
                }


            }
}

