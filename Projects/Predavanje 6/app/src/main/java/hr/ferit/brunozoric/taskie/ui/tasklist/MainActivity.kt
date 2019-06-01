package hr.ferit.brunozoric.taskie.ui.tasklist

import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.ui.base.BaseActivity
import hr.ferit.brunozoric.taskie.ui.tasklist.fragment.TasksFragment

class MainActivity : BaseActivity() {

    override fun getLayoutResourceId() = R.layout.activity_main

    override fun setUpUi() {
        showFragment(TasksFragment.newInstance())
    }

}