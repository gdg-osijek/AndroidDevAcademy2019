package hr.ferit.brunozoric.taskie.ui.tasklist.fragment

import hr.ferit.brunozoric.taskie.model.BackendTask
import hr.ferit.brunozoric.taskie.model.request.AddTaskRequest

interface AddTaskDilogContract {

    interface View {

        fun onTaskAdded(task: BackendTask)

        fun onTaskAddFailed()
        fun showDataError()

    }

    interface Presenter {

        fun setView(view: AddTaskDilogContract.View)

        fun onAddTask(task: AddTaskRequest)

    }

}