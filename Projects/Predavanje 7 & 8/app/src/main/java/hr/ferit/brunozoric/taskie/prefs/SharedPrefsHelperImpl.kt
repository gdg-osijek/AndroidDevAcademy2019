package hr.ferit.brunozoric.taskie.prefs

import android.content.SharedPreferences
import hr.ferit.brunozoric.taskie.factory.SharedPreferencesFactory

const val KEY_USER_TOKEN = "user_token"
const val PREFERENCES_NAME = "taskie_prefs"

class SharedPrefsHelperImpl(private val preferences: SharedPreferences) : SharedPrefsHelper {

    // API authentication token
    override fun getUserToken(): String = preferences.getString(KEY_USER_TOKEN, "") ?: ""

    override fun storeUserToken(token: String) = preferences.edit().putString(KEY_USER_TOKEN, token).apply()

    override fun clearUserToken() = preferences.edit().remove(KEY_USER_TOKEN).apply()
}

class TestSharedPreferences() : SharedPrefsHelper {
    override fun getUserToken(): String = ""

    override fun storeUserToken(token: String) {
    }

    override fun clearUserToken() {
    }
}

fun provideSharedPrefs(): SharedPrefsHelper {
    return SharedPrefsHelperImpl(SharedPreferencesFactory.preferences)
}