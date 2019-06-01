package osc.ada.terezijaumiljanovic.bestpizzas.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import osc.ada.terezijaumiljanovic.R
import osc.ada.terezijaumiljanovic.bestpizzas.common.showFragment
import osc.ada.terezijaumiljanovic.bestpizzas.persistence.PizzaPrefs

abstract class BaseActivity : AppCompatActivity() {

    private val currentTheme: String? = PizzaPrefs.getString(PizzaPrefs.KEY_THEME_NAME, "Default")

    override fun onCreate(savedInstanceState: Bundle?) {
        setAppTheme()
        super.onCreate(savedInstanceState)

        setContentView(getLayoutResourceId())
        setUpUi()

    }

    private fun setAppTheme() {
        when (currentTheme) {
            DEFAULT_THEME -> setTheme(R.style.Theme_App_Default)
            THEME_ONE -> setTheme(R.style.Theme_App_ThemeOne)
            THEME_TWO -> setTheme(R.style.Theme_App_ThemeTwo)
        }
    }

    protected fun showFragment(fragment: Fragment) {
        showFragment(R.id.fragmentContainer, fragment)
    }

    abstract fun getLayoutResourceId(): Int
    abstract fun setUpUi()

    companion object {
        const val DEFAULT_THEME = "Default"
        const val THEME_ONE = "Cool theme 1"
        const val THEME_TWO = "Cool theme 2"
    }

}