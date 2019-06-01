package osc.ada.terezijaumiljanovic.bestpizzas.persistence

import osc.ada.terezijaumiljanovic.bestpizzas.app.BestPizzasApplication
import osc.ada.terezijaumiljanovic.bestpizzas.db.DaoProvider
import osc.ada.terezijaumiljanovic.bestpizzas.model.Pizza

class PizzaRoomRepository : BestPizzasRepository {

    private var db = DaoProvider.getInstance(BestPizzasApplication.getAppContext())

    private var pizzaDao = db.pizzaDao()

    override fun addPizza(pizza: Pizza) {
        pizzaDao.insertPizza(pizza)
    }

    override fun getPizzas(): List<Pizza> =
        pizzaDao.getAllPizzas()


    override fun getPizzaBy(id: Int): Pizza {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deletePizzaBy(id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearAllPizzas() {
        pizzaDao.deleteAllPizzas()
    }

    override fun changePizzaGrade(pizza: Pizza, newGrade: Int) {
        pizzaDao.updatePizzaGrade(pizza.pizzaDbId!!, newGrade)
    }

}