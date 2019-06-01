package osc.ada.terezijaumiljanovic.bestpizzas.ui.fragments.changegrade

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.RadioButton
import kotlinx.android.synthetic.main.fragment_dialog_change_grade.*
import osc.ada.terezijaumiljanovic.R
import osc.ada.terezijaumiljanovic.bestpizzas.model.Pizza
import osc.ada.terezijaumiljanovic.bestpizzas.persistence.FakeRepository
import osc.ada.terezijaumiljanovic.bestpizzas.persistence.PizzaRoomRepository

class ChangeGradeFragmentDialog : DialogFragment() {

    private var repository = PizzaRoomRepository()
    private var gradeChangedListener: GradeChangedListener? = null
    lateinit var pizza: Pizza
    private var grade = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.FragmentDialogTheme)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dialog_change_grade, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initUI()
    }

    private fun initUI() {

        val childCount = gradeChangeRadioGroup.childCount
        (0 until childCount).map { gradeChangeRadioGroup.getChildAt(it) as RadioButton }
            .filter { (it.text.toString()).toInt() == pizza.grade }
            .forEach { it.isChecked = true }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    private fun initListeners() {
        saveGrade.setOnClickListener {

            repository.changePizzaGrade(pizza, grade)

            gradeChangedListener!!.onGradeChanged()

            dismiss()
        }

        gradeChangeRadioGroup.setOnCheckedChangeListener { group, id ->
            grade = when (id) {
                gradeChangeOneRadioBtn.id -> 1
                gradeChangeTwoRadioBtn.id -> 2
                gradeChangeThreeRadioBtn.id -> 3
                gradeChangeFourRadioBtn.id -> 4
                gradeChangeFiveRadioBtn.id -> 5
                else -> 1
            }
        }
    }

    fun setGradeChangedListener(gradeChangedListener: GradeChangedListener) {
        this.gradeChangedListener = gradeChangedListener
    }

    companion object {
        fun newInstance() = ChangeGradeFragmentDialog()
    }

}