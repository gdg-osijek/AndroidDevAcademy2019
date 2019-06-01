package osc.ada.terezijaumiljanovic.bestpizzas.app

import android.app.Application
import android.content.Context

class BestPizzasApplication : Application() {

    companion object {
        private lateinit var instance: BestPizzasApplication

        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }

}