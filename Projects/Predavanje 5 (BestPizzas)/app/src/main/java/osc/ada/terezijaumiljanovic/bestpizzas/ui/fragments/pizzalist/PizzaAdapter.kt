package osc.ada.terezijaumiljanovic.bestpizzas.ui.fragments.pizzalist

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import osc.ada.terezijaumiljanovic.R
import osc.ada.terezijaumiljanovic.bestpizzas.common.inflate
import osc.ada.terezijaumiljanovic.bestpizzas.model.Pizza

class PizzaAdapter(
    private val pizzas: MutableList<Pizza>, private val onGradeClickListener: OnGradeLayoutClickListener
) : RecyclerView.Adapter<PizzaHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaHolder {
        return PizzaHolder(parent.inflate(R.layout.item_pizza),  onGradeClickListener)
    }

    override fun getItemCount() = pizzas.size

    override fun onBindViewHolder(holder: PizzaHolder, position: Int) {
        holder.bind(pizzas[position])
    }

    fun updatePizzas(pizzas: List<Pizza>) {
        this.pizzas.clear()
        this.pizzas.addAll(pizzas)
        notifyDataSetChanged()
    }

}