package osc.ada.terezijaumiljanovic.bestpizzas.ui.fragments.pizzalist

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_pizza.view.*
import osc.ada.terezijaumiljanovic.R
import osc.ada.terezijaumiljanovic.bestpizzas.app.BestPizzasApplication
import osc.ada.terezijaumiljanovic.bestpizzas.model.Pizza

class PizzaHolder(
    itemView: View,
    private val onGradeClickListener: OnGradeLayoutClickListener
) : RecyclerView.ViewHolder(itemView) {

    private lateinit var pizza: Pizza

    fun bind(pizza: Pizza) {
        this.pizza = pizza
        itemView.pizzaType.text = pizza.type
        itemView.place.text = pizza.place
        val price =
            BestPizzasApplication.getAppContext().resources.getString(R.string.pizzaPrice, pizza.price.toString())
        itemView.pizzaPrice.text = price
        itemView.pizzaGrade.text = pizza.grade.toString()

        itemView.gradeLayout.setOnClickListener { onGradeClickListener.onGradeLayoutClick(pizza) }
    }


}
