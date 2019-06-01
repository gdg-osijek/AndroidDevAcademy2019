package osc.ada.terezijaumiljanovic.bestpizzas.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import osc.ada.terezijaumiljanovic.bestpizzas.model.Pizza

@Database(entities = [Pizza::class], version = 1)
abstract class DaoProvider : RoomDatabase() {

    abstract fun pizzaDao(): PizzaDao

    companion object {
        private var instance: DaoProvider? = null

        fun getInstance(context: Context): DaoProvider {

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    DaoProvider::class.java,
                    "PizzaDb"
                ).allowMainThreadQueries().build()
            }
            return instance as DaoProvider
        }
    }
}
