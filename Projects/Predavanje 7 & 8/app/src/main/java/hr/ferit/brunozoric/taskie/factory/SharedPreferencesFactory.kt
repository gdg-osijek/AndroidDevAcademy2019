package hr.ferit.brunozoric.taskie.factory

import android.content.Context
import android.content.SharedPreferences
import hr.ferit.brunozoric.taskie.Taskie
import hr.ferit.brunozoric.taskie.prefs.PREFERENCES_NAME

object SharedPreferencesFactory {

    fun setup() {
        preferences
    }

    val preferences: SharedPreferences by lazy {
        Taskie.instance.getSharedPreferences(
            PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
    }
}