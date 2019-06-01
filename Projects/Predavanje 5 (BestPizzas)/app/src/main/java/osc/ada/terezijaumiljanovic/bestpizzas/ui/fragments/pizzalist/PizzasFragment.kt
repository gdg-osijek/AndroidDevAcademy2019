package osc.ada.terezijaumiljanovic.bestpizzas.ui.fragments.pizzalist

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_pizzas.*
import osc.ada.terezijaumiljanovic.R
import osc.ada.terezijaumiljanovic.bestpizzas.model.Pizza
import osc.ada.terezijaumiljanovic.bestpizzas.persistence.BestPizzasRepository
import osc.ada.terezijaumiljanovic.bestpizzas.persistence.FakeRepository
import osc.ada.terezijaumiljanovic.bestpizzas.persistence.PizzaRoomRepository
import osc.ada.terezijaumiljanovic.bestpizzas.ui.fragments.addpizza.AddPizzaFragmentDialog
import osc.ada.terezijaumiljanovic.bestpizzas.ui.fragments.addpizza.PizzaAddedListener
import osc.ada.terezijaumiljanovic.bestpizzas.ui.base.BaseFragment
import osc.ada.terezijaumiljanovic.bestpizzas.ui.fragments.changegrade.ChangeGradeFragmentDialog
import osc.ada.terezijaumiljanovic.bestpizzas.ui.fragments.changegrade.GradeChangedListener

class PizzasFragment : BaseFragment(), PizzaAddedListener, ClearAllListener, OnGradeLayoutClickListener,
    GradeChangedListener {


    private val repository: BestPizzasRepository = PizzaRoomRepository()
    private val adapter by lazy { PizzaAdapter(mutableListOf(), this) }

    override fun getLayoutResourceId() = R.layout.fragment_pizzas

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUi()
        initListeners()
        showPizzas()
    }

    private fun showPizzas() {
        adapter.updatePizzas(repository.getPizzas())

    }

    private fun initListeners() {
        addPizza.setOnClickListener { addPizza() }
    }

    private fun setUpUi() {
        pizzasRecyclerView.layoutManager = LinearLayoutManager(context)
        pizzasRecyclerView.adapter = adapter
    }

    companion object {
        fun newInstance() = PizzasFragment()
    }

    private fun addPizza() {
        val dialog = AddPizzaFragmentDialog.newInstance()
        dialog.setPizzaAddedListener(this)
        dialog.show(childFragmentManager, dialog.tag)
    }

    override fun onPizzaAdded() {
        showPizzas()
    }

    override fun clearAllPizzas() {

        repository.clearAllPizzas()
        showPizzas()
    }

    override fun onGradeLayoutClick(pizza: Pizza) {
        val dialog = ChangeGradeFragmentDialog.newInstance()
        dialog.pizza = pizza
        dialog.setGradeChangedListener(this)
        dialog.show(childFragmentManager, dialog.tag)
    }

    override fun onGradeChanged() {
        showPizzas()
    }


}