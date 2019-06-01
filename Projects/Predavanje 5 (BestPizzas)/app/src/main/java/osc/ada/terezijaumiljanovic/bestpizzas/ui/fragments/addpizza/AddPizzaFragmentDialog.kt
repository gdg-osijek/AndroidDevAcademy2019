package osc.ada.terezijaumiljanovic.bestpizzas.ui.fragments.addpizza

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.TextUtils.isEmpty
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import kotlinx.android.synthetic.main.fragment_dialog_add_pizza.*
import osc.ada.terezijaumiljanovic.R
import osc.ada.terezijaumiljanovic.bestpizzas.model.Pizza
import osc.ada.terezijaumiljanovic.bestpizzas.persistence.BestPizzasRepository
import osc.ada.terezijaumiljanovic.bestpizzas.persistence.FakeRepository
import osc.ada.terezijaumiljanovic.bestpizzas.persistence.PizzaRoomRepository

class AddPizzaFragmentDialog : DialogFragment() {

    private val repository: BestPizzasRepository = PizzaRoomRepository()
    private var pizzaAddedListener: PizzaAddedListener? = null
    private var grade: Int = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.FragmentDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dialog_add_pizza, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    fun setPizzaAddedListener(listener: PizzaAddedListener) {
        pizzaAddedListener = listener
    }

    private fun initListeners() {
        savePizza.setOnClickListener {
            savePizza()
            pizzaAddedListener?.onPizzaAdded()
        }
        gradeRadioGroup.setOnCheckedChangeListener { group, id ->
            grade = when (id) {
                gradeOneRadioBtn.id -> 1
                gradeTwoRadioBtn.id -> 2
                gradeThreeRadioBtn.id -> 3
                gradeFourRadioBtn.id -> 4
                gradeFiveRadioBtn.id -> 5
                else -> 1
            }
        }
    }

    private fun savePizza() {
        if (isEmpty(typeInput.text)) {
            typeInput.error = getString(R.string.empty_field_error)
        }
        if (isEmpty(placeInput.text)) {
            placeInput.error = getString(R.string.empty_field_error)
        }
        if (isEmpty(priceInput.text)) {
            priceInput.error = getString(R.string.empty_field_error)
        }
        if (!isInputEmpty()) {
            val type = typeInput.text.toString()
            val place = placeInput.text.toString()
            val price = priceInput.text.toString()

            repository.addPizza(
                Pizza(
                    type = type,
                    place = place,
                    price = price.toInt(),
                    grade = grade
                )
            )

            clearUi()
            dismiss()
        }
    }

    private fun clearUi() {
        typeInput.text!!.clear()
        placeInput.text!!.clear()
        priceInput.text!!.clear()
        gradeOneRadioBtn.isChecked = true
    }

    private fun isInputEmpty(): Boolean =
        isEmpty(typeInput.text) || isEmpty(placeInput.text) || isEmpty(priceInput.text)

    companion object {
        fun newInstance() = AddPizzaFragmentDialog()
    }

}