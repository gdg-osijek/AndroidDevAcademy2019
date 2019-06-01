package osc.ada.terezijaumiljanovic.bestpizzas.db

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import osc.ada.terezijaumiljanovic.bestpizzas.model.Pizza


@Dao
interface PizzaDao{

    @Query("SELECT * FROM Pizza")
    fun getAllPizzas(): List<Pizza>

    @Insert(onConflict = IGNORE)
    fun insertPizza(pizza:Pizza)

    @Delete()
    fun deletePizza(pizza: Pizza)

    @Query("UPDATE Pizza SET grade= :pizzaGrade WHERE pizzaDbId = :pizzaId")
    fun updatePizzaGrade(pizzaId: Long, pizzaGrade: Int)

    @Query("DELETE from Pizza")
    fun deleteAllPizzas()

}