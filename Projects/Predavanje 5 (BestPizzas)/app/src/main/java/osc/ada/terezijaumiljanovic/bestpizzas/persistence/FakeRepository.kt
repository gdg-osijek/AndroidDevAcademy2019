package osc.ada.terezijaumiljanovic.bestpizzas.persistence

import osc.ada.terezijaumiljanovic.bestpizzas.model.Pizza

object FakeRepository : BestPizzasRepository {

    private val pizzas = mutableListOf<Pizza>()
    private var currentId = 0

    override fun addPizza(pizza: Pizza) {
        val pizzaTemp =
            Pizza(id = currentId, type = pizza.type, place = pizza.place, price = pizza.price, grade = pizza.grade)
        pizzaTemp.id = currentId
        pizzas.add(pizzaTemp)
        currentId++
    }

    override fun deletePizzaBy(id: Int) {
        pizzas.removeAll { (it.id) == id }
    }

    override fun getPizzaBy(id: Int): Pizza {
        return pizzas.first { it.id == id }
    }

    override fun getPizzas(): List<Pizza> = pizzas

    override fun clearAllPizzas() {
        pizzas.clear()
    }

     override fun changePizzaGrade(pizza: Pizza, newGrade: Int){
        pizzas.first{it.id == pizza.id}.grade = newGrade
    }

    fun count() = pizzas.size

}