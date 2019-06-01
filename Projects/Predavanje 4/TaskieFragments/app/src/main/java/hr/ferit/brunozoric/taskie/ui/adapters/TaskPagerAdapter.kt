package hr.ferit.brunozoric.taskie.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hr.ferit.brunozoric.taskie.ui.fragments.TasksFragment


class TaskPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> TasksFragment.newInstance()
            1 -> TasksFragment.newInstance()
            else -> TasksFragment.newInstance()
        }
    }

    override fun getCount() = 2
}