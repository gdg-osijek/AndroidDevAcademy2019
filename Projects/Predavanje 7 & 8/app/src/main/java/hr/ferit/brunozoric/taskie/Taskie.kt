package hr.ferit.brunozoric.taskie

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import hr.ferit.brunozoric.taskie.factory.SharedPreferencesFactory

class Taskie: Application() {

    companion object {
        lateinit var instance: Taskie
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        SharedPreferencesFactory.setup()
    }
}