package osc.ada.terezijaumiljanovic.bestpizzas.common

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

fun FragmentActivity.showFragment(containerId: Int, fragment: Fragment, shouldAddToBackStack: Boolean = false, tag: String = ""){
    supportFragmentManager.beginTransaction().apply {
        if(shouldAddToBackStack){
            addToBackStack(tag)
        }
    }.replace(containerId, fragment).commitAllowingStateLoss()
}