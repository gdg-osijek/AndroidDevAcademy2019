package osc.ada.terezijaumiljanovic.bestpizzas.persistence

import osc.ada.terezijaumiljanovic.bestpizzas.model.Pizza

interface BestPizzasRepository {

    fun addPizza(pizza: Pizza)
    fun getPizzas(): List<Pizza>
    fun getPizzaBy(id: Int): Pizza
    fun deletePizzaBy(id: Int)
    fun clearAllPizzas()
    fun changePizzaGrade(pizza: Pizza, newGrade: Int)

}