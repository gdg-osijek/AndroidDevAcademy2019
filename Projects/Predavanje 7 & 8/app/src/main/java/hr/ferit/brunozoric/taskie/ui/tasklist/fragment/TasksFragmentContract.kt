package hr.ferit.brunozoric.taskie.ui.tasklist.fragment

import hr.ferit.brunozoric.taskie.model.BackendTask
import hr.ferit.brunozoric.taskie.model.Task

interface TasksFragmentContract {

    interface View{

        fun onTaskListRecieved(tasks: MutableList<BackendTask>)

        fun onGetTasksFailed()

    }

    interface Presenter{

        fun setView(view: TasksFragmentContract.View)

        fun onGetAllTasks()

    }

}