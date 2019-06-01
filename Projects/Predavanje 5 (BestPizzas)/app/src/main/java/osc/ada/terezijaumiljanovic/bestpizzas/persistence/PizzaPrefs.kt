package osc.ada.terezijaumiljanovic.bestpizzas.persistence

import android.preference.PreferenceManager
import android.text.BoringLayout
import osc.ada.terezijaumiljanovic.bestpizzas.app.BestPizzasApplication

object PizzaPrefs{

    const val KEY_THEME_NAME = "KEY_THEME_NAME"

    private fun sharedPrefs() = PreferenceManager.getDefaultSharedPreferences(BestPizzasApplication.getAppContext())

    fun store(key: String, value: String){
        sharedPrefs().edit().putString(key,value).apply()
    }

    fun getString(key: String, defaultValue: String): String? {
        return sharedPrefs().getString(key, defaultValue)
    }

}