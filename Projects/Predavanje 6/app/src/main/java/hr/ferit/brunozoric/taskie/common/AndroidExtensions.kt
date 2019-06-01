package hr.ferit.brunozoric.taskie.common

import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import hr.ferit.brunozoric.taskie.model.BackendPriorityTask

fun FragmentActivity.showFragment(containerId: Int, fragment: Fragment, shouldAddToBackStack: Boolean = false, tag: String = ""){
    supportFragmentManager.beginTransaction().apply {
        if(shouldAddToBackStack){
            addToBackStack(tag)
        }
    }.replace(containerId, fragment).commitAllowingStateLoss()
}

fun Spinner.priorityFactory(): BackendPriorityTask {
    return  when (this.selectedItemPosition) {
        0 -> BackendPriorityTask.LOW
        1 -> BackendPriorityTask.MEDIUM
        else -> BackendPriorityTask.HIGH
    }
}
