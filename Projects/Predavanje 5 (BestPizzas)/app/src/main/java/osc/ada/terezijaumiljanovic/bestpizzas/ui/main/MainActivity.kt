package osc.ada.terezijaumiljanovic.bestpizzas.ui.main

import android.app.Dialog
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.RadioButton
import android.widget.RadioGroup
import osc.ada.terezijaumiljanovic.R
import osc.ada.terezijaumiljanovic.bestpizzas.persistence.PizzaPrefs
import osc.ada.terezijaumiljanovic.bestpizzas.ui.base.BaseActivity
import osc.ada.terezijaumiljanovic.bestpizzas.ui.fragments.pizzalist.ClearAllListener
import osc.ada.terezijaumiljanovic.bestpizzas.ui.fragments.pizzalist.PizzasFragment

class MainActivity : BaseActivity() {

    override fun getLayoutResourceId() = R.layout.activity_main
    private var clearAllListener: ClearAllListener? = null

    override fun setUpUi() {
        val fragment = PizzasFragment.newInstance()
        clearAllListener = fragment
        showFragment(fragment)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_pizzas, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.changeAppThemeMenuItem -> showChangeThemeDialog()
            R.id.clearAllPizzasMenuItem -> showClearAllDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showClearAllDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.clear_all_pizzas_title))
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setPositiveButton(android.R.string.yes) { _, _ -> clearAllListener!!.clearAllPizzas() }
            .setNegativeButton(android.R.string.no, null).show()
    }

    private fun showChangeThemeDialog() {

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_theme_change_radio_group)

        val radioGroup = dialog.findViewById(R.id.themeRadioGroup) as RadioGroup

        val childCount = radioGroup.childCount
        val currentThemeName = getCurrentThemeName()

        (0 until childCount).map { radioGroup.getChildAt(it) as RadioButton }
            .filter { it.text.toString() == currentThemeName }
            .forEach { it.isChecked = true }

        radioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            (0 until childCount)
                .map { radioGroup.getChildAt(it) as RadioButton }
                .filter { it.id == checkedId }
                .forEach { saveTheme(it.text.toString()) }
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun saveTheme(themeName: String) {
        PizzaPrefs.store(PizzaPrefs.KEY_THEME_NAME, themeName)
        recreate()
    }

    private fun getCurrentThemeName(): String? {
        return PizzaPrefs.getString(PizzaPrefs.KEY_THEME_NAME,"Default")
    }

}